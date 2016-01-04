package edu.avans.hartigehap.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("OTHER")
public class withVAT extends Vat {

    private Double vat = 0.21;

    @Override
    public Double calculateVat(Double price) {
        return price + (price * this.vat);
    }
}
