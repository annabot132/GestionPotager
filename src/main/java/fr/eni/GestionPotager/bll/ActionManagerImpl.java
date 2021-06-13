package fr.eni.GestionPotager.bll;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
		if (action.getDate().before(localDateToDate(LocalDate.now()))) {
			throw new BllException("La date entrée doit être posterieur à la date du jour !");
		}
		dao.save(action);

	}

	@Override
	public void deleteActionById(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public void deleteAction(Action action) {
		dao.delete(action);

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
	public List<Action> findAllActionFor2Weeks() {
		LocalDate dateJour = LocalDate.now();
		LocalDate dateJourPlus15 = LocalDate.now().plusDays(14);
		return dao.findActionsByIntervalDate(localDateToDate(dateJour), localDateToDate(dateJourPlus15));

	}

	@Override
	public List<Action> findAllActionByPotagerFor2Weeks(Integer idPotager) {
		LocalDate dateJour = LocalDate.now();
		LocalDate dateJourPlus15 = LocalDate.now().plusDays(14);
		return dao.findActionsByPotagerByIntervalDate(idPotager, localDateToDate(dateJour),
				localDateToDate(dateJourPlus15));
	}

	@Override
	public List<Action> findAllActionByPotagerByCarreFor2Weeks(Integer idPotager, Integer idCarre) {
		LocalDate dateJour = LocalDate.now();
		LocalDate dateJourPlus15 = LocalDate.now().plusDays(14);
		return dao.findActionsByPotagerByCarreByIntervalDate(idCarre, idPotager, localDateToDate(dateJour),
				localDateToDate(dateJourPlus15));

	}

	public Date localDateToDate(LocalDate date) {
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}

}
