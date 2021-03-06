package controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;

import dao.TaskDAO;
import bean.User;
import bean.Task;
import bean.Comment;
import tools.TimeWarp;

@Controller
@RequestMapping(value = "/")
public class TaskController {

	final static Logger logger = LoggerFactory.getLogger(TaskController.class);
	private String username;
	private Task editItem = new Task();
	private List<Task> tasks;
	private List<Comment> comments;
	private List<String> grouplist;
	private String groupListDefault = "all";
	private String taskFilterDefault = "all";
	private Comment newComment = new Comment();
	private int editingActive = 0;
	private int activeTask = 0;
	private int activeTab = 0;
	private int theme = 1;

	public static boolean startup = true;

	@Inject
	private TaskDAO dao;

	public TaskDAO getDao() {
		return dao;
	}

	public void setDao(TaskDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {

		if (error != null) {
			model.addAttribute("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			logger.info("logout ifin jälkeen:" + logout);
			activeTask = 0;
			editingActive = 0;
			startup = true;
			activeTab = 0;
			model.addAttribute("logout", "Olet kirjautunut ulos");
		}

		//registration form is a Spring form so we have to place
		//the User object values to it
		logger.info("Suoritetaan controllerin login-metodia");
		User user = new User("", "");
		model.addAttribute("user", user);
		
//		if (startup) { model.addAttribute("logout", false); } else {
//		model.addAttribute("logout", true); }
		 
		return "login";
	}

	/**
	 * Method for customising the error message for user & commenting exception
	 * for admin
	 * 
	 * @param request
	 * @param key
	 * @return customised error message
	 */
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Virheellinen käyttäjätunnus ja/tai salasana" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		} else if (exception instanceof LockedException) {
			error = "Käyttäjätunnus lukittu liian monen kirjautumisyrityksen vuoksi" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			error = "Yhteysvirhe - yritä hetken kuluttua uudelleen" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		} else {
			error = "Yhteysvirhe - yritä hetken kuluttua uudelleen" + "<!--VIRHEILMOITUS: " + exception.toString() + "-->";
		}

		return error;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getView(Map<String, Object> model, Principal principal) {
		if (startup) {
			this.username = null;
			this.editItem = new Task();
			this.tasks = null;
			this.comments = null;
			this.grouplist = null;
			this.groupListDefault = "all";
			this.taskFilterDefault = "all";
			this.newComment = new Comment();
			this.editingActive = 0;
			this.activeTask = 0;
			this.activeTab = 0;
			this.username = principal.getName();
			this.theme = 1;
			this.theme = dao.getTheme(principal.getName());
			startup = false;
		}
		if (activeTab == 0) {
			if (!taskFilterDefault.equalsIgnoreCase("all")) {
				if (taskFilterDefault.equalsIgnoreCase("currentWeek")) {
					tasks = dao.getTasksOfTimePeriod(username,
							TimeWarp.startOfCurrentWeek(),
							TimeWarp.endOfCurrentWeek());
				} else if (taskFilterDefault.equalsIgnoreCase("nextWeek")) {
					tasks = dao.getTasksOfTimePeriod(username,
							TimeWarp.startOfNextWeek(),
							TimeWarp.endOfNextWeek());
				}
			} else {
				tasks = dao.getAllPrivate(username);
			}
		} else if (activeTab == 1) {
			tasks = dao.getAllDone(username);
		} else if (activeTab == 2) {
			grouplist = dao.getGroupList();
			if (!groupListDefault.equalsIgnoreCase("all")) {
				tasks = dao.getAllSharedByGroup(groupListDefault);
			} else {
				tasks = dao.getAllShared();
			}
		} else if (activeTab == 9) {
			tasks = dao.getAllTasks();
		}
		for (Task t : tasks) {
			t.setNumComments(dao.getNumComments(t.getId()));
		}
		if (activeTask > 0) {
			comments = dao.getComments(activeTask);
		}
		model.put("tasks", tasks);
		model.put("newTask", this.editItem);
		model.put("comments", comments);
		model.put("grouplist", grouplist);
		model.put("groupListDefault", groupListDefault);
		model.put("taskFilterDefault", taskFilterDefault);
		model.put("newComment", newComment);
		model.put("edit", Integer.toString(editingActive));
		model.put("activeTask", activeTask);
		model.put("activeTab", activeTab);
		model.put("theme", this.theme);
		model.put("user", principal.getName());
		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addTask(@ModelAttribute("newTask") Task task,
			Principal principal) {
		if (!task.getTask().isEmpty()) {
			if (task.getDate() == null) {
				task.setDate(LocalDate.of(1970, 1, 1));
			}
			if (task.getTime() == null) {
				task.setTime(LocalTime.of(23, 59));
			}
			String username = principal.getName();
			if (task.getShared() && task.getGroup().equals("")) {
				task.setShared(false);
			}
			task.setGroup(task.getGroup());
			dao.addTask(task, username);
		}
		editingActive = 0;
		editItem.resetTask();
		activeTask = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public String deleteTask(@RequestParam String delTask,
			@RequestParam String delType) {
		int d = Integer.parseInt(delTask);
		if (d > 0) {
			if (delType.equalsIgnoreCase("destroy")) {
				dao.deletePrivateTask(d);
			} else {
				dao.deleteTask(d);
			}
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
	public String shareTask(@RequestParam String shareTask,
			@RequestParam String groupID, @RequestParam String shareStatus) {
		int sh = Integer.parseInt(shareTask);
		if (sh > 0 && !groupID.equals("")) {
			dao.shareTask(sh, groupID.toUpperCase(),
					(shareStatus.equalsIgnoreCase("true") ? true : false));
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

	@RequestMapping(value = "addComment", method = RequestMethod.POST)
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
		int tID = Integer.parseInt(themeID);
		this.theme = tID;
		dao.saveTheme(username, tID);
		activeTask = 0;
		editingActive = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "tabChange", method = RequestMethod.POST)
	public String changeTab(@RequestParam String tabID) {
		int tID = Integer.parseInt(tabID);
		activeTab = tID;
		activeTask = 0;
		editingActive = 0;
		editItem.resetTask();
		groupListDefault = "all";
		taskFilterDefault = "all";
		return "redirect:/index";
	}

	@RequestMapping(value = "getGroupTasks", method = RequestMethod.POST)
	public String getGroupTasks(@RequestParam String groupSelection) {
		groupListDefault = groupSelection;
		activeTask = 0;
		editingActive = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "getFilteredTasks", method = RequestMethod.POST)
	public String getFilteredTasks(@RequestParam String taskFilter) {
		taskFilterDefault = taskFilter;
		activeTask = 0;
		editingActive = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "setDone", method = RequestMethod.POST)
	public String setDone(@RequestParam String doneID,
			@RequestParam String doneValue) {
		int dID = Integer.parseInt(doneID);
		int dVA = Integer.parseInt(doneValue);
		if (dID > 0) {
			dao.setDone(dID, dVA);
		}
		activeTask = 0;
		editingActive = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "setLink", method = RequestMethod.POST)
	public String setLink(@RequestParam String linkedID,
			@RequestParam String linkedUser) {
		int liID = Integer.parseInt(linkedID);
		if (liID > 0) {
			dao.setLink(liID, linkedUser);
		}
		activeTask = 0;
		editingActive = 0;
		return "redirect:/index";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String saveUser(Model model, @Valid User user,
			BindingResult bindingResult) {
		// if User class validation rules did not pass
		// return to login and must set the hashed pw back to empty
		if (bindingResult.hasErrors()) {
			user.setEmptyPassword("");
			return "login";
		}
		// check there's no such username already in teh database
		boolean duplicateUsername = getDao().searchUser(user.getUsername());
		if (!duplicateUsername) {
			user.setRole("ROLE_USER");
			getDao().saveUser(user);
			// if registration is successful, redirect to login page, and
			// registration form
			// values have to be set to empty again
			model.addAttribute("success", "Rekisteröinti onnistui.");
			user.setUsername("");
			user.setEmptyPassword("");
			model.addAttribute("user", user);
			return "login";
		} else {
			// if username already exists, return to login page with
			// error message and set regist. form values to empty
			model.addAttribute("userExistsError",
					"Antamallasi käyttäjänimellä on jo rekisteröidytty palveluun.");
			user.setUsername("");
			user.setEmptyPassword("");
			model.addAttribute("user", user);
			return "login";
		}
	}

	// we're not needing this page right now but it's working as an example
	// in case we need it later + printing of user details is also an example
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