package fr.eni.GestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Plante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPlante;

	@NotBlank(message = "Le nom est obligatoire!")
	private String nom;

	private TypePlante typePlante;

	@NotBlank(message = "La variété est obligatoire!")
	private String variete;

	@DecimalMin(value = "0.001", message = "La surface occupée par une plante ne peut être inférieure à 0,001 m²!")
	private Double surfaceOccupee;

	@OneToMany(mappedBy = "plante", cascade = CascadeType.ALL)
	private List<Plantation> listePlantations = new ArrayList<Plantation>();

	public Plante(String nom, TypePlante typePlante, String variete, double surfaceOccupee) {
		this.nom = nom;
		this.typePlante = typePlante;
		this.variete = variete;
		this.surfaceOccupee = surfaceOccupee;
	}

	@Override
	public String toString() {
		return "Plante [idPlante=" + idPlante + ", nom=" + nom + ", typePlante=" + typePlante + ", variete=" + variete
				+ ", surfaceOccupee=" + surfaceOccupee + "]";
	}

}
