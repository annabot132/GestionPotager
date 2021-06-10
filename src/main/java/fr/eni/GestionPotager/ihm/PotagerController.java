package fr.eni.GestionPotager.ihm;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.GestionPotager.bll.BllException;
import fr.eni.GestionPotager.bll.CarreManager;
import fr.eni.GestionPotager.bll.PlanteManager;
import fr.eni.GestionPotager.bll.PotagerManager;
import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Plantation;
import fr.eni.GestionPotager.bo.Plante;

@Controller
public class PotagerController {

	@Autowired
	CarreManager carreMgr;

	@Autowired
	PlanteManager plantaMgr;

	@Autowired
	PotagerManager potagerMgr;
	
	@Autowired
	PlanteManager planteMgr;

	@GetMapping("potager/{idPotager}")
	public String afficherDetailPotager(@PathVariable("idPotager") Integer idPotager, Carre carre,Plante plante, Plantation plantation, Model model) {
		// affichage détail potager => liste de carrés
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("IDPotager", idPotager);
		model.addAttribute("lstPlantes",planteMgr.findAll());
		
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
			@PathVariable("idCarre") Integer idCarre, Carre carre,Plante plante, Plantation plantation, Model model) {
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());

		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());
		model.addAttribute("lstPlantes",planteMgr.findAll());

//		System.err.println("////////////////////////////////////////////////");
//		// System.err.println(carreMgr.findById(idCarre));
//		System.err.println("////////////////////////////////////////////////");
//		System.err.println(carreMgr.findById(idCarre).getListePlantations());
//		System.err.println("////////////////////////////////////////////////");


		return "potagerDetail";

	}

	@PostMapping("/potager/{idPotager}/addCarre")
	public String ajouterCarreAuPotager(@Valid Carre carre, @PathVariable("idPotager") Integer idPotager, Model model)
			throws BllException {

		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());

		carreMgr.ajouterCarrePotager(potagerMgr.getPotagerById(idPotager), carre);

		//System.err.println(carre);

		return "redirect:/potager/{idPotager}";

	}

	@PostMapping("/potager/{idPotager}/carre/{idCarre}/addPlantation")
	public String ajouterPlantationAuCarre(@Valid Plantation plantation,@Valid Plante plante, @PathVariable("idPotager") Integer idPotager,
			@PathVariable("idCarre") Integer idCarre, Carre carre, Model model) throws BllException {

		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());

		
		// lstPlantes
		model.addAttribute("lstPlantes",planteMgr.findAll());

		//Plante plante2 = (Plante) model.getAttribute("plante");

		
		
		
		System.err.println("////////////////");
		System.err.println(plante);
		
		// add plantation
	//	carreMgr.ajouterPlantationAuCarre(carre, plante, plantation);
		
		carreMgr.ajouterPlantationAuCarre(carre, plante,(int) model.getAttribute("quantite"), (LocalDate)model.getAttribute("miseEnPlace"),  (LocalDate)model.getAttribute("recolte"));
		
		
		
		System.err.println(plantation);

		return "redirect:/potager/{idPotager}";

	}
}
