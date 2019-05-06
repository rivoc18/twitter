import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 *  Servlet implementation class HelloWorld
 */
public class Operation extends HttpServlet {
 

/**
 * Default constructor.
 */
 public Operation() {
 }
 
 /*
 * This method will handle all GET request.
 */
 protected void doGet(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException {
 
 	response.setContentType( " text / plain " );
 	String a=request.getParameter("a");
 	String b=request.getParameter("b");
 	String o=request.getParameter("o");
 	int s=0;
 	if(o.equals("addition")) {
 		s=Integer.parseInt(a)+Integer.parseInt(b);
 	}
 	if(o.equals("soustraction")) {
 		s=Integer.parseInt(a)-Integer.parseInt(b);
 	}
 	if(o.equals("multiplication")) {
 		s=Integer.parseInt(a)*Integer.parseInt(b);
 	}
 	if(o.equals("divison")) {
 		s=Integer.parseInt(a)/Integer.parseInt(b);
 	}
	PrintWriter out = response.getWriter ();
	out.println(s);
 }
 

}