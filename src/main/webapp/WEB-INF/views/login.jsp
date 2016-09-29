<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.jkmsteam.citypulse.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to cityPulse!</title>

<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />
<script src="<c:url value="/resources/js/jQuery.js" />"></script>
<script>
$(document).ready(function(){
    $("#map").mouseup(function(){
		$("#formLatitude").val(map.getCenter().lat());
    	$("#formLongitude").val(map.getCenter().lng());
    	$("#formZoom").val(map.getZoom());
    });
});

var geocoder;
var map;

function initMap() {
    geocoder = new google.maps.Geocoder();
	map = new google.maps.Map($("#map").get(0), {
		center: {lat: ${lat}, lng: ${lng}},
		zoom: ${zoom}
  });
}

function setCenterMap(geocoder, resultsMap) {
//    var address = "1570 Woodward Ave, Detroit, MI 48226, USA";
    var address = "Detroit, Michigan";
    geocoder.geocode( { 'address': address}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
          resultsMap.setCenter(results[0].geometry.location);
        } else {
          alert("Geocode was not successful for the following reason: " + status);
        }
    });
}
</script>

</head>
<body>
<div class = "container">
<h1>Cool Main Title</h1>
<h2>Subtitle goes here</h2>
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      testAPI();
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      $("#status").html("Please log into this app.");
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
    	$("#status").html("Please log into Facebook.");
    	$("#saveButton").hide(500);
    	$("#welcomeMessage").hide(500);
    }
  }
  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '<%=GlobalVariables.FACEBOOK_APP_ID%>',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.7' // use graph api version 2.7
  });
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
  };
  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', {fields: ['last_name', 'first_name', 'email']}, function(response) {
    	$("#formFbId").val(response.id);
    	$("#formEmail").val(response.email);
    	$("#formFirstName").val(response.first_name);
    	$("#formLastName").val(response.last_name);
    	$("#formLatitude").val(map.getCenter().lat());
    	$("#formLongitude").val(map.getCenter().lng());
    	$("#formZoom").val(map.getZoom());
    	$("#saveButton").show(500);
    	$("#welcomeMessage").show(500);
      console.log('Successful login for: ' + response.email);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.first_name + '!';
      document.getElementById('user').value=currentUser;
    });
  }
</script>
 <div class="FB">
<div id="loginButton" class="fb-login-button" data-max-rows="1"
	scope="public_profile,email,user_friends"
	onlogin="checkLoginState();"
	onlogout="checkLoginState();"
	data-size="xlarge" data-show-faces="false" data-auto-logout-link="true">
</div>

<div id="status"></div>

<form:form method="post" modelAttribute="userForm" action="${login}" id="saveUserForm">
	<p id="welcomeMessage">Select location on the map where you want <br>to search for bars and press the button<br></p>
	<form:button type="submit" id="saveButton" hidden="true">Save and continue</form:button>
	<form:input path="fbId" id="formFbId" type="text" readonly="true" hidden="true"/><br>
	<form:input path="email" id="formEmail" type="text" readonly="true" hidden="true"/><br>
	<form:input path="firstName" id="formFirstName" type="text" readonly="true" hidden="true"/><br>
	<form:input path="lastName" id="formLastName" type="text" readonly="true" hidden="true"/><br>
	<form:input path="latitude" id="formLatitude" type="text" readonly="true" hidden="true"/><br>
	<form:input path="longitude" id="formLongitude" type="text" readonly="true" hidden="true"/><br>
	<form:input path="zoom" id="formZoom" type="text" readonly="true" hidden="true"/><br>
</form:form>
</div>
<p id="currentLocation"></p>

<div id="map"></div>
   <script async defer
     src="https://maps.googleapis.com/maps/api/js?key=<%=GlobalVariables.DISPLAY_MAP_JS_KEY%>&callback=initMap">
   </script>
</div>
</body>
</html>