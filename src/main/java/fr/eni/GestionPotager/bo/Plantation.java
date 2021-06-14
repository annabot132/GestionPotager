package fr.eni.GestionPotager.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Plantation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPlantation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date miseEnPlace;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recolte;

	
//	@Min(value = 1, message = "La quantité de plante ne peut être inférieure à 1.")
	private Integer quantite;

	@ManyToOne
	private Carre carre;
	
	@ManyToOne
	private Plante plante;

	public Plantation(Date miseEnPlace, Date recolte, Integer quantite, Carre carre, Plante plante) {
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
		builder.append(", carre=");
		builder.append(carre);
		builder.append(", plante=");
		builder.append(plante);
		builder.append("]");
		return builder.toString();
	}



}
