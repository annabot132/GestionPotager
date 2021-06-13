package fr.eni.GestionPotager.bo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ActionTest {

	@Test
	void testActionDateStringPotagerCarre() {
		Potager potager = new Potager("STR", "STR", 25, "STR");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals(Potager.class, potager.getClass());
		assertEquals(Carre.class, carre.getClass());
		assertEquals(String.class, action.getEvenement().getClass());
		assertEquals(Date.class, action.getDate().getClass());
	}

	@Test
	void testOGetIdAction() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		action.setIdAction(1);
		assertEquals(1, action.getIdAction());
	}

	@Test
	void testGetDate() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals(date, action.getDate());
	}

	@Test
	void testGetEvenement() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals("testAction1", action.getEvenement());
	}

	@Test
	void testGetPotager() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals(potager, action.getPotager());
	}

	@Test
	void testGetCarre() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals(carre, action.getCarre());
	}

	@Test
	void testSetIdAction() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		action.setIdAction(1);
		action.setIdAction(2);
		assertEquals(2, action.getIdAction());
	}

	@Test
	void testSetDate() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		cal.set(2022, 5, 11);
		Date date2 = cal.getTime();
		action.setDate(date2);
		assertEquals(date2, action.getDate());
	}

	@Test
	void testSetEvenement() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		action.setEvenement("NouveauTest");
		assertEquals("NouveauTest", action.getEvenement());
	}

	@Test
	void testSetPotager() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		Potager potager2 = new Potager("test2", "test2", 22, "test2");
		action.setPotager(potager2);
		assertEquals(potager2, action.getPotager());
	}

	@Test
	void testSetCarre() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals(Action.class, action.getClass());

	}

	@Test
	void testEqualsObject() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals(Action.class, action.getClass());
	}

	@Test
	void testCanEqual() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		Action action2 = new Action(date, "testAction1", potager, carre);
		assertEquals(action, action2);
	}

	@Test
	void testAction() {
		Potager potager = new Potager("test", "test", 2, "test");
		Carre carre = new Carre(0, "test", Exposition.MI_OMBRE, potager);
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 11);
		Date date = cal.getTime();
		Action action = new Action(date, "testAction1", potager, carre);
		assertEquals(action, action);
	}

}
