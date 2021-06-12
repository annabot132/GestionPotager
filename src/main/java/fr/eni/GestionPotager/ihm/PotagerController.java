package fr.eni.GestionPotager.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String afficherDetailPotager(
			@PathVariable("idPotager") Integer idPotager, 
			Carre carre, 
			Plante plante,
			Plantation plantation, 
			Model model) {
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("IDPotager", idPotager);
		model.addAttribute("lstPlantes", planteMgr.findAll());

		return "potagerDetail";

	}

	@GetMapping("/potager/{idPotager}/deleteCarre/{idCarre}")
	public String supprimerCarre(
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre,
			Model model) {
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		carreMgr.deleteCarre(idCarre);

		return "redirect:/potager/{idPotager}";

	}

	@GetMapping("/potager/{idPotager}/carre/{idCarre}")
	public String afficherDetailCarre(
			@PathVariable("idPotager") Integer idPotager,
			@PathVariable("idCarre") Integer idCarre, 
			Carre carre, 
			Plante plante, 
			Plantation plantation, 
			Model model) {
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());
		model.addAttribute("lstPlantes", planteMgr.findAll());

		return "potagerDetail";

	}

	@PostMapping("/potager/{idPotager}/addCarre")
	public String ajouterCarreAuPotager(
			@Valid Carre carre, 
			@PathVariable("idPotager") Integer idPotager, 
			Model model)
			throws BllException {
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());

		carreMgr.ajouterCarrePotager(potagerMgr.getPotagerById(idPotager), carre);

		return "redirect:/potager/{idPotager}";
	}

	/**
	 * Ajoute la plantation au carré. 
	 * Le RequestParam permet de recupérer le parametre "planteID" de la page html (avec la liste des plantes dans le select)
	 *  
	 * @param plantation
	 * @param plante
	 * @param idPotager
	 * @param idCarre
	 * @param carre
	 * @param idPlante
	 * @param result
	 * @param model
	 * @return
	 * @throws BllException
	 */
	@PostMapping("/potager/{idPotager}/carre/{idCarre}/addPlantation")
	public String ajouterPlantationAuCarre(
			@Valid Plantation plantation, 
			Carre carre,
			Plante plante,
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre, 
			@RequestParam("planteID") Integer idPlante, 
			Model model) throws BllException {

		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("lstPlantes", planteMgr.findAll());
		
		carreMgr.ajouterPlantationAuCarre(carreMgr.findById(idCarre), planteMgr.findPlanteById(idPlante), plantation);

		return "redirect:/potager/{idPotager}/carre/{idCarre}";

	}
}
