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
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		dao.save(plante1);
		assertNotNull(dao.findOnePlanteOneVariete("tomate", "cerise"));
	}
	
	

	@Test
	@Transactional
	final void testSave() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		dao.save(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		assertEquals(listePlantes.size(), 1);
	}

	@Test
	@Transactional
	final void testFindById() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		dao.save(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		Plante planteTest = dao.findById(listePlantes.get(0).getIdPlante()).orElse(null);
		assertEquals(planteTest.getNom(), plante1.getNom());

	}

	@Test
	@Transactional
	final void testFindAll() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		dao.save(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		assertEquals(listePlantes.size(), 1);
	}

	@Test
	@Transactional
	final void testDeleteById() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		dao.save(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		dao.deleteById(listePlantes.get(0).getIdPlante());
		List<Plante> listePlantes2 = (List<Plante>) dao.findAll();
		assertEquals(listePlantes2.size(), 0);
	}

	@Test
	@Transactional
	final void testDelete() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		dao.save(plante1);
		dao.delete(plante1);
		List<Plante> listePlantes = (List<Plante>) dao.findAll();
		assertEquals(listePlantes.size(), 0);
	}

}
