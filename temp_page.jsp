<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Temperature by RB</title>
    <link rel="stylesheet" href="temp.css" />
</head>
<body>
	
	<div id="outerDiv">
      <div class="container">

        <form action="tempServlet" method="post">
          <input type="text" name="cityName" placeholder="Enter city name..." />
          <input type="submit" value=" Go! " />
        </form>

        <div id="imageC">
          <img src="${imgPath}" alt="nature img" />
        </div>

        <div id="smallButtons">
          <span>${date}</span>
          <span>${city}</span>
        </div>

        <div id="content">
          <h2>${temperature}<sup>o</sup> Celcius</h2>
          <p>${weatherCondition}</p>
        </div>

        <div class="bottomCards">
          <div class="card1">
            <img src="images/humidity.png" alt="cardImg" class="cardImg" />
            <h4>Humidity : ${humidity}%</h4>
          </div>

          <div class="card2">
            <img src="images/windSpeed.png" alt="cardImg" class="cardImg" />
            <h4>Wind Speed : ${windSpeed} Km/Hr</h4>
          </div>
        </div>

      </div>
    
    </div>
	
</body>
</html>