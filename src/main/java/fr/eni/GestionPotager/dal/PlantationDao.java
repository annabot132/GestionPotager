package fr.eni.GestionPotager.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.GestionPotager.bo.Plantation;

public interface PlantationDao extends CrudRepository<Plantation, Integer>{
	
}
