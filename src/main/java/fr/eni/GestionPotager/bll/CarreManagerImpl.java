package fr.eni.GestionPotager.bll;

import java.util.ArrayList;
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

	@Autowired
	private PlanteManager planteMgr;

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
			double reste = potager.getSurface() - (calculSurfaceCarre(potager) + carre.getSurface());
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

//	@Override
//	@Transactional
//	// TODO TEST + MODIF
//	public void ajouterPlantationAuCarre(Carre carre, Plante plante, Plantation plantation) throws BllException {
//		// surface plans < surface carré
//
//		List<Plantation> lstPlantation = findById(carre.getIdCarre()).getListePlantations();
//
//		List<String> lstNomPlante = new ArrayList<String>();
//
//		// contrainte nom ok
//		for (Plantation plantation2 : lstPlantation) {
//			if (lstNomPlante.contains(plantation2.getPlante().getNom())) {
//
//			} else {
//				lstNomPlante.add(plantation2.getPlante().getNom());
//			}
//		}
//
//		if (lstNomPlante.size() == 3) {
//			throw new BllException("Il y a déjà 3 plantes dans votre carré");
//		}
//System.err.println(lstNomPlante);
//		float surfaceSurCarreExistant = 0;
//		for (Plantation plantation2 : lstPlantation) {
//			// surfaceSurCarreExistant += la surface de la plante * la qte
//			surfaceSurCarreExistant += ((plantation2.getPlante().getSurfaceOccupee()) * plantation.getQuantite());
//			System.err.println("____________DANS LA BLL POUR CONTRAINTE____________");
//			System.err.println("surfaceSurCarreExistant : " + surfaceSurCarreExistant);
//
//		}
//		// si la surface occupé par la plante (en cours) * sa qté + la surface occupé du
//		// carré qui existe > surface du carré
//		//Float surfaceOccupee =  plante.getSurfaceOccupee() * plantation.getQuantite();
//		Double surfaceOccupee =  planteMgr.findPlanteById(plante.getIdPlante()).getSurfaceOccupee() * plantation.getQuantite();
//		if (surfaceOccupee + surfaceSurCarreExistant > carre.getSurface()) {
//			throw new BllException("Pas assez de place dans le carré");
//		}
//
//		plantation.setCarre(findById(carre.getIdCarre()));
//		plantation.setPlante(planteMgr.findPlanteById(plante.getIdPlante()));
//
//		// Plantation plantation = new Plantation(dateMiseEnPlace, dateDeRecolte, qte,
//		// carre, plante);
//
//		carre.getListePlantations().add(plantation);
//
//		plantationDao.save(plantation);
//		carreDao.save(carre);
//
//	}

	@Override
	@Transactional
	// TODO TEST + MODIF
	public void ajouterPlantationAuCarre(Carre carre, Plante plante, Plantation plantation) throws BllException {
		// surface plans < surface carré

		

		List<String> lstNomPlante = new ArrayList<String>();

//		// contrainte nom ok
//		for (Plantation p : lstPlantation) {
//			if (lstNomPlante.contains(p.getPlante().getNom())) {
//
//			} else {
//				lstNomPlante.add(p.getPlante().getNom());
//			}
//		}
//
//		if (lstNomPlante.size() == 3) {
//			throw new BllException("Il y a déjà 3 plantes dans votre carré");
//		}
		System.err.println("lstNomPlante : " + lstNomPlante);
		
		
		List<Plantation> lstPlantationDuCarreInBdd = findById(carre.getIdCarre()).getListePlantations();
		System.out.println("lstPlantation : " +lstPlantationDuCarreInBdd);
		// contrainte surface 
		
		
		float surfaceRestanteDuCarre = carre.getSurface();
		System.out.println("*******************************************************************************");
		System.out.println("surfaceRestantDuCarre(init) : " + surfaceRestanteDuCarre);
		System.out.println("*******************************************************************************");
		
		float surfaceTotalDesPlantationsDuCarreInBdd = 0;
		for (Plantation p : lstPlantationDuCarreInBdd) {
			//System.out.println(p.getPlante().getSurfaceOccupee() * p.getQuantite());
			surfaceTotalDesPlantationsDuCarreInBdd += (p.getPlante().getSurfaceOccupee()) * p.getQuantite();
			
			
		}
		
		surfaceRestanteDuCarre -= surfaceTotalDesPlantationsDuCarreInBdd;
		
		System.out.println("*******************************************************************************");
		System.out.println("surfaceRestantDuCarre(apres deduction) : " + surfaceRestanteDuCarre);
		System.out.println("*******************************************************************************");
		
		float surfaceAAdd = (float) (plante.getSurfaceOccupee() * plantation.getQuantite());
		
		// si surface restante sur le carré - la surface a ajouté < 0 => exception sinon ajoute
		
		if ((surfaceRestanteDuCarre - surfaceAAdd) < 0 ) {
			System.err.println("surfaceRestanteDuCarre : " + surfaceRestanteDuCarre);
			System.err.println("surfaceAAdd : " + surfaceAAdd);
			throw new BllException("Pas assez de place dans le carré");
		}
		else {
			System.out.println("y'a dla place");
			System.err.println("surfaceRestanteDuCarre : " + surfaceRestanteDuCarre);
			System.err.println("surfaceAAdd : " + surfaceAAdd);
			
			carre.getListePlantations().add(plantation);
			
			
			plantation.setPlante(planteMgr.findPlanteById(plante.getIdPlante()));
			plantation.setCarre(findById(carre.getIdCarre()));
			
			
			plantationDao.save(plantation);
			carreDao.save(carre);
		}
		
		
//		
//		float surfaceTotalDesPlantationsSurCarreExistant = 0;
//		for (Plantation p : lstPlantationInBDD) {
//			// surfaceSurCarreExistant += la surface de la plante * la qte
//			surfaceTotalDesPlantationsSurCarreExistant += ((float)((p.getPlante().getSurfaceOccupee()) * plantation.getQuantite()));
//			System.err.println("SURFACE TOTAL DES PLANTATIONS DANS CARRE : " + surfaceTotalDesPlantationsSurCarreExistant);
//			System.err.println("SURFACE DU CARRE : " + carre.getSurface());
//			//			surfaceTotalDesPlantationsSurCarreExistant += ()			
//		}
//		System.out.println("____________DANS LA BLL POUR CONTRAINTE____________");
//		System.out.println("surfaceSurCarreExistant : " + surfaceTotalDesPlantationsSurCarreExistant);
//		System.out.println("lstPlantation : " + lstPlantationInBDD);
//		System.out.println("___________________________________________________");
//		
		
		// si la surface occupé par la plante (en cours) * sa qté + la surface occupé du
		// carré qui existe > surface du carré
		// Float surfaceOccupee = plante.getSurfaceOccupee() * plantation.getQuantite();
//		Double surfaceOccupee = planteMgr.findPlanteById(plante.getIdPlante()).getSurfaceOccupee() * plantation.getQuantite();
		
		
		
//		
//		if (surfaceOccupee + surfaceTotalDesPlantationsSurCarreExistant > carre.getSurface()) {
//			throw new BllException("Pas assez de place dans le carré");
//		}
//
//		plantation.setCarre(findById(carre.getIdCarre()));
//		plantation.setPlante(planteMgr.findPlanteById(plante.getIdPlante()));

		// Plantation plantation = new Plantation(dateMiseEnPlace, dateDeRecolte, qte,
		// carre, plante);

		

	}

	@Override
	public List<Plantation> findAllImplantationsForOnePlante(Integer id) {
		return plantationDao.findAllPlantationForOnePlante(id);
	}

	@Override
	public List<Carre> findAllEmptyCarre() {

		return carreDao.carreVide();
	}

}
