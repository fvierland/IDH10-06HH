package edu.avans.hartigehap.domain;



import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.beans.factory.annotation.Autowired;

//import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.avans.hartigehap.service.ConceptStateService;
import edu.avans.hartigehap.service.ConfirmedStateService;
import edu.avans.hartigehap.service.FinalStateService;
import edu.avans.hartigehap.service.ReservationService;
import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@Entity
@Getter @Setter
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="STATE")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Table(name = "RESERVATION_STATE", uniqueConstraints =
@UniqueConstraint(name="type_unique", columnNames={"STATE"})
)

public abstract class IReservationState extends DomainObject
{
	@Autowired
	@Transient
	protected Reservation reservation;
	
	@Autowired
	@Transient
    protected ConceptStateService conceptStateService;
	
	@Autowired
	@Transient
    protected ConfirmedStateService confirmedStateService;
	
	@Autowired
	@Transient
    protected FinalStateService finalStateService;
	
	@Autowired
    @Transient
    protected ReservationService reservationService;	
	
	@Column(name = "STATE", insertable = false, updatable = false)
    private String state;
	
	public abstract void makeFinal(Reservation reservation) throws InvalidStateException;

    public abstract void makeConcept(Reservation reservation) throws InvalidStateException;

    public abstract void makeConfirmed(Reservation reservation) throws InvalidStateException;
	
	@Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
