package fr.eni.GestionPotager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.GestionPotager.bo.Plantation;

public interface PlantationDao extends CrudRepository<Plantation, Integer>{

	
	@Query("SELECT  p from Plantation p where p.plante.idPlante=:idPlante")
	List<Plantation> findAllPlantationForOnePlante(@Param("idPlante")Integer idPlante);
	
	
}
