package com.brhackday.ehnohs;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Social_Data_MashupServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
