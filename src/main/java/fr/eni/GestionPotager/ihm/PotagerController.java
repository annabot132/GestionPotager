package fr.eni.GestionPotager.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.GestionPotager.bll.BllException;
import fr.eni.GestionPotager.bll.CarreManager;
import fr.eni.GestionPotager.bll.PlanteManager;
import fr.eni.GestionPotager.bll.PotagerManager;
import fr.eni.GestionPotager.bo.Carre;

@Controller
public class PotagerController {

	@Autowired
	CarreManager carreMgr;

	@Autowired
	PlanteManager plantaMgr;

	@Autowired
	PotagerManager potagerMgr;
	
	
	

	@GetMapping("potager/{idPotager}")
	public String afficherDetailPotager(@PathVariable("idPotager") Integer idPotager,Carre carre, Model model) {
		// affichage détail potager => liste de carrés
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("IDPotager", idPotager);

		return "potagerDetail"; // on reste sur la meme page

	}

	@GetMapping("/potager/{idPotager}/deleteCarre/{idCarre}")
	public String supprimerCarre(@PathVariable("idPotager") Integer idPotager, @PathVariable("idCarre") Integer idCarre,
			Model model) {
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		carreMgr.deleteCarre(idCarre);

		return "redirect:/potager/{idPotager}";

	}

	@GetMapping("/potager/{idPotager}/carre/{idCarre}")
	public String afficherDetailCarre(@PathVariable("idPotager") Integer idPotager,
			@PathVariable("idCarre") Integer idCarre, Carre carre, Model model) {
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		
		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());

//		System.err.println("////////////////////////////////////////////////");
//		// System.err.println(carreMgr.findById(idCarre));
//		System.err.println("////////////////////////////////////////////////");
//		System.err.println(carreMgr.findById(idCarre).getListePlantations());
//		System.err.println("////////////////////////////////////////////////");

		
		 return "potagerDetail"; 
	}

	@PostMapping("/potager/{idPotager}/addCarre")
	public String ajouterPotager(@Valid Carre carre,@PathVariable("idPotager")Integer idPotager, Model model) throws BllException{
		
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		
		
	
		
		carreMgr.ajouterCarrePotager(potagerMgr.getPotagerById(idPotager), carre);
				
		System.err.println(carre);
		
		
		
		return "redirect:/potager/{idPotager}";
		
		
	}

}
