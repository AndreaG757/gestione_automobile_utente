package it.prova.gestioneautomobileutente.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobileutente.model.Utente;
import it.prova.gestioneautomobileutente.service.MyServiceFactory;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usernameParam = request.getParameter("inputUsername");
		String pswParam = request.getParameter("inputPassword");
		
		Utente utente = null;
		try {
			
			utente = MyServiceFactory.getUtenteServiceInstance().findByUsernameAndPassword(usernameParam, pswParam);						
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/login.jsp");
		}
		
		if(utente != null) {
			request.getSession().setAttribute("userInfo", utente);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/login.jsp").forward(request, response);	
		}
			
	}

}
