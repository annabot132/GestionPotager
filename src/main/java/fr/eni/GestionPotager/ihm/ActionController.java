package fr.eni.GestionPotager.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.GestionPotager.bll.ActionManager;
import fr.eni.GestionPotager.bll.PotagerManager;
import fr.eni.GestionPotager.bo.Action;

@Controller
public class ActionController {
	

	@Autowired
	ActionManager actionMg;

	
	
	@GetMapping("/actions")
	public String listerPotagers(Model model, Action action) {
		model.addAttribute("actions", actionMg.findAllAction());
		return "actions";
	}

//	@GetMapping("/actions/delete/{id}")
//	public String supprimerPotager(@PathVariable("id") Integer id, Model model) {
//
//		return "redirect:/actions";
//	}
//
//
//	@PostMapping("/actions/add")
//	public String addPotager(@Valid Potager potager, BindingResult result, Model model) {
//		return "redirect:/actions";
//
//	}
	
	

}
