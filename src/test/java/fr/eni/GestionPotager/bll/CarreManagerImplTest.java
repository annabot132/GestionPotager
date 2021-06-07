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

	// pour test carré, sinon erreur de transient
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

		System.err.println(carremgr.findAll().size());
		assertEquals(carremgr.findAll().size(), 1);
	}

	@Test
	@Transactional
	void testDeleteCarre() {
		System.out.println("__________testDeleteCarre()____________");
		Potager potagerTest = new Potager("Au fond du jardin", "Super potager", 5, "Quimper");
		Carre carreTest = new Carre(20, "Argileux", Exposition.MI_OMBRE, potagerTest);

		daoPotager.save(potagerTest);
		carremgr.createCarre(carreTest);

		System.err.println(carreTest.getIdCarre());
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

		System.err.println(carreTest.getSol());

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

}
