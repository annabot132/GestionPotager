package fr.eni.GestionPotager.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Plantation;
import fr.eni.GestionPotager.bo.Plante;
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
	
	/**
	 * ajouter un carre à un potager
	 * @param potager
	 * @param carre
	 * @throws BllException
	 */
	public void ajouterCarrePotager(Potager potager, Carre carre) throws BllException;
	

	/**
	 * ajouter une plantation à un carre
	 * @param carre
	 * @param plante
	 * @param qte
	 * @param dateMiseEnPlace
	 * @param dateDeRecolte
	 * @throws BllException
	 */
	public void ajouterPlantationAuCarre(Carre carre, Plante plante, int qte, LocalDate dateMiseEnPlace, LocalDate dateDeRecolte ) throws BllException;

	/**
	 * charche toutes les implantations d'une plante
	 * @param Integer id
	 * @return List<Plante>
	 */
	public List<Plantation> findAllImplantationsForOnePlante(Integer id);

}
