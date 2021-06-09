package fr.eni.GestionPotager.ihm;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.GestionPotager.bll.BllException;
import fr.eni.GestionPotager.bll.PlanteManager;
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.TypePlante;

@Controller
public class PlanteController {
	
	@Autowired
	PlanteManager manager;
	
	@PostConstruct
	void init() throws BllException {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		Plante plante2 = new Plante("tomate", TypePlante.FRUIT, "boeuf", (float) 0.75);
		manager.createPlante(plante1);
		manager.createPlante(plante2);
	}
	
	@PostMapping("/plante/add")
	public String addPlante(@Valid Plante plante, BindingResult result, Model model) throws BllException{
		if (result.hasErrors()) {
			return "vuPlantes";
		}
		manager.createPlante(plante);
		return "redirect:/plante/add"; 

	}
	@GetMapping("/plante/add")
	public String findPlante(Model model) throws BllException{
		model.addAttribute("plantes", manager.findAll());
		
		return "vuPlantes"; 

	}
	@GetMapping("/plante/delete/{id}")
	public String deletePlante(@PathVariable("id") Integer id, Model model) {	
		manager.deletePlanteById(id);
		
	    return "redirect:/plante/add";
	}

}
