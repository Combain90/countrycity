package it.objectmethod.countrycity.principale.model.dao.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;
import it.objectmethod.countrycity.principale.model.dao.IDaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@Component
public class DaoCountry extends NamedParameterJdbcDaoSupport implements IDaoCountry {
	
	@Autowired
	public void setDs(DataSource ds) {
		setDataSource(ds);
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
