package fr.eni.GestionPotager.ihm;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
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
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.bo.TypePlante;

@Controller
public class AccueilController {

	@Autowired
	PotagerManager potMg;

	@Autowired
	PlanteManager planteMg;
	
	@Autowired
	CarreManager carreMg;
	
	@PostConstruct
	public void init() throws BllException {
		Potager potager1 = new Potager("devant la maison", "mon potager de la maison", 50, "Quimper");
		Potager potager2 = new Potager("serre", "ma serre", 25, "Quimper");
		
		Carre carre = new Carre(15, "argileux", Exposition.MI_OMBRE, potager1);
		Carre carre2 = new Carre(10, "argileux", Exposition.SOLEIL, potager1);
		Carre carre3 = new Carre(10, "argileux", Exposition.SOLEIL, potager1);
		Carre carre4 = new Carre(10, "humifère", Exposition.SOLEIL, potager2);
		Carre carre5 = new Carre(10, "humifère", Exposition.SOLEIL, potager2);
		
		carreMg.ajouterCarrePotager(potager1, carre);
		carreMg.ajouterCarrePotager(potager1, carre2);
		carreMg.ajouterCarrePotager(potager1, carre3);
		carreMg.ajouterCarrePotager(potager2, carre4);
		carreMg.ajouterCarrePotager(potager2, carre5);

		Plante tomate1 = new Plante("tomate", TypePlante.FRUIT, "cerise",(float) 0.75);
		Plante tomate2 = new Plante("tomate", TypePlante.FRUIT, "grenn zebra", (float) 0.75);
		Plante tomates3 = new Plante("tomate", TypePlante.FRUIT, "coeur de boeuf", (float) 0.75);
		Plante carottes = new Plante("carotte", TypePlante.RACINE, "nantes",(float) 0.3);
		Plante betterave = new Plante("betterave", TypePlante.RACINE, "egypte", (float) 0.3);
		Plante epinard = new Plante("epinard", TypePlante.FEUILLE, "geant", (float) 0.3);
		Plante courges = new Plante("courge", TypePlante.RACINE, "potimarron", (float) 0.5);
		Plante poivron = new Plante("poivron", TypePlante.FRUIT, "jaune", (float) 0.5);
		Plante laitue = new Plante("batavia", TypePlante.FEUILLE, "Reine des glaces",(float) 0.25);
		Plante radis = new Plante("radis", TypePlante.RACINE, "flamboyant", (float) 0.2);
		
		planteMg.createPlante(tomate1);
		planteMg.createPlante(tomate2);
		planteMg.createPlante(tomates3);
		planteMg.createPlante(carottes);
		planteMg.createPlante(betterave);
		planteMg.createPlante(epinard);
		planteMg.createPlante(courges);
		planteMg.createPlante(poivron);
		planteMg.createPlante(laitue);
		planteMg.createPlante(radis);
		
		carreMg.ajouterPlantationAuCarre(carre4, tomate1, 2, LocalDate.now().minusWeeks(4), LocalDate.now().plusWeeks(4));
		carreMg.ajouterPlantationAuCarre(carre4, tomate2, 1, LocalDate.now().minusWeeks(4), LocalDate.now().plusWeeks(5));
		carreMg.ajouterPlantationAuCarre(carre4, poivron, 3, LocalDate.now().minusWeeks(4), LocalDate.now().plusWeeks(4));
		carreMg.ajouterPlantationAuCarre(carre4, laitue, 3, LocalDate.now(), LocalDate.now().plusWeeks(4));
		
		carreMg.ajouterPlantationAuCarre(carre5, tomate1, 1, LocalDate.now().minusWeeks(4), LocalDate.now().plusWeeks(4));
		carreMg.ajouterPlantationAuCarre(carre5, tomates3, 2, LocalDate.now().minusWeeks(4), LocalDate.now().plusWeeks(6));
		carreMg.ajouterPlantationAuCarre(carre5, carottes,5, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4));
		
		carreMg.ajouterPlantationAuCarre(carre, courges, 4, LocalDate.now().minusWeeks(4), LocalDate.now().plusWeeks(12));
		carreMg.ajouterPlantationAuCarre(carre, epinard, 1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(3));
		carreMg.ajouterPlantationAuCarre(carre, betterave, 1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(6));
		
		carreMg.ajouterPlantationAuCarre(carre2, radis, 5, LocalDate.now(), LocalDate.now().plusWeeks(3));
		carreMg.ajouterPlantationAuCarre(carre2, laitue, 5, LocalDate.now(), LocalDate.now().plusWeeks(4));
		carreMg.ajouterPlantationAuCarre(carre2, carottes,5, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(6));
	}

	@GetMapping("/accueil")
	public String listerPotagers(Model model, Potager potager) {
		model.addAttribute("potagers", potMg.getAllPotager());
		//potager.setNom("pot");
		return "accueil";
	}

	@GetMapping("/accueil/delete/{id}")
	public String supprimerPotager(@PathVariable("id") Integer id, Model model) {
		potMg.removePotagerById(id);

		return "redirect:/accueil";
	}

	@GetMapping("/accueil/show/{id}")
	public String visualiserPotager(@PathVariable("id") Integer id, Model model) {
		potMg.removePotagerById(id);
		return "redirect:/accueil";
	}

	@PostMapping("/accueil/add")
	public String addPotager(@Valid Potager potager, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "accueil";
		}
			potMg.addPotager(potager);
			
	
		return "redirect:/accueil";

	}

}
