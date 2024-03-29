package fr.eni.GestionPotager.ihm;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.GestionPotager.bll.ActionManager;
import fr.eni.GestionPotager.bll.BllException;
import fr.eni.GestionPotager.bll.CarreManager;
import fr.eni.GestionPotager.bll.PlanteManager;
import fr.eni.GestionPotager.bo.Action;
import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Plantation;
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.bo.TypePlante;

@Controller
public class PlanteController {

	@Autowired
	PlanteManager manager;

	@Autowired
	CarreManager carreMg;

@Autowired
ActionManager actionMg;
//	@PostConstruct
//	void init() throws BllException {
//		Potager potager1 = new Potager("devant la maison", "mon potager de la maison", 50, "Quimper");
//		Potager potager2 = new Potager("devant", "j'ai le carre3", 50, "Quimper");
//		Carre carre = new Carre(16, "j'ai des plantations", Exposition.SOLEIL, potager1);
//		Carre carre2 = new Carre(8, "no plantation", Exposition.SOLEIL, potager1);
//		Carre carre3 = new Carre(8, "no plantation", Exposition.SOLEIL, potager1);
//
//		carreMg.ajouterCarrePotager(potager1, carre);
//		carreMg.ajouterCarrePotager(potager1, carre2);
//		carreMg.ajouterCarrePotager(potager2, carre3);
//
//		Plante plante = new Plante("Plante1", TypePlante.FEUILLE, "varPlante1", 2);
//		Plante plante2 = new Plante("Plante2", TypePlante.FEUILLE, "varPlante2", 2);
//		manager.createPlante(plante);
//		manager.createPlante(plante2);
//
//		carreMg.ajouterPlantationAuCarre(carre, plante, 2, LocalDate.now(), LocalDate.now());
//		carreMg.ajouterPlantationAuCarre(carre, plante2, 2, LocalDate.now(), LocalDate.now());
//
//		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
//		Plante plante3 = new Plante("tomate", TypePlante.FRUIT, "boeuf", (float) 0.75);
//		manager.createPlante(plante1);
//		manager.createPlante(plante3);
//
//		carreMg.ajouterPlantationAuCarre(carre3, plante3, 2, LocalDate.now().plusWeeks(1),
//				LocalDate.now().plusWeeks(2));
//
//	}
	@PostConstruct
	void init() throws BllException {
		Plante plante = new Plante("Radis", TypePlante.RACINE, "Flamboyant", 0.1);
		Plante plante2 = new Plante("Navet", TypePlante.RACINE, "Globe", 0.2);
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "Cerise",  0.75);
		Plante plante3 = new Plante("tomate", TypePlante.FRUIT, "Boeuf", 0.75);
		Plante plante4 = new Plante("Epinars", TypePlante.FEUILLE, "Géant d'hivers", 0.2);
		Plante plante5 = new Plante("Petit pois", TypePlante.FRUIT, "Vert", 0.2);
		Plante plante6 = new Plante("courgette", TypePlante.FRUIT, "Jaune",  1);
		Plante plante7 = new Plante("Courgette", TypePlante.FRUIT, "Ronde de Nice", 0.75);
		manager.createPlante(plante1);
		manager.createPlante(plante3);
		manager.createPlante(plante);
		manager.createPlante(plante2);
		manager.createPlante(plante4);
		manager.createPlante(plante5);
		manager.createPlante(plante6);
		manager.createPlante(plante7);
		
		
		
		Potager potager1 = new Potager("devant la maison", "mon potager de la maison", 50, "Quimper");
		Potager potager2 = new Potager("derrière la maison", "Serre", 50, "Quimper");
		
		
		Carre carre = new Carre(16, "argileux", Exposition.SOLEIL, potager1);
		Carre carre2 = new Carre(8, "argileux", Exposition.MI_OMBRE, potager1);
		Carre carre3 = new Carre(8, "argileux", Exposition.MI_OMBRE, potager1);

		Carre carre4 = new Carre(10, "limoneux", Exposition.SOLEIL, potager2);
		Carre carre5 = new Carre(10, "limoneux", Exposition.SOLEIL, potager2);
		
		
		carreMg.ajouterCarrePotager(potager1, carre);
		carreMg.ajouterCarrePotager(potager1, carre2);
		carreMg.ajouterCarrePotager(potager1, carre3);
		
		carreMg.ajouterCarrePotager(potager2, carre4);
		carreMg.ajouterCarrePotager(potager2, carre5);

		
		
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 18);
		Date date2 = cal.getTime();
				Action ac = new Action(date2, "Arroser", potager1, carre2);
		Action ac2 = new Action(date2, "Arroser", potager1, carre3);
		

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 5, 20);
		Date date3 = cal.getTime();
		Action ac3 = new Action(date3, "Désherber", potager2, carre4);
		Action ac24 = new Action(date3, "Désherber", potager2, carre5);
		
		actionMg.addAction(ac3);
		actionMg.addAction(ac);
		actionMg.addAction(ac2);
		actionMg.addAction(ac24);

		
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(2021, 5, 1);
		Date dateplant = cal.getTime();
		
		cal3.set(2021, 5, 25);
		Date daterec1 = cal.getTime();
		
		cal3.set(2021, 5, 20);
		Date daterec2 = cal.getTime();
		
		cal3.set(2021, 8, 20);
		Date daterec3 = cal.getTime();
		Plantation plantation = new Plantation(dateplant, daterec2, 2, carre3, plante2);
		Plantation plantation2 = new Plantation(dateplant, daterec1, 2, carre2, plante);
		Plantation plantation3 = new Plantation(daterec1, daterec3, 2, carre2, plante6);
		
		
		carreMg.ajouterPlantationAuCarre(carre, plante2, plantation);
		carreMg.ajouterPlantationAuCarre(carre, plante, plantation2);
		carreMg.ajouterPlantationAuCarre(carre, plante6, plantation3);

	}

	@GetMapping("/plante/add")
	public String findPlante(Model model, Plante plante) throws BllException {
		model.addAttribute("plantes", manager.findAll());

//		plantations = new ArrayList<Plantation>();

		return "vuPlantes";
	}

	@PostMapping("/plante/add")
	public String addPlante(@Valid Plante plante, BindingResult result, Model model) throws BllException {
		if (result.hasErrors()) {
			model.addAttribute("plantes", manager.findAll());
			return "vuPlantes";
		}
		manager.createPlante(plante);
		return "redirect:/plante/add";

	}

	@GetMapping("/plante/delete/{id}")
	public String deletePlante(@PathVariable("id") Integer id, Model model) {

		manager.deletePlanteById(id);

		return "redirect:/plante/add";
	}

	@GetMapping("/plante/find/{id}")
	public String rechercherPlante(@PathVariable("id") Integer id, Plante plante, Model model) {
		model.addAttribute("plantes", manager.findAll());
		model.addAttribute("plantations", carreMg.findAllImplantationsForOnePlante(id));
//		System.err.println(carreMg.findAllImplantationsForOnePlante(id).get(0).);
		return "vuPlantes";
	}


	@GetMapping("plante/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Plante plante = manager.findPlanteById(id);
		model.addAttribute("plante", plante);
		return "modifPlante";

	}

	@PostMapping("plante/update/{id}")
	public String updatePlante(@PathVariable("id") Integer id, @Valid Plante plante, BindingResult result,
			Model model) {
		plante.setIdPlante(id);
		if (result.hasErrors()) {
			return "modifPlante";
		}
		manager.updatePlante(plante);
		return "redirect:/plante/add";

	}

}
