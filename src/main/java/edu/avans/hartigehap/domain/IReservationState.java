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

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@Entity
@Getter @Setter
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="STATE")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Table(name = "RESERVATION_STATUS", uniqueConstraints =
@UniqueConstraint(name="type_unique", columnNames={"STATE"})
)

public abstract class IReservationState extends DomainObject
{
	@Transient
	protected Reservation reservation;
	
	@Column(name = "STATE", insertable = false, updatable = false)
    private String state;
	
	public abstract void makeFinal(Reservation reservation) throws InvalidReservationStatusActionException;

    public abstract void makeConcept(Reservation reservation) throws InvalidReservationStatusActionException;

    public abstract void makeConfirmed(Reservation reservation) throws InvalidReservationStatusActionException;
	
	@Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
