package it.objectmethod.countrycity.principale.model.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.model.pojo.CountryLanguageBean;

public interface IDaoLanguage {
	public List<String> getLanguagesByCountryCode(String code);
	public List<CountryLanguageBean> getCountriesLanguages();
}
