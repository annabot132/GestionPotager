package fr.eni.GestionPotager.bll;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.dal.PlanteDao;

@Service
public class PlanteManagerImpl implements PlanteManager {

	@Autowired
	PlanteDao planteDao;
	
	
	@Override
	@Transactional
	public void createPlante(Plante plante) {
		planteDao.save(plante);
		
	}

	@Override
	public List<Plante> findAll() {
		List<Plante> listePlantes = (List<Plante>)planteDao.findAll();
		return listePlantes;
	}

	@Override
	public Plante findPlanteById(Integer id) {
		return planteDao.findById(id).orElse(null);
	}

	@Override
	public void updatePlante(Plante plante) {
		planteDao.save(plante)	;
	}

	@Override
	public void deletePlante(Plante plante) {
		planteDao.delete(plante);
		
	}

	@Override
	public void deletePlanteById(Integer id) {
		planteDao.deleteById(id);	
	}


	
	

}
