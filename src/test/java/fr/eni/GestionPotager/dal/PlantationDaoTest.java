package fr.eni.GestionPotager.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Plantation;

@SpringBootTest
class PlantationDaoTest {

	@Autowired
	PlantationDao dao;

	@Test
	@Transactional
	final void testSave() {
		Plantation plantation1 = new Plantation(LocalDate.now(), LocalDate.now().plusWeeks(6), 1, null, null);
		dao.save(plantation1);
		List<Plantation> listePlantatuions = (List<Plantation>) dao.findAll();
		assertEquals(listePlantatuions.size(), 1);

	}

	@Test
	@Transactional
	final void testFindById() {
		Plantation plantation1 = new Plantation(LocalDate.now(), LocalDate.now().plusWeeks(6), 1, null, null);
		dao.save(plantation1);
		List<Plantation> listePlantations = (List<Plantation>) dao.findAll();
		Plantation plantationTest = dao.findById(listePlantations.get(0).getIdPlantation()).orElse(null);
		assertEquals(plantationTest.getMiseEnPlace(), plantation1.getMiseEnPlace());
	}

	@Test
	@Transactional
	final void testFindAll() {
		Plantation plantation1 = new Plantation(LocalDate.now(), LocalDate.now().plusWeeks(6), 1, null, null);
		Plantation plantation2 = new Plantation(LocalDate.now(), LocalDate.now().plusWeeks(8), 2, null, null);
		dao.save(plantation1);
		dao.save(plantation2);
		List<Plantation> listePlantatuions = (List<Plantation>) dao.findAll();
		assertEquals(listePlantatuions.size(), 2);

	}

	@Test
	@Transactional
	final void testDeleteById() {
		Plantation plantation1 = new Plantation(LocalDate.now(), LocalDate.now().plusWeeks(6), 1, null, null);
		dao.save(plantation1);
		List<Plantation> listePlantations = (List<Plantation>) dao.findAll();
		dao.deleteById(listePlantations.get(0).getIdPlantation());
		List<Plantation> listePlantations2 = (List<Plantation>) dao.findAll();
		assertEquals(listePlantations2.size(), 0);
	}

	@Test
	@Transactional
	final void testDelete() {
		Plantation plantation1 = new Plantation(LocalDate.now(), LocalDate.now().plusWeeks(6), 1, null, null);
		dao.save(plantation1);
		dao.delete(plantation1);
		List<Plantation> listePlantations = (List<Plantation>) dao.findAll();

		assertEquals(listePlantations.size(), 0);
	}

}
