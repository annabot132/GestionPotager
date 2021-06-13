package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Potager;

@SpringBootTest
class PotagerManagerTest {

	@Autowired
	PotagerManager manager;

	@Test
	@Transactional
	void testAddPotager() {
		List<Potager> listePotagersBefore = manager.getAllPotager();
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);
		assertEquals(listePotagersBefore.size() + 1, manager.getAllPotager().size());
	}

	@Test
	@Transactional
	void testRemovePotager() {
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);
		List<Potager> listePotagersBefore = manager.getAllPotager();
		manager.removePotager(potager1);
		assertEquals(listePotagersBefore.size() - 1, manager.getAllPotager().size());
	}

	@Test
	@Transactional
	void testGetAllPotager() {
		List<Potager> listePotagersBefore = manager.getAllPotager();
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		Potager potager2 = new Potager("la", "le grand potager", 15, "Painpont");
		manager.addPotager(potager1);
		manager.addPotager(potager2);
		List<Potager> listeP = manager.getAllPotager();
		System.err.println(listeP);
		assertEquals(listePotagersBefore.size() + 2, manager.getAllPotager().size());

	}

	@Transactional
	@Test
	void testGetPotagerById() {
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);
		List<Potager> listePotagerTest = (List<Potager>) manager.getAllPotager();
		Potager potagerTest2 = manager.getPotagerById(listePotagerTest.get(listePotagerTest.size() - 1).getIdPotager());
		assertEquals(potager1.getLocalisation(), potagerTest2.getLocalisation());
	}

	@Transactional
	@Test
	void testRemovePotagerById() {
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);
		List<Potager> listePotagerTest = (List<Potager>) manager.getAllPotager();
		Potager potagerTest2 = manager.getPotagerById(listePotagerTest.get(listePotagerTest.size() - 1).getIdPotager());
		manager.removePotagerById(potagerTest2.getIdPotager());
		assertEquals(listePotagerTest.size() - 1, manager.getAllPotager().size());
	}

	@Transactional
	@Test
	void testUpdatePotager() {
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);
		potager1.setLocalisation("c'est par là");
		manager.updatePotager(potager1);
		List<Potager> listePotagerTest = (List<Potager>) manager.getAllPotager();
		Potager potagerTest2 = manager.getPotagerById(listePotagerTest.get(listePotagerTest.size() - 1).getIdPotager());
		assertEquals("c'est par là", potagerTest2.getLocalisation());
	}

}
