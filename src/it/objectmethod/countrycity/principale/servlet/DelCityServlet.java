package it.objectmethod.countrycity.principale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.countrycity.principale.dao.DaoCity;
import it.objectmethod.countrycity.principale.dao.impl.DaoCityConcreta;

public class DelCityServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("id");
		DaoCity dc=new DaoCityConcreta();
		int ris=dc.deleteRecord(id);
		
		String risultato="TUPLA ELIMINATA CORRETTAMENTE";
		if(ris==0) {
			risultato="ERRORE DURANTE L'ELIMINAZIOEN";
		}
		
		request.setAttribute("risultato", risultato);
		request.getRequestDispatcher("source/RisDel.jsp").forward(request, response); // mando alla pagina dei risultati
	}
}
