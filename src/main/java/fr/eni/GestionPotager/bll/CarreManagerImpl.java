package fr.eni.GestionPotager.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Carre;
import fr.eni.GestionPotager.bo.Exposition;
import fr.eni.GestionPotager.bo.Plantation;
import fr.eni.GestionPotager.bo.Plante;
import fr.eni.GestionPotager.bo.Potager;
import fr.eni.GestionPotager.bo.TypePlante;
import fr.eni.GestionPotager.dal.CarreDao;
import fr.eni.GestionPotager.dal.PlantationDao;
import fr.eni.GestionPotager.dal.PlanteDao;

@Service
public class CarreManagerImpl implements CarreManager {

	@Autowired
	private CarreDao carreDao;


	@Autowired
	private PotagerManager potagerManager;

	@Autowired
	private PlantationDao plantationDao;
	
	@Autowired
	private PlanteManager planteMgr;
	
	@Autowired
	private CarreManager carreMgr;
	

	@PostConstruct
	public void init() throws BllException {
		Potager potager1 = new Potager("devant la maison", "mon potager de la maison", 50, "Quimper");
		Potager potager2 = new Potager("devant", "j'ai le carre3", 50, "Quimper");
		Potager potager3 = new Potager("jardin", "j'ai rien ", 50, "Quimper");
		Carre carre = new Carre(16, "j'ai des plantations", Exposition.SOLEIL, potager1);
		Carre carre2 = new Carre(8, "no plantation", Exposition.SOLEIL, potager1);
		Carre carre3 = new Carre(8, "no plantation", Exposition.SOLEIL, potager1);

		
		potagerManager.addPotager(potager2);
		potagerManager.addPotager(potager3);
		
		
		ajouterCarrePotager(potager1, carre);
		ajouterCarrePotager(potager1, carre2);
		ajouterCarrePotager(potager2, carre3);
		
		Plante plante = new Plante("Plante1", TypePlante.FEUILLE, "varPlante1", 2);
		Plante plante2 = new Plante("Plante2", TypePlante.FEUILLE, "varPlante2", 2);
		planteMgr.createPlante(plante);
		planteMgr.createPlante(plante2);
		
		
		ajouterPlantationAuCarre(carre, plante, 2, LocalDate.now(), LocalDate.now());
		ajouterPlantationAuCarre(carre, plante2, 2, LocalDate.now(), LocalDate.now());
		
		
//		System.err.println("______________INIT CarreMgrImpl________________");
//		System.err.println(potagerManager.getAllPotager());
//		System.err.println("______________________________");
//		System.err.println(planteMgr.findAll());
//		System.err.println("______________________________");
//		System.err.println(carreMgr.findAll());
		
	}
	
	

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
		
		List<String> lstNomPlante = new ArrayList<String>();
		for (Plantation plantation : lstPlantation) {
			if (lstNomPlante.contains(plantation.getPlante().getNom())) {
				
			}else {
				lstNomPlante.add(plantation.getPlante().getNom());
			}
		}
		
		
		if (lstNomPlante.size()==3) {
			throw new BllException("Il y a déjà 3 plantes dans votre carré");
		}

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
