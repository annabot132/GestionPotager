package fr.eni.GestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank(message = "Le nom est obligatoire!")
	private String nom;
	
	@Min(value = 1, message = "La surface d'un potager ne peut être inérieuere à 1 m²!")
	private double surface;
	
	@NotBlank(message = "La ville est obligatoire!")
	private String ville;

	@OneToMany(mappedBy = "potager", cascade = CascadeType.ALL)
	private List<Carre> listeCarres = new ArrayList<Carre>();

	public Potager(String localisation, String nom, float surface, String ville) {
		this.localisation = localisation;
		this.nom = nom;
		this.surface = surface;
		this.ville = ville;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Potager [idPotager=");
		builder.append(idPotager);
		builder.append(", localisation=");
		builder.append(localisation);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", surface=");
		builder.append(surface);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}

	
}
