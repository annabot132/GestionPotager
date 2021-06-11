package fr.eni.GestionPotager.bll;

import java.util.List;

import fr.eni.GestionPotager.bo.Action;

public interface ActionManager {

	/**
	 * Ajouter une Action 
	 * @param  Action action
	 * @throws BllException
	 */
	public void addAction(Action action) throws BllException;

	/**
	 * Supprimer une action
	 * @param Integer id
	 */
	public void removeActionById(Integer id);

	/**
	 * modifier une action
	 * @param Action action
	 */
	public void updateAction(Action action);

	/**
	 * lister toutes les actions
	 * @return List<Action> 
	 */
	public List<Action> findAllAction();

	/**
	 * Trouver une action par son id
	 * @param id
	 * @return
	 */
	public Action findActionById(Integer id);
	
	/**
	 * liste toutes les actions des 15 jours à venir
	 * @return List<Action>
	 */
	public List<Action> findAllActionFor2Weeks();
	
	/**
	 * liste toutes les actions des 15 jours à venir pour un potager
	 * @param Integer id
	 * @return List<Action>
	 */
	public List<Action> findAllActionByPotagerFor2Weeks(Integer idPotager);

	/**
	 * liste toutes les actions des 15 jours à venir pour un carre d'un potager
	 * @param Integer id
	 * @return List<Action>
	 */
	public List<Action> findAllActionByPotagerByCarreFor2Weeks(Integer idPotager, Integer idCarre);
}
