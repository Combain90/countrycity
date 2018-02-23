package it.objectmethod.countrycity.principale.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.countrycity.principale.dao.DaoCity;
import it.objectmethod.countrycity.principale.dao.impl.DaoCityConcreta;
import it.objectmethod.countrycity.principale.pojo.CityBean;

public class AddAlterCityServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String nome=request.getParameter("nome");
		String codiceStato=request.getParameter("codiceStato");
		String popolazione=request.getParameter("popolazione");
		String distretto=request.getParameter("distretto");
		DaoCity dc=new DaoCityConcreta();
		int ris=0;
		if(Integer.parseInt(id)>0) { // HO UNA CITTA' ESISTENTE
			ris=dc.updateCity(id, nome, codiceStato, popolazione, distretto);
		}else { // HO UNA NUOVA CITTA'
			ris=dc.addCity(nome, codiceStato, popolazione, distretto);	
		}
		
		String risultato="TUPLA MODIFICATA/INSERITA CORRETTAMENTE";
		if(ris==0) {
			risultato="ERRORE DURANTE L'INSERIMENTO/MODIFICA";
		}
		
		request.setAttribute("risultato", risultato);
		request.getRequestDispatcher("source/Risposta.jsp").forward(request, response); // mando alla pagina dei risultati
	}
}
