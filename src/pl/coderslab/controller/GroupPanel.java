package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.UserGroup;

/**
 * Servlet implementation class GroupPanel
 */
@WebServlet("/GroupPanel")
public class GroupPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupPanel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupId = Integer.parseInt(request.getParameter("id"));
		UserGroup userGroup = UserGroup.loadById(groupId);
		request.setAttribute("userGroup", userGroup);
		getServletContext().getRequestDispatcher("/WEB-INF/group_panel.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newGroupName = request.getParameter("newGroupName");
		int groupId = Integer.parseInt(request.getParameter("id"));
		UserGroup updatedGroup = new UserGroup(groupId, newGroupName);
		updatedGroup.saveToDb();
		getServletContext().getRequestDispatcher("/UsersGroupPanel").forward(request, response);;
//		doGet(request, response);
	}

}