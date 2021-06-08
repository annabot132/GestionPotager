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
	@Transactional
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
		if (dao.countSurface(potager.getIdPotager()) == null) {
			return 0;
		}
		return dao.countSurface(potager.getIdPotager());

	}
}
