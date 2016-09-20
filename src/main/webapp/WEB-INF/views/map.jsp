<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map {
	height: 100%;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pick Your Poison</title>
</head>
<body>
	<div id="map"></div>
	<!-- Replace the value of the key parameter with your own API key. -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=DISPLAY_MAP_JS_KEY&libraries=places&callback=initMap"
		script defer></script>

	<script>
		//This example requires the Places library. Include the libraries=places
		//parameter when you first load the API. For example:
		//<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

		var map;
		var infowindow;

		function initMap() {
			var pyrmont = {
				lat : 42.335376,
				lng : -83.050000
			};

			map = new google.maps.Map(document.getElementById('map'), {
				center : pyrmont,
				zoom : 15
			});

			//if we choose to show traffic, use these two lines
			//var trafficLayer = new google.maps.TrafficLayer();
			//trafficLayer.setMap(map);

			infowindow = new google.maps.InfoWindow();
			var service = new google.maps.places.PlacesService(map);
			service.nearbySearch({
				location : pyrmont,
				radius : 3000,
				type : [ 'bar' ]
			}, callback);
		}

		function callback(results, status) {
			if (status === google.maps.places.PlacesServiceStatus.OK) {
				for (var i = 0; i < results.length; i++) {
					createMarker(results[i]);
				}
			}
		}

		function createMarker(place) {
			var placeLoc = place.geometry.location;
			var marker = new google.maps.Marker({
				map : map,
				position : place.geometry.location
			});

			google.maps.event.addListener(marker, 'click', function() {
				infowindow.setContent(place.name);
				infowindow.open(map, this);
			});
		}
	</script>
</body>
</html>