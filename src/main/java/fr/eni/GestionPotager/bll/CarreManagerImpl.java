package fr.eni.GestionPotager.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.dal.CarreDao;

@Service
public class CarreManagerImpl implements CarreManager {

	@Autowired
	private CarreDao dao;
	
	@Autowired
	private PotagerManager potagerManager;

	@Override
	public void createCarre(Carre carre) {
		dao.save(carre);

	}

	@Override
	public void deleteCarre(Integer idCarre) {
		dao.deleteById(idCarre);

	}

	@Override
	public void updateCarre(Carre carre) {
		dao.save(carre);
	}

	@Override
	public Carre findById(Integer idCarre) {
		return dao.findById(idCarre).orElse(null);
	}

	@Override
	public List<Carre> findAll() {

		return (List<Carre>) dao.findAll();
	}

	@Override
	public void ajouterCarrePotager(Potager potager, Carre carre) {

//		if (potagerManager.getPotagerById(potager.getIdPotager())== null){
//			potagerManager.addPotager(potager);
//		}
//		carre. setPotager(potager);
//		potager.getListeCarres().add(carre);
//		createCarre(carre);
		
		//		@Override
//		@Transactional
//		public void addCarreInPotager(Potager potager, Carre carre) {
//			carre.setPotager(potager);
//			potager.getListeCarres().add(carre);
//			addPotager(potager);
//			carreMg.createCarre(carre);
//		}
	}

}
