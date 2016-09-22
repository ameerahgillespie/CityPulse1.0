package com.jkmsteam.citypulse;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkmsteam.model.dao.RatingsDAO;
import com.jkmsteam.model.dto.Rating;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RatingsController {
	
	private static final Logger logger = LoggerFactory.getLogger(RatingsController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
			return "ratingsform";
	}
	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	public String addRating(@RequestParam("ratetype") String ratetype, @RequestParam("placeId") String placeId){
		
		logger.info("Rate your experience" + ratetype);
		Rating rating = new Rating ();
		rating.setDead(1);
		rating.setPlaceId(placeId);
		rating = RatingsDAO.updateRating(rating);
		logger.info("Total count" + ratetype);
		return "ratingsform";
	}
	
}
