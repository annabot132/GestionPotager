package fr.eni.GestionPotager.bll;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.dal.PotagerDao;

@Service
public class PotagerManagerImpl implements PotagerManager {

	@Autowired
	PotagerDao dao;
	@Autowired
	CarreManager carreMg;

	@Override
	public void addPotager(Potager potager) {
		dao.save(potager);
	}

	@Override
	public void removePotager(Potager potager) {
		dao.delete(potager);

	}

	@Override
	public void updatePotager(Potager potager) {
		dao.save(potager);

	}

	@Override
	public List<Potager> getAllPotager() {
		return (List<Potager>) dao.findAll();
	}

	@Override
	public Potager getPotagerById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public void removePotagerById(Integer id) {
		dao.deleteById(id);

	}

	

}
