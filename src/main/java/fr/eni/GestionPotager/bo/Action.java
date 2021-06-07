package fr.eni.GestionPotager.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAction;
	private LocalDate date;
	private String evenement;

	@OneToOne
	private Potager potager;
	@OneToOne
	private Carre carre;

	public Action(LocalDate date, String evenement, Potager potager, Carre carre) {
		super();
		this.date = date;
		this.evenement = evenement;
		this.potager = potager;
		this.carre = carre;
	}

}
