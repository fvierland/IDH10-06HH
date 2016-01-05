package edu.avans.hartigehap.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "RESERVATIONS")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true, of= {"name"})

public class Reservation extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty (message = "{validation.reservation.name.NotEmpty.message}")
	private String name;
	
	@NotEmpty (message = "{validation.reservation.description.NotEmpty.message}")
	private String description;
	
	@NotNull (message = "{validation.reservation.groupSize.NotNull.message}")
	@Size (min=5, max=50, message = "{validation.reservation.groupSize.Size.message}")
	private int groupSize;
		
	@ManyToOne
	@JoinColumn (name="RoomID")
	private Facility facility;
	
		public void setRoom(Facility facility)
	{
		this.facility = facility;
	}
		
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private IReservationState state;
	
		
	@OneToMany (mappedBy="reservation")
	private List<IPeriod> iPeriods;
	
	public void addPeriod (IPeriod iPeriod)
	{
		this.iPeriods.add(iPeriod);
	}
		
	public void setReservationState(IReservationState state)
	{
		this.state = state;
	}
	
}