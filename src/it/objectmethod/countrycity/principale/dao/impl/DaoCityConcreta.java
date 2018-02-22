package it.objectmethod.countrycity.principale.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.countrycity.principale.connessione.DbConnection;
import it.objectmethod.countrycity.principale.dao.DaoCity;
import it.objectmethod.countrycity.principale.pojo.CityBean;

public class DaoCityConcreta implements DaoCity {

	private Connection conn=null;
	
	public DaoCityConcreta() {
		conn=DbConnection.connectionFactory();
	}
	
	@Override
	public List<CityBean> queryCityPopolation(String stato) {
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<CityBean> list=null;
		String query="SELECT city.Name, city.Population FROM country,city WHERE country.Code=city.CountryCode AND city.CountryCode=?";
		
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, stato);
			rs = pstm.executeQuery();
			list=riempiLista(rs);
			
			// CHIUDO LE RISORSE  E LA CONNESSIONE AL DB
			
			pstm.close();
			conn.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY DI DAO CITY");
			e.printStackTrace();
		} finally {
		// FORZO LA CHISURA DEL DB E DELLE RISORSE
			try{
			  if(pstm!=null)
			    pstm.close();
			}catch(SQLException se2){
			}// nothing we can do
			
			try{
			  if(conn!=null)
			     conn.close();
			}catch(SQLException se){
			  se.printStackTrace();
			 }
		  } // END FINALLY
		return list;
	}
	
	private List<CityBean> riempiLista(ResultSet rs) throws SQLException{
		List<CityBean> lista=new ArrayList<CityBean>();
		while(rs.next()) {
			CityBean cb=new CityBean();
			cb.setCountryCode("CountryCode");
			cb.setNome(rs.getString("Name"));
			cb.setPopolazione(rs.getString("Population"));
			
			lista.add(cb);
		}
		return lista;
	}
}
