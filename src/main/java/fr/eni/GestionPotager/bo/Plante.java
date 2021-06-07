package fr.eni.GestionPotager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public Plante(String nom, TypePlante typePlante, String variete, float surfaceOccupee) {
		super();
		this.nom = nom;
		this.typePlante = typePlante;
		this.variete = variete;
		this.surfaceOccupee = surfaceOccupee;
	}

}
