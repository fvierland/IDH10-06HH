package edu.avans.hartigehap.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("NONE")
public class withoutVAT extends Vat {

    private Double vat = 0.00;

    @Override
    public Double calculateVat(Double price) {
        return price + (price * this.vat);
    }
}
