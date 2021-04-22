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

@WebServlet("/ExecuteInsertAutomobileServlet")
public class ExecuteInsertAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertAutomobileServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// estraggo input
		String marcaInputParam = request.getParameter("marca");
		String genereInputParam = request.getParameter("modello");
		String cilindrataInputStringParam = request.getParameter("cilindrata");
		String dataImmatricolazioneStringParam = request.getParameter("dataImmatricolazione");

		Date dataImmatricolazioneParsed = UtilityAutomobileForm.parseDataPubblicazioneFromString(dataImmatricolazioneStringParam);

		if (!UtilityAutomobileForm.validateInput(marcaInputParam, genereInputParam, cilindrataInputStringParam,
				dataImmatricolazioneStringParam) || dataImmatricolazioneParsed == null || UtilityAutomobileForm.avoidNumberException(cilindrataInputStringParam) == null) {
			
			Automobile temp = new Automobile();			
			temp.setMarca(marcaInputParam);
			temp.setModello(genereInputParam);			
			
			if(UtilityAutomobileForm.avoidNumberException(cilindrataInputStringParam) != null)
				temp.setCilindrata(Integer.parseInt(UtilityAutomobileForm.avoidNumberException(cilindrataInputStringParam)));
			
			temp.setDataImmatricolazione(dataImmatricolazioneParsed);	
			
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("auto_insert", temp);
			
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);		
						
			return;
		}

		Automobile autoInstance = new Automobile(marcaInputParam, genereInputParam,
				Integer.parseInt(cilindrataInputStringParam), dataImmatricolazioneParsed);

		try {

			MyServiceFactory.getAutomobileServiceInstance().inserisciNuovo(autoInstance);
			request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;

		}

		// andiamo ai risultati
		request.getRequestDispatcher("/automobile/results.jsp").forward(request, response);

	}
	

}
