package fr.eni.GestionPotager.bll;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Action;
import fr.eni.GestionPotager.dal.ActionDao;

@Service
public class ActionManagerImpl implements ActionManager {

	
	@Autowired
	ActionDao dao;
	
	@Override
	public void addAction(Action action) throws BllException {
		if (action.getDate().isBefore(LocalDate.now())) {
			throw new BllException("La date entrée doit être posterieur à la date du jour.");
		}
		dao.save(action);

	}

	@Override
	public void removeActionById(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public void updateAction(Action action) {
		dao.save(action);

	}

	@Override
	public List<Action> findAllAction() {
		return (List<Action>) dao.findAll();
	}

	@Override
	public Action findActionById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Action> find2SemainesAction() {
		return null;
	}

}
