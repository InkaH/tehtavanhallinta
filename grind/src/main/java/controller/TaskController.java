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

import dao.TaskDAO;
import bean.Task;
import bean.Comment;

@Controller
@RequestMapping(value = "/")
public class TaskController {
	
	private Task editItem = new Task();
	private List<Task> tasks;
	private List<Comment> comments;
	private Comment newComment = new Comment();
	private int editingActive = 0;
	private int activeTask = 0;
	private int theme = 3;

	@Inject
	private TaskDAO dao;

	public TaskDAO getDao() {
		return dao;
	}

	public void setDao(TaskDAO dao) {
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
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getView(Map<String, Object> model, Principal principal) {
		String username = principal.getName();
		tasks = dao.getAll(username);
		if (activeTask > 0) {
			comments = dao.getComments(activeTask);
		}
		model.put("tasks", tasks);
		model.put("newTask", this.editItem);
		model.put("comments", comments);
		model.put("newComment", newComment);
		model.put("edit", Integer.toString(editingActive));
		model.put("activeTask", activeTask);
		model.put("theme", this.theme);
		model.put("user", principal.getName());
		return "index";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addTask(@ModelAttribute("newTask") Task task, Principal principal) {
		if (!task.getTask().isEmpty()) {
			if (task.getDate() == null) {
				task.setDate(LocalDate.of(1970, 1, 1));
			}
			if (task.getTime() == null) {
				task.setTime(LocalTime.of(23, 59));
			}
			String username = principal.getName();
			dao.addTask(task, username);
		}
		editingActive = 0;
		editItem.resetTask();
		activeTask = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public String deleteTask(@RequestParam String delTask) {
		int de = Integer.parseInt(delTask);
		if (de > 0) {
			dao.deleteTask(de);
		}
		editingActive = 0;
		activeTask = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editTask(@RequestParam String editTask) {
		int ed = Integer.parseInt(editTask);
		if (ed > 0) {
			for (Task t : tasks) {
				if (t.getId() == ed) {
					this.editItem = t;
					editingActive = 1;
					if (editItem.getDate().compareTo(LocalDate.of(1970, 1, 1)) == 0) {
						editItem.setDate(null);
						editItem.setTime(null);
					}
					return "redirect:/index";
				}
			}
		}
		activeTask = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public String shareTask(@RequestParam String shareTask, @RequestParam String groupID) {
		int sh = Integer.parseInt(shareTask);
		if (sh > 0) {
			dao.shareTask(sh, groupID.toUpperCase());
		}
		activeTask = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	public String cancel() {
		editingActive = 0;
		editItem.resetTask();
		activeTask = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "commentAdd", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("newComment") Comment c) {
		dao.addComment(c);
		activeTask = c.getTask();
		return "redirect:/index";
	}
	
	@RequestMapping(value = "delComment", method = RequestMethod.POST)
	public String delComment(@RequestParam String delComment) {
		int dc = Integer.parseInt(delComment);
		if (dc > 0) {
			dao.deleteComment(dc);
		}
		editingActive = 0;
		return "redirect:/index";
	}
	
	@RequestMapping(value = "activation", method = RequestMethod.POST)
	public String activateTask(@RequestParam String activeTask) {
		int at = Integer.parseInt(activeTask);
		if (this.activeTask == at) {
			this.activeTask = 0;
		} else {
			this.activeTask = at;
		}
		return "redirect:/index";
	}
	
	@RequestMapping(value = "theme", method = RequestMethod.POST)
	public String changeTheme(@RequestParam String themeID) {
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