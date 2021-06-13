package fr.eni.GestionPotager.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.TypePlante;

@SpringBootTest
class PlanteDaoTest {

	@Autowired
	PlanteDao dao;

	@Test
	@Transactional
	final void findOnePlanteOneVariete() {
		Plante plante1 = new Plante("TESTNOM", TypePlante.FRUIT, "TESTVAR", 0.75);
		dao.save(plante1);
		assertNotNull(dao.findOnePlanteOneVariete("TESTNOM", "TESTVAR"));
	}

	@Test
	@Transactional
	final void testSave() {
		List<Plante> listePlantesBefore = (List<Plante>) dao.findAll();
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", 0.75);
		dao.save(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		assertEquals(listePlantesBefore.size() + 1, listePlantes.size());
	}

	@Test
	@Transactional
	final void testFindById() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", 0.75);
		dao.save(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		Plante planteTest = dao.findById(listePlantes.get(listePlantes.size() - 1).getIdPlante()).orElse(null);
		assertEquals(planteTest.getNom(), plante1.getNom());

	}

	@Test
	@Transactional
	final void testFindAll() {
		List<Plante> listePlantesBefore = (List<Plante>) dao.findAll();
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", 0.75);
		dao.save(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		assertEquals(listePlantesBefore.size() + 1, listePlantes.size());
	}

	@Test
	@Transactional
	final void testDeleteById() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", 0.75);
		dao.save(plante1);
		List<Plante> listePlantesBefore = (List<Plante>) dao.findAll();
		dao.deleteById(listePlantesBefore.get(listePlantesBefore.size() - 1).getIdPlante());
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		assertEquals(listePlantesBefore.size() - 1, listePlantes.size());
	}

	@Test
	@Transactional
	final void testDelete() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", 0.75);
		dao.save(plante1);
		List<Plante> listePlantesBefore = (List<Plante>) dao.findAll();
		dao.delete(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		assertEquals(listePlantesBefore.size() - 1, listePlantes.size());
	}

}
