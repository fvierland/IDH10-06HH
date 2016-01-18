package edu.avans.hartigehap.web.controller;

import edu.avans.hartigehap.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.*;
import org.springframework.validation.*;


import org.springframework.context.MessageSource;
import edu.avans.hartigehap.web.util.*;

import java.util.*;
import edu.avans.hartigehap.service.*;
import javax.servlet.http.*;
import javax.validation.Valid;

import edu.avans.hartigehap.web.form.Message;

@Controller
//@PreAuthorization for ("hasRole('ROLE_EMPLOYEE')")
public class ReservationController {
    
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private FacilityService facilityService;
    
    //List reservations
    @RequestMapping(value = "/restaurants/{restaurantName}/reservations", method = RequestMethod.GET)
    public String listReservations (@PathVariable("restaurantName")String restaurantName, Model uiModel) {
    	warmupRestaurant(restaurantName, uiModel);
    	
    	List<Reservation> reservations = this.reservationService.findAll();
    	   	

        uiModel.addAttribute("reservations", reservations);
        return "hartigehap/listreservations";
    }

       
    // Redirect naar Editreservations via creeer reservering
    @RequestMapping(value = "/restaurants/{restaurantName}/reservations", params = "form", method = RequestMethod.GET)
    public String createReservationForm(@PathVariable("restaurantName") String restaurantName, Model uiModel) {

        warmupRestaurant(restaurantName, uiModel);

        Reservation reservation = new Reservation();
        uiModel.addAttribute("reservation", reservation);
        return "hartigehap/editreservations";
    }
    
    //Reservering updaten of maken, controleren adhv reserverings id (indien bekend = update)
    private String handleCreateOrUpdateReservation(boolean isCreate, String restaurantName, Reservation reservation, BindingResult bindingResult, Model uiModel,
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message",
                    new Message("error", messageSource.getMessage("reservation_save_fail", new Object[] {}, locale)));
            uiModel.addAttribute("reservation", reservation);
            return "hartigehap/editreservations";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message",
                new Message("success", messageSource.getMessage("reservation_save_success", new Object[] {}, locale)));
      

        if(isCreate) {
            // relate Reservation to current restaurant
            Restaurant restaurant = warmupRestaurant(restaurantName, uiModel);
            reservation.setRestaurants(Arrays.asList(new Restaurant[] { restaurant }));
            // to get the auto generated id
            reservation = reservationService.save(reservation);
        } else { // update
        	Reservation existingReservation = reservationService.findById(reservation.getId());
            assert existingReservation != null : "Reservation should exist";

            // update user-editable fields
            existingReservation.updateEditableFields(reservation);
            reservationService.save(existingReservation);
        }
        
        return "redirect:/restaurants/" + restaurantName + "/reservations/"
                + UrlUtil.encodeUrlPathSegment(reservation.getId().toString(), httpServletRequest);
    }
    
    //Reservering bijwerken
    @RequestMapping(value = "/restaurants/{restaurantName}/reservations/{id}", params = "form", method = RequestMethod.PUT)
    public String updateReservation(
            // the path variable {id} is not used; data binding retrieves its info from
            // query string parameters and form fields, so reservations includes id as well
            @PathVariable("restaurantName") String restaurantName, @Valid Reservation reservation,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
            RedirectAttributes redirectAttributes, Locale locale ){

        return handleCreateOrUpdateReservation(false, restaurantName, reservation, bindingResult, uiModel,
                httpServletRequest, redirectAttributes, locale);
        }
    
    //Reservering aanmaken
    @RequestMapping(value = "/restaurants/{restaurantName}/newreservations", method = RequestMethod.POST)
    public String createReservation(@PathVariable("restaurantName") String restaurantName, Model uiModel)          {
    	List<Customer> customers = this.customerService.findAll();
        List<IFacility> iFacility = this.facilityService.findByType("Room");

        uiModel.addAttribute("reservation", new Reservation());
        uiModel.addAttribute("customers", customers);
        uiModel.addAttribute("ifacility", iFacility);
        return "hartigehap/newreservations";
    	
        
    }
    
    
    
    // Reservering verwijderen
    @RequestMapping(value = "/restaurants/{restaurantName}/reservations/{id}", params = "delete", method = RequestMethod.GET)
    public String delete(@PathVariable("restaurantName") String restaurantName, @PathVariable("id") Long id) {

        
        reservationService.delete(id);
        return "redirect:/restaurants/" + restaurantName + "/reservations/";
    }
    
    
    
   private Restaurant warmupRestaurant(String restaurantName, Model uiModel) {
        Collection<Restaurant> restaurants = restaurantService.findAll();
        uiModel.addAttribute("restaurants", restaurants);
        Restaurant restaurant = restaurantService.fetchWarmedUp(restaurantName);
        uiModel.addAttribute("restaurant", restaurant);
        return restaurant;
    }
          
}

