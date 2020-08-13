

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * Servlet implementation class MosaicAPIServlet
 */
@WebServlet("/MosaicAPIServlet")
public class MosaicAPIServlet extends HttpServlet {
	
	private Main server;
	private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MosaicAPIServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig sg) throws ServletException {
    	super.init(sg);
    	server = new Main();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   try {
		System.out.println("New API Connection from " + request.getRemoteAddr() + ": " + request.getQueryString());
	    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		String[] pairs = request.getQueryString().split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
		PrintWriter out = response.getWriter();
		if (query_pairs.get("task").equals("getall")) {
			server.getDBUtil().getAllBusinesses(out);
		}
		else if (query_pairs.get("task").equals("categoryquery")) {
			server.getDBUtil().getBusinessesFromCategory(query_pairs.get("categoryString"),out);
		}
		else if (query_pairs.get("task").equals("textquery")) {
			String qt = query_pairs.get("querytext");
			server.getDBUtil().returnBusinessesInJSON(qt,out);
		}
		else if (query_pairs.get("task").equals("login")) {
			server.getDBUtil().loginUser(query_pairs.get("username"),query_pairs.get("password"), out);
		}
		else if (query_pairs.get("task").equals("register")) {
			server.getDBUtil().registerUser(query_pairs.get("username"),query_pairs.get("password"), out);
		}
		else if (query_pairs.get("task").equals("addrating")) {
			server.getDBUtil().addRating(Double.parseDouble(query_pairs.get("rating")), query_pairs.get("business"), out);
		}
		else if(query_pairs.get("task").equals("getrating")) {
			server.getDBUtil().getRating(query_pairs.get("business"), out);
		}
		else { 
			server.getDBUtil().getAllBusinesses(out);
		}
		System.out.println("Returning data to " + request.getRemoteAddr() + ".");
		//String jsonString = this.gson.toJson(rl);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    //out.println("API Servlet");
	    //out.print(jsonString);
	    out.flush();   
	   } catch (Exception sqle) {
		   System.out.println(sqle.getMessage());
	   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
