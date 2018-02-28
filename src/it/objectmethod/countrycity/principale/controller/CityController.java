package it.objectmethod.countrycity.principale.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import it.objectmethod.countrycity.principale.model.dao.DaoCity;
import it.objectmethod.countrycity.principale.model.dao.DaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CityBean;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@Controller
public class CityController {
	
	@Autowired
	private DaoCity daoCity;
	@Autowired
	private DaoCountry daoCountry;
	
	/*
	public void setDaoCountry(DaoCountry daoCountry) {
		this.daoCountry = daoCountry;
	}	
	public void setDaoCity(DaoCity daoCity) {
		this.daoCity = daoCity;
	} */
	//MI SERVONO I SET DELLE DAO PERCHE @Autowired SI POGGIA SUI SET PER INIETTARE LE DIPENDENZE. però funziona anche senza set !!! bhoo
	
	@RequestMapping("/city")
	public String citiesByCountry (@RequestParam("codice") String codiceStato, @RequestParam("continente") String continente, ModelMap map) {
		
		//ApplicationContext ctx = getIoC();
		// DaoCity dc=(DaoCityConcreta)ctx.getBean("daoCity");
		
		List<CityBean> lista=daoCity.getCitiesByCode(codiceStato);
		
		map.addAttribute("lista", lista); // passo la lista
		map.addAttribute("codiceStato", codiceStato);
		map.addAttribute("continente", continente);
		return "City";
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteCity( @RequestParam("id") String id){
		
		//ApplicationContext ctx = getIoC();
		//DaoCity dc=(DaoCityConcreta)ctx.getBean("daoCity");
		
		int ris=daoCity.deleteCityById(id);
		
		String risultato="TUPLA ELIMINATA CORRETTAMENTE";
		if(ris==0) {
			risultato="ERRORE DURANTE L'ELIMINAZIOEN";
		}
		return new ModelAndView("Risposta","risultato",risultato);
	}
	
	@RequestMapping("form")
	public String formCity(ModelMap map, @RequestParam("id")String id){
		
		//ApplicationContext ctx = getIoC();
		//DaoCountry dcountry=(DaoCountryConcreta)ctx.getBean("daoCountry");
		
		List<CountryBean> allCountryCode=daoCountry.getAllCountries(); // devo passarla per settare il campo countryCode
		map.addAttribute("allCountryCode", allCountryCode); // passo la lista alla form di add/Update delle city
		
		if(Integer.parseInt(id)>0) { // HO UNA CITTA' ESISTENTE
			
			//DaoCity dc=(DaoCityConcreta)ctx.getBean("daoCity");
			
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
	
	@RequestMapping("addUpdate")
	public ModelAndView addUpdateCity(HttpServletRequest request){
		
		String id=request.getParameter("id");
		String nome=request.getParameter("nome");
		String codiceStato=request.getParameter("codiceStato");
		String popolazione=request.getParameter("popolazione");
		String distretto=request.getParameter("distretto");
		
		//ApplicationContext ctx = getIoC();
		//DaoCity dc=(DaoCityConcreta)ctx.getBean("daoCity");
		
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
	
	/* ---- NON SERVE PERCHE' USO LE ANNOTAZIONI
	private ApplicationContext getIoC() {
		return new ClassPathXmlApplicationContext("/applicationContext.xml");
	}
	*/
}
