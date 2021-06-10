package fr.eni.GestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Plante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPlante;
	private String nom;
	private TypePlante typePlante;
	private String variete;
	private float surfaceOccupee;

	@OneToMany(mappedBy = "plante", cascade = CascadeType.ALL)
	private List<Plantation> listePlantations = new ArrayList<Plantation>();

	public Plante(String nom, TypePlante typePlante, String variete, float surfaceOccupee) {
		super();
		this.nom = nom;
		this.typePlante = typePlante;
		this.variete = variete;
		this.surfaceOccupee = surfaceOccupee;
	}

}
