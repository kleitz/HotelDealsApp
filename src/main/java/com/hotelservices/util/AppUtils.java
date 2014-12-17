package com.hotelservices.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class AppUtils {
	private static boolean isLoaded=false;
	private static Properties properties;
	
	/**
	 * This method loads the properties file if not already loaded and returns the desired property
	 * @param propName
	 * @return
	 */
	public static String getProperty(String propName){
		InputStream input = null;
		if(properties==null || !isLoaded){
			properties = new Properties();
			try{
			input = AppUtils.class.getResourceAsStream("/project.properties");
			 
			// load a properties file
			properties.load(input);
	 
			// get the property value and print it out
			
			}catch( FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return properties.getProperty(propName);
	}
	/**
	 * This method creates the encoded url from base url and parameters
	 * @param baseURL
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 */
	public static URL createUrl(String baseURL,Map params) throws UnsupportedEncodingException, MalformedURLException{
		
		Iterator l_i = null;

		l_i = params.keySet().iterator();
		String urlString =  baseURL+ "?";
		while (l_i.hasNext()) {
				String key = (String) l_i.next();
				String value = URLEncoder.encode(
						((String[]) params.get(key))[0],
						AppUtils.getProperty("URL.ENCODING"));
				urlString += key + "=" + value + "&";
		}
		return new URL(urlString);
	}

}
