package edu.avans.hartigehap.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.avans.hartigehap.domain.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.avans.hartigehap.service.ConceptStateService;
import edu.avans.hartigehap.service.ConfirmedStateService;
import edu.avans.hartigehap.service.CustomerService;
import edu.avans.hartigehap.service.FinalStateService;
import edu.avans.hartigehap.service.ReservationService;
import edu.avans.hartigehap.service.FacilityService;
import edu.avans.hartigehap.web.form.Message;

@Controller
//@PreAuthorization for ("hasRole('ROLE_EMPLOYEE')")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private FinalStateService finalStateService;

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private FacilityService facilityService;

    @Autowired
    private ConfirmedStateService confirmedStateService;

    @Autowired
    private ConceptStateService conceptStateService;

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String listReservations(Model uiModel) {

        List<Reservation> reservations = this.reservationService.findAll();

        uiModel.addAttribute("reservations", reservations);
        return "reservations/index";
    }

    @RequestMapping(value = "/reservations/new", method = RequestMethod.GET)
    public String newReservation(Model uiModel) {
        List<Customer> customers = this.customerService.findAll();
        List<IFacility> facilities = this.facilityService.findByType("Facility");

        uiModel.addAttribute("reservation", new Reservation());
        uiModel.addAttribute("customers", customers);
        uiModel.addAttribute("facilities", facilities);
        return "reservations/new";
    }

    @RequestMapping(value = "/reservations/finalize/{reservationId}", method = RequestMethod.GET)
    public String makeFinal(Model uiModel, @PathVariable("reservationId") Long reservationId) {
        Reservation reservation = this.reservationService.findById(reservationId);

        if(reservation == null) {
            uiModel.addAttribute("message", new Message("pas op", "Deze reservering is niet gevonden."));
            return this.listReservations(uiModel);
        }

        try {
            IReservationState status = reservation.getState();
            status.setFinalStateService(this.finalStateService);
            status.setReservationService(this.reservationService);
            status.makeFinal(reservation);
            uiModel.addAttribute("message", new Message("succes", "De status is veranderd"));
            return this.listReservations(uiModel);
        } catch (InvalidStateException exception) {
            uiModel.addAttribute("message", new Message("pas op", "De status is niet gewijzigd: " + exception.getMessage()));
            return this.listReservations(uiModel);
        }
    }


    @RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
    public String showReservation(Model uiModel, @PathVariable("reservationId") Long reservationId) {
        Reservation reservation = this.reservationService.findById(reservationId);
        if (reservation != null) {
        	uiModel.addAttribute("reservation", reservation);
        	return "reservations/show";
        } else {
        	return "reservations/index";
        }
    }

    @RequestMapping(value = "/reservations/new", method = RequestMethod.POST)
    public String storeReservation(@RequestParam("customer") Long customerId,
                                   @RequestParam("name") String name,
                                   @RequestParam("groupSize") Integer groupSize,
                                   @RequestParam("description") String description,
                                   @RequestParam("startDateTime") String startDateTime,
                                   @RequestParam("endDateTime") String endDateTime,
                                   @RequestParam("facility") Long roomId,
                                   @RequestParam(value="decorators", required = false) String[] decorators){

        Reservation reservation = new Reservation();

        Customer customer = this.customerService.findById(customerId);
        reservation.setCustomer(customer);
        reservation.setName(name);
        reservation.setDescription(description);
        reservation.setGroupSize(groupSize);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm");
        DateTime startTime = formatter.parseDateTime(startDateTime);
        DateTime endTime = formatter.parseDateTime(endDateTime);

        FacilityFactory facilityFactory = FacilityFactory.getInstance();
        List<String> roomDecorators = new ArrayList<String>();
        if (decorators != null)
        	roomDecorators.addAll(Arrays.asList(decorators));
        IFacility facility = facilityFactory.buildFacility(facilityService.findById(roomId), roomDecorators);
        reservation.setFacility(facility);
       
        
        PeriodFactory PeriodFactory = edu.avans.hartigehap.domain.PeriodFactory.getInstance();
        List<IPeriod> periods = PeriodFactory.buildPeriod(startTime, endTime, reservation);
        reservation.setIPeriods(periods);

        List<ConceptState> conceptStates = this.conceptStateService.findAll();
        ConceptState conceptState= conceptStates.get(0);

        reservation.setState(conceptState);

        this.reservationService.save(reservation);
        return "redirect:/reservations";
    }
}
