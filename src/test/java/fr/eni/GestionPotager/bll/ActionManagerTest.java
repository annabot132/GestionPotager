package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
	PotagerManager pManager;

	@Autowired
	CarreManager cManager;

	@Autowired
	ActionManager aManager;

	@Test
	@Transactional
	void testAjoutAction() throws BllException {
		System.out.println("__________testAjoutContraintes()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		pManager.addPotager(potagerTest);
		cManager.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021,  06,  11);
		Date date = cal.getTime();
		
		Action action = new Action(date, "event", potagerTest, carreTest);
		aManager.addAction(action);

		assertEquals(aManager.findAllAction().size(), 1);
	}

	@Test
	@Transactional
	void testAjoutActionContraintes() throws BllException {
		System.out.println("__________testAjoutContraintes()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		pManager.addPotager(potagerTest);
		cManager.createCarre(carreTest);

		Calendar cal = Calendar.getInstance();
		cal.set(2021,  04,  11);
		Date date = cal.getTime();
		
		Action action = new Action(date, "event", potagerTest, carreTest);

		Exception exception = assertThrows(BllException.class, () -> {

			aManager.addAction(action);
		});

		String expectedMessage = "La date entrée doit être posterieur à la date du jour.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	@Transactional
	void testFindAllActionFor2Weeks() throws BllException {
		System.out.println("__________ ___________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		pManager.addPotager(potagerTest);
		cManager.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021,  05,  17);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		cal.set(2021,  06,  11);
		Date date2 = cal.getTime();
		Action action2 = new Action(date2, "event", potagerTest, carreTest);
		aManager.addAction(action);
		aManager.addAction(action2);
		System.err.println(aManager.findAllAction());
		System.err.println(aManager.findAllActionFor2Weeks());
		assertEquals(aManager.findAllActionFor2Weeks().size(), 1);
	}
	
//	@Test
//	@Transactional
	void testFindAllActionByPotagerByFor2Weeks() throws BllException {
		System.out.println("__________ ___________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		pManager.addPotager(potagerTest);
		cManager.createCarre(carreTest);
		Calendar cal = Calendar.getInstance();
		cal.set(2021,  05,  17);
		Date date = cal.getTime();
		Action action = new Action(date, "event", potagerTest, carreTest);
		cal.set(2021,  06,  11);
		Date date2 = cal.getTime();
		Action action2 = new Action(date2, "event", potagerTest, carreTest);
		aManager.addAction(action);
		

		assertEquals(aManager.findAllAction().size(), 1);
	}
	

}
