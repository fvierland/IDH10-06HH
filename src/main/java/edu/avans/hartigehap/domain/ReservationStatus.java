package edu.avans.hartigehap.domain;



import javax.persistence.Entity;

//import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table (name ="ReservationStatus")
@Getter @Setter
@ToString(callSuper=true, includeFieldNames=true, of= {"name"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")

public class ReservationStatus extends DomainObject
{
	private static final long serialVersionUID = 1L;

	
	protected Reservation reservation;
	public String status;
	
}
