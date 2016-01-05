package edu.avans.hartigehap.domain;

import java.util.Iterator;
import java.util.List;

public class FacilityFactory {
	
    private static FacilityFactory _instance;

    private FacilityFactory () {}

    public static FacilityFactory getInstance () {
        if (_instance == null) {
        	_instance = new FacilityFactory();
        }
    	return _instance;
    }
	
	public IFacility buildRoom(IFacility facility, List<String> additions) {		
		for (Iterator<String> i = additions.iterator(); i.hasNext(); ) {
			switch(i.next()) {
			case "WIFI":
				facility = new WifiDecorator(facility, 10);
				break;
			case "BEAMER":
				facility = new BeamerDecorator(facility, 20);
				break;
			case "MENU":
				facility = new MenuDecorator(facility, 30);
				break;
			case "DECORATION":
				facility = new DecorationDecorator(facility, 40);
				break;				
			}
		}

		return facility;	
	}

}
