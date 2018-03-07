package it.objectmethod.countrycity.principale.model.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

public interface IDaoCountry {
	public List<String> getContinents();
	public List<CountryBean> getCountriesByContinent(String continent);
	public List<CountryBean> getAllCountries();
}
