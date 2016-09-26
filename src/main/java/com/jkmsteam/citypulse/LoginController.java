package com.jkmsteam.citypulse;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkmsteam.citypulse.*;
import com.jkmsteam.model.dao.UsersDAO;
import com.jkmsteam.model.dto.User;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String begin(
			@CookieValue(value = "lat", defaultValue = "42.335376") Double lat,
			@CookieValue(value = "lng", defaultValue = "-83.050000") Double lng,
			@CookieValue(value = "zoom", defaultValue = "16") Integer zoom,
			HttpServletResponse response,
			Model model) {

		Cookie latCookie = new Cookie("lat", lat.toString());
		response.addCookie(latCookie);
		Cookie lngCookie = new Cookie("lng", lng.toString());
		response.addCookie(lngCookie);
		Cookie zoomCookie = new Cookie("zoom", zoom.toString());
		response.addCookie(zoomCookie);

		model.addAttribute("lat", lat);
		model.addAttribute("lng", lng);
		model.addAttribute("zoom", zoom);		
		
		User user = new User();
		model.addAttribute("userForm", user);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userForm") User user,
			BindingResult result, Model model) {
		double lat = user.getLatitude();
		double lng = user.getLongitude();
		int zoom = user.getZoom();
		long fb_id = user.getFbId();
		User tempUser = UsersDAO.getUserById(user.getFbId());
		logger.info("user's fbId: "+ user.getFbId());
		logger.info("tempUser's fbId: "+ tempUser.getFbId());
		if (tempUser.getFbId() == user.getFbId()) {
			UsersDAO.setUserLocation(user.getFbId(), lat, lng, zoom);
			logger.info(user.getFirstName() + " already exist, updating location");
		} else {
			logger.info(user.toString());
			UsersDAO.addUser(user);
			logger.info(user.getFirstName() + " saved");
		}
		return "redirect:/?lat=" + lat + "&lng=" + lng + "&zoom=" + zoom + "&userId=" + fb_id;
	}
	
	
}
