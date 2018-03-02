package it.objectmethod.countrycity.principale.model.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.model.pojo.CountryBean;
import it.objectmethod.countrycity.principale.model.pojo.CountryLanguageBean;

public interface IDaoCountry {
	public List<String> getContinents();
	public List<CountryBean> getCountriesByContinent(String continent);
	public List<CountryBean> getAllCountries();
	public List<String> getLanguagesByCountryCode(String code);
	public List<CountryLanguageBean> getCountriesLanguages();
}
