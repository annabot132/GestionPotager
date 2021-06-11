package fr.eni.GestionPotager.dal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.GestionPotager.bo.Action;


public interface ActionDao extends CrudRepository<Action, Integer>{

	@Query("SELECT a from Action a where a.date >=:dateJour and a.date <:dateJourPlus15 ORDER BY a.date")
	List<Action> findActionsByIntervalDate(@Param("dateJour") Date dateJour,
			@Param("dateJourPlus15") Date dateJourPlus15);

	@Query("SELECT a from Action a where a.potager.idPotager = :idPotager and  a.date >=:dateJour and a.date <:dateJourPlus15 ORDER BY a.date")
	List<Action> findActionsByPotagerByIntervalDate(@Param("idPotager") Integer idPotager, @Param("dateJour") Date dateJour,
			@Param("dateJourPlus15") Date dateJourPlus15 );
	
	@Query("SELECT a from Action a where a.carre.idCarre = :idCarre and a.potager.idPotager = :idPotager and  a.date >=:dateJour and a.date <:dateJourPlus15 ORDER BY a.date")
	List<Action> findActionsByPotagerByCarreByIntervalDate(@Param("idCarre") Integer idCarre, @Param("idPotager") Integer idPotager, @Param("dateJour") Date dateJour,
			@Param("dateJourPlus15") Date dateJourPlus15 );
	
}
