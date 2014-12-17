package com.services.hotelservices;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.hotelservices.util.AppUtils;

public class HotelDealService implements DealService {
	private static final 
		String SEARCH_URL		=	"HOTELS.DEALS.URL";
	private static final 
		String REQUEST_METHOD	=	"POST";
	private static final
		int BAD_REQUEST_ERROR	=	400;
	private static final
		int INTERNAL_SRVR_ERROR	=	500;
	@Override
	public String searchDeals(Map params) {

		URL serverUrl						= null;
		HttpURLConnection httpConnection 	= null;
		String line_buffer 					= null;
		DataOutputStream outStream			= null;
		DataInputStream inStream 			= null;
		ByteArrayOutputStream binOutStream	= null;
		BufferedReader brResponse 			= null;
		String responseString				= null;
		
		try {
			//create url after reading base url and them appending the request paramaters
			serverUrl = AppUtils.createUrl(AppUtils.getProperty(SEARCH_URL), params);
			httpConnection = (HttpURLConnection) serverUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setUseCaches(false);

			httpConnection.setRequestMethod(REQUEST_METHOD);
			binOutStream = new ByteArrayOutputStream();
			outStream = new DataOutputStream(
					httpConnection.getOutputStream());
			outStream.write(binOutStream.toByteArray());
			outStream.flush();
			outStream.close();

			//check for server error
			if (httpConnection.getResponseCode() == BAD_REQUEST_ERROR
					|| httpConnection.getResponseCode() == INTERNAL_SRVR_ERROR) {
				inStream = new DataInputStream(
						httpConnection.getErrorStream());

			} else {
				inStream = new DataInputStream(
						httpConnection.getInputStream());
			}

			brResponse = new BufferedReader(new InputStreamReader(inStream));
			responseString = "";
			while ((line_buffer = brResponse.readLine()) != null) {
				responseString += line_buffer;

			}
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return the response json as a string
		return responseString;
	}
}
