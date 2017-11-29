package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Exercise;

/**
 * Servlet implementation class AddNewExercise
 */
@WebServlet("/AddNewExercise")
public class AddNewExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewExercise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newExTitle = request.getParameter("newExTitle");
		String newExDescription = request.getParameter("newExDescription");
		Exercise exerciseCreated = new Exercise(newExTitle, newExDescription);
		if (!newExTitle.equals("") && !newExDescription.equals("")) {
			exerciseCreated.saveToDb();
		}
		getServletContext().getRequestDispatcher("/ExercisePanel").forward(request, response);
	}

}
