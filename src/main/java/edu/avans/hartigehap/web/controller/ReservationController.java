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

import edu.avans.hartigehap.web.form.*;

@Controller
//@PreAuthorization for ("hasRole('ROLE_EMPLOYEE')")
public class ReservationController {
    
	@Autowired
    private MessageSource messageSource;
	

	
	@Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantService restaurantService;
    
     
    
    //List all reservations   - WERKT! 
    @RequestMapping(value = "/restaurants/{restaurantName}/reservations", method = RequestMethod.GET)
    public String listReservations (@PathVariable("restaurantName")String restaurantName, Model uiModel) {
    	warmupRestaurant(restaurantName, uiModel);
    	
    	List<Reservation> reservations = this.reservationService.findAll();
    	   	
        uiModel.addAttribute("reservations", reservations);
        return "hartigehap/listreservations";
    }
    
//  show reservation
    @RequestMapping(value = "/restaurants/{restaurantName}/reservations/{id}", method = RequestMethod.GET)
    public String showReservation(@PathVariable("restaurantName") String restaurantName, @PathVariable("id") Long id,
            Model uiModel) {

        warmupRestaurant(restaurantName, uiModel);
        
        Reservation reservation = reservationService.findById(id);
        uiModel.addAttribute("reservation", reservation);
        return "hartigehap/showreservations";
    }        
        

    @RequestMapping(value = "/restaurants/{restaurantName}/reservations/{id}", params = "form", method = RequestMethod.GET)
	public String updateReservationForm(@PathVariable("restaurantName") String restaurantName, @PathVariable("id") Long id, Model uiModel) {

		warmupRestaurant(restaurantName, uiModel);
		
	
		
		Reservation reservation = reservationService.findById(id);
		uiModel.addAttribute("reservation", reservation);
		return "hartigehap/editreservations";
	}

	@RequestMapping(value = "/restaurants/{restaurantName}/reservations/{id}", params = "form", method = RequestMethod.PUT)
	public String updateReservation(
			// the path variable is not used; data binding retrieves its info from
			// query string parameters and form fields, so reservation includes id as well
			@PathVariable("restaurantName") String restaurantName,
			@PathVariable("id") Long id, 
			@Valid Reservation reservation, 
			BindingResult bindingResult,
			Model uiModel, 
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, 
			Locale locale)
			 {
		
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"reservation_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("reservation", reservation);
			return "hartigehap/editreservations";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"reservation_save_success", new Object[] {}, locale)));

		
		Reservation existingReservation = reservationService.findById(reservation.getId());
        assert existingReservation != null : "Reservation should exist";
        
        // update user-editable fields
        existingReservation.updateEditableFields(reservation);

        reservationService.save(existingReservation);
		return "redirect:/restaurants/" + restaurantName + "/reservations/"
				+ UrlUtil.encodeUrlPathSegment(reservation.getId().toString(),
						httpServletRequest);
	}
    
	@RequestMapping(value = "/restaurants/{restaurantName}/reservations", params = "form", method = RequestMethod.GET)
	public String createReservationForm(@PathVariable("restaurantName") String restaurantName, Model uiModel) {

		warmupRestaurant(restaurantName, uiModel);
				
		Reservation reservation = new Reservation();
		uiModel.addAttribute("reservation", reservation);
		return "hartigehap/editreservations";
	}
	

	@RequestMapping(value = "/restaurants/{restaurantName}/reservations", params = "form", method = RequestMethod.POST)
	public String createReservation(@PathVariable("restaurantName") String restaurantName, @Valid Reservation reservation, BindingResult bindingResult,
		Model uiModel, HttpServletRequest httpServletRequest,
		RedirectAttributes redirectAttributes, Locale locale)
	{

		if(bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"reservation_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("reservation", reservation);
			return "hartigehap/editreservations";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"reservation_save_success", new Object[] {}, locale)));
				
		// relate reservation to current restaurant
		Restaurant restaurant = warmupRestaurant(restaurantName, uiModel);
		reservation.setRestaurants(Arrays.asList(new Restaurant[]{restaurant}));
		
		// to get the auto generated id
		Reservation storedReservation = reservationService.save(reservation);
		
		return "redirect:/restaurants/" + restaurantName + "/reservations/"
				+ UrlUtil.encodeUrlPathSegment(storedReservation.getId().toString(),
						httpServletRequest);
	}
    
    
	
	
    
    
    // Reservering verwijderen - WERKT!
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

