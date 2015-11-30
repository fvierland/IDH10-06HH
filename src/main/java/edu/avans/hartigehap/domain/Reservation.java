package edu.avans.hartigehap.domain;

import javax.persistence.Entity;

import javax.persistence.Table;

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

	private String name;
	private String description;
	private int Size;
	
	
	
	
}