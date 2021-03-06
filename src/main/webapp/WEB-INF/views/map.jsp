<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jkmsteam.citypulse.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>


<!-- STYLE BRACKETS WERE HERE -->

<style>
/*  html, body {
	height: 100%;
	margin: 0;
	padding: 0;  */
/* }

 #map {
	top: 10px;
	left: 10px;
	height: 97%;
	width: 1000px;
}  */

.voteForm {
display: inline-block;
float: right;
width: 25%;
padding: 13px;
color: #ffffff;
	/* position: absolute;
	top: 280px;
	left: 1030px;
	width: 310px; */
}

#barInfo {
	position: absolute;
	top: 10px;
	left: 1030px;
	width: 310px;
}


#container {
width: 960px; 
max-width: 90%;
position: relative;
left: 50%;
margin-left: -480px;
line-height: 1.4em;
}



#map {
position: absolute;
height: 80vh;
width: 65vw;
margin-left: auto;
margin-right: auto;
z-index: -1;
right: 400px; 
}

/*  ADD BUTTON HERE*/
 button {
/* border-radius: 5px;
border: none;
background-color: #3A3A3A;
margin: 0 4px 10px 8px;
height: 40px;
width: 50px;
box-shadow: 0px 3px 0px 0px #222121; */

background-color: #ff0000; /* Green */
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    font-family: 'Raleway', sans-serif;

}
 


/* 
#map {
	position: absolute;
	top: 10px;
	left: 300px;
	width: 1000px;
	height: 1000px;
} */

 h1 {
text-align: center;
font-weight: 700;
text-transform: uppercase;
margin-top: 0;
margin-bottom: 0;
font-size: 80px;
color: #ffffff;
}


h2 {
font-weight: 300;
text-align: center;
font-size: 50px;
margin-bottom: 70px;
color: #ffffff;
}

body {
font-family: 'Raleway', sans-serif;
font-weight: 200;
color: #222;
background-color: #37474F;
position: relative;
right: 0;
margin: 0;
}

label {
	display: block;
	width: 200px;
}

table, th, td {
	border: 1px solid;
}

.submit {
	margin: 10px 0;
}

</style>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css?family=Raleway:400,500" rel="stylesheet">
<title>Pick A Bar</title>
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

<!--BOOTSTRAP LINKS  -->
 <script src="<c:url value="/resources/js/jQuery.js" />"></script>
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
                rel="stylesheet">
</head>

<body>

<!--BOOTSTRAP -->
<div class="container">
<div class="row">

<br></br>
<br></br>
<h1>Welcome to city<span style="color:red">PULSE</span></h1>

<h2>Too many dudes?<span style="color:red"> Rate it.</span></h2>


<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.7&appId=326766364336305";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>


<div class="col-md-4"> </div>
<div class="col-md-4"> 

	<div id="map" class= "img-responsive">
		<!-- Replace the value of the key parameter with your own API key. -->
	</div>
</div>
	<div id="barInfo">
	
	
	<!-- SHARE BUTTON!!! -->
<!-- 		<div class="fb-share-button" data-href="http://localhost:8080/citypulse/login" data-layout="button" data-size="large" data-mobile-iframe="false"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A8080%2Fcitypulse%2Flogin&amp;src=sdkpreparse">Share</a></div>
 -->	
 
 
 	<p id="barName"></p>
		<p id="barAddress"></p>
		<p id="barPhone"></p>
	</div>
	
	<div class="col-md-4">
	
	<!--VOTING FORM  -->
<div class="voteForm img-responsive">
	<form action="vote" id="voteForm" method="post">
		<label id="placeName"></label><br>
		
		<input type="text" id="currentUser" name="userId" hidden="true">
		<input type="text" id="currentPlace" name="placeId" hidden="true">
		<br></br>
		<label>What's your overall feeling about this place?<br>
		<br></br>
			<input type="radio" name="overallRate" value="dead"> Dead<br>
			<input type="radio" name="overallRate" value="justRight" checked>
			Just Right<br> <input type="radio" name="overallRate"
			value="jumping"> Jumping Jumping!
		</label><br>
		<p>Tell us about your experience with this bar:</p>
		<input type="checkbox" name="coverCharge" value="1"> This
		place has cover charge<br> <input type="checkbox" name="crowded"
			value="1"> Too many dudes!<br> <input
			type="checkbox" name="expensive" value="1"> Prices are too
		high!<br> <input type="checkbox" name="loud" value="1"> I
		can't hear my thoughts!<br> <input type="checkbox"
			name="bigGroups" value="1"> It's good for big groups<br>
		<input type="checkbox" name="smallGroups" value="1"> It's good
		for small groups<br> <input type="checkbox" name="safePlace"
			value="1"> I feel safe here<br> <input type="checkbox"
			name="goodParking" value="1"> Good parking options<br> <input
			type="text" id="latForm" name="lat" hidden="true"> <input
			type="text" id="lngForm" name="lng" hidden="true"> <input
			type="text" id="zoomForm" name="zoom" hidden="true">
<br></br>
		<button type="submit">Rate It!</button>
		
	</form>
	</div>
	<!--bootstrappy -->
	</div>
