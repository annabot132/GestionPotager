package fr.eni.GestionPotager.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.GestionPotager.bll.ActionManager;
import fr.eni.GestionPotager.bll.BllException;
import fr.eni.GestionPotager.bll.CarreManager;
import fr.eni.GestionPotager.bll.PotagerManager;
import fr.eni.GestionPotager.bo.Action;
import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Potager;

@Controller
public class ActionController {

	@Autowired
	ActionManager actionMg;

	@Autowired
	CarreManager carreMgr;

	@Autowired
	PotagerManager potagerMgr;

	@GetMapping("/actions")
	public String listerActions(Action action, Potager potager, Model model) {
		System.err.println("tuuuuuuuuuuuuuuuuu "+potagerMgr.getAllPotager());
		model.addAttribute("actions", actionMg.findAllActionFor2Weeks());
		model.addAttribute("listePotagers", potagerMgr.getAllPotager());
		List<Action>  listevide = new ArrayList<Action>();
		model.addAttribute("listeActionsByPot", listevide);	
		model.addAttribute("listeActionsByPotByCarre", listevide);
		return "actions";
	}



	@PostMapping("/actions/add")
	public String addPotager(@Valid Action action, @RequestParam("idPotager") Integer idPotager,  BindingResult result, Potager potager, Model model) throws BllException {
	
		if (result.hasErrors()) {
			model.addAttribute("actions", actionMg.findAllActionFor2Weeks());
			model.addAttribute("listePotagers", potagerMgr.getAllPotager());
			model.addAttribute("carres", carreMgr.findAll());
			List<Action>  listevide = new ArrayList<Action>();
			model.addAttribute("listeActionsByPot", listevide);	
			model.addAttribute("listeActionsByPotByCarre", listevide);
			
			return "actions";
		}
		
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		action.setPotager(potagerMgr.getPotagerById(idPotager));
		actionMg.addAction(action);
		
//if (result.hasErrors()) {
//			
//			return "actions";
//		}
		
		
		return "redirect:/actions";

		
	}

	@GetMapping("/actions/find/{idPotager}")
	public String afficherActionsPotager(@PathVariable("idPotager") Integer idPotager,  Action action, Potager potager, Carre carre, Integer IdPotager,  
			Model model) {
		model.addAttribute("actions", actionMg.findAllActionFor2Weeks());
		model.addAttribute("listePotagers", potagerMgr.getAllPotager());
		model.addAttribute("carres", carreMgr.findAll());
		List<Action>  listevide = new ArrayList<Action>();
		model.addAttribute("listeActionsByPotByCarre", listevide);
		model.addAttribute("listeActionsByPot", actionMg.findAllActionByPotagerFor2Weeks(idPotager));
		model.addAttribute("listeCarres", carreMgr.findCarreByPotager(idPotager));
		model.addAttribute("nomPotager", potagerMgr.getPotagerById(idPotager).getNom());
		return "actions";
	}
	
	
	
}
