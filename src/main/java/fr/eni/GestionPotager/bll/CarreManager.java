package fr.eni.GestionPotager.bll;

import java.util.List;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Potager;

/**
 * 
 * @author pheydon2021
 *
 */
public interface CarreManager {

	/**
	 * Créé un carré
	 * @param carre
	 */
	public void createCarre(Carre carre);

	/**
	 * Supprime un carré par son id
	 * @param idCarre
	 */
	public void deleteCarre(Integer idCarre);

	/**
	 * Update un carré
	 * @param carre
	 */
	public void updateCarre(Carre carre);

	/**
	 * Cherche un carré par son id
	 * @param idCarre
	 * @return
	 */
	public Carre findById(Integer idCarre);

	/**
	 * Trouve tout les carrés
	 * @return
	 */
	public List<Carre> findAll();
	
	public void ajouterCarrePotager(Potager potager, Carre carre);
	
}
