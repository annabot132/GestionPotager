package fr.eni.GestionPotager.ihm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import fr.eni.GestionPotager.bo.Potager;

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
			Potager potager,
			Carre carre, 
			Plante plante,
			Plantation plantation,
			Integer idPlantation,
			Model model) {
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("lstPlantes", planteMgr.findAll());
		
		
		model.addAttribute("nomPotager", potagerMgr.getPotagerById(idPotager).getNom());
		
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
			Potager potager,
			Carre carre, 
			Plante plante, 
			Plantation plantation, 
			Model model) {
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());
		model.addAttribute("lstPlantes", planteMgr.findAll());
		model.addAttribute("nomPotager", potagerMgr.getPotagerById(idPotager).getNom());
		
		return "potagerDetail";

	}

	@PostMapping("/potager/{idPotager}/addCarre")
	public String ajouterCarreAuPotager(
			@PathVariable("idPotager") Integer idPotager,
			@Valid Carre carre, 
			BindingResult result, Model model)
			throws BllException {
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("nomPotager", potagerMgr.getPotagerById(idPotager).getNom());

		
		if (result.hasErrors()) {
			return "potagerDetail";
		}
		try {
			carreMgr.ajouterCarrePotager(potagerMgr.getPotagerById(idPotager), carre);
		} catch (BllException e) {
//			result.addError(new FieldError("carre", "surface", e.getMessage()));
			result.addError(new FieldError("carre", "sol", e.getMessage()));
		}
		if (result.hasErrors()) {
			return "potagerDetail";
		}
		
		

		return "redirect:/potager/{idPotager}";
	}

	/**
	 * Ajoute la plantation au carré. 
	 * Le RequestParam permet de recupérer le parametre "idPlante" de la page html (avec la liste des plantes dans le select)
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
//			Potager potager,
			Carre carre,
			Plante plante,
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre, 
			@RequestParam("idPlante") Integer idPlante, 
			BindingResult result, Model model) throws BllException {

		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("idCarre", carreMgr.findById(idCarre).getIdCarre());
		
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("lstPlantes", planteMgr.findAll());
		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());
		
		
		if (result.hasErrors()) {
			return "potagerDetail";
		}
		try {
			carreMgr.ajouterPlantationAuCarre(carreMgr.findById(idCarre), planteMgr.findPlanteById(idPlante), plantation);
		} catch (BllException e) {
//			result.addError(new FieldError("plantation", "quantite", e.getMessage()));
//			result.addError(new FieldError("plantation", "miseEnPlace", e.getMessage()));
			result.addError(new FieldError("plantation", "recolte", e.getMessage()));
		}
		if (result.hasErrors()) {
			return "potagerDetail";
		}
		
		return "potagerDetail";

	}
	
	@GetMapping("/potager/{idPotager}/carre/{idCarre}/deletePlantation/{idPlantation}")
	public String supprimerPlantation(
			@Valid Plantation plantation, 
			Potager potager,
			Carre carre,
			Plante plante,
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre,
			@PathVariable("idCarre") Integer idPlantation,
			 
			Model model) throws BllException {
//		@PathVariable("idPlantation") Integer idPlantation,
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("lstPlantes", planteMgr.findAll());
		carreMgr.deletePlantationOfCarre(plantation, carre);

		return "redirect:/potager/{idPotager}/carre/{idCarre}";

	}
	
	@GetMapping("/potager/{idPotager}/carre/{idCarre}/editPlantation/{idPlantation}")
	public String showUpdateForm(
			Plantation plantation, 
			Potager potager,
			Carre carre,
			Plante plante,
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre,
			@PathVariable("idPlantation") Integer idPlantation,
			Model model) throws BllException {

		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());
		model.addAttribute("lstPlantes", planteMgr.findAll());
		model.addAttribute("nomPotager", potagerMgr.getPotagerById(idPotager).getNom());
		
		Carre carreChoix = carreMgr.findById(idCarre);
		List<Plantation> lstPlant = carreChoix.getListePlantations();
		for (Plantation p : lstPlant) {
			if (p.getIdPlantation()==idPlantation) {
				model.addAttribute(p);
			}
		};

		return "modifPlantation";

	}
	
	@PostMapping("/potager/{idPotager}/carre/{idCarre}/updatePlantation/{idPlantation}")
	public String UpdatePlantation(
			@Valid Plantation plantation, 
			Potager potager,
			Carre carre,
			Plante plante,
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre,
			@PathVariable("idPlantation") Integer idPlantation,
			@RequestParam("idPlante") Integer idPlante, 
			BindingResult result, Model model) throws BllException {

		plantation.setIdPlantation(idPlantation);
		
		
		
		
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());
		model.addAttribute("lstPlantes", planteMgr.findAll());
		
		System.err.println("JE SUIS LA : "+ plantation+ carreMgr.findById(idCarre)+ planteMgr.findPlanteById(idPlante));
		
		if (result.hasErrors()) {
			return "modifPlantation";
		}
		try {
			carreMgr.modifierPlantationOfCarre(plantation, carreMgr.findById(idCarre), planteMgr.findPlanteById(idPlante));
			
		} catch (BllException e) {
			
			result.addError(new FieldError("plantation", "quantite", e.getMessage()));
			result.addError(new FieldError("plantation", "miseEnPlace", e.getMessage()));
			result.addError(new FieldError("plantation", "recolte", e.getMessage()));
		}
		if (result.hasErrors()) {
			return "modifPlantation";
		}
		

		return "redirect:/potager/{idPotager}/carre/{idCarre}";
	}
	
	@GetMapping("/potager/{idPotager}/carre/editCarre/{idCarre}")
	public String showUpdateFormCarre(
			Potager potager,
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre,
			Model model) throws BllException {

		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		model.addAttribute("nomPotager", potagerMgr.getPotagerById(idPotager).getNom());
		
		
		Carre carre = carreMgr.findById(idCarre);
		model.addAttribute("carre",carre);
		return "modifCarre";

	}
	
	@PostMapping("/potager/{idPotager}/carre/updateCarre/{idCarre}")
	public String updateCarre(
			@Valid Carre carre,
			@PathVariable("idPotager") Integer idPotager, 
			@PathVariable("idCarre") Integer idCarre,
			BindingResult result, Model model) throws BllException {

		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		
		if (result.hasErrors()) {
			return "modifCarre";
		}
		
		try {
			carre.setPotager(potagerMgr.getPotagerById(idPotager));
			carre.setIdCarre(idCarre);
			carreMgr.updateCarre(carre);
		} catch (BllException e) {
			result.addError(new FieldError("carre", "sol", e.getMessage()));
//			result.addError(new FieldError("carre", "surface", e.getMessage()));
		}
		
		if (result.hasErrors()) {
			return "modifCarre";
		}
		
	
		return "redirect:/potager/{idPotager}/carre/{idCarre}";
	}
	
}
