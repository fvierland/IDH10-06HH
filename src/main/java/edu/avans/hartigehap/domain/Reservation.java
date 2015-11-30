package edu.avans.hartigehap.domain;

import javax.persistence.Entity;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "RESERVATION")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id") 
@Getter @Setter
@ToString(callSuper=true, includeFieldNames=true, of= {"name"})
public class Reservation extends DomainObject {
	private static final long serialVersionUID = 1L;

	@NotEmpty (message = "{validation.reservation.name.NotEmpty.message}")
	private String name;
	
	@NotEmpty (message = "{validation.reservation.description.NotEmpty.message}")
	private String description;
	
	@NotNull (message = "{validation.reservation.groupSize.NotNull.message}")
	@Size (min=2, max= 99, message = "{validation.reservation.groupSize.Size.message}")
	private int groupSize;
	
	
	
	
}