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
	@Test
	@Transactional
	void testAjouterPlantationAuCarreContrainte3Plantes() throws BllException {
		System.out.println("__________testAjouterPlantationAuCarreContrainte3Plantes()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(30, "Argileux", Exposition.MI_OMBRE, potagerTest);

		Plante plante1 = new Plante("PlanteTest", TypePlante.FEUILLE, "var", 5);
		Plante plante2 = new Plante("PlanteTest2", TypePlante.FRUIT, "var",6);
		Plante plante3 = new Plante("PlanteTest3", TypePlante.FEUILLE, "var", 2);
		Plante plante4 = new Plante("PlanteTest4", TypePlante.FEUILLE, "var", 2);
		
		planteMgr.createPlante(plante1);
		planteMgr.createPlante(plante2);
		planteMgr.createPlante(plante3);
		planteMgr.createPlante(plante4);
		

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);
		
		carremgr.ajouterPlantationAuCarre(carreTest, plante1, 1, LocalDate.now(), LocalDate.now());
		carremgr.ajouterPlantationAuCarre(carreTest, plante2, 1, LocalDate.now(), LocalDate.now());
		//carremgr.ajouterPlantationAuCarre(carreTest, plante2, 1, LocalDate.now(), LocalDate.now());
		carremgr.ajouterPlantationAuCarre(carreTest, plante3, 1, LocalDate.now(), LocalDate.now());
		
		
		

		Exception exception = assertThrows(BllException.class, () -> {

			carremgr.ajouterPlantationAuCarre(carreTest, plante4, 1, LocalDate.now(), LocalDate.now());
		});

		String expectedMessage = "Il y a déjà 3 plantes dans votre carré";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));



	}
	
	

	@Test
	@Transactional
	void testFindAllImplantationsForOnePlante() throws BllException  {
		System.out.println("_____________________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(30, "Argileux", Exposition.MI_OMBRE, potagerTest);

		Plante plante1 = new Plante("PlanteTest", TypePlante.FEUILLE, "var", 5);
		Plante plante2 = new Plante("PlanteTest2", TypePlante.FRUIT, "var",6);
		Plante plante3 = new Plante("PlanteTest3", TypePlante.FEUILLE, "var", 2);
		Plante plante4 = new Plante("PlanteTest4", TypePlante.FEUILLE, "var", 2);
		
		planteMgr.createPlante(plante1);
		
		

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);
		
		assertEquals(carremgr.findAllImplantationsForOnePlante(planteMgr.findAll().get(0).getIdPlante()).size(),1);

	}

}