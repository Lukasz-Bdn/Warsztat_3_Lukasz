package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User loadedUser = User.loadUserById(id);
		request.setAttribute("loadedUser", loadedUser);
		getServletContext().getRequestDispatcher("/WEB-INF/edit_user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newUsername = request.getParameter("newUsername");
		String newEmail = request.getParameter("newEmail");
		String newUserGroupId = request.getParameter("newUserGroupId");
		int userId = Integer.parseInt(request.getParameter("id"));
		User editedUser = User.loadUserById(userId);
		if (!newUsername.equals("")) {
			editedUser.setUsername(newUsername);
		}
		if (!newEmail.equals("")) {
			editedUser.setEmail(newEmail);
		}
		if (!newUserGroupId.equals("")) {
			int newUserGroupIdInt = Integer.parseInt(newUserGroupId);
			editedUser.setUser_group_id(newUserGroupIdInt);
		}
		editedUser.saveToDb();
		getServletContext().getRequestDispatcher("/UsersPanel").forward(request, response);
	}

}