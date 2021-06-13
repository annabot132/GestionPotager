package fr.eni.GestionPotager.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PotagerTest {

	@Test
	void testPotagerStringStringDoubleString() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		assertEquals(String.class, potager.getVille().getClass());
		assertEquals(String.class, potager.getNom().getClass());
		assertEquals(String.class, potager.getLocalisation().getClass());
		assertEquals(Double.class,((Object)potager.getSurface()).getClass());
	}

	@Test
	void testGetIdPotager() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		potager.setIdPotager(1);
		assertEquals(1, potager.getIdPotager());		}

	@Test
	void testGetLocalisation() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		assertEquals("STR", potager.getLocalisation());	}

	@Test
	void testGetNom() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		assertEquals("STR", potager.getNom());	}

	@Test
	void testGetSurface() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		assertEquals(25, potager.getSurface());	}

	@Test
	void testGetVille() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		assertEquals("STR", potager.getVille());	}

	@Test
	void testSetIdPotager() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		potager.setIdPotager(1);
		potager.setIdPotager(2);
		assertEquals(2, potager.getIdPotager());	}

	@Test
	void testSetLocalisation() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		potager.setLocalisation("Brest");
		assertEquals("Brest", potager.getLocalisation() );
	}

	@Test
	void testSetNom() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		potager.setNom("Brest");
		assertEquals("Brest", potager.getNom());	}

	@Test
	void testSetSurface() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		potager.setSurface(2);
		assertEquals(2, potager.getSurface());	}

	@Test
	void testSetVille() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		potager.setVille("Brest");
		assertEquals("Brest", potager.getVille());	}

	@Test
	void testEqualsObject() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		assertEquals(Potager.class, potager.getClass());
	}

	@Test
	void testCanEqual() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Potager potager2 = new Potager("STR", "STR", 25, "STR");
		assertEquals(potager, potager2);
	}

	@Test
	void testPotager() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		assertEquals(potager, potager);
	}

}
