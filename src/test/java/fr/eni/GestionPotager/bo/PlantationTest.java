package fr.eni.GestionPotager.bo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

class PlantationTest {

	@Test
	void testPlantationDateDateIntegerCarrePlante() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(Plante.class, plante1.getClass());
		assertEquals(Carre.class, carre1.getClass());
		assertEquals(Integer.class, plantation1.getQuantite().getClass());
		assertEquals(Date.class, plantation1.getMiseEnPlace().getClass());
		assertEquals(Date.class, plantation1.getRecolte().getClass());
	}

	@Test
	void testGetIdPlantation() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		plantation1.setIdPlantation(1);
		assertEquals(1, plantation1.getIdPlantation());
	}

	@Test
	void testGetMiseEnPlace() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(date, plantation1.getMiseEnPlace());
	}

	@Test
	void testGetRecolte() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(date1, plantation1.getRecolte());
	}

	@Test
	void testGetQuantite() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(1, plantation1.getQuantite());
	}

	@Test
	void testGetCarre() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(1, plantation1.getQuantite());
	}

	@Test
	void testGetPlante() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(plante1, plantation1.getPlante());
	}

	@Test
	void testSetIdPlantation() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		plantation1.setIdPlantation(1);
		plantation1.setIdPlantation(2);
		assertEquals(2, plantation1.getIdPlantation());
	}

	@Test
	void testSetMiseEnPlace() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		cal2.set(2022, 8, 18);
		Date date3 = cal2.getTime();
		plantation1.setMiseEnPlace(date3);
		assertEquals(date3, plantation1.getMiseEnPlace());
	}

	@Test
	void testSetRecolte() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		cal2.set(2022, 8, 18);
		Date date3 = cal2.getTime();
		plantation1.setRecolte(date3);
		assertEquals(date3, plantation1.getRecolte());
	}

	@Test
	void testSetQuantite() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		plantation1.setQuantite(10);
		assertEquals(10, plantation1.getQuantite());
	}

	@Test
	void testSetCarre() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		Carre carre2 = new Carre(10, "sableux", Exposition.MI_OMBRE, null);
		plantation1.setCarre(carre2);
		assertEquals(carre2, plantation1.getCarre());
	}

	@Test
	void testSetPlante() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		Plante plante2 = new Plante("TEST2", TypePlante.FRUIT, "TEST", (double) 0.1);
		plantation1.setPlante(plante2);
		assertEquals(plante2, plantation1.getPlante());
	}

	@Test
	void testEqualsObject() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(Plantation.class, plantation1.getClass());
	}

	@Test
	void testCanEqual() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		Plantation plantation2= new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(plantation1, plantation2);
	}

	@Test
	void testPlantation() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		assertEquals(plantation1, plantation1);
	}

}
