<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<spring:url value="/resources/core/css/bootstrap.min.css"
var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<title>Insert title here</title>
<style>
.btn::after{content:" (5)"
}
</style>
</head>
<body>
<h1>All Users</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>framework</th>
					<th>Action</th>
				</tr>
			</thead>

<%-- 			<c:forEach var="user" items="${users}">
 --%>			   
 
  <tr>
				<td>
					123456
				
				</td>
				<td>bar name</td>
				<td>bar@email.com</td>
				<td>
                                 <%--  <c:forEach var="framework" items="${user.framework}"
                                                             varStatus="loop"> --%>
					dead bar
    				      <%--   <c:if test="${not loop.last}">,</c:if>
				  </c:forEach> --%>
                                </td>
				<td>
				  <spring:url value="/users/${user.id}" var="userUrl" />
				  <spring:url value="/users/${user.id}/delete" var="deleteUrl" />
				  <spring:url value="/users/${user.id}/update" var="updateUrl" />

				  <button class="btn btn-info"
                                          onclick="location.href='/citypulse/vote?ratetype=dead&placeId=ChIJ4VGa2DMtO4gRZiAz2kIFeak'">dead</button>
				  <button class="btn btn-primary"
                                          onclick="location.href='/citypulse/vote?ratetype=jumping'">jumpin</button>
				  <button class="btn btn-danger"
                                          onclick="location.href='/citypulse/vote?ratetype=dudes'">too many dudes</button>
                                </td>
			    </tr>
			<%-- </c:forEach> --%>
		</table>

	</div>
</body>
</html>