package fr.eni.GestionPotager.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.GestionPotager.bo.Plantation;

public interface PlantationDao extends CrudRepository<Plantation, Integer>{

}
