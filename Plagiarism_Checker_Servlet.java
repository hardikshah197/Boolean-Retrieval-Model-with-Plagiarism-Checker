

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearcherServlet
 */
@WebServlet("/SearcherServlet")
public class Plagiarism_Checker_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Plagiarism_Checker_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");  
        PrintWriter out = response.getWriter(); 
        
        out.println("<html>");
        out.println("<body>");
		
        FileReader f2 = new FileReader("./files/searchFile/search.txt");
        BufferedReader f2s = new BufferedReader(f2);
        List<String> st = new ArrayList<String>();
        
        //adding searchable words to a list
        try{
        	String temp;
        	while((temp = f2s.readLine()) != null){
        		st.add(temp);
        	}
        }
        catch(Exception e){
        	System.out.println(e);
        }
        String s;        
        for(int j=0;j<st.size();j++){
	        String d=st.get(j).toLowerCase();
	        int i = 0; 
	        FileReader f=  new FileReader("./files/test.txt");
	        BufferedReader fs = new BufferedReader(f);
	        try{
	        	while((s = fs.readLine()) != null){
	        		s=s.toLowerCase();
	        		while(s.contains(d)){
	        			int index = s.indexOf(d) + d.length();
	        			s=s.substring(index);
	        			i++;
	        		}
	        	}
//	        	
	        	System.out.println(d+" ----  "+i);
	        }
	        catch(Exception e){
	        	System.out.println(e);
	        }
        }
        
		out.print("<div><center><h1>Successfully find by servlet</h1></center></div>");
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
