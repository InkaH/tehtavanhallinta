package controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.TehtavaDao;
import bean.Tehtava;

@Controller
@RequestMapping(value = "/")
public class TehtavaController {
	
	private Tehtava editItem = new Tehtava(); // controller sustain one Tehtava object in order to pre-fill the main form
	private List<Tehtava> tehtavat; // list of all tasks of the user
	private int editingActive = 0; // editing mode (0 = false, 1 = true)
	private int activeTask = 0;
	private int theme = 3;

	@Inject
	private TehtavaDao dao; // makes all database management methods available in controller class

	public TehtavaDao getDao() {
		return dao;
	}

	public void setDao(TehtavaDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = {"/", "/login" }, method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		if (error != null) {
			model.addAttribute("error", "Virheellinen käyttäjänimi tai salasana.");
		}
		if (logout != null) {
			model.addAttribute("msg", "Olet kirjautunut ulos.");
		}
		return "login";
	}
	
	// executed every time when entering index.jsp (value= "/index")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getView(Map<String, Object> model, Principal principal) {
		String username = principal.getName(); //get username from login user
		tehtavat = dao.haeKaikki(username);
		model.put("tehtavat", tehtavat); // send all tasks to UI in a variable called 'tehtavat'
		model.put("uusiTehtava", this.editItem); // send the form pre-fill object to UI in a variable called 'uusiTehtava'
		model.put("edit", Integer.toString(editingActive)); // send the state of editing mode to UI in a variable called 'edit'
		model.put("activeTask", activeTask);
		model.put("theme", this.theme);
		return "index";
	}
	
	// the task creation/editing form calls the method on submit <form action="add"...>  -->  @RequestMapping(value="add"...)
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String lisaaTehtava(@ModelAttribute("uusiTehtava") Tehtava task, Principal principal) { // get the Tehtava object from the form
		if (!task.getKuvaus().isEmpty()) {
			if (task.getAjankohtaPvm() == null) {
				task.setAjankohtaPvm(LocalDate.of(1970, 1, 1));
			}
			if (task.getAjankohtaKlo() == null) {
				task.setAjankohtaKlo(LocalTime.of(23, 59));
			}
			String st = task.getTiedot();
			if (!st.equals("")) {
				task.setTiedot(st + "\n");
			}
			String username = principal.getName(); //get username from login user
			dao.lisaaTehtava(task, username); // if the header of task is not empty, insert the new task into database
		}
		editingActive = 0; // set editing mode off
		editItem.nollaaTehtava(); // clear the content of the form pre-fill object
		activeTask = 0;
		return "redirect:/index"; // move to getView method (value = "/")
	}

	// the task deletion form calls the method on submit <form action="del"...>  -->  @RequestMapping(value="del"...)
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public String poistaTehtava(@RequestParam String delTask) { // get attribute delTask (task id) from the form
		int de = Integer.parseInt(delTask);
		if (de > 0) {
			dao.poistaTehtava(de); // if delTask > 0 (Tehtava object exists), remove the task from database
		}
		editingActive = 0;
		activeTask = 0;
		return "redirect:/index";
	}

	// the task editing form calls the method on submit
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String muokkaaTehtava(@RequestParam String editTask) {
		int ed = Integer.parseInt(editTask);
		if (ed > 0) {
			for (Tehtava t : tehtavat) { // search the right task in a loop
				if (t.getId() == ed) {
					this.editItem = t; // copy the editable task reference to the form pre-fill object
					editingActive = 1; // set editing mode on
					if (editItem.getAjankohtaPvm().compareTo(LocalDate.of(1970, 1, 1)) == 0) {
						editItem.setAjankohtaPvm(null);
						editItem.setAjankohtaKlo(null);
					}
					return "redirect:/index"; // quit searching and move to getView method
				}
			}
		}
		activeTask = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public String jaaTehtava(@RequestParam String shareTask, @RequestParam String groupID) {
		int sh = Integer.parseInt(shareTask);
		if (sh > 0) {
			dao.jaaTehtava(sh, groupID.toUpperCase());
		}
		activeTask = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	public String peruuta() {
		editingActive = 0;
		editItem.nollaaTehtava();
		activeTask = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "comment", method = RequestMethod.POST)
	public String lisaaKommentti(@RequestParam String commentedText, @RequestParam String commentedTask) {
		int ct = Integer.parseInt(commentedTask);
		dao.lisaaKommentti(ct, commentedText);
		activeTask = ct;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "theme", method = RequestMethod.POST)
	public String vaihdaTeema(@RequestParam String themeID) {
		this.theme = Integer.parseInt(themeID);
		activeTask = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		}
		return "403";
	}
}