package com.booking.utility;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class CommonUtility {
	
	public String getURLBase(String request) throws MalformedURLException {

	    URL requestURL = new URL(request);
	    String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
	    return requestURL.getProtocol() + "://" + requestURL.getHost() + port+"/management/";

	}
}
