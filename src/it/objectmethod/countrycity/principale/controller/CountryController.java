package it.objectmethod.countrycity.principale.controller;


import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.countrycity.principale.model.dao.DaoCountry;
import it.objectmethod.countrycity.principale.model.dao.impl.DaoCountryConcreta;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@Controller
public class CountryController{
	
	@RequestMapping("/home")
	private String continents(HttpServletRequest request) {
		
		DaoCountry dc=new DaoCountryConcreta();
		List<String> lista=dc.getContinents();
		request.setAttribute("lista", lista); // passo la lista tramite http.
		return "Index"; // vado alla vista desiderata
	}
	
	@RequestMapping("/country")
	public ModelAndView countriesByContinent(ModelMap map, HttpServletRequest request) {
		
		String continente=request.getParameter("continente"); // uso il getParameter per recuperare i dati
		
		if(continente.equals("North") || continente.equals("South")) {
			continente +=" America";
		}
		
		DaoCountry dc=new DaoCountryConcreta();
		List<CountryBean> lista=dc.getCountriesByContinent(continente);
		map.addAttribute("continente", continente);
		return new ModelAndView("Country","lista",lista);
	}
}
