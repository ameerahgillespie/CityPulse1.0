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

		RatingsDAO.addRating(rating);

		List counts = RatingsDAO.getAggregateRatings();
//		logger.info("Aggregate: " + counts);
		
		Object[] maps = new Object[11];
		Map<String, Long> deadMap = new HashMap<String, Long>();
		Map<String, Long> justRightMap = new HashMap<String, Long>();
		Map<String, Long> jumpingMap = new HashMap<String, Long>();
		Map<String, Long> coverChargeMap = new HashMap<String, Long>();
		Map<String, Long> crowdedMap = new HashMap<String, Long>();
		Map<String, Long> expensiveMap = new HashMap<String, Long>();
		Map<String, Long> loudMap = new HashMap<String, Long>();
		Map<String, Long> bigGroupsMap = new HashMap<String, Long>();
		Map<String, Long> smallGroupsMap = new HashMap<String, Long>();
		Map<String, Long> safePlaceMap = new HashMap<String, Long>();
		Map<String, Long> goodParkingMap = new HashMap<String, Long>();
		
		for (Object rating1: counts) {
			Object[] ratings = (Object[]) rating1;
			deadMap.put((String)ratings[0], (Long)ratings[1]);
			justRightMap.put((String)ratings[0], (Long)ratings[2]);
			jumpingMap.put((String)ratings[0], (Long)ratings[3]);
			coverChargeMap.put((String)ratings[0], (Long)ratings[4]);
			crowdedMap.put((String)ratings[0], (Long)ratings[5]);
			expensiveMap.put((String)ratings[0], (Long)ratings[6]);
			loudMap.put((String)ratings[0], (Long)ratings[7]);
			bigGroupsMap.put((String)ratings[0], (Long)ratings[8]);
			smallGroupsMap.put((String)ratings[0], (Long)ratings[9]);
			safePlaceMap.put((String)ratings[0], (Long)ratings[10]);
			goodParkingMap.put((String)ratings[0], (Long)ratings[11]);			
		}
		maps[0] = deadMap;
		maps[1] = justRightMap;
		maps[2] = jumpingMap;
		maps[3] = coverChargeMap;
		maps[4] = crowdedMap;
		maps[5] = expensiveMap;
		maps[6] = loudMap;
		maps[7] = bigGroupsMap;
		maps[8] = smallGroupsMap;
		maps[9] = safePlaceMap;
		maps[10] = goodParkingMap;

		Gson gson = new Gson();
		String data = gson.toJson(maps);
		System.out.println(data);
		model.addAttribute("jsonData", data);
		model.addAttribute("lat", lat);
		model.addAttribute("lng", lng);
		model.addAttribute("zoom", zoom);
		model.addAttribute("userId", userId);

		return "map";
	}
}
