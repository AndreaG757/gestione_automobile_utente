package it.prova.gestioneautomobileutente.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestioneautomobileutente.service.MyServiceFactory;



@WebServlet("/ExecuteVisualizzaAutomobileServlet")
public class ExecuteVisualizzaAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		String idAutomobileParam = request.getParameter("idAutomobile");

		if (!NumberUtils.isCreatable(idAutomobileParam)) {
			
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
			
		}

		try {
			request.setAttribute("visualizza_automobile_attr", MyServiceFactory.getAutomobileServiceInstance().caricaSingoloElemento(Long.parseLong(idAutomobileParam)));
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/automobile/show.jsp").forward(request, response);
	}

}
