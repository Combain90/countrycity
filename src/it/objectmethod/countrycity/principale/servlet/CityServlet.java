package it.objectmethod.countrycity.principale.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.countrycity.principale.dao.DaoCity;
import it.objectmethod.countrycity.principale.dao.impl.DaoCityConcreta;
import it.objectmethod.countrycity.principale.pojo.CityBean;

public class CityServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codiceStato=request.getParameter("codice");
		String continente=request.getParameter("continente");
		
		DaoCity dc=new DaoCityConcreta();
		List<CityBean> lista=dc.queryCityPopolation(codiceStato);
		request.setAttribute("lista", lista); // passo la lista
		request.setAttribute("codiceStato", codiceStato);
		request.setAttribute("continente", continente);
		request.getRequestDispatcher("source/City.jsp").forward(request, response); // mando alla pagina dei risultati
	}

}
