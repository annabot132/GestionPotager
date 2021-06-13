package fr.eni.GestionPotager.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Plantation;
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.TypePlante;

@SpringBootTest
class PlantationDaoTest {

	@Autowired
	PlantationDao daoPlantation;
	@Autowired
	PlanteDao daoPlante;
	@Autowired
	CarreDao daoCarre;

	@Test
	@Transactional
	final void testSave() {
		List<Plantation> listePlantatuionsBefore = (List<Plantation>) daoPlantation.findAll();
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, null, null);
		daoPlantation.save(plantation1);
		List<Plantation> listePlantatuions = (List<Plantation>) daoPlantation.findAll();
		assertEquals(listePlantatuionsBefore.size() + 1, listePlantatuions.size());
	}

	@Test
	@Transactional
	final void testFindById() {
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, null, null);
		daoPlantation.save(plantation1);
		List<Plantation> listePlantations = (List<Plantation>) daoPlantation.findAll();
		Plantation plantationTest = daoPlantation
				.findById(listePlantations.get(listePlantations.size() - 1).getIdPlantation()).orElse(null);
		assertEquals(plantationTest.getMiseEnPlace(), plantation1.getMiseEnPlace());
	}

	@Test
	@Transactional
	final void testFindAll() {
		List<Plantation> listePlantatuionsBefore = (List<Plantation>) daoPlantation.findAll();
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, null, null);
		Plantation plantation2 = new Plantation(date, date1, 5, null, null);
		daoPlantation.save(plantation1);
		daoPlantation.save(plantation2);
		List<Plantation> listePlantatuions = (List<Plantation>) daoPlantation.findAll();
		assertEquals(listePlantatuionsBefore.size() + 2, listePlantatuions.size());
	}

	@Test
	@Transactional
	final void testDeleteById() {
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, null, null);
		daoPlantation.save(plantation1);
		List<Plantation> listePlantationsBefore = (List<Plantation>) daoPlantation.findAll();
		daoPlantation.deleteById(listePlantationsBefore.get(listePlantationsBefore.size() - 1).getIdPlantation());
		List<Plantation> listePlantations = (List<Plantation>) daoPlantation.findAll();
		assertEquals(listePlantationsBefore.size() - 1, listePlantations.size());
	}

	@Test
	@Transactional
	final void testDelete() {
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, null, null);
		daoPlantation.save(plantation1);
		List<Plantation> listePlantationsBefore = (List<Plantation>) daoPlantation.findAll();
		daoPlantation.delete(plantation1);
		List<Plantation> listePlantations = (List<Plantation>) daoPlantation.findAll();
		assertEquals(listePlantationsBefore.size() - 1, listePlantations.size());
	}

	@Test
	@Transactional
	final void testFindAllPlantationForOnePlante() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		daoCarre.save(carre1);
		Plante plante1 = new Plante("TEST", TypePlante.FRUIT, "TEST", (double) 0.1);
		daoPlante.save(plante1);
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 5, 11);
		Date date = cal.getTime();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 8, 18);
		Date date1 = cal2.getTime();
		Plantation plantation1 = new Plantation(date, date1, 1, carre1, plante1);
		daoPlantation.save(plantation1);
		System.err.println(daoPlantation.findAll());
		List<Plante> listeplantes = (List<Plante>) daoPlante.findAll();
		assertEquals(1, daoPlantation
				.findAllPlantationForOnePlante(listeplantes.get(listeplantes.size() - 1).getIdPlante()).size());
	}

}
