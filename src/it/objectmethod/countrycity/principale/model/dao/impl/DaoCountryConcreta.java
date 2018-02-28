package it.objectmethod.countrycity.principale.model.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import it.objectmethod.countrycity.principale.model.dao.DaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

@Component
public class DaoCountryConcreta extends NamedParameterJdbcDaoSupport implements DaoCountry {
	
	@Autowired
	public void setDs(DataSource ds) {
		setDataSource(ds);
	}
	
	
	@Override
	public List<String> getContinents() {
		
		String query="SELECT distinct Continent FROM country";
		return getJdbcTemplate().query(query,new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int colonna) throws SQLException {
				return rs.getString("Continent");
			}
			
		});
	}
	
//	@Override
//	public List<CountryBean> getCountriesByContinent(String continent) {
//		
//		String query="SELECT * FROM country WHERE country.Continent= ?";
//		return getJdbcTemplate().execute(query, new PreparedStatementCallback<List<CountryBean>>() {
//			
//				@Override
//		    	public List<CountryBean> doInPreparedStatement(java.sql.PreparedStatement ps) throws SQLException, DataAccessException {
//				   ps.setString(1,continent);          
//			       ResultSet rs=ps.executeQuery();
//			       return riempiLista(rs);
//		    	}  
//	    });  
//	}
	
	@Override
	public List<CountryBean> getCountriesByContinent(String continent) {
		List<CountryBean> ret = null;
		String query="SELECT country.Code AS codice , country.Name AS nome , country.Region AS regione , country.Population AS popolazione , country.Continent AS continente FROM country WHERE country.Continent= ?";
		BeanPropertyRowMapper<CountryBean> rm = new BeanPropertyRowMapper<CountryBean>(CountryBean.class);
		ret = getJdbcTemplate().query(query, new Object[]{continent}, rm);
		return ret;
	}

	@Override
	public List<CountryBean> getAllCountries() {
		String query="SELECT country.Code AS codice , country.Name AS nome , country.Region AS regione , country.Population AS popolazione , country.Continent AS continente FROM country";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<CountryBean>(CountryBean.class));
	}
	
	
//	private List<CountryBean> riempiLista(ResultSet rs) throws SQLException{
//		List<CountryBean> lista=new ArrayList<CountryBean>();
//		while(rs.next()) {
//			CountryBean cb=new CountryBean();
//			cb.setNome(rs.getString("Name"));
//			cb.setCodice(rs.getString("Code"));
//			cb.setContinente(rs.getString("Continent"));
//			cb.setPopolazione(rs.getString("Population"));
//			cb.setRegione(rs.getString("Region"));
//			lista.add(cb);
//		}
//		return lista;
//	}
}
