package com.jkmsteam.citypulse;

import java.text.DateFormat;
import java.util.ArrayList;
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
		

//		List<Rating> counts = RatingsDAO.getAllRatings();
		List counts = RatingsDAO.getAggregateRatings();
		logger.info("Aggregate: " + counts);
		
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
		
		for (Object rating: counts) {
			Object[] ratings = (Object[]) rating;
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

//		for (Object rating : counts) {
//		//	ratingsMap.put(rating.getPlaceId(), rating);
//			Object[] ratings = (Object[]) rating;
//			System.out.print("placeid: " + ratings[0]);
//			ratingsMap.put("placeid", (Integer)ratings[0]);
//			System.out.print("\tdead: " + ratings[1]);
//			System.out.print("\tjust right: " + ratings[2]);
//			System.out.println();
//		}
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
