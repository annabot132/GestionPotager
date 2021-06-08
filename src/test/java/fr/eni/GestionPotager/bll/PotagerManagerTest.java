package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Potager;

@SpringBootTest
class PotagerManagerTest {

	@Autowired
	PotagerManager manager;

	@Test
	@Transactional
	void testAddPotager() {
		System.out.println("_________testAddPotager()_______");
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);

		assertNotNull(manager.getAllPotager().size());

	}

	@Test
	@Transactional
	void testRemovePotager() {
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);

		manager.removePotager(potager1);
		assertEquals(manager.getAllPotager().size(), 0);
	}

	@Test
	@Transactional
	void testGetAllPotager() {

		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		Potager potager2 = new Potager("la", "le grand potager", 15, "Painpont");
		manager.addPotager(potager1);
		manager.addPotager(potager2);
		List<Potager> listeP = manager.getAllPotager();
		System.err.println(listeP);

		assertEquals(manager.getAllPotager().size(), 2);

	}

	@Transactional
	@Test
	void testGetPotagerById() {
		System.out.println("_______________testGetPotagerById()______________");
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);

		List<Potager> listePotagerTest = (List<Potager>) manager.getAllPotager();
		System.err.println(listePotagerTest);
		Potager potagerTest2 = manager.getPotagerById(listePotagerTest.get(0).getIdPotager());

		assertEquals(potager1.getLocalisation(), potagerTest2.getLocalisation());

	}

	@Transactional
	@Test
	void testRemovePotagerById() {
		System.out.println("_______________testRemovePotagerById()______________");
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);

		List<Potager> listePotagerTest = (List<Potager>) manager.getAllPotager();
		System.err.println(listePotagerTest);
		Potager potagerTest2 = manager.getPotagerById(listePotagerTest.get(0).getIdPotager());
		
		manager.removePotagerById(potagerTest2.getIdPotager());
		
		assertEquals(manager.getAllPotager().size(), 0);
		
	}

}
