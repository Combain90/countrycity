package it.objectmethod.countrycity.principale.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.countrycity.principale.connessione.DbConnection;
import it.objectmethod.countrycity.principale.dao.DaoCountry;
import it.objectmethod.countrycity.principale.pojo.CountryBean;

public class DaoCountryConcreta implements DaoCountry {
	
	private Connection conn=null;
	
	public DaoCountryConcreta() {
		conn=DbConnection.connectionFactory();
	}
	
	@Override
	public List<CountryBean> queryContinent() {
		
		Statement stm=null;
		ResultSet rs=null;
		List<CountryBean> list=null;
		try {
			stm=conn.createStatement();
			String query="SELECT DISTINCT country.Continent FROM country";
			rs = stm.executeQuery(query);
			list=riempiListaContinent(rs);
			
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
	public List<CountryBean> queryCountry(String continent) {
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<CountryBean> list=null;
		String query="SELECT country.Name, country.Code FROM country WHERE country.Continent= ? ";
		
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, continent);
			rs=pstm.executeQuery();
			list=riempiListaCountry(rs);
			
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

	private List<CountryBean> riempiListaCountry(ResultSet rs) throws SQLException{
		List<CountryBean> lista=new ArrayList<CountryBean>();
		while(rs.next()) {
			CountryBean cb=new CountryBean();
			cb.setStato(rs.getString("Name"));
			cb.setCodice(rs.getString("Code"));
			lista.add(cb);
		}
		return lista;
	}
	
	private List<CountryBean> riempiListaContinent(ResultSet rs) throws SQLException{
		List<CountryBean> lista=new ArrayList<CountryBean>();
		while(rs.next()) {
			CountryBean cb=new CountryBean();
			cb.setContinente(rs.getString("Continent"));
			lista.add(cb);
		}
		return lista;
	}

	@Override
	public List<String> allCountryCode() {
		Statement stm=null;
		ResultSet rs=null;
		List<String> list=null;
		try {
			stm=conn.createStatement();
			String query="SELECT country.Code FROM country";
			rs = stm.executeQuery(query);
			list=riempiListaCountryCode(rs);
			
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
	
	private List<String> riempiListaCountryCode(ResultSet rs) throws SQLException{
		List<String> lista=new ArrayList<String>();
		while(rs.next()) {
			lista.add(rs.getString("Code"));
		}
		return lista;
	}
}
