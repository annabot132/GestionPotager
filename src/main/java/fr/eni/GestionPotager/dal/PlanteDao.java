package fr.eni.GestionPotager.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.GestionPotager.bo.Plante;

public interface PlanteDao extends CrudRepository<Plante, Integer> {

	@Query("select p from Plante p where p.nom=:nom and p.variete=:variete")
	Plante findOnePlanteOneVariete(@Param("nom") String nom, @Param("variete") String variete);

	
	
}
