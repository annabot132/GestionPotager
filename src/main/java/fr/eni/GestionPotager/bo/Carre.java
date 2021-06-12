package fr.eni.GestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	private double surface;
	private String sol;
	private Exposition exposition;

	@ManyToOne
	private Potager potager;

	@OneToMany(mappedBy = "carre", cascade = CascadeType.ALL)
	private List<Action> listeActions = new ArrayList<Action>();

	@OneToMany(mappedBy = "carre", cascade = CascadeType.ALL)
	private List<Plantation> listePlantations = new ArrayList<Plantation>();

	public Carre(double surface, String sol, Exposition exposition, Potager potager) {
		this.surface = surface;
		this.sol = sol;
		this.exposition = exposition;
		this.potager = potager;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Carre [idCarre=");
		builder.append(idCarre);
		builder.append(", surface=");
		builder.append(surface);
		builder.append(", sol=");
		builder.append(sol);
		builder.append(", exposition=");
		builder.append(exposition);
		builder.append(", potager=");
		builder.append(potager);
		builder.append("]");
		return builder.toString();
	}

}
