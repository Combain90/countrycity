package it.objectmethod.countrycity.principale.model.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		
		/*
		 * Ti lascio il vecchio codice per confrontare gli errori commessi
		 * 
		 *  return getJdbcTemplate().query("SELECT DISTINCT Continent FROM country", new RowMapper<String>() {
		 *  	@Override                                                                                     
		 *  	public String mapRow (ResultSet rs, int rownumber) throws SQLException {				      
		 *                                                                                                    
		 *  		String continent = (rs.getString("Continent"));                                           
		 *  		return continent;                                                                         
		 *  	}			                                                                                  
		 *  });                                                                                               
		 * 
		 * 
		 */
		
		// 1 Non ritornare mai direttamente il risultato di un metodo invocato, ma salvarsi in una variabile tale risultato e ritornare essa.
		// 2 Non accalcare troppo codice sulla stessa riga, in fase di debug diventa complesso ricostruire la causa dell'errore
		// 3 Non creare "macchine di Goldberg", non scrivere codice estremamente complesso per eseguire compiti estremamente semplici 
		
		
		//Codice complesso
		String sql = "SELECT DISTINCT Continent FROM country";
		List<String> ret = null;
		ret = getJdbcTemplate().queryForList(sql, String.class);
		return ret;
	}
	
	@Override
	public List<CountryBean> getAllCountries() {
		String query="SELECT country.Code AS codice , country.Name AS nome , country.Region AS regione , country.Population AS popolazione , country.Continent AS continente FROM country";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<CountryBean>(CountryBean.class));
	}
	
	@Override
	public List<CountryBean> getCountriesByContinent(String continent) {
		
		String query="SELECT country.Code AS codice , country.Name AS nome , country.Region AS regione , country.Population AS popolazione , country.Continent AS continente FROM country WHERE country.Continent= ?";
		BeanPropertyRowMapper<CountryBean> rm = new BeanPropertyRowMapper<CountryBean>(CountryBean.class);
		List<CountryBean> list = getJdbcTemplate().query(query, new Object[]{continent}, rm);
		return list;
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
