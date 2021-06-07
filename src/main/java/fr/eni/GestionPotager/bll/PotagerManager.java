package fr.eni.GestionPotager.bll;

import java.util.List;

import fr.eni.GestionPotager.bo.Potager;

/**
 * CRUD de Potager
 * 
 * @author cbertran2021
 *
 */
public interface PotagerManager {

	/**
	 * Ajoute un potager
	 * 
	 * @param potager
	 * @throws BllException 
	 */
	public void addPotager(Potager potager);

	/**
	 * Supprime un potager
	 * 
	 * @param potager
	 */
	public void removePotager(Potager potager);

	/**
	 * Met a jour un potager
	 * 
	 * @param potager
	 */
	public void updatePotager(Potager potager);

	/**
	 * Recupere tout les potager
	 * 
	 * @return liste de potager
	 */
	public List<Potager> getAllPotager();

	/**
	 * Recupere un potager par son identifiant
	 * 
	 * @param id
	 * @return potager
	 */
	public Potager getPotagerById(Integer id);

	/**
	 * Supprime un potager par son identifiant
	 * 
	 * @param id
	 */
	public void removePotagerById(Integer id);
	
	
	

}
