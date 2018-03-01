package it.objectmethod.countrycity.principale.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.countrycity.principale.model.pojo.CountryBean;



/*
 * Questo RowMapper è creato solo a scopo didattico, non abbiamo alcuna necessità di utilizzarlo nel nostro codice
 * ma è importante sapere che esistono, come funzionano e quando utilizzarli
 * 
 */
public class CountryMapper implements RowMapper<CountryBean>{

	@Override
	public CountryBean mapRow(ResultSet rs, int index) throws SQLException {
		CountryBean tmp = new CountryBean();
		tmp.setCodice(rs.getString("Code"));
		tmp.setNome(rs.getString("Name"));
		tmp.setContinente(rs.getString("Continent"));
//		if(tmp.getPopolazione() > 1000) { //Customizzazione dell'estrazione dei dati
//			tmp.setGovForm("DICTATORSHIP"); 
//		} else {
//			tmp.setGovForm("CAPOCLASSE"); 
//		}
		
		//Un RowMapper custom lo si utilizza solo e soltanto nel rarissimo caso in cui sia necessario elaborare l'estrazione dei dati
		return tmp;
	}

	
}
