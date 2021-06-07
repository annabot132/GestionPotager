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
	final void testCreatePlante() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		planteMg.createPlante(plante1);
		assertEquals(planteMg.findAll().size(), 1);
	}

	@Test
	@Transactional
	final void testFindAll() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		planteMg.createPlante(plante1);
		assertEquals(planteMg.findAll().size(), 1);
	}

	@Test
	@Transactional
	final void testFindPlanteById() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		planteMg.createPlante(plante1);
		List<Plante> listePlantes = planteMg.findAll();
		Plante planteTest = planteMg.findPlanteById(listePlantes.get(0).getIdPlante());
		assertEquals(planteTest.getNom(), plante1.getNom());
	}

	@Test
	@Transactional
	final void testUpdatePlante() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		planteMg.createPlante(plante1);
		plante1.setVariete("Green");
		planteMg.updatePlante(plante1);
		assertEquals(planteMg.findAll().get(0).getVariete(), "Green");
	}

	@Test
	@Transactional
	final void testDeletePlante() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		planteMg.createPlante(plante1);
		planteMg.deletePlante(plante1);
		assertEquals(planteMg.findAll().size(), 0);

	}

	@Test
	@Transactional
	final void testDeletePlanteById() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		planteMg.createPlante(plante1);
		List<Plante> listePlantes = planteMg.findAll();
		planteMg.deletePlanteById(listePlantes.get(0).getIdPlante());
		assertEquals(planteMg.findAll().size(), 0);
	}

}
