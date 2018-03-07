package it.objectmethod.countrycity.principale.model.dao.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import it.objectmethod.countrycity.principale.model.dao.IDaoLanguage;
import it.objectmethod.countrycity.principale.model.pojo.CountryLanguageBean;

@Component
public class DaoLanguage extends NamedParameterJdbcDaoSupport implements IDaoLanguage {
	
	@Autowired
	public void setDs(DataSource ds) {
		setDataSource(ds);
	}
	
	@Override
	public List<CountryLanguageBean> getCountriesLanguages(){
		String query="SELECT country.Name AS country, countrylanguage.Language AS linguaggio FROM country INNER JOIN countrylanguage ON country.Code=countrylanguage.CountryCode WHERE countrylanguage.IsOfficial=\"T\"";
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

}
