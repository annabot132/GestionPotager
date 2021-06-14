package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.dal.PotagerDao;

@SpringBootTest
class CarreManagerImplTest {

	@Autowired
	private CarreManager carremgr;

	@Autowired
	private PotagerDao daoPotager;

	@Test
	@Transactional
	void testCreateCarre() {
		System.out.println("__________testCreateCarre()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);

	}

	@Test
	@Transactional
	void testDeleteCarre() {
		System.out.println("__________testDeleteCarre()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);

		carremgr.deleteCarre(carreTest.getIdCarre());

		assertEquals(carremgr.findAll().size(), 0);

	}

	@Test
	@Transactional
	void testUpdateCarre() {
		System.out.println("__________testUpdateCarre()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);
		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);

		carreTest.setSol("Terreux");

		List<Carre> listeCarreTest = (List<Carre>) carremgr.findAll();
		Carre carreTest2 = carremgr.findById(listeCarreTest.get(0).getIdCarre());

		carremgr.updateCarre(carreTest);

		assertEquals(carreTest2.getSol(), carreTest.getSol());

	}

	@Test
	@Transactional
	void testFindById() {
		System.out.println("__________testFindById()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);

		List<Carre> listeCarreTest = (List<Carre>) carremgr.findAll();
		Carre carreTest2 = carremgr.findById(listeCarreTest.get(0).getIdCarre());

		assertEquals(carreTest.getSol(), carreTest2.getSol());

	}

	@Test
	@Transactional
	void testFindAll() {
		System.out.println("__________testFindAll()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);
		Carre carreTest2 = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);
		Carre carreTest3 = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);
		carremgr.createCarre(carreTest2);
		carremgr.createCarre(carreTest3);

		assertEquals(carremgr.findAll().size(), 3);

	}

	@Test
	@Transactional
	void testajouterCarrePotagerExiste() throws BllException {
		System.err.println("__________testajouterCarrePotager() avec Potager EXISTANT____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 20, "Quimper");
		daoPotager.save(potagerTest);

		Carre carreTest = new Carre(15, "Argileux", Exposition.MI_OMBRE, null);

		carremgr.ajouterCarrePotager(potagerTest, carreTest);

	}

	@Test
	@Transactional
	void testajouterCarrePotagerExistePas() throws BllException {
		System.out.println("__________testajouterCarrePotager()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 20, "Quimper");
		Carre carreTest = new Carre(15, "Argileux", Exposition.MI_OMBRE, null);

		carremgr.ajouterCarrePotager(potagerTest, carreTest);

	}

	@Test
	@Transactional
	void testajouterCarrePotagerContrainteSurface() throws BllException {
		System.out.println("__________testajouterCarrePotager() TEST CONTRAINTE TAILLE POLTAGER___________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 20, "Quimper");
		daoPotager.save(potagerTest);
		Carre carreTest = new Carre(21, "Argileux", Exposition.MI_OMBRE, null);

		Exception exception = assertThrows(BllException.class, () -> {
			carremgr.ajouterCarrePotager(potagerTest, carreTest);
		});

		String expectedMessage = "Il n'y a plus de place dans le potager!! il vous reste: "
				+ (potagerTest.getSurface() - carreTest.getSurface()) + "  m²";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		System.err.println("Liste carré : " + carremgr.findAll());
	}

	@Test
	@Transactional
	void testfindCarreByPotager() {
		System.out.println("__________XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXR___________");
		Potager potager = new Potager("STR", "STR", 25, "STR");
		daoPotager.save(potager);
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		Carre carre2 = new Carre(10, "argileux", Exposition.SOLEIL, potager);
		carremgr.createCarre(carre2);
		carremgr.createCarre(carre1);
		List<Potager> listePotager = (List<Potager>) daoPotager.findAll();


		assertEquals(2, carremgr.findCarreByPotager(listePotager.get(listePotager.size()-1).getIdPotager()));
	}
}
