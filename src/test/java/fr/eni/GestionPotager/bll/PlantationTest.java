package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.bo.TypePlante;
import fr.eni.GestionPotager.dal.PotagerDao;

@SpringBootTest
class PlantationTest {

	@Autowired
	private CarreManager carremgr;

	// pour test carré, sinon erreur de transient
	@Autowired
	private PotagerDao daoPotager;

	@Autowired
	private PlanteManager planteMgr;

	@Test
	@Transactional
	void testAjouterPlantationAuCarre() throws BllException {
		System.out.println("__________testAjouterPlantationAuCarre()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		Plante plante = new Plante("PlanteTest", TypePlante.FEUILLE, "var", 5);
		planteMgr.createPlante(plante);

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);

		carremgr.ajouterPlantationAuCarre(carreTest, plante, 2, LocalDate.now(), LocalDate.now());

		System.err.println(carreTest.getListePlantations());

		System.err.println(carremgr.findById(carreTest.getIdCarre()));

		assertEquals(carremgr.findById(carreTest.getIdCarre()).getListePlantations().size(), 1);

	}

	@Test
	@Transactional
	void testAjouterPlantationAuCarreContrainte() throws BllException {
		System.out.println("__________testAjouterPlantationAuCarreContrainte()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		Plante plante = new Plante("PlanteTest", TypePlante.FEUILLE, "var", 5);
		planteMgr.createPlante(plante);

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);

		Exception exception = assertThrows(BllException.class, () -> {

			carremgr.ajouterPlantationAuCarre(carreTest, plante, 5, LocalDate.now(), LocalDate.now());
		});

		String expectedMessage = "Pas assez de place dans le carré";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		System.err.println(carreTest.getListePlantations());

		System.err.println(carremgr.findById(carreTest.getIdCarre()));
		
		System.out.println("Message recu : " + actualMessage);

	}

}