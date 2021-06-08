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
	PlanteManager planteMg;

	@Autowired
	CarreManager carreMg;

	@Autowired
	PlanteManager potagerMg;

	@PostConstruct
	public void init() throws BllException {
		Potager potager1 = new Potager("devant la maison", "mon potager de la maison", 10, "Quimper");
		Potager potager2 = new Potager("derriere la maison", "ma serre de la maison", 20, "Quimper");
		Carre carre = new Carre(4, "argileux", Exposition.SOLEIL, potager1);
		Carre carre2 = new Carre(4, "argileux", Exposition.SOLEIL, potager1);
		Carre carre3 = new Carre(2, "argileux", Exposition.SOLEIL, potager1);
		Carre carreSerre1 = new Carre(8, "tourbeux", Exposition.SOLEIL, potager2);
		Carre carreSerre2 = new Carre(8, "tourbeux", Exposition.SOLEIL, potager2);

		carreMg.ajouterCarrePotager(potager1, carre);
		carreMg.ajouterCarrePotager(potager1, carre2);
		carreMg.ajouterCarrePotager(potager1, carre3);
		carreMg.ajouterCarrePotager(potager2, carreSerre1);
		carreMg.ajouterCarrePotager(potager2, carreSerre2);
	}

	@Test
	@Transactional
	final void testFindAllCarre() {
		assertEquals(carreMg.findAll().size(), 5);
	}

}
