package fr.eni.GestionPotager.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.GestionPotager.bll.PlanteManager;
import fr.eni.GestionPotager.bll.PotagerManager;
import fr.eni.GestionPotager.bo.Potager;

@Controller
public class AcceuilController {

	@Autowired
	PotagerManager potMg;
	
	@Autowired
	PlanteManager planteMg;
	
	@GetMapping("/acceuil")
	public String listerPotagers(Model model, Potager potager) {
		model.addAttribute("potagers", potMg.getAllPotager());
		potager.setNom("pot");
		return "acceuil";
	}

	@GetMapping("/acceuil/delete/{id}")
	public String supprimerPotager(@PathVariable("id") Integer id, Model model) {
		potMg.removePotagerById(id);
		
		return "redirect:/acceuil";
	}
	
	@GetMapping("/acceuil/show/{id}")
	public String visualiserPotager(@PathVariable("id") Integer id, Model model) {
		potMg.removePotagerById(id);
		return "redirect:/acceuil";
	}
	
	@PostMapping("/acceuil/add")
	public String addPotager(@Valid Potager potager, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "acceuil";
		}
		potMg.addPotager(potager);
		return "redirect:/acceuil";

	}
	
	
	
}
