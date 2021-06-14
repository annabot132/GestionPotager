package fr.eni.GestionPotager.bo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAction;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

//	@NotBlank(message = "Le description de l'action à faire est obligatoire")
	private String evenement;

	@ManyToOne
	private Potager potager;

	@ManyToOne
	private Carre carre;

	public Action(Date date, String evenement, Potager potager, Carre carre) {
		super();
		this.date = date;
		this.evenement = evenement;
		this.potager = potager;
		this.carre = carre;
	}

}
