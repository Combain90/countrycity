package it.objectmethod.countrycity.principale.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import it.objectmethod.countrycity.principale.model.dao.DaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@Controller
public class CountryController{
	
	@Autowired
	private DaoCountry daoCountry;
	
	/*
	public void setDaoCountry(DaoCountry daoCountry) {
		this.daoCountry = daoCountry;
	}
	*/
	//MI SERVE IL SET DELLA DAO PERCHE @Autowired SI POGGIA SUI SET PER INIETTARE LE DIPENDENZE. però funziona anche senza set !!! bhoo

	@RequestMapping("/home")
	public String continents(HttpServletRequest request) {
		
		//ApplicationContext ctx = getIoC();
		//DaoCountry dc=(DaoCountryConcreta)ctx.getBean("daoCountry");
		
		List<String> lista=daoCountry.getContinents();
		request.setAttribute("lista", lista); // passo la lista tramite http. //non fare così con spring by Ivan M.
		return "Index"; // vado alla vista desiderata
	}
	
	@RequestMapping("/country")
	public ModelAndView countriesByContinent(ModelMap map, HttpServletRequest request) {
		
		String continente=request.getParameter("continente"); // uso il getParameter per recuperare i dati		
		if(continente.equals("North") || continente.equals("South")) {
			continente +=" America";
		}
		
		//ApplicationContext ctx = getIoC();
		//DaoCountry dc=(DaoCountryConcreta)ctx.getBean("daoCountry");
		
		List<CountryBean> lista=daoCountry.getCountriesByContinent(continente);
		map.addAttribute("continente", continente);
		
		return new ModelAndView("Country","lista",lista);
	}
	
	/*
	private ApplicationContext getIoC() {
		return new ClassPathXmlApplicationContext("/applicationContext.xml");
	}
	*/
}
