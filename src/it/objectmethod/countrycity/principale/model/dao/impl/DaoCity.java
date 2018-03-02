package it.objectmethod.countrycity.principale.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;
import it.objectmethod.countrycity.principale.model.dao.IDaoCity;
import it.objectmethod.countrycity.principale.model.pojo.CityBean;

@Component
public class DaoCity extends NamedParameterJdbcDaoSupport implements IDaoCity {
	
	@Autowired
	public void setDs(DataSource ds) {
		setDataSource(ds);
	}
	
	
	@Override
	public int deleteCityById(String id) {
		String query = "DELETE FROM city WHERE city.ID=:id";
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("id", id);
		int ret=0;
		ret= getNamedParameterJdbcTemplate().update(query, map);
		return ret;
	}
	// 1 se l'eliminazione ha successo, 0 altrimenti

	@Override
	public int addCity(String nome, String codiceStato, String popolazione, String distretto) {
		String query = "INSERT INTO city (Name,CountryCode,District,Population) VALUES (:nome,:codiceStato,:distretto,:popolazione)";
		Map<String,String> map=new HashMap<String,String>();
		map.put("nome",nome);
		map.put("codiceStato",codiceStato);
		map.put("popolazione",popolazione);
		map.put("distretto",distretto);
		int ret=0;
		ret=getNamedParameterJdbcTemplate().update(query, map);
		return ret;
	}
	// 1 se l'eliminazione ha successo, 0 altrimenti

	@Override
	public int updateCity(String id, String nome, String codiceStato, String popolazione, String distretto) {

		String query = "UPDATE city SET Name=:nome,CountryCode=:codiceStato,District=:distretto,Population=:popolazione WHERE city.ID=:id";
		Map<String,String> map=new HashMap<String,String>();
		map.put("nome",nome);
		map.put("codiceStato",codiceStato);
		map.put("popolazione",popolazione);
		map.put("distretto",distretto);
		map.put("id", id);
		int ret=0;
		ret=getNamedParameterJdbcTemplate().update(query, map);
		return ret;
	}
	// 1 se l'eliminazione ha successo, 0 altrimenti
	
	@Override
	public List<CityBean> getCitiesByCode(String code) {
		String query = "SELECT city.ID as id, city.Name as nome, city.CountryCode as countryCode, city.Population as popolazione , city.District as distretto FROM city  WHERE city.CountryCode = :code";
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("code", code);
		List<CityBean> ret = null;
		BeanPropertyRowMapper<CityBean> rm = new BeanPropertyRowMapper<CityBean>(CityBean.class);
		ret = getNamedParameterJdbcTemplate().query(query, map, rm);
		return ret;
	}

	@Override
	public CityBean getCity(String id) {
		String query = "SELECT city.ID as id, city.Name as nome, city.CountryCode as countryCode, city.Population as popolazione , city.District as distretto FROM city WHERE city.ID=:id";
		Map<String,String> map=new HashMap<String,String>();                                                                                                                               
		map.put("id",id);                           
		BeanPropertyRowMapper<CityBean> rm = new BeanPropertyRowMapper<CityBean>(CityBean.class);
		CityBean ret = getNamedParameterJdbcTemplate().queryForObject(query, map, rm);
		return ret;
	}
}
