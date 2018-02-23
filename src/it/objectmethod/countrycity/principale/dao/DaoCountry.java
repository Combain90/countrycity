package it.objectmethod.countrycity.principale.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.pojo.CountryBean;

public interface DaoCountry {
	public List<CountryBean> getContinents();
	public List<CountryBean> getCountriesByContinent(String continent);
	public List<CountryBean> getAllCountries();
}
