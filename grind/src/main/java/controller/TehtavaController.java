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
		model.put("uusiTehtava", editItem);
		model.put("muokkaus", editingActive + "");
		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String lisaaTehtava(Map<String, Object> model, @ModelAttribute("uusiTehtava") Tehtava task) {
		if (!task.getKuvaus().isEmpty()) {
			dao.lisaaTehtava(task);
		}
		editingActive = 0;
		editItem = new Tehtava();
		return "redirect:/";
	}

	@RequestMapping(value = "act", method = RequestMethod.POST)
	public String operoiTehtava(
			Map<String, Object> model, 
			@RequestParam String delItem,
			@RequestParam String editing) {
		int edit = Integer.parseInt(editing);
		int deli = Integer.parseInt(delItem);
		if (edit > 0){
			for (Tehtava t : tehtavat){
				if (t.getId() == deli){
					editItem = t;
					editingActive = 1;
					dao.poistaTehtava(deli);
					return "redirect:/";
				}
			}
		} else {
			dao.poistaTehtava(Integer.parseInt(delItem));
			editingActive = 0;
			editItem = new Tehtava();
		}
		return "redirect:/";
	}
}