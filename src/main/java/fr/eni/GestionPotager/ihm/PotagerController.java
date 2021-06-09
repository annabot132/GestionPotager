package fr.eni.GestionPotager.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.GestionPotager.bll.CarreManager;
import fr.eni.GestionPotager.bll.PlanteManager;
import fr.eni.GestionPotager.bll.PotagerManager;

@Controller
public class PotagerController {

	@Autowired
	CarreManager carreMgr;

	@Autowired
	PlanteManager plantaMgr;

	@Autowired
	PotagerManager potagerMgr;

	// @GetMapping("potager/{id}/detail")
	@GetMapping("potager/{idPotager}")
	public String afficherDetailPotager(@PathVariable("idPotager") Integer idPotager, Model model) {
		// affichage détail potager => liste de carrés
		// System.out.println(carreMgr.findAll());
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());
		
		
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());

		// model.addAttribute("lstCarres", carreMgr.findAll());//ressort la liste de
		// TOUS les carrés => besoin de sortir la liste de tous les carré par idPotager
		return "potagerDetail"; // on reste sur la meme page

	}

//	@GetMapping("/potager/deleteCarre/{idCarre}")
//	public String supprimerCarre(@PathVariable("idCarre") Integer idCarre, Model model) {

	@GetMapping("/potager/{idPotager}/deleteCarre/{idCarre}")
	public String supprimerCarre(@PathVariable("idPotager") Integer idPotager, @PathVariable("idCarre") Integer idCarre,
			Model model) {
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		carreMgr.deleteCarre(idCarre);
		// System.err.println(idCarre);
		// Integer idPotager = potagerMgr.getPotagerById(1).getIdPotager();

		// quand on delete un carré avec des plantations qui lui sont lié => erreur
		// contrainte (au niveau plantation) => cascade pour les plantations ?

		return "redirect:/potager/{idPotager}";
		// return "potagerDetail";

	}

	@GetMapping("/potager/{idPotager}/detail/{idCarre}")
	public String afficherDetailCarre(@PathVariable("idPotager") Integer idPotager,
			@PathVariable("idCarre") Integer idCarre, Model model) {
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());
		
		
		
		System.err.println("////////////////////////////////////////////////");
		//System.err.println(carreMgr.findById(idCarre));
		System.err.println("////////////////////////////////////////////////");
		System.err.println(carreMgr.findById(idCarre).getListePlantations());
		System.err.println("////////////////////////////////////////////////");
		return "redirect:/potager/{idPotager}";
	}

//	@PostMapping("/potager/detail/add/{id}")
//	public String ajouterPotager(@PathVariable("id")Integer id, Model model){
//		
//		
//		
//		
//		
//		return "redirect:/potager/{id}/detail";
//		
//		
//	}

}
