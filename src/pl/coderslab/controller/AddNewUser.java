package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User;

/**
 * Servlet implementation class AddNewUser
 */
@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		int userGroupId = 1;
		try {
			userGroupId = Integer.parseInt(request.getParameter("user_group_id"));
		} catch (NumberFormatException e) {
			;
		}
		String password = request.getParameter("password");
		User user = new User(username, email, password, userGroupId);
		if (!username.equals("") && !email.equals("") && !password.equals("")) {
			user.saveToDb();
		} 
		getServletContext().getRequestDispatcher("/UsersPanel").forward(request, response);
	}

}
