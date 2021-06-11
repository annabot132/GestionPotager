package fr.eni.GestionPotager.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Action;
import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Potager;

@SpringBootTest
class ActionDaoTest {

	@Autowired
	ActionDao dao;
	@Autowired
	PotagerDao daoPot;
	@Autowired
	CarreDao daoCarre;
	
	@Test
	@Transactional
	final void testSave() {
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  06,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", null, null);
		dao.save(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		assertEquals(listeActions.size(), 1);

	}

	@Test
	@Transactional
	final void testFindById() {
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  06,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", null, null);
		dao.save(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		Action actionTest = dao.findById(listeActions.get(0).getIdAction()).orElse(null);
		assertEquals(actionTest.getDate(), action.getDate());
	}

	@Test
	@Transactional
	final void testFindAll() {
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  06,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", null, null);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2020,  06,  18);
		Date date2 = cal2.getTime();
		Action action2 = new Action(date2, "testAction2", null, null);
		dao.save(action);
		dao.save(action2);

		List<Action> listeActions = (List<Action>) dao.findAll();
		assertEquals(listeActions.size(), 2);

	}

	@Test
	@Transactional
	final void testDeleteById() {
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  06,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", null, null);
		dao.save(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		dao.deleteById(listeActions.get(0).getIdAction());
		List<Action> listeActions2 = (List<Action>) dao.findAll();

		assertEquals(listeActions2.size(), 0);
	}

	@Test
	@Transactional
	final void testDelete() {
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  06,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", null, null);
		dao.save(action);
		dao.delete(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		assertEquals(listeActions.size(), 0);
	}
	
	@Test
	@Transactional
	final void findActionsByIntervalDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  5,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", null, null);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2020,  6,  18);
		Date date2 = cal2.getTime();
		Action action2 = new Action(date2, "testAction2", null, null);
		dao.save(action);
		dao.save(action2);
		Calendar cal4 = Calendar.getInstance();
		cal4.set(2020,  5,  27);
		Date date4 = cal4.getTime();
		
		List<Action> listeActions = (List<Action>) dao.findActionsByIntervalDate(date, date4);
		assertEquals(listeActions.size(), 1);
	}
	
	@Test
	@Transactional
	final void findActionsByPotagerByIntervalDate() {
		Potager potager =  new Potager("test", "test", 2, "test");
		daoPot.save(potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  5,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, null);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2020,  5,  18);
		Date date2 = cal2.getTime();
		Action action2 = new Action(date2, "testAction2", potager, null);
		dao.save(action);
		dao.save(action2);
		Calendar cal4 = Calendar.getInstance();
		cal4.set(2020,  5,  27);
		Date date4 = cal4.getTime();
		List<Action> listeActions = (List<Action>) dao.findAll();
		List<Action> listeActionsPot = (List<Action>) dao.findActionsByPotagerByIntervalDate(listeActions.get(0).getPotager().getIdPotager(), date, date4 );
		assertEquals(listeActionsPot.size(), 2);
	}
 
	
	@Test
	@Transactional
	final void findActionsByPotagerByCarreByIntervalDate() {
		Potager potager =  new Potager("test", "test", 2, "test");
		daoPot.save(potager);
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		daoCarre.save(carre);
		Calendar cal = Calendar.getInstance();
		cal.set(2020,  5,  11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2020,  5,  18);
		Date date2 = cal2.getTime();
		Action action2 = new Action(date2, "testAction2", potager, carre);
		dao.save(action);
		dao.save(action2);
		Calendar cal4 = Calendar.getInstance();
		cal4.set(2020,  5,  27);
		Date date4 = cal4.getTime();
		List<Action> listeActions = (List<Action>) dao.findAll();
		List<Action> listeActionsPot = (List<Action>) dao.findActionsByPotagerByCarreByIntervalDate(listeActions.get(0).getCarre().getIdCarre(), listeActions.get(0).getPotager().getIdPotager(), date, date4 );
		assertEquals(listeActionsPot.size(), 2);
	}
	
}
