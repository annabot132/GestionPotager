package fr.eni.GestionPotager.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Potager;

@SpringBootTest
class PotagerDaoTest {

	@Autowired
	PotagerDao dao;

	@Test
	@Transactional
	final void testSave() {
		Potager potager1 = new Potager("Au fond du jardin", "mon super potager", 5, "Quimper");
		dao.save(potager1);
		List<Potager> listePotagers = (List<Potager>) dao.findAll();

		assertEquals(listePotagers.size(), 1);
	}

	@Test
	@Transactional
	final void testFindById() {
		Potager potager1 = new Potager("Au fond du jardin", "mon super potager", 5, "Quimper");
		dao.save(potager1);
		List<Potager> listePotagers = (List<Potager>) dao.findAll();

		Potager potagerTest = dao.findById(listePotagers.get(0).getIdPotager()).orElse(null);
		assertEquals(potagerTest.getNom(), potager1.getNom());
	}

	@Test
	@Transactional
	final void testFindAll() {
		Potager potager1 = new Potager("Au fond du jardin", "mon super potager", 5, "Quimper");
		Potager potager2 = new Potager("Devant la maison", "mon super potager en carré", 2, "Quimper");

		dao.save(potager1);
		dao.save(potager2);
		List<Potager> listePotagers = (List<Potager>) dao.findAll();

		assertEquals(listePotagers.size(), 2);
	}

	@Test
	@Transactional
	final void testDeleteById() {
		Potager potager1 = new Potager("Au fond du jardin", "mon super potager", 5, "Quimper");
		dao.save(potager1);
		List<Potager> listePotagers = (List<Potager>) dao.findAll();
		dao.deleteById(listePotagers.get(0).getIdPotager());
		List<Potager> listePotagers2 = (List<Potager>) dao.findAll();

		assertEquals(listePotagers2.size(), 0);

	}

	@Test
	@Transactional
	final void testDelete() {
		Potager potager1 = new Potager("Au fond du jardin", "mon super potager", 5, "Quimper");
		dao.save(potager1);
		dao.delete(potager1);
		List<Potager> listePotagers = (List<Potager>) dao.findAll();

		assertEquals(listePotagers.size(), 0);
	}

}
