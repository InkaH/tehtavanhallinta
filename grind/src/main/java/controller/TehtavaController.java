package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.inject.Inject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.TehtavaDaoImpl;
import bean.TehtavaImpl;

@Controller
@RequestMapping(value = "/")
public class TehtavaController {

	@Inject
	private TehtavaDaoImpl dao;

	public TehtavaDaoImpl getDao() {
		return dao;
	}

	public void setDao(TehtavaDaoImpl dao) {
		this.dao = dao;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getView(Map<String, Object> model) {
		model.put("tehtavat", dao.haeKaikki());
		model.put("uusiTehtava", new TehtavaImpl());
		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String lisaaTehtava(@ModelAttribute("uusiTehtava") TehtavaImpl task) {
		dao.lisaaTehtava(task);
		return "redirect:/";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public String delete(@RequestParam String delItem) {
		if (!delItem.isEmpty()) {
			dao.poistaTehtava(Integer.parseInt(delItem));
		}
		return "redirect:/";
	}
}