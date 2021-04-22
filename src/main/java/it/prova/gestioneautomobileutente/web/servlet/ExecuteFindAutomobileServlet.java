package it.prova.gestioneautomobileutente.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobileutente.model.Automobile;
import it.prova.gestioneautomobileutente.service.MyServiceFactory;
import it.prova.gestioneautomobileutente.utility.UtilityAutomobileForm;

@WebServlet("/ExecuteFindAutomobileServlet")
public class ExecuteFindAutomobileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ExecuteFindAutomobileServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String marcaInputParam = request.getParameter("marca");
		String modelloInputParam = request.getParameter("modello");
		String cilindrataInputStringParam = request.getParameter("cilindrata");
		String dataImmatricolazioneStringParam = request.getParameter("dataImmatricolazione");

		Date dataImmatricolazioneParsed = UtilityAutomobileForm.parseDataPubblicazioneFromString(dataImmatricolazioneStringParam);

		if (marcaInputParam.isEmpty() && modelloInputParam.isEmpty() && cilindrataInputStringParam.isEmpty() 
				&& dataImmatricolazioneStringParam.isEmpty() ) {
			try {
				request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
				request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
				return;
			}
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/automobile/results.jsp").forward(request, response);
			return;
		}
		
		Integer temp = 0;
		if (!cilindrataInputStringParam.isEmpty()) {
			temp = Integer.parseInt(cilindrataInputStringParam);
		}
		
		Automobile automobileInstance = new Automobile(marcaInputParam, modelloInputParam, temp, dataImmatricolazioneParsed);
		
		try {
			request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().cercaPerEsempio(automobileInstance));
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/results.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("automobile/results.jsp").forward(request, response);
		
	}

}
