package it.objectmethod.countrycity.principale.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.countrycity.principale.model.dao.IDaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@RestController
@CrossOrigin
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
	
	@RequestMapping(value="/list")
	public List<CountryBean> getAllCountries(){
		List<CountryBean> list=null;
		list=daoCountry.getAllCountries();
		return list;
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
