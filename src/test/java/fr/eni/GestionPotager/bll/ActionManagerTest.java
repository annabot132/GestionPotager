package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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

		Action action = new Action(LocalDate.of(2021, 7, 1), "event", potagerTest, carreTest);
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

		Action action = new Action(LocalDate.of(2021, 5, 1), "event", potagerTest, carreTest);

		Exception exception = assertThrows(BllException.class, () -> {

			aManager.addAction(action);
		});

		String expectedMessage = "La date entrée doit être posterieur à la date du jour.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
