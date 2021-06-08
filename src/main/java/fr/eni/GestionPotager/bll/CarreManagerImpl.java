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
	private PotagerManager potagerManager;

	@Autowired
	private PlantationDao plantationDao;

	@Override
	@Transactional
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
		potagerManager.addPotager(potager);
		if ((calculSurfaceCarre(potager) + carre.getSurface()) > potager.getSurface()) {
			float reste = potager.getSurface() - (calculSurfaceCarre(potager) + carre.getSurface());
			throw new BllException("Il n'y a plus de place dans le potager!! il vous reste: " + reste + "  m²");
		}
		carre.setPotager(potager);
		potager.getListeCarres().add(carre);
		createCarre(carre);

	}

	public float calculSurfaceCarre(Potager potager) {
		if (carreDao.countSurface(potager.getIdPotager()) == null) {
			return 0;
		}
		return carreDao.countSurface(potager.getIdPotager());

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

		if (plante.getSurfaceOccupee() * qte + surfaceSurCarreExistant > carre.getSurface()) {
			throw new BllException("Pas assez de place dans le carré");
		}

		Plantation plantation = new Plantation(dateMiseEnPlace, dateDeRecolte, qte, carre, plante);

		carre.getListePlantations().add(plantation);

		plantationDao.save(plantation);
		carreDao.save(carre);

	}
}
