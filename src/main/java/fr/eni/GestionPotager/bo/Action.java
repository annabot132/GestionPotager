package fr.eni.GestionPotager.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAction;
	private Date date;
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
