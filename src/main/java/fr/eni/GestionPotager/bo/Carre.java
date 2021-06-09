package fr.eni.GestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Carre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCarre;
	private float surface;
	private String sol;
	private Exposition exposition; 

	@ManyToOne
	private Potager potager;

	
	//@OneToMany(mappedBy = "carre")
	@OneToMany(mappedBy = "carre", cascade = CascadeType.ALL, fetch=FetchType.EAGER )
	private List<Plantation> listePlantations = new ArrayList<Plantation>();

	public Carre(float surface, String sol, Exposition exposition, Potager potager) {
		this.surface = surface;
		this.sol = sol;
		this.exposition = exposition;
		this.potager = potager;
	}

	
}
