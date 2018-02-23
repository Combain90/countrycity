package it.objectmethod.countrycity.principale.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.countrycity.principale.dao.DaoCountry;
import it.objectmethod.countrycity.principale.dao.impl.DaoCountryConcreta;
import it.objectmethod.countrycity.principale.pojo.CountryBean;

public class IndexServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DaoCountry dc=new DaoCountryConcreta();
		List<String> lista=dc.getContinents();
		request.setAttribute("lista", lista); // passo la lista
		request.getRequestDispatcher("source/Index.jsp").forward(request, response); // mando alla pagina dei risultati
	}
}
