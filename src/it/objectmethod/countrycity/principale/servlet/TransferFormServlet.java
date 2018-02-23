package it.objectmethod.countrycity.principale.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.objectmethod.countrycity.principale.dao.DaoCity;
import it.objectmethod.countrycity.principale.dao.DaoCountry;
import it.objectmethod.countrycity.principale.dao.impl.DaoCityConcreta;
import it.objectmethod.countrycity.principale.dao.impl.DaoCountryConcreta;
import it.objectmethod.countrycity.principale.pojo.CityBean;
import it.objectmethod.countrycity.principale.pojo.CountryBean;

public class TransferFormServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DaoCountry dcountry=new DaoCountryConcreta();
		List<CountryBean> allCountryCode=dcountry.getAllCountries(); // devo passarla per settare il campo countryCode
		request.setAttribute("allCountryCode", allCountryCode); // passo la lista alla form di add/Update delle city
		
		String id=request.getParameter("id");
		if(Integer.parseInt(id)>0) { // HO UNA CITTA' ESISTENTE
			DaoCity dc=new DaoCityConcreta();
			CityBean cb=dc.getCity(id);
			request.setAttribute("nome", cb.getNome()); 
			request.setAttribute("codiceStato", cb.getCountryCode()); 
			request.setAttribute("distretto", cb.getDistretto()); 
			request.setAttribute("popolazione", cb.getPopolazione());
			request.setAttribute("id", id);
		}else { // HO UNA NUOVA CITTA'
			request.setAttribute("nome", "INSERISCI"); 
			request.setAttribute("codiceStato", null); 
			request.setAttribute("distretto", "INSERISCI"); 
			request.setAttribute("popolazione", "0");
			request.setAttribute("id", "-1");
		}
		
		
		request.getRequestDispatcher("source/formAddAlter.jsp").forward(request, response); // mando alla pagina dei risultati
	}
}
