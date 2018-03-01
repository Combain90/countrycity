package it.objectmethod.countrycity.principale.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.countrycity.principale.model.dao.DaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@RestController
@RequestMapping(value="/api/countries")
public class CountryController{
	
	@Autowired
	private DaoCountry daoCountry;
	
	@RequestMapping("/continents")
	public List<String> continents() {
		List<String> lista=null;
		lista=daoCountry.getContinents();
		return lista; // vado alla vista desiderata
	}
	
	@RequestMapping("/{continent}/list")
	public List<CountryBean> countriesByContinent(@PathVariable("continent")String continente) {
		List<CountryBean> lista=null;
		lista=daoCountry.getCountriesByContinent(continente);
		return lista;
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
