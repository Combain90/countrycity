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

public class CountryServlet extends HttpServlet {
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String continente=request.getParameter("continente");
			
			if(continente.equals("North") || continente.equals("South")) {
				continente +=" America";
			}
			
			DaoCountry dc=new DaoCountryConcreta();
			List<CountryBean> lista=dc.queryCountry(continente);
			request.setAttribute("lista", lista); // passo la lista
			request.setAttribute("continente", continente);
			request.getRequestDispatcher("source/Country.jsp").forward(request, response); // mando alla pagina dei risultati
		}
	}