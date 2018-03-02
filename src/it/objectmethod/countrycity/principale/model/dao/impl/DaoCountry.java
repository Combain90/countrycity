package it.objectmethod.countrycity.principale.model.dao.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;
import it.objectmethod.countrycity.principale.model.dao.IDaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;
import it.objectmethod.countrycity.principale.model.pojo.CountryLanguageBean;

@Component
public class DaoCountry extends NamedParameterJdbcDaoSupport implements IDaoCountry {
	
	@Autowired
	public void setDs(DataSource ds) {
		setDataSource(ds);
	}
	
	
	@Override
	public List<CountryLanguageBean> getCountriesLanguages(){
		String query="SELECT country.Name AS nome, countrylanguage.Language AS linguaggio FROM country INNER JOIN countrylanguage ON country.Code=countrylanguage.CountryCode WHERE countrylanguage.IsOfficial=\"T\"";
		List<CountryLanguageBean> ret=null;
		BeanPropertyRowMapper<CountryLanguageBean> rm = new BeanPropertyRowMapper<CountryLanguageBean>(CountryLanguageBean.class);
		ret=getJdbcTemplate().query(query, rm);
		return ret;
	}
	
	@Override
	public List<String> getLanguagesByCountryCode(String code){
		String query="SELECT countrylanguage.language FROM countrylanguage WHERE countrylanguage.CountryCode=?";
		List<String> ret=null;
		ret=getJdbcTemplate().queryForList(query, new Object[] {code}, String.class);
		return ret;
	}
	
	@Override
	public List<String> getContinents() {
		String sql = "SELECT DISTINCT Continent FROM country";
		List<String> ret = null;
		ret = getJdbcTemplate().queryForList(sql, String.class);
		return ret;
	}
	
	@Override
	public List<CountryBean> getAllCountries() {
		String query="SELECT country.Code AS codice , country.Name AS nome , country.Region AS regione , country.Population AS popolazione , country.Continent AS continente FROM country";
		List<CountryBean> ret=null;
		BeanPropertyRowMapper<CountryBean> rm = new BeanPropertyRowMapper<CountryBean>(CountryBean.class);
		ret= getJdbcTemplate().query(query, rm);
		return ret;
	}
	
	@Override
	public List<CountryBean> getCountriesByContinent(String continent) {
		String query="SELECT country.Code AS codice , country.Name AS nome , country.Region AS regione , country.Population AS popolazione , country.Continent AS continente FROM country WHERE country.Continent= ?";
		BeanPropertyRowMapper<CountryBean> rm = new BeanPropertyRowMapper<CountryBean>(CountryBean.class);
		List<CountryBean> ret =null;
		ret=getJdbcTemplate().query(query, new Object[]{continent}, rm);
		return ret;
	}
}
