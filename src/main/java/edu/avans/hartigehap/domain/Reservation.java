package edu.avans.hartigehap.domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor

public class Reservation extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty (message = "{validation.reservation.name.NotEmpty.message}")
	private String name;
	
	@NotEmpty (message = "{validation.reservation.description.NotEmpty.message}")
	private String description;
	
	@NotNull (message = "{validation.reservation.groupSize.NotNull.message}")
	private int groupSize;
	
	@ManyToOne
	@JoinColumn (name="FACILITY_ID")
	private IFacility facility;
	
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private IReservationState state;
		
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy="reservation")
	private List<IPeriod> iPeriods;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
	//no cascading
    @ManyToMany
    private Collection<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	public void addPeriod (IPeriod Period)
	{
		this.iPeriods.add(Period);
	}
	public void setReservationState(IReservationState state)
	{
		this.state = state;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public void setFacility(IFacility facility)
	{
		this.facility = facility;
	}
	
	public void updateEditableFields(Reservation reservation) {
		    name = reservation.name;
	        groupSize = reservation.groupSize;
	        description = reservation.description;
	    }
	public Reservation (String name, String description, int groupSize){
		 this.name=name;
		 this.description=description;
		 this.groupSize=groupSize;
		}
	}
