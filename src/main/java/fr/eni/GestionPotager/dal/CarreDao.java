package fr.eni.GestionPotager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.GestionPotager.bo.Carre;

public interface CarreDao extends CrudRepository<Carre, Integer> {

	@Query("SELECT SUM (c.surface) from Carre c where c.potager.idPotager=:idPotager")
	Integer countSurface(@Param("idPotager") Integer idPotager);

	@Query("select c from Carre c where c.listePlantations(1)=:null")
	List<Carre> carreVide();

}