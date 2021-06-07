package fr.eni.GestionPotager.bll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.TypePlante;

@SpringBootTest
class PlanteManagerImplContraintesTest {

	@Autowired
	PlanteManager planteMg;
	
	@Test
	@Transactional
	final void testCreatePlante() throws BllException {
		Plante plante1 = new Plante("tomate", TypePlante.FRUIT, "cerise", (float) 0.75);
		planteMg.createPlante(plante1);
		
		Exception exception = assertThrows(BllException.class, () -> {
			planteMg.createPlante(plante1);
		});

		String expectedMessage = "La plante existe déjà";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
		}
		
}
