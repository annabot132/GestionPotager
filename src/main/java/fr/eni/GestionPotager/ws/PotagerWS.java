package fr.eni.GestionPotager.ws;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.GestionPotager.bll.CarreManager;
import fr.eni.GestionPotager.bll.PotagerManager;
import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Potager;

@RestController
public class PotagerWS {
	
	@Autowired
	PotagerManager pManager;
	
	@Autowired
	CarreManager cManager;
	
	
	@GetMapping("/WS/potager/{id}")
	public Potager getPotagerWithId(@PathVariable Integer id){
		return pManager.getPotagerById(id);
	}
	
	@GetMapping("/WS/carre")
	public List<Carre> getCarreEmpty(){
		return cManager.findAllEmptyCarre();
	}

}
