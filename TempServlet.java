// Temp is for Temperature
package my_servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tempServlet")
public class MyServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String imgPath = "images/clear.png";
		
		final String apiKey = "YOUR_API_KEY";
		String city = req.getParameter("cityName");
		String apiURL = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey;
		
		// API Integration
		URL url = new URL(apiURL);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		
		// reading data from network
		InputStreamReader reader = new InputStreamReader(connection.getInputStream());
		Scanner sc = new Scanner(reader);
		
		StringBuilder responseData = new StringBuilder();
		
		while(sc.hasNext()) {
			responseData.append(sc.nextLine());
		}
		
		sc.close();
		
		// type casting - parsing data into JSON
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(responseData.toString(),JsonObject.class);
		
		// date & time
		long dateTimeStamp = jsonObject.get("dt").getAsLong() * 1000;
		String date = new Date(dateTimeStamp).toString();
		
		// temperature
		double tempKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		int tempCelsius = (int)(tempKelvin - 273.15);
		
		// humidity
		int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
		
		//wind speed
		double windspeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		
		// weather condition 
		String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		
		// different weather conditions:
		switch(weatherCondition) 
		{
			case "Clear" :
				imgPath = "images/clear.png";
			    break; 
			case "Clouds" :
				imgPath = "images/cloudsImg.png";
			    break;
			case "Haze"  :
				imgPath = "images/haze.png";
			    break;  
			case "Windy"  :
				imgPath = "images/windy.png";
			    break;      
			case "Rainy" :
				imgPath = "images/rainy.png";
			    break;     
			case "Snow"  :
				imgPath = "images/snow.png";
			    break;           
			case "Sunny" :
				imgPath = "images/sunny.png";
			    break;
			default :
				imgPath = "images/default.png";
				
		}
		
		// set data as a request attributes
		req.setAttribute("date", date);
		req.setAttribute("city", city);
		req.setAttribute("temperature", tempCelsius);
		req.setAttribute("weatherCondition", weatherCondition);
		req.setAttribute("humidity", humidity);
		req.setAttribute("windSpeed", windspeed);
		req.setAttribute("imgPath", imgPath);
		
		connection.disconnect();
		
		req.getRequestDispatcher("/temp.jsp").forward(req, res);
		
	}

}