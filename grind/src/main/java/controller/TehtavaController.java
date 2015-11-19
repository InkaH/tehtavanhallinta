package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
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

	@Inject
	private TehtavaDao dao; // makes all database management methods available in controller class

	public TehtavaDao getDao() {
		return dao;
	}

	public void setDao(TehtavaDao dao) {
		this.dao = dao;
	}
	
	// executed every time before entering index.jsp (value= "/")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getView(Map<String, Object> model) {
		tehtavat = dao.haeKaikki();
		model.put("tehtavat", tehtavat); // send all tasks to UI in a variable called 'tehtavat'
		model.put("uusiTehtava", this.editItem); // send the form pre-fill object to UI in a variable called 'uusiTehtava'
		model.put("edit", Integer.toString(editingActive)); // send the state of editing mode to UI in a variable called 'edit'
		return "index";
	}
	
	// the task creation/editing form calls the method on submit <form action="add"...>  -->  @RequestMapping(value="add"...)
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String lisaaTehtava(@ModelAttribute("uusiTehtava") Tehtava task) { // get the Tehtava object from the form
		if (!task.getKuvaus().isEmpty()) {
			if (task.getAjankohtaPvm() == null) {
				task.setAjankohtaPvm(LocalDate.of(1970, 1, 1));
			}
			if (task.getAjankohtaKlo() == null) {
				task.setAjankohtaKlo(LocalTime.of(0, 0));
			}
			dao.lisaaTehtava(task); // if the header of task is not empty, insert the new task into database
		}
		editingActive = 0; // set editing mode off
		editItem.nollaaTehtava(); // clear the content of the form pre-fill object
		return "redirect:/"; // move to getView method (value = "/")
	}

	// the task deletion form calls the method on submit <form action="del"...>  -->  @RequestMapping(value="del"...)
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public String poistaTehtava(@RequestParam String delTask) { // get attribute delTask (task id) from the form
		int de = Integer.parseInt(delTask);
		if (de > 0) {
			dao.poistaTehtava(de); // if delTask > 0 (Tehtava object exists), remove the task from database
		}
		editingActive = 0;
		return "redirect:/";
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
					return "redirect:/"; // quit searching and move to getView method
				}
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public String jaaTehtava(@RequestParam String shareTask, @RequestParam String groupID) {
		int sh = Integer.parseInt(shareTask);
		if (sh > 0) {
			dao.jaaTehtava(sh, groupID);
		}
		return "redirect:/";
	}
}