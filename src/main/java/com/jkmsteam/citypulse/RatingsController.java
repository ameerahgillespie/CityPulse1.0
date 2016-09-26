package com.jkmsteam.citypulse;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
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
//	@RequestMapping(value = "/vote", method = RequestMethod.GET)
//	public String addRating(@RequestParam("ratetype") String ratetype, @RequestParam("placeId") String placeId, Model model) {
//		
//		logger.info("Rate your experience " + ratetype);
//		Rating rating = new Rating ();
//		rating.setDead(1);
//		rating.setPlaceId(placeId);
//		rating = RatingsDAO.updateRating(rating);
//		//model.addAttribute(rating);
//		logger.info("Total count" + ratetype);
//	List<Rating> counts = RatingsDAO.getAllRatings();
//		
//		//create hashmap of ratings for all place IDs in database
//		Map <String, Rating> ratingsMap = new HashMap<String, Rating>();
//		
//		for (Rating ratingAll : counts) {
//			ratingsMap.put(ratingAll.getPlaceId(), ratingAll);
//			//System.out.println(rating)
//		}
//		Gson gson = new Gson();
//		String data = gson.toJson(ratingsMap);
//		System.out.println(data);
//		model.addAttribute("jsonData", data); 
//		return "map";
//	}
	
	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public String addVote(
			@RequestParam("userId") long userId,
			@RequestParam("placeId") String placeId,
			@RequestParam("overallRate") String overallRate,
			@RequestParam(value = "coverCharge", defaultValue = "0") Integer coverCharge,
			@RequestParam(value = "crowded", defaultValue = "0") Integer crowded,
			@RequestParam(value = "expensive", defaultValue = "0") Integer expensive,
			@RequestParam(value = "loud", defaultValue = "0") Integer loud,
			@RequestParam(value = "bigGroups", defaultValue = "0") Integer bigGroups,
			@RequestParam(value = "smallGroups", defaultValue = "0") Integer smallGroups,
			@RequestParam(value = "safePlace", defaultValue = "0") Integer safePlace,
			@RequestParam(value = "goodParking", defaultValue = "0") Integer goodParking,
			@RequestParam("lat") double lat,
			@RequestParam("lng") double lng,
			@RequestParam("zoom") int zoom,
			Model model) {
		
//		logger.info("userId: " + userId + "placeId");
		Rating rating = new Rating();
		rating.setUserId(userId);
		rating.setPlaceId(placeId);
		if (overallRate.equals("dead")) {
			rating.setDead(1);
			rating.setJustRight(0);
			rating.setJumping(0);
		} else if (overallRate.equals("justRight")) {
			rating.setDead(0);
			rating.setJustRight(1);
			rating.setJumping(0);
		} else {
			rating.setDead(0);
			rating.setJustRight(0);
			rating.setJumping(1);			
		}
		rating.setCoverCharge(coverCharge);
		rating.setCrowded(crowded);
		rating.setExpensive(expensive);
		rating.setLoud(loud);
		rating.setBigGroups(bigGroups);
		rating.setSmallGroups(smallGroups);
		rating.setSafePlace(safePlace);
		rating.setGoodParking(goodParking);
		
		logger.info("this specific rating: " + rating.toString());
		RatingsDAO.addRating(rating);
		//model.addAttribute(rating);
		List<Rating> counts = RatingsDAO.getAllRatings();
		
		//create hashmap of ratings for all place IDs in database
		Map <String, Rating> ratingsMap = new HashMap<String, Rating>();
		
		for (Rating ratingAll : counts) {
			ratingsMap.put(ratingAll.getPlaceId(), ratingAll);
			//System.out.println(rating);
		}
		Gson gson = new Gson();
		String data = gson.toJson(ratingsMap);
		System.out.println(data);
		model.addAttribute("jsonData", data);
		model.addAttribute("lat", lat);
		model.addAttribute("lng", lng);
		model.addAttribute("zoom", zoom);
		model.addAttribute("userId", userId);

		return "map";
	}

}
