package fr.eni.GestionPotager.bll;

import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public void ajouterCarrePotager(Potager potager, Carre carre) throws BllException {
		System.err.println("potager id " + potager);
		if (potagerManager.getPotagerById(potager.getIdPotager())== null){
			potagerManager.addPotager(potager);
			System.err.println(potager.getIdPotager());
		}
//		//dao.countSurface(potager.getIdPotager()) + 
//		if ((carre.getSurface()) > potager.getSurface() ) {
//			throw new BllException("Plus de place dans le potager");
//		}
		carre.setPotager(potager);
		potager.getListeCarres().add(carre);
		createCarre(carre);
		
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
