package it.objectmethod.countrycity.principale.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import it.objectmethod.countrycity.principale.model.dao.DaoCity;
import it.objectmethod.countrycity.principale.model.dao.DaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CityBean;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@Controller
@RequestMapping(value="/api/cities")
public class CityController {
	
	@Autowired
	private DaoCity daoCity;
	@Autowired
	private DaoCountry daoCountry;
	
	@RequestMapping("/{codiceStato}")
	@ResponseBody
	public List<CityBean> citiesByCountry (@PathVariable("codiceStato") String codice) {
		List<CityBean> lista=null;
		lista=daoCity.getCitiesByCode(codice);
		return lista;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteCity( @RequestParam("id") String id){
		
		int ris=daoCity.deleteCityById(id);
		
		String risultato="TUPLA ELIMINATA CORRETTAMENTE";
		if(ris==0) {
			risultato="ERRORE DURANTE L'ELIMINAZIOEN";
		}
		return new ModelAndView("Risposta","risultato",risultato);
	}
	
	@RequestMapping("/form")
	public String formCity(ModelMap map, @RequestParam("id")String id){
		
		List<CountryBean> allCountryCode=daoCountry.getAllCountries(); // devo passarla per settare il campo countryCode
		map.addAttribute("allCountryCode", allCountryCode); // passo la lista alla form di add/Update delle city
		
		if(Integer.parseInt(id)>0) { // HO UNA CITTA' ESISTENTE
			
			
			CityBean cb=daoCity.getCity(id);
			map.addAttribute("nome", cb.getNome()); 
			map.addAttribute("codiceStato", cb.getCountryCode()); 
			map.addAttribute("distretto", cb.getDistretto()); 
			map.addAttribute("popolazione", cb.getPopolazione());
			map.addAttribute("id", id);
		}else { // HO UNA NUOVA CITTA'
			map.addAttribute("nome", "INSERISCI"); 
			map.addAttribute("codiceStato", null); 
			map.addAttribute("distretto", "INSERISCI"); 
			map.addAttribute("popolazione", "0");
			map.addAttribute("id", "-1");
		}
		
		return "Form";
	}
	
	@RequestMapping("/addUpdate")
	public ModelAndView addUpdateCity(HttpServletRequest request){
		
		String id=request.getParameter("id");
		String nome=request.getParameter("nome");
		String codiceStato=request.getParameter("codiceStato");
		String popolazione=request.getParameter("popolazione");
		String distretto=request.getParameter("distretto");
		
		int ris=0;
		if(Integer.parseInt(id)>0) { // HO UNA CITTA' ESISTENTE
			ris=daoCity.updateCity(id, nome, codiceStato, popolazione, distretto);
		}else { // HO UNA NUOVA CITTA'
			ris=daoCity.addCity(nome, codiceStato, popolazione, distretto);	
		}
		
		String risultato="TUPLA MODIFICATA/INSERITA CORRETTAMENTE";
		if(ris==0) {
			risultato="ERRORE DURANTE L'INSERIMENTO/MODIFICA";
		}
		
		return new ModelAndView("Risposta","risultato",risultato);
	}
}
