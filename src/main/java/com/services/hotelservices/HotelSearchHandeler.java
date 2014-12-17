package com.services.hotelservices;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HotelDealsService
 */
public class HotelSearchHandeler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HotelSearchHandeler() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SearchHotels(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SearchHotels(request, response);
	}

	public void SearchHotels(HttpServletRequest request,
		HttpServletResponse response) {
		
		//create a service for handelling the request 
		//TODO: make is as generic and get service object from some service factory
		Map params = request.getParameterMap();
		DealService dealService = new HotelDealService();
		String l_resString = dealService.searchDeals(params);
		
		//this servlet handles only json request so mime type is json
		response.setContentType("application/json");
		try {		
			PrintWriter printWriter = response.getWriter();
			printWriter.println(l_resString);
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
