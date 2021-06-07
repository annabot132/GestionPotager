package fr.eni.GestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Potager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPotager;
	private String localisation;
	private String nom;
	private float surface;
	private String ville;

	@OneToMany(mappedBy = "potager")
	private List<Carre> listeCarres = new ArrayList<Carre>();

	public Potager(String localisation, String nom, float surface, String ville) {
		this.localisation = localisation;
		this.nom = nom;
		this.surface = surface;
		this.ville = ville;
	}

}