</div>
</div>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=<%=GlobalVariables.DISPLAY_MAP_JS_KEY%>&libraries=places&callback=initMap"
		script defer></script>
	<script>
		var data = JSON.parse('${jsonData}'); 
		var deadSet = data[0];
		var justRightSet = data[1];
		var jumpingSet = data[2];
		var coverChargeSet = data[3];
		var crowdedSet = data[4];
		var expensiveSet = data[5];
		var loudSet = data[6];
		var bigGroupsSet = data[7];
		var smallGroupsSet = data[8];
		var safePlaceSet = data[9];
		var goodParkingSet = data[10]; 
// 		console.log("start");
// 		console.log(data[0].['51a557fd8b88caaf213178693318af59cc804052']);
		var map;
		var infowindow;
	
		function initMap() {
			var pyrmont = {
 				lat : ${lat},
				lng : ${lng}
			};
			map = new google.maps.Map(document.getElementById('map'), {
				center : pyrmont,
				zoom : ${zoom}
			});
			
		      var myLocation = pyrmont; //Sets variable to geo location long and lat co-ordinates.
				var myPosition = new google.maps.Marker({
					position : myLocation,
					icon : {
						path : google.maps.SymbolPath.CIRCLE,
						scale : 5
					},
					draggable : true,
					map : map,
					title: "You are here"
				});
		      
				var infoWindowLoc = new google.maps.InfoWindow({map: map});
				infoWindowLoc.setPosition(pyrmont);
				infoWindowLoc.setContent('You are here');
			
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
		    var pinColor = "b8b8b8";
		    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
		    new google.maps.Size(21, 34),
	        new google.maps.Point(0,0),
	        new google.maps.Point(10, 34));

			var placeLoc = place.geometry.location;
			var marker = new google.maps.Marker({
				map : map,
				position : place.geometry.location,
				icon: pinImage
			});
						
			var deadRate = 0;
			var justRightRate = 0;
			var jumpingRate = 0;
			var coverChargeRate = 0;
			var crowdedRate = 0;
			var expensiveRate = 0;
			var loudRate = 0;
			var bigGroupsRate = 0;
			var smallGroupsRate = 0;
			var safePlaceRate = 0;
			var goodParkingRate = 0;
			if (deadSet[place.id]) deadRate = deadSet[place.id];
			if (justRightSet[place.id]) justRightRate = justRightSet[place.id];
			if (jumpingSet[place.id]) jumpingRate = jumpingSet[place.id];
			if (coverChargeSet[place.id]) coverChargeRate = coverChargeSet[place.id];
			if (crowdedSet[place.id]) crowdedRate = crowdedSet[place.id];
			if (expensiveSet[place.id]) expensiveRate = expensiveSet[place.id];
			if (loudSet[place.id]) loudRate = loudSet[place.id];
			if (bigGroupsSet[place.id]) bigGroupsRate = bigGroupsSet[place.id];
			if (smallGroupsSet[place.id]) smallGroupsRate = smallGroupsSet[place.id];
			if (safePlaceSet[place.id]) safePlaceRate = safePlaceSet[place.id];
			if (goodParkingSet[place.id]) goodParkingRate = goodParkingSet[place.id];
			
			if(deadRate > justRightRate && deadRate > jumpingRate) {
				var pinColorD = "737aff";
			    var pinImageD = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColorD,
				    new google.maps.Size(21, 34),
			        new google.maps.Point(0,0),
			        new google.maps.Point(10, 34));				
				marker.setIcon(pinImageD);
			} else if(justRightRate > deadRate && justRightRate > jumpingRate) {
				var pinColorR = "ff3030";
			    var pinImageR = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColorR,
				    new google.maps.Size(21, 34),
			        new google.maps.Point(0,0),
			        new google.maps.Point(10, 34));				
				marker.setIcon(pinImageR);				
			} else if(jumpingRate > deadRate && jumpingRate > justRightRate) {
				var pinColorJ = "ff0000";
			    var pinImageJ = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColorJ,
				    new google.maps.Size(21, 34),
			        new google.maps.Point(0,0),
			        new google.maps.Point(10, 34));				
				marker.setIcon(pinImageJ);
				marker.setAnimation(google.maps.Animation.BOUNCE);			}
//console.log(dataRating);
			var infoContent = place.name + "<br>" 
					+ "Rating of this place:<br>"
					+ "Dead: " + deadRate + "  Just Right: " + justRightRate + "  Jumping Jumping!: " + jumpingRate + "<br>"
					+ "Has cover charge: " + coverChargeRate + "<br>"
					+ "Too many dudes!: " + crowdedRate + "<br>"
					+ "Prices are way too high!: " + expensiveRate + "<br>"
					+ "I can't hear my thoughts!: " + loudRate + "<br>"
					+ "Good for big groups: " + bigGroupsRate + "<br>"
					+ "Good for small groups: " + smallGroupsRate + "<br>"
					+ "This place is safe: " + safePlaceRate + "<br>"
					+ "Good parking options: " + goodParkingRate + "<br>";
			var userId = ${userId};
			google.maps.event
					.addListener(
							marker,
							'click',
							function() {
								$("#currentUser").val(userId);
								$("#currentPlace").val(place.id);
								$("#latForm").val(map.getCenter().lat());
								$("#lngForm").val(map.getCenter().lng());
								$("#zoomForm").val(map.getZoom());
								$("#placeName").html(place.name);
								
								//console.log("test");
								var content = infoContent;
								infowindow.setContent(content);
								infowindow.open(map, this);								
							});
		}
		
	</script>
	<!--BOOTSTRAP JARS  -->
 <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>










