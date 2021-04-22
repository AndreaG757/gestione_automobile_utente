package it.prova.gestioneautomobileutente.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobileutente.model.Automobile;

@WebServlet("/PrepareInsertAutomobileServlet")
public class PrepareInsertAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		
		request.setAttribute("auto_insert", new Automobile());
		request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
	}

}
