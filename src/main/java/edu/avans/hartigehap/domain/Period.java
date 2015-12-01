package edu.avans.hartigehap.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table (name ="PERIODS")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public abstract class Period extends DomainObject{
	private static final long serialVersionUID = 1L;

		
	@ManyToOne
	protected Reservation reservation;
	
	
	public abstract void setDate(DateTime date);

	
	

}