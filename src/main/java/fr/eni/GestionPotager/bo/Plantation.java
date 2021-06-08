package fr.eni.GestionPotager.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Plantation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPlantation;
	private LocalDate miseEnPlace;
	private LocalDate recolte;
	private Integer quantite;

	@ManyToOne
	private Carre carre;
	@OneToOne
	private Plante plante;

	public Plantation(LocalDate miseEnPlace, LocalDate recolte, Integer quantite, Carre carre, Plante plante) {
		super();
		this.miseEnPlace = miseEnPlace;
		this.recolte = recolte;
		this.quantite = quantite;
		this.carre = carre;
		this.plante = plante;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Plantation [idPlantation=");
		builder.append(idPlantation);
		builder.append(", miseEnPlace=");
		builder.append(miseEnPlace);
		builder.append(", recolte=");
		builder.append(recolte);
		builder.append(", quantite=");
		builder.append(quantite);
		builder.append(", plante=");
		builder.append(plante);
		builder.append("]");
		return builder.toString();
	}

}
