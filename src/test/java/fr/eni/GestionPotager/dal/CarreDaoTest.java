package fr.eni.GestionPotager.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Potager;

@SpringBootTest
class CarreDaoTest {

	@Autowired
	CarreDao dao;

	@Autowired
	PotagerDao daoPotager;

	@Test
	@Transactional
	final void testSave() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		dao.save(carre1);
		List<Carre> listeCarres = (List<Carre>) dao.findAll();
		assertEquals(listeCarres.size(), 1);
	}

	@Test
	@Transactional
	final void testFindById() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		dao.save(carre1);
		List<Carre> listeCarres = (List<Carre>) dao.findAll();

		Carre carreTest = dao.findById(listeCarres.get(0).getIdCarre()).orElse(null);
		assertEquals(carreTest.getExposition(), carre1.getExposition());
	}

	@Test
	@Transactional
	final void testFindAll() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		Carre carre2 = new Carre(10, "argileux", Exposition.SOLEIL, null);
		dao.save(carre1);
		dao.save(carre2);
		List<Carre> listeCarres = (List<Carre>) dao.findAll();
		assertEquals(listeCarres.size(), 2);
	}

	@Test
	@Transactional
	final void testDeleteById() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		dao.save(carre1);
		List<Carre> listeCarres = (List<Carre>) dao.findAll();
		dao.deleteById(listeCarres.get(0).getIdCarre());
		List<Carre> listeCarres2 = (List<Carre>) dao.findAll();

		assertEquals(listeCarres2.size(), 0);

	}

	@Test
	@Transactional
	final void testDelete() {
		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, null);
		dao.save(carre1);
		dao.delete(carre1);
		List<Carre> listeCarres2 = (List<Carre>) dao.findAll();

		assertEquals(listeCarres2.size(), 0);

	}

	@Test
	@Transactional
	final void testSommeSurface() {
		System.out.println("___________testSommeSurface()____________");
		Potager potager = new Potager("STR", "STR", 25, "STR");
		daoPotager.save(potager);

		Carre carre1 = new Carre(2, "sableux", Exposition.MI_OMBRE, potager);
		Carre carre2 = new Carre(10, "argileux", Exposition.SOLEIL, potager);
		dao.save(carre1);
		dao.save(carre2);

		System.err.println(dao.findAll());
		System.out.println(dao.countSurface(potager.getIdPotager()));

		float sumSurface = (float) (carre1.getSurface() + carre2.getSurface());
		assertEquals((float)dao.countSurface(potager.getIdPotager()), sumSurface );
	}

}
