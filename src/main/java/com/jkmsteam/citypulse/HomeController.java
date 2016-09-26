package com.jkmsteam.citypulse;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam("lat") Double lat,
				@RequestParam("lng") Double lng,
				@RequestParam("zoom") Integer zoom,
				@RequestParam("userId") long userId,
				HttpServletResponse response,
				Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Cookie latCookie = new Cookie("lat", lat.toString());
		response.addCookie(latCookie);
		Cookie lngCookie = new Cookie("lng", lng.toString());
		response.addCookie(lngCookie);
		Cookie zoomCookie = new Cookie("zoom", zoom.toString());
		response.addCookie(zoomCookie);
		

		List<Rating> counts = RatingsDAO.getAllRatings();
//		List<Rating> counts = RatingsDAO.getAggregateRatings();
		logger.info("Aggregate: " + counts);
		//create hashmap of ratings for all place IDs in database
		Map <String, Rating> ratingsMap = new HashMap<String, Rating>();
		
		for (Rating rating : counts) {
			ratingsMap.put(rating.getPlaceId(), rating);
			System.out.println(rating);
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
