package fr.eni.GestionPotager.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.dal.CarreDao;

@Service
public class CarreManagerImpl implements CarreManager {

	@Autowired
	private CarreDao dao;

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

}
