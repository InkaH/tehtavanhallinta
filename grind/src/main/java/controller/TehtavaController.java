package controller;

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

	private Tehtava editItem = new Tehtava();
	private List<Tehtava> tehtavat;
	private int editingActive = 0;

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
		tehtavat = dao.haeKaikki();
		model.put("tehtavat", tehtavat);
		model.put("uusiTehtava", this.editItem);
		model.put("edit", Integer.toString(editingActive));
		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String lisaaTehtava(@ModelAttribute("uusiTehtava") Tehtava task) {
		if (!task.getKuvaus().isEmpty()) {
			dao.lisaaTehtava(task);
		}
		editingActive = 0;
		editItem.nollaaTehtava();
		return "redirect:/";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public String poistaTehtava(@RequestParam String delTask) {
		int de = Integer.parseInt(delTask);
		if (de > 0) {
			System.out.println(tehtavat);
			System.out.println(delTask);
			dao.poistaTehtava(de);
		}
		editingActive = 0;
		return "redirect:/";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String muokkaaTehtava(@RequestParam String editTask) {
		int ed = Integer.parseInt(editTask);
		if (ed > 0) {
			System.out.println(tehtavat);
			System.out.println(editTask);
			for (Tehtava t : tehtavat) {
				if (t.getId() == ed) {
					this.editItem = t;
					editingActive = 1;
					return "redirect:/";
				}
			}
		}
		return "redirect:/";
	}
}