package com.brhackday.ehnohs;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import com.simpleyql.Api;
import com.simpleyql.ApiFactory;

@SuppressWarnings("serial")
public class Social_Data_MashupServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    if (req.getSession().getAttribute("api") == null && req.getSession().getAttribute("authdata") == null ) {
		
			Api api = ApiFactory.getApiInstance(
		            "dj0yJmk9Y1BZV0dXNGplZGY5JmQ9WVdrOVZYbGxTVVJGTTJVbWNHbzlNVEUxTWpFeU1qYzJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD1mZg--",
		            "9545c6d12f33582bd070958186d8b1ddd2c8b8f2",
		            "http://socialdatamashup.appspot.com/simpleyqlcallback",
		            false, null);
		    
		    req.getSession().setAttribute("api", api);
			try {
				api.askAuthorization(req, resp, "http://socialdatamashup.appspot.com/realizaqueryautorizada");
			} catch (Exception e) {
				System.out.println("asdasd");
			}
	    } else {
	    	RequestDispatcher dispatcher = req.getRequestDispatcher("/realizaqueryautorizada");
	    	try {
	    		dispatcher.forward(req,	 resp);
	    	} catch ( Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
