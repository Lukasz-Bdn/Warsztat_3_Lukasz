package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Exercise;

/**
 * Servlet implementation class ExerciseDetails
 */
@WebServlet("/ExerciseDetails")
public class ExerciseDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int exerciseId = Integer.parseInt(request.getParameter("id"));
		Exercise loadedExercise = Exercise.loadById(exerciseId);
		request.setAttribute("loadedExercise", loadedExercise);
		System.out.println(exerciseId);
		System.out.println(loadedExercise.getTitle());
		getServletContext().getRequestDispatcher("/WEB-INF/exercise_details.jsp").forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int exerciseId = Integer.parseInt(request.getParameter("id"));
		Exercise loadedExercise = Exercise.loadById(exerciseId);
		String newExerciseTitle = request.getParameter("newExerciseTitle");
		String newExerciseDescription = request.getParameter("newExerciseDescription");
		if (!newExerciseTitle.equals("")) {
			loadedExercise.setTitle(newExerciseTitle);
		}
		if (!newExerciseDescription.equals("")) {
			loadedExercise.setDescription(newExerciseDescription);
		}
		loadedExercise.saveToDb();
		getServletContext().getRequestDispatcher("/ExercisePanel").forward(request, response);
		
	}

}
