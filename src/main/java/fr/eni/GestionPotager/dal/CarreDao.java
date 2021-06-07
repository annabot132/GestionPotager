package fr.eni.GestionPotager.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.GestionPotager.bo.Carre;

public interface CarreDao extends CrudRepository<Carre, Integer> {

}
