package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.TypePlante;

@SpringBootTest
class PlanteManagerImplTest {

	@Autowired
	PlanteManager planteMg;

	@Test
	@Transactional
	final void testCreatePlante() throws BllException {
		List<Plante> listePlantesBefore = planteMg.findAll();
		Plante plante1 = new Plante("TEST01", TypePlante.FRUIT, "TEST01", 0.75);
		planteMg.createPlante(plante1);
		assertEquals(listePlantesBefore.size() + 1, planteMg.findAll().size());
	}

	@Test
	@Transactional
	final void testCreatePlanteAvecContrainteExiste() throws BllException {
		Plante plante1 = new Plante("TEST01", TypePlante.FRUIT, "TEST01", 0.75);
		planteMg.createPlante(plante1);
		Exception exception = assertThrows(BllException.class, () -> {
			planteMg.createPlante(plante1);
		});
		String expectedMessage = "La plante existe déjà !";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@Transactional
	final void testFindAll() throws BllException {
		List<Plante> listePlantesBefore = planteMg.findAll();
		Plante plante1 = new Plante("TEST01", TypePlante.FRUIT, "TEST01", 0.75);
		planteMg.createPlante(plante1);
		assertEquals(listePlantesBefore.size() + 1, planteMg.findAll().size());
	}

	@Test
	@Transactional
	final void testFindPlanteById() throws BllException {
		Plante plante1 = new Plante("TEST01", TypePlante.FRUIT, "TEST01", 0.75);
		planteMg.createPlante(plante1);
		List<Plante> listePlantes = planteMg.findAll();
		Plante planteTest = planteMg.findPlanteById(listePlantes.get(listePlantes.size()-1).getIdPlante());
		assertEquals(planteTest.getNom(), plante1.getNom());
	}

	@Test
	@Transactional
	final void testUpdatePlante() throws BllException {
		Plante plante1 = new Plante("TEST01", TypePlante.FRUIT, "TEST01", 0.75);
		planteMg.createPlante(plante1);
		plante1.setVariete("Green");
		planteMg.updatePlante(plante1);
		assertEquals("Green",planteMg.findAll().get(planteMg.findAll().size()-1).getVariete());
	}

	@Test
	@Transactional
	final void testDeletePlante() throws BllException {
		Plante plante1 = new Plante("TEST01", TypePlante.FRUIT, "TEST01", 0.75);
		planteMg.createPlante(plante1);
		List<Plante> listePlantes = planteMg.findAll();
		planteMg.deletePlante(plante1);
		assertEquals(listePlantes.size() - 1, planteMg.findAll().size());

	}

	@Test
	@Transactional
	final void testDeletePlanteById() throws BllException {
		Plante plante1 = new Plante("TEST01", TypePlante.FRUIT, "TEST01", 0.75);
		planteMg.createPlante(plante1);
		List<Plante> listePlantes = planteMg.findAll();
		planteMg.deletePlanteById(listePlantes.get(listePlantes.size() - 1).getIdPlante());
		assertEquals(listePlantes.size() - 1, planteMg.findAll().size());
	}

}
