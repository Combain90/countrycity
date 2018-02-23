package it.objectmethod.countrycity.principale.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.pojo.CountryBean;

public interface DaoCountry {
	public List<CountryBean> queryContinent();
	public List<CountryBean> queryCountry(String continent);
	public List<String> allCountryCode();
}
