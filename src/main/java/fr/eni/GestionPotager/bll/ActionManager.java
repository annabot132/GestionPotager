package fr.eni.GestionPotager.bll;

import java.util.List;

import fr.eni.GestionPotager.bo.Action;

public interface ActionManager {

	public void addAction(Action action) throws BllException;

	public void removeActionById(Integer id);

	public void updateAction(Action action);

	public List<Action> findAllAction();

	public Action findActionById(Integer id);
	
	public List<Action> find2SemainesAction();

}
