package it.prova.gestioneautomobileutente.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobileutente.service.MyServiceFactory;



@WebServlet("/PrepareDeleteAutomobileServlet")
public class PrepareDeleteAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       

    public PrepareDeleteAutomobileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		String idAutomobileParam = request.getParameter("idAutomobile");

		try {
			request.setAttribute("automobileDaEliminare", MyServiceFactory.getAutomobileServiceInstance().caricaSingoloElemento(Long.parseLong(idAutomobileParam)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/automobile/delete.jsp").forward(request, response);
	}

}
