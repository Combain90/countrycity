package it.objectmethod.countrycity.principale.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public List<CityBean> getCitiesByStato(String stato) {
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<CityBean> list=null;
		String query="SELECT * " + 
				" FROM city " + 
				" WHERE city.CountryCode = ?";
		
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
			cb.setCountryCode(rs.getString("CountryCode"));
			cb.setDistretto(rs.getString("District"));
			cb.setId(rs.getString("ID"));
			cb.setNome(rs.getString("Name"));
			cb.setPopolazione(rs.getString("Population"));
			
			lista.add(cb);
		}
		return lista;
	}

	@Override
	public int deleteRecord(String id) {
		PreparedStatement pstm=null;

		String query="DELETE FROM city WHERE city.ID=?";
		int successo=0;
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, id);
			successo=pstm.executeUpdate();
			
			// CHIUDO LE RISORSE  E LA CONNESSIONE AL DB
			pstm.close();
			conn.close();
			
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
			  if(conn!=null) {
			     conn.close();
			  }
			}catch(SQLException se){
			  se.printStackTrace();
			 }
		  }// END FINALLY
		return successo;
	}
	// 1 se l'eliminazione ha successo, 0 altrimenti

	@Override
	public CityBean infoCity(String id) {
		PreparedStatement pstm=null;
		ResultSet rs=null;
		CityBean cb=new CityBean();
		String query="SELECT * FROM city WHERE city.ID=?";
		
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, id);
			rs = pstm.executeQuery(); // ci sta un solo risultato. L'ID E' UNIVOCO
			
			while(rs.next()) {
				cb.setCountryCode(rs.getString("CountryCode"));
				cb.setDistretto(rs.getString("District"));
				cb.setId(rs.getString("ID"));
				cb.setNome(rs.getString("Name"));
				cb.setPopolazione(rs.getString("Population"));
			}
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
		return cb;
	}
	
	@Override
	public int AddRecordCity(String nome,String codiceStato,String popolazione,String distretto) {
		PreparedStatement pstm=null;

		String query="INSERT INTO city (Name,CountryCode,District,Population) VALUES (?,?,?,?)";
		int successo=0;
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, nome);
			pstm.setString(2, codiceStato);
			pstm.setString(3, distretto);
			pstm.setString(4, popolazione);
			successo=pstm.executeUpdate();
			// CHIUDO LE RISORSE  E LA CONNESSIONE AL DB
			pstm.close();
			conn.close();
			
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
			  if(conn!=null) {
			     conn.close();
			  }
			}catch(SQLException se){
			  se.printStackTrace();
			 }
		  }// END FINALLY
		return successo;
	}
	// 1 se l'eliminazione ha successo, 0 altrimenti
	
	@Override
	public int UpdateRecordCity(String id,String nome,String codiceStato,String popolazione,String distretto) {
		PreparedStatement pstm=null;
		
		String query="UPDATE city SET Name=?,CountryCode=?,District=?,Population=? WHERE city.ID=?";
		int successo=0;
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, nome);
			pstm.setString(2, codiceStato);
			pstm.setString(3, distretto);
			pstm.setString(4, popolazione);
			pstm.setString(5,id);
			successo=pstm.executeUpdate();
			// CHIUDO LE RISORSE  E LA CONNESSIONE AL DB
			pstm.close();
			conn.close();
			
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
			  if(conn!=null) {
			     conn.close();
			  }
			}catch(SQLException se){
			  se.printStackTrace();
			 }
		  }// END FINALLY
		return successo;
	}
	// 1 se l'eliminazione ha successo, 0 altrimenti
	
}
