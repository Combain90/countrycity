package it.objectmethod.countrycity.principale.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.countrycity.principale.model.dao.impl.DaoLanguage;
import it.objectmethod.countrycity.principale.model.pojo.CountryLanguageBean;

@RestController
@CrossOrigin
@RequestMapping(value="/api/languages")
public class LanguageRestController {
	
	@Autowired
	private DaoLanguage daoLanguage;
	
	@GetMapping(value="/list")
	public List<CountryLanguageBean> getCountriesLanguages(){
		List<CountryLanguageBean> ret=null;
		ret=daoLanguage.getCountriesLanguages();
		return ret;
	}
	
	@GetMapping(value="/{stato}/list")
	public List<String> languagesByCountry(@PathVariable("stato") String code){
		List<String> ret=null;
		ret=daoLanguage.getLanguagesByCountryCode(code);
		return ret;
	}
}
