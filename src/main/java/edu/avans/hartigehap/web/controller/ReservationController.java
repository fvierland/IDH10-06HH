package edu.avans.hartigehap.web.controller;

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

import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;
import org.springframework.validation.*;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import edu.avans.hartigehap.web.form.*;
import javax.servlet.http.Part;

import org.springframework.context.MessageSource;
import edu.avans.hartigehap.web.util.*;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import edu.avans.hartigehap.service.*;
import javax.servlet.http.*;

import edu.avans.hartigehap.web.form.Message;

@Controller
//@PreAuthorization for ("hasRole('ROLE_EMPLOYEE')")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantService restaurantService;
    
    @RequestMapping(value = "/restaurants/{restaurantName}/reservations", method = RequestMethod.GET)
    public String listReservations (@PathVariable("restaurantName")String restaurantName, Model uiModel) {
    	warmupRestaurant(restaurantName, uiModel);
    	
    	List<Reservation> reservations = this.reservationService.findAll();
    	
    	

        uiModel.addAttribute("reservations", reservations);
        return "hartigehap/listreservations";
    }

   private Restaurant warmupRestaurant(String restaurantName, Model uiModel) {
        Collection<Restaurant> restaurants = restaurantService.findAll();
        uiModel.addAttribute("restaurants", restaurants);
        Restaurant restaurant = restaurantService.fetchWarmedUp(restaurantName);
        uiModel.addAttribute("restaurant", restaurant);
        return restaurant;
    }
    
}


