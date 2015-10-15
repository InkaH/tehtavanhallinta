package controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bean.Tehtava;
import bean.TehtavaImpl;
import dao.TehtavaDaoImpl;


@Controller
@RequestMapping (value="/")
public class TehtavaServlet {

	@Inject
	private TehtavaDaoImpl dao;
	
	public TehtavaDaoImpl getDao() {
		return dao;
	}

	public void setDao(TehtavaDaoImpl dao) {
		this.dao = dao;
	}
	
	//HENKILöIDEN LISTAUS
		@RequestMapping(value="/",method=RequestMethod.GET)
		public String getView(Map<String, Object> model) {
			model.put("tehtavat", dao.haeKaikki());	
			return "index";
	
	//FORMIN TEKEMINEN
	/**@RequestMapping(value="uusi", method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		Tehtava tyhjaTehtava = new TehtavaImpl();
		tyhjaTehtava.setKuvaus("tässä kuvaus");
		model.addAttribute("tehtava", tyhjaTehtava);
		return "henk/createForm";
	}**/
	
	//FORMIN TIETOJEN VASTAANOTTO
	/**
	@RequestMapping(value="uusi", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="henkilo") HenkiloImpl henkilo) {
		dao.talleta(henkilo);
		return "redirect:/henkilot/" + henkilo.getId();
	}**/
	
	//HENKILÃ–N TIETOJEN NÃ„YTTÃ„MINEN
	/**
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Henkilo henkilo = dao.etsi(id);
		model.addAttribute("henkilo", henkilo);
		return "henk/view";
	}**/
	
	
	}	
}