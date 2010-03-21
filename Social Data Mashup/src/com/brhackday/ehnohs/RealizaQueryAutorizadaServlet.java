package com.brhackday.ehnohs;
import java.io.IOException;
import javax.servlet.http.*;

import com.simpleyql.Api;
import com.simpleyql.ApiFactory;
import com.simpleyql.QueryResult;
import com.sun.xml.internal.fastinfoset.dom.DOMDocumentParser;

@SuppressWarnings("serial")
public class RealizaQueryAutorizadaServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    String authData = req.getParameter("authdata");
	    
	    if (authData != null) {
	    	req.getSession().setAttribute("authdata", authData);
	    }
	    
	    try {
	    	Api api = (Api)req.getSession().getAttribute("api");
	    	
		    QueryResult qr = api.query("use 'http://www.yqlblog.net/samples/twitter.status.xml'; insert into twitter.status (status,username,password) values ('Playing with INSERT UPDATE and DELETE in YQL', 'rsgama','t1168g')", authData);
		    qr.getText();
		    
	    	QueryResult latlong = api.query("select * from xml where url='http://ipinfodb.com/ip_query.php'",authData);
	    	
	    	String res = latlong.getText();
	    	
	    	resp.getWriter().append(res);
	    	String lat = res.substring(res.indexOf("<Latitude>") + "<Latitude>".length(), res.indexOf("</Latitude>"));
	    	String longi = res.substring(res.indexOf("<Longitude>") + "<Longitude>".length(), res.indexOf("</Longitude>"));
	    	
	    	resp.getWriter().append(lat + "\n");
	    	resp.getWriter().append(longi + "\n");
	    	
	    	QueryResult qr3 = api.query("select * from maps.map where latitude = " + lat + " and longitude = " + longi, authData);

	    	res = qr3.getText();
	    	resp.getWriter().append("\n\n\n\n\n\n"+res+"\n\n\n\n\n\n");

	    	String t = res.substring(res.indexOf("<Result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">") + "<Result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">".length(), res.indexOf("</Result>"));
	    	
	    	resp.getWriter().append("INSERT INTO meme.user.posts (type, content) VALUES (\"text\", \"<a href='"+t+"'></a>\")");
	    	
	    	QueryResult qr2 = api.query("INSERT INTO meme.user.posts (type, content) VALUES (\"text\", \"socorro!!<a href='"+t+"'>Estou aqui!!</a>\")", authData);
	    	
	    	
		    resp.getWriter().append("1" + qr2.getText() + "\n\n\n\n\n" + qr2.getText());
		    resp.getWriter().flush();
	    } catch(Exception e) {

		    e.printStackTrace(resp.getWriter());
		    resp.getWriter().flush();
	    }
	}
}
