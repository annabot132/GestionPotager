package fr.eni.GestionPotager.ihm;

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

import fr.eni.GestionPotager.bll.BllException;
import fr.eni.GestionPotager.bll.CarreManager;
import fr.eni.GestionPotager.bll.PlanteManager;
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
		Potager potager1 = new Potager("devant la maison", "mon potager de la maison", 50, "Quimper");
		Potager potager2 = new Potager("devant", "j'ai le carre3", 50, "Quimper");
		Carre carre = new Carre(16, "j'ai des plantations", Exposition.SOLEIL, potager1);
		Carre carre2 = new Carre(8, "no plantation", Exposition.SOLEIL, potager1);
		Carre carre3 = new Carre(8, "no plantation", Exposition.SOLEIL, potager1);

		carreMg.ajouterCarrePotager(potager1, carre);
		carreMg.ajouterCarrePotager(potager1, carre2);
		carreMg.ajouterCarrePotager(potager2, carre3);

		Plante plante = new Plante("Plante1", TypePlante.FEUILLE, "varPlante1", 2);
		Plante plante2 = new Plante("Plante2", TypePlante.FEUILLE, "varPlante2", 2);
		manager.createPlante(plante);
		manager.createPlante(plante2);
		
		
		
		Date date = new Date();
		
		Plantation plantation = new Plantation(date, date, 2, carre3, plante2);
		Plantation plantation2 = new Plantation(date, date, 2, carre2, plante);
		
		
		
		carreMg.ajouterPlantationAuCarre(carre, plante, plantation);
		carreMg.ajouterPlantationAuCarre(carre, plante2, plantation2);

		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		Plante plante3 = new Plante("tomate", TypePlante.FRUIT, "boeuf", (float) 0.75);
		manager.createPlante(plante1);
		manager.createPlante(plante3);

		carreMg.ajouterPlantationAuCarre(carre3, plante3, plantation);

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
			return "vuPlantes";
		}
		manager.updatePlante(plante);
		return "redirect:/plante/add";

	}

}
