package fr.eni.GestionPotager.bll;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.GestionPotager.bo.Action;
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

	@Autowired
	private ActionManager actionMg;
	
	
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
		
		if ((carre.getSurface() <= 0 )) {
			throw new BllException("La surface de votre carré ne peut être inférieur ou égal à 0.");
		}
		
		if ((calculSurfaceCarre(potager) + carre.getSurface()) > potager.getSurface()) {
			double reste = potager.getSurface() - (calculSurfaceCarre(potager) + carre.getSurface());
			throw new BllException("Il n'y a plus de place dans le potager!! il vous reste: " + reste + "  m²");
		}
		if (potagerManager.getPotagerById(potager.getIdPotager())== null){
			potagerManager.addPotager(potager);
		}
		carre. setPotager(potager);
		potager.getListeCarres().add(carre);
		createCarre(carre);
//		
//			@Override
//		@Transactional
//		public void addCarreInPotager(Potager potager, Carre carre) {
//			carre.setPotager(potager);
//			potager.getListeCarres().add(carre);
//			addPotager(potager);
//			carreMg.createCarre(carre);
//		}

		}	
		


	public float calculSurfaceCarre(Potager potager) {
		if (carreDao.countSurface(potager.getIdPotager()) == null) {
			return 0;
		}
		return carreDao.countSurface(potager.getIdPotager());

	}

	@Override
	@Transactional

	public void ajouterPlantationAuCarre(Carre carre, Plante plante, Plantation plantation) throws BllException {
		List<Plantation> lstPlantationDuCarreInBdd = findById(carre.getIdCarre()).getListePlantations();
		List<String> lstNomPlante = new ArrayList<String>();

		for (Plantation p : lstPlantationDuCarreInBdd) {
			if (lstNomPlante.contains(p.getPlante().getNom())) {

			} else {
				lstNomPlante.add(p.getPlante().getNom());
			}
		}

		
		/**
		 *   si la liste des noms == 3 et si elle ne contient pas le nom de la plante =>
		 exception
		 de sorte que si on a dans notre potager : 1 plant de tomate cerise, 1 plant
		 de tomate boeuf, 1 plant de tomate X, et 1 plant de choux, 1 plant de
		 concombre => pas d'exception mais si on essaye de rajouter des betteraves =>
		 exception
		 */
		if (lstNomPlante.size() == 3 && !lstNomPlante.contains(plante.getNom())) {
//			System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\dans if exception/////////////////////////");
//			System.err.println("lstNomPlante : " + lstNomPlante);
//			System.out.println("lstPlantation : " +lstPlantationDuCarreInBdd);
			throw new BllException("Il y a déjà 3 plantes dans votre carré");
		}

//		System.err.println("lstNomPlante : " + lstNomPlante);
//		System.out.println("lstPlantation : " +lstPlantationDuCarreInBdd);

		// contrainte surface
		float surfaceRestanteDuCarre = carre.getSurface();
		// recupere surface total des plantation lié au carré
		float surfaceTotalDesPlantationsDuCarreInBdd = 0;
		for (Plantation p : lstPlantationDuCarreInBdd) {
			// System.out.println(p.getPlante().getSurfaceOccupee() * p.getQuantite());
			surfaceTotalDesPlantationsDuCarreInBdd += (p.getPlante().getSurfaceOccupee()) * p.getQuantite();
		}

		surfaceRestanteDuCarre -= surfaceTotalDesPlantationsDuCarreInBdd;

		float surfaceAAdd = (float) (plante.getSurfaceOccupee() * plantation.getQuantite());

		// si surface restante sur le carré - la surface a ajouté < 0 => exception sinon
		// ajoute

///////////////////// CHECK SI MERGE OK D'ICI
/////////////////////
/////////////////////
    
		if ((surfaceRestanteDuCarre - surfaceAAdd) < 0) {
//			System.err.println("surfaceRestanteDuCarre : " + surfaceRestanteDuCarre);
//			System.err.println("surfaceAAdd : " + surfaceAAdd);
			throw new BllException("Pas assez de place dans le carré");
		} else {
//			System.out.println("il y a de la place");
//			System.err.println("surfaceRestanteDuCarre : " + surfaceRestanteDuCarre);
//			System.err.println("surfaceAAdd : " + surfaceAAdd);

			carre.getListePlantations().add(plantation);

			plantation.setPlante(planteMgr.findPlanteById(plante.getIdPlante()));
			plantation.setCarre(findById(carre.getIdCarre()));

			plantationDao.save(plantation);
			carreDao.save(carre);
		}

		/////////////////////Ajout Anna
		Action action = new Action(plantation.getMiseEnPlace(), plantation.getQuantite()+" "+plantation.getPlante().getNom()+"(s) '"+plantation.getPlante().getVariete()+"' à Planter" , carre.getPotager(), carre);
		Action action2 = new Action(plantation.getRecolte(), plantation.getPlante().getNom()+"(s) '"+plantation.getPlante().getVariete()+"' à Récolter" , carre.getPotager(), carre);
		actionMg.addAction(action);
		actionMg.addAction(action2);
		////////////////////////////
		
		plantationDao.save(plantation);
		carreDao.save(carre);
   
/////////////////////
/////////////////////
///////////////////// CHECK SI MERGE OK A DE LA

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
