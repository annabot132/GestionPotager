package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Potager;

@SpringBootTest
class CarreManagerImplTestAvecDonnees {

	@Autowired
	PlanteManager planteMg ;

	@Autowired
	CarreManager carreMg;

	@Autowired
	PotagerManager potagerMg;


	@Test
	@Transactional
	final void testFindAllCarre() {
		assertEquals(carreMg.findAll().size(), 5);
	}

}
