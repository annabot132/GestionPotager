package fr.eni.GestionPotager.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Action;

@SpringBootTest
class ActionDaoTest {

	@Autowired
	ActionDao dao;

	@Test
	@Transactional
	final void testSave() {
		Action action = new Action(LocalDate.now(), "rien", null, null);
		dao.save(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		assertEquals(listeActions.size(), 1);

	}

	@Test
	@Transactional
	final void testFindById() {
		Action action = new Action(LocalDate.now(), "rien", null, null);
		dao.save(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		Action actionTest = dao.findById(listeActions.get(0).getIdAction()).orElse(null);
		assertEquals(actionTest.getDate(), action.getDate());
	}

	@Test
	@Transactional
	final void testFindAll() {
		Action action = new Action(LocalDate.now(), "rien", null, null);
		Action action2 = new Action(LocalDate.now(), "une autre", null, null);
		dao.save(action);
		dao.save(action2);

		List<Action> listeActions = (List<Action>) dao.findAll();
		assertEquals(listeActions.size(), 2);

	}

	@Test
	@Transactional
	final void testDeleteById() {
		Action action = new Action(LocalDate.now(), "rien", null, null);
		dao.save(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		dao.deleteById(listeActions.get(0).getIdAction());
		List<Action> listeActions2 = (List<Action>) dao.findAll();

		assertEquals(listeActions2.size(), 0);
	}

	@Test
	@Transactional
	final void testDelete() {
		Action action = new Action(LocalDate.now(), "rien", null, null);
		dao.save(action);
		dao.delete(action);
		List<Action> listeActions = (List<Action>) dao.findAll();
		assertEquals(listeActions.size(), 0);
	}

}
