package fr.eni.GestionPotager.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.GestionPotager.bo.Plante;

public interface PlanteDao extends CrudRepository<Plante, Integer> {

}
