package com.brhackday.ehnohs;
import java.io.IOException;
import javax.servlet.http.*;

import com.simpleyql.Api;
import com.simpleyql.ApiFactory;
import com.simpleyql.QueryResult;

@SuppressWarnings("serial")
public class RealizaQueryAutorizadaServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    String authData = req.getParameter("authdata");
	    
	    if (authData != null) {
	    	req.getSession().setAttribute("authdata", authData);
	    }
	    
	    try {
	    	Api api = (Api)req.getSession().getAttribute("api");
	    	
		    QueryResult qr = api.query("use 'http://www.yqlblog.net/samples/twitter.status.xml'; insert into twitter.status (status,username,password) values (\"Playing with INSERT UPDATE and DELETE in YQL\", \"rsgama\",\"t1168g\")", authData);
		    
	    	QueryResult qr2 = api.query("INSERT INTO meme.user.posts (type, content) VALUES (\"text\", \"socorro!!\")", authData);
	    	
	    	QueryResult qr3 = api.query("select * from xml where url='http://ipinfodb.com/ip_query.php'", authData);
	    	
		    resp.getWriter().append("1" + qr.getText() + "\n\n\n\n\n\n" + qr2.getText() + "\n\n\n\n\n" + qr3.getText());
		    resp.getWriter().flush();
	    } catch(Exception e) {

		    e.printStackTrace(resp.getWriter());
		    resp.getWriter().flush();
	    }
	}
}
