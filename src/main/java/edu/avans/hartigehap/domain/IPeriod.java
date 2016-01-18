package edu.avans.hartigehap.domain;

import javax.persistence.*;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERIOD")
@Getter
@Setter
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public abstract class IPeriod extends DomainObject{
	private static final long serialVersionUID = 1L;

	@Column(name = "DTYPE", insertable = false, updatable = false)
	private String type;

	protected LocalDate date;
	
	@ManyToOne()
	protected Reservation reservation;
	
	public abstract void setDate(DateTime date);

	@Override
	public String toString() {
		return this.type;
	}
	

}