<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link href="https://fonts.googleapis.com/css2?family=Bagel+Fat+One&family=Butterfly+Kids&family=Diphylleia&family=Dongle&family=Hi+Melody&family=Lilita+One&family=New+Rocker&family=Noto+Sans+KR:wght@100..900&family=Playwrite+AU+SA:wght@100..400&family=Sue+Ellen+Francisco&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.6.0/remixicon.min.css" />
<link rel="stylesheet" href="res/css/bootstrap.min.css" />
<link rel="stylesheet" href="res/css/animate.min.css"/>
<link rel="stylesheet" href="res/css/style.css"/>
<script src="res/js/jquery.min.js"></script>
<script src="res/js/popper.min.js"></script>
<script src="res/js/bootstrap.min.js"></script>
</head>
<body>
<tiles:insertAttribute name="header"/>
<div class="container">
<div class="row">
	<div class="col-md-12 pt-md-5 pt-3"><jsp:include page="${skinPath }"/></div>
</div>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>