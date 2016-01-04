package edu.avans.hartigehap.domain;

public class VATFactory {

    public static VATFactory _instance;

    private static Vat withVAT;

    private static Vat withoutVAT;

    private VATFactory() {};

    public static VATFactory getInstance() {
        if(_instance == null) {
            _instance = new VATFactory();
        }
        return _instance;
    }

    public Vat getVat(String type) {
        if(type == "withVAT") {
            if(withVAT == null) {
                withVAT = new withVAT();
            }
            return withVAT;
        } 
        if(withoutVAT == null) {
            withoutVAT = new withoutVAT();
        }
        return withoutVAT;
    }
}
