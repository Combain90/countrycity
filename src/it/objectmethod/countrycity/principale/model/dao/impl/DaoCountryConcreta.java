package it.objectmethod.countrycity.principale.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.countrycity.principale.connessione.DbConnection;
import it.objectmethod.countrycity.principale.model.dao.DaoCountry;
import it.objectmethod.countrycity.principale.model.pojo.CountryBean;

public class DaoCountryConcreta implements DaoCountry {
	
	private Connection conn=null;
	
	public DaoCountryConcreta() {
		conn=DbConnection.connectionFactory();
	}
	
	@Override
	public List<String> getContinents() {
		
		Statement stm=null;
		ResultSet rs=null;
		List<String> list= new ArrayList<String>();
		try {
			stm=conn.createStatement();
			String query="SELECT distinct Continent FROM country";
			rs = stm.executeQuery(query);
			
			while(rs.next()) {
				list.add(rs.getString("Continent"));
			}
			
			// CHIUDO LE RISORSE  E LA CONNESSIONE AL DB
			
			stm.close();
			conn.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY1 DEL DAO COUNTRY");
			e.printStackTrace();
		} finally {
		// FORZO LA CHISURA DEL DB E DELLE RISORSE
			try{
			  if(stm!=null)
			    stm.close();
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
	
	@Override
	public List<CountryBean> getCountriesByContinent(String continent) {
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<CountryBean> list=null;
		String query="SELECT * FROM country WHERE country.Continent= ? ";
		
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, continent);
			rs=pstm.executeQuery();
			list=riempiLista(rs);
			
			// CHIUDO LE RISORSE  E LA CONNESSIONE AL DB
			pstm.close();
			conn.close();
			rs.close();
				
		}catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY2 DEL DAO COUNTRY");
			e.printStackTrace();
		} finally {
		// FORZO LA CHISURA DEL DB E DELLE RISORSE
			try{
				 if(pstm!=null) {
					pstm.close();
				 }
				}catch(SQLException se2){
				}// nothing we can do
						
			try{
				 if(conn!=null) {
					conn.close();
				 }
				}catch(SQLException se){
				 se.printStackTrace();
				}
			 } // END FINALLY
		return list;
	}

	@Override
	public List<CountryBean> getAllCountries() {
		Statement stm=null;
		ResultSet rs=null;
		List<CountryBean> list=null;
		try {
			stm=conn.createStatement();
			String query="SELECT * FROM country";
			rs = stm.executeQuery(query);
			list=riempiLista(rs);
			
			// CHIUDO LE RISORSE  E LA CONNESSIONE AL DB
			
			stm.close();
			conn.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY1 DEL DAO COUNTRY");
			e.printStackTrace();
		} finally {
		// FORZO LA CHISURA DEL DB E DELLE RISORSE
			try{
			  if(stm!=null)
			    stm.close();
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
	
	
	private List<CountryBean> riempiLista(ResultSet rs) throws SQLException{
		List<CountryBean> lista=new ArrayList<CountryBean>();
		while(rs.next()) {
			CountryBean cb=new CountryBean();
			cb.setNome(rs.getString("Name"));
			cb.setCodice(rs.getString("Code"));
			cb.setContinente(rs.getString("Continent"));
			cb.setPopolazione(rs.getString("Population"));
			cb.setRegione(rs.getString("Region"));
			lista.add(cb);
		}
		return lista;
	}
}
