package fr.eni.GestionPotager.bo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CarreTest {

	@Test
	void testCarreDoubleStringExpositionPotager() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals(Potager.class, potager.getClass());
		assertEquals(Exposition.class, carre1.getExposition().getClass());
		assertEquals(String.class, carre1.getSol().getClass());
		assertEquals(Double.class, ((Object) carre1.getSurface()).getClass());
	}

	@Test
	void testGetIdCarre() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		carre1.setIdCarre(1);
		assertEquals(1, carre1.getIdCarre());
	}

	@Test
	void testGetSurface() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals(2, carre1.getSurface());
	}

	@Test
	void testGetSol() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals("sableux", carre1.getSol());
	}

	@Test
	void testGetExposition() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals(Exposition.MI_OMBRE, carre1.getExposition());
	}

	@Test
	void testGetPotager() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals(potager, carre1.getPotager());
	}

	@Test
	void testSetIdCarre() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		carre1.setIdCarre(1);
		carre1.setIdCarre(2);
		assertEquals(2, carre1.getIdCarre());
	}

	@Test
	void testSetSurface() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		carre1.setSurface(12.0);
		assertEquals(12, carre1.getSurface());
	}

	@Test
	void testSetSol() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		carre1.setSol("testsol");
		;
		assertEquals("testsol", carre1.getSol());
	}

	@Test
	void testSetExposition() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		carre1.setExposition(Exposition.SOLEIL);
		;
		assertEquals(Exposition.SOLEIL, carre1.getExposition());
	}

	@Test
	void testSetPotager() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		Potager potager2 = new Potager("test", "STR", 25, "STR");

		carre1.setPotager(potager2);
		assertEquals(potager2, carre1.getPotager());
	}

	@Test
	void testEqualsObject() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals(Carre.class, carre1.getClass());
	}

	@Test
	void testCanEqual() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		Carre carre2 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals(carre1, carre2);
	}

	@Test
	void testCarre() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		assertEquals(carre1, carre1);
	}
}
