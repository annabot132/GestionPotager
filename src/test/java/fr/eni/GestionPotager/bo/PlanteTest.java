package fr.eni.GestionPotager.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlanteTest {

	@Test
	void testPlanteStringTypePlanteStringDouble() {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", 0.75);
		assertEquals(String.class, plante1.getNom().getClass());
		assertEquals(String.class, plante1.getVariete().getClass());
		assertEquals(Double.class,((Object)plante1.getSurfaceOccupee()).getClass());
		assertEquals(TypePlante.class, plante1.getTypePlante().getClass());

	}

	@Test
	void testGetIdPlante() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		plante1.setIdPlante(1);
		assertEquals(1, plante1.getIdPlante());
	}

	@Test
	void testGetNom() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		assertEquals("TEST", plante1.getNom());	}

	@Test
	void testGetTypePlante() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		assertEquals(TypePlante.FRUIT, plante1.getTypePlante());	}

	@Test
	void testGetVariete() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		assertEquals("TEST2", plante1.getVariete());	}

	@Test
	void testGetSurfaceOccupee() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		assertEquals(0.75, plante1.getSurfaceOccupee());	}

	@Test
	void testSetIdPlante() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		plante1.setIdPlante(1);
		plante1.setIdPlante(2);
		assertEquals(2, plante1.getIdPlante());	}

	@Test
	void testSetNom() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		plante1.setNom("TEST2");
		assertEquals("TEST2", plante1.getNom());
	}

	@Test
	void testSetTypePlante() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		plante1.setTypePlante(TypePlante.RACINE);
		assertEquals(TypePlante.RACINE, plante1.getTypePlante());
	}

	@Test
	void testSetVariete() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		plante1.setVariete("TEST2");
		assertEquals("TEST2", plante1.getVariete());
	}

	@Test
	void testSetSurfaceOccupee() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		plante1.setSurfaceOccupee((double) 2);
		assertEquals(2, plante1.getSurfaceOccupee());
	}

	@Test
	void testEqualsObject() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		assertEquals(Plante.class, plante1.getClass());
	}

	@Test
	void testCanEqual() {
		Plante plante2 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		assertEquals(plante2, plante1);
	}

	@Test
	void testPlante() {
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST2", 0.75);
		assertEquals(plante1, plante1);
	}

}
