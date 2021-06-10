package fr.eni.GestionPotager.ihm;

import java.time.LocalDate;

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
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.bo.TypePlante;

@Controller
public class PlanteController {

	@Autowired
	PlanteManager manager;

	@Autowired
	CarreManager carreMg;

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
