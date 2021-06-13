package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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
class ActionManagerTest {

	@Autowired
	PotagerManager potagerMg;

	@Autowired
	CarreManager carreMg;

	@Autowired
	ActionManager actionMg;

	@Test
	@Transactional
	void testAjoutAction() throws BllException {
		List<Action> listeActionsBefore = actionMg.findAllAction();
		Potager potagerTest = new Potager("TEST01", "TEST01", 5, "TEST01");
		Carre carreTest = new Carre(20, "XX", Exposition.MI_OMBRE, potagerTest);
		potagerMg.addPotager(potagerTest);
		carreMg.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 06, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		actionMg.addAction(action);
		assertEquals(listeActionsBefore.size() + 1, actionMg.findAllAction().size());
	}

	@Test
	@Transactional
	void testAjoutActionContraintes() throws BllException {
		Potager potagerTest = new Potager("TEST01", "TEST01", 5, "TEST01");
		Carre carreTest = new Carre(20, "XX", Exposition.MI_OMBRE, potagerTest);
		potagerMg.addPotager(potagerTest);
		carreMg.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 04, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		Exception exception = assertThrows(BllException.class, () -> {
			actionMg.addAction(action);
		});
		String expectedMessage = "La date entrée doit être posterieur à la date du jour !";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@Transactional
	void testFindAllAction() throws BllException {
		List<Action> listeActionsBefore = actionMg.findAllAction();
		Potager potagerTest = new Potager("TEST01", "TEST01", 5, "TEST01");
		Carre carreTest = new Carre(20, "XX", Exposition.MI_OMBRE, potagerTest);
		potagerMg.addPotager(potagerTest);
		carreMg.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 06, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		actionMg.addAction(action);
		assertEquals(listeActionsBefore.size() + 1, actionMg.findAllAction().size());
	}

	@Test
	@Transactional
	void testDeleteAction() throws BllException {
		Potager potagerTest = new Potager("TEST01", "TEST01", 5, "TEST01");
		Carre carreTest = new Carre(20, "XX", Exposition.MI_OMBRE, potagerTest);
		potagerMg.addPotager(potagerTest);
		carreMg.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 06, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		actionMg.addAction(action);
		List<Action> listeActionsBefore = actionMg.findAllAction();
		actionMg.deleteAction(action);
		assertEquals(listeActionsBefore.size() - 1, actionMg.findAllAction().size());
	}

	@Test
	@Transactional
	void testUpdateAction() throws BllException {
		List<Action> listeActionsBefore = actionMg.findAllAction();
		Potager potagerTest = new Potager("TEST01", "TEST01", 5, "TEST01");
		Carre carreTest = new Carre(20, "XX", Exposition.MI_OMBRE, potagerTest);
		potagerMg.addPotager(potagerTest);
		carreMg.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 06, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		actionMg.addAction(action);
		action.setEvenement("TESTEVENT");
		actionMg.updateAction(action);
		assertEquals(action.getEvenement(),
				actionMg.findAllAction().get(actionMg.findAllAction().size() - 1).getEvenement());

	}

	@Test
	@Transactional
	void testFindActionById() throws BllException {
		Potager potagerTest = new Potager("TEST01", "TEST01", 5, "TEST01");
		Carre carreTest = new Carre(20, "XX", Exposition.MI_OMBRE, potagerTest);
		potagerMg.addPotager(potagerTest);
		carreMg.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 06, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		actionMg.addAction(action);
		assertEquals(action.getEvenement(), actionMg
				.findActionById(actionMg.findAllAction().get(actionMg.findAllAction().size() - 1).getIdAction()).getEvenement());
	}

	@Test
	@Transactional
	void testDeleteActionById() throws BllException {
		Potager potagerTest = new Potager("TEST01", "TEST01", 5, "TEST01");
		Carre carreTest = new Carre(20, "XX", Exposition.MI_OMBRE, potagerTest);
		potagerMg.addPotager(potagerTest);
		carreMg.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 06, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		actionMg.addAction(action);
		List<Action> listeActionsBefore = actionMg.findAllAction();
		actionMg.deleteActionById(listeActionsBefore.get(listeActionsBefore.size() - 1).getIdAction());
		assertEquals(listeActionsBefore.size() - 1, actionMg.findAllAction().size());
	}

	@Test
	@Transactional
	void testFindAllActionFor2Weeks() throws BllException {
		List<Action> listeActionsBefore = actionMg.findAllActionFor2Weeks();
		LocalDate dateld = LocalDate.now().plusDays(4);
		LocalDate dateld2 = LocalDate.now().plusDays(10);
		Date date = actionMg.localDateToDate(dateld);
		Date date2 = actionMg.localDateToDate(dateld2);
		Action action = new Action(date, "testAction2", null, null);
		Action action2 = new Action(date2, "testAction2", null, null);
		actionMg.addAction(action);
		actionMg.addAction(action2);
		assertEquals(listeActionsBefore.size() + 2, actionMg.findAllActionFor2Weeks().size());
	}

	@Test
	@Transactional
	void testFindAllActionByPotagerByFor2Weeks() throws BllException {
		Potager potagerTest = new Potager("TESTFINDBYPOTAGER", "TESTFINDBYPOTAGER", 5, "TESTFINDBYPOTAGER");
		potagerMg.addPotager(potagerTest);
		LocalDate dateld = LocalDate.now().plusDays(4);
		LocalDate dateld2 = LocalDate.now().plusDays(10);
		Date date = actionMg.localDateToDate(dateld);
		Date date2 = actionMg.localDateToDate(dateld2);
		Action action = new Action(date, "testAction2", potagerTest, null);
		Action action2 = new Action(date2, "testAction2", potagerTest, null);
		actionMg.addAction(action);
		actionMg.addAction(action2);
		assertEquals(2, actionMg.findAllActionByPotagerFor2Weeks(actionMg.findAllActionFor2Weeks()
				.get(actionMg.findAllActionFor2Weeks().size() - 1).getPotager().getIdPotager()).size());
	}

	@Test
	@Transactional
	void testFindAllActionByPotagerByCarreByFor2Weeks() throws BllException {
		Potager potagerTest = new Potager("TESTFINDBYPOTAGER", "TESTFINDBYPOTAGER", 5, "TESTFINDBYPOTAGER");
		potagerMg.addPotager(potagerTest);
		Carre carre = new Carre(2, "Test", Exposition.MI_OMBRE, potagerTest);
		carreMg.createCarre(carre);
		LocalDate dateld = LocalDate.now().plusDays(4);
		LocalDate dateld2 = LocalDate.now().plusDays(10);
		Date date = actionMg.localDateToDate(dateld);
		Date date2 = actionMg.localDateToDate(dateld2);
		Action action = new Action(date, "testAction2", potagerTest, carre);
		Action action2 = new Action(date2, "testAction2", potagerTest, carre);
		actionMg.addAction(action);
		actionMg.addAction(action2);
		assertEquals(2,
				actionMg.findAllActionByPotagerByCarreFor2Weeks(
						actionMg.findAllActionFor2Weeks().get(actionMg.findAllActionFor2Weeks().size() - 1).getCarre()
								.getPotager().getIdPotager(),
						actionMg.findAllActionFor2Weeks().get(actionMg.findAllActionFor2Weeks().size() - 1).getCarre()
								.getIdCarre())
						.size());
	}

}
