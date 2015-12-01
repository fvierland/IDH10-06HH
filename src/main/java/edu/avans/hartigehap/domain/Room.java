package edu.avans.hartigehap.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="Rooms")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id") 
@Getter @Setter

public class Room extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int price;
	private String description;
	
	
//	@OneToMany(mappedBy="room")
//	private List<Reservation> reservations;
	
	
}