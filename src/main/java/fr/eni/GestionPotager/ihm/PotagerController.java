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

	@GetMapping("potager/{idPotager}")
	public String afficherDetailPotager(@PathVariable("idPotager") Integer idPotager, Model model) {
		// affichage détail potager => liste de carrés
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		
		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());

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
			@PathVariable("idCarre") Integer idCarre, Model model) {
		model.addAttribute("idPotager", potagerMgr.getPotagerById(idPotager).getIdPotager());

		model.addAttribute("lstCarres", potagerMgr.getPotagerById(idPotager).getListeCarres());
		
		model.addAttribute(("lstPlantations"), carreMgr.findById(idCarre).getListePlantations());

//		System.err.println("////////////////////////////////////////////////");
//		// System.err.println(carreMgr.findById(idCarre));
//		System.err.println("////////////////////////////////////////////////");
//		System.err.println(carreMgr.findById(idCarre).getListePlantations());
//		System.err.println("////////////////////////////////////////////////");

		//return "redirect:/potager/{idPotager}/carre/{idCarre}"; // => mauvaise redirection => dois
		// afficher page de "détail du carré"
		 return "potagerDetail"; // => renvoi bien les plantations
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
