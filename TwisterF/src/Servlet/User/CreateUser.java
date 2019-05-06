package Servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType( " text / plain " );
		String login=request.getParameter("login");
		String password=request.getParameter("password");
		
		PrintWriter out = response.getWriter ();
		try {
			JSONObject retour=Service.User.createUser(login,password);
			out.printf(retour.toString());
			//createUser?login=jere&password=123
		} catch (JSONException e) {
			response.setContentType("text/plain");
			out.print(e.getMessage());
			out.print("1");
		} catch (SQLException e) {
			response.setContentType("text/plain");
			out.print(e.getMessage());
			out.print("2");
		} catch (InstantiationException e) {
			response.setContentType("text/plain");
			out.print(e.getMessage());
			out.print("3");
		} catch (IllegalAccessException e) {
			response.setContentType("text/plain");
			out.print(e.getMessage());
			out.print("4");
		} catch (ClassNotFoundException e) {
			response.setContentType("text/plain");
			out.print(e.getMessage());
			out.print("5");
		}
		
	}

}
