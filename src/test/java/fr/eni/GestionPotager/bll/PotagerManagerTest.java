package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

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
		Potager potager1 = new Potager("ici", "le petit potager", 9, "Painpont");
		manager.addPotager(potager1);
		System.err.println(potager1);
		assertNotNull(manager.getPotagerById(potager1.getIdPotager()));
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
		
	}
/*
	@Test
	void testGetPotagerById() {
		fail("Not yet implemented");
	}

	@Test
	void testRemovePotagerById() {
		fail("Not yet implemented");
	}
*/
}
