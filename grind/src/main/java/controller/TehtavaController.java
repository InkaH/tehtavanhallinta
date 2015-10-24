package controller;

import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.TehtavaDao;
import bean.TehtavaImpl;

@Controller
@RequestMapping(value = "/")
public class TehtavaController {

	@Inject
	private TehtavaDao dao;

	public TehtavaDao getDao() {
		return dao;
	}

	public void setDao(TehtavaDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getView(Map<String, Object> model) {
		model.put("tehtavat", dao.haeKaikki());
		model.put("uusiTehtava", new TehtavaImpl());
		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String lisaaTehtava(@ModelAttribute("uusiTehtava") TehtavaImpl task) {
		if (!task.getKuvaus().isEmpty()) {
			dao.lisaaTehtava(task);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public String poistaTehtava(@RequestParam String delItem) {
		if (!delItem.isEmpty()) {
			dao.poistaTehtava(Integer.parseInt(delItem));
		}
		return "redirect:/";
	}
}