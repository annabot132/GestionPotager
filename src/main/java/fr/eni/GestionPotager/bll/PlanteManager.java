package fr.eni.GestionPotager.bll;

import java.util.List;

import fr.eni.GestionPotager.bo.Plante;

public interface PlanteManager {
	
	/**
	 * creer une plante 
	 * @param plante
	 * @throws BllException 
	 */
	public void createPlante(Plante plante) throws BllException;
	
	/**
	 * lister toutes les plantes
	 * @return List<Plante> 
	 */
	public List<Plante> findAll();
	
	/**
	 * trouver une plante par son id
	 * @param Integer id
	 * @return Plante
	 */
	public Plante findPlanteById(Integer id);
	
	/**
	 * modifier une plante
	 * @param Plante plante
	 */
	public void updatePlante(Plante plante);
	
	/**
	 * supprimer une plante
	 * @param Plante plante
	 */
	public void deletePlante(Plante plante);
	
	/**
	 * supprimer une plante par son id
	 * @param Integer id
	 */
	public void deletePlanteById(Integer id);
	
}
