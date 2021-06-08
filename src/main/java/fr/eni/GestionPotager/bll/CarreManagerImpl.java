package fr.eni.GestionPotager.bll;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Plantation;
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.dal.CarreDao;
import fr.eni.GestionPotager.dal.PlantationDao;

@Service
public class CarreManagerImpl implements CarreManager {

	@Autowired
	private CarreDao carreDao;

	@Autowired
	private PlantationDao plantationDao;

	@Autowired
	private PotagerManager potagerManager;

	@Override
	public void createCarre(Carre carre) {
		carreDao.save(carre);

	}

	@Override
	public void deleteCarre(Integer idCarre) {
		carreDao.deleteById(idCarre);

	}

	@Override
	public void updateCarre(Carre carre) {
		carreDao.save(carre);
	}

	@Override
	public Carre findById(Integer idCarre) {
		return carreDao.findById(idCarre).orElse(null);
	}

	@Override
	public List<Carre> findAll() {

		return (List<Carre>) carreDao.findAll();
	}

	@Override
	@Transactional
	public void ajouterCarrePotager(Potager potager, Carre carre) throws BllException {
		System.err.println("potager id " + potager);
		if (potagerManager.getPotagerById(potager.getIdPotager()) == null) {
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

		// @Override
//		@Transactional
//		public void addCarreInPotager(Potager potager, Carre carre) {
//			carre.setPotager(potager);
//			potager.getListeCarres().add(carre);
//			addPotager(potager);
//			carreMg.createCarre(carre);
//		}
	}

	@Override
	@Transactional
	public void ajouterPlantationAuCarre(Carre carre, Plante plante, int qte, LocalDate dateMiseEnPlace,
			LocalDate dateDeRecolte) throws BllException {
		// surface plans < surface carré

		List<Plantation> lstPlantation = carre.getListePlantations();

		float surfaceSurCarreExistant = 0;
		for (Plantation plantation : lstPlantation) {
			surfaceSurCarreExistant += (plantation.getPlante().getSurfaceOccupee() * plantation.getQuantite());
		}
		
		if (plante.getSurfaceOccupee()*qte+surfaceSurCarreExistant > carre.getSurface()) {
			throw new BllException("Pas assez de place dans le carré");
		}

		Plantation plantation = new Plantation(dateMiseEnPlace, dateDeRecolte, qte, carre, plante);

		carre.getListePlantations().add(plantation);

		plantationDao.save(plantation);
		carreDao.save(carre);

	}

}
