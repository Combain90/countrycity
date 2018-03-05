package it.objectmethod.countrycity.principale.rest.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.countrycity.principale.model.dao.IDaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;
import it.objectmethod.countrycity.principale.model.pojo.CountryLanguageBean;

@RestController
@RequestMapping(value="/api/countries")
public class CountryRestController{
	
	@Autowired
	private IDaoCountry daoCountry;
	
	@RequestMapping(value="/continents")
	public List<String> continents() {
		List<String> lista=null;
		lista=daoCountry.getContinents();
		return lista;
	}
	
	@RequestMapping("/{continent}/list")
	public List<CountryBean> countriesByContinent(@PathVariable("continent")String continente) {
		List<CountryBean> lista=null;
		lista=daoCountry.getCountriesByContinent(continente);
		return lista;
	}
	
	@GetMapping(value="/languages")
	public List<CountryLanguageBean> getCountriesLanguages(){
		List<CountryLanguageBean> ret=null;
		ret=daoCountry.getCountriesLanguages();
		return ret;
	}
	
	@GetMapping(value="languages/{stato}/list")
	public List<String> languagesByCountry(@PathVariable("stato") String code){
		List<String> ret=null;
		ret=daoCountry.getLanguagesByCountryCode(code);
		return ret;
	}
	
//	@RequestMapping(value="/{countryCode}", method=RequestMethod.GET)
//	public CountryBean getCountry() {
//		
//	}
//	
//	@RequestMapping(value="/{countryCode}", method=RequestMethod.DELETE)
//	public int deleteCountry() {
//		
//	}
//	
//	@RequestMapping(value="/{countryCode}", method=RequestMethod.PUT)
//	public CountryBean addEditCountry() {
//		
//	}
	
//	@GetMapping(value="/{countryCode}")
//	public CountryBean getCountry() {
//		
//	}
//	
//	@DeleteMapping(value="/{countryCode}")
//	public int deleteCountry() {
//		
//	}
//	
//	@PutMapping(value="/{countryCode}")
//	public CountryBean addEditCountry() {
//		
//	}

}
