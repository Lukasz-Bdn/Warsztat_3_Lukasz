package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.UserGroup;

/**
 * Servlet implementation class AddNewGroup
 */
@WebServlet("/AddNewGroup")
public class AddNewGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newGroupName = request.getParameter("newGroupName");
		UserGroup newGroup = new UserGroup(newGroupName);
		newGroup.saveToDb();
		getServletContext().getRequestDispatcher("/UsersGroupPanel").forward(request, response);
	}

}