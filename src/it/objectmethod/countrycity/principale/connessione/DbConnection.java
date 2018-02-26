package it.objectmethod.countrycity.principale.connessione;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnection {
	
	 private DbConnection() { // EVITO CHE QUALCUNO ISTANZI UNA CLASSE DI UTILITA'. HO SOLO METODI STATICI
	 }
	 
	 public static Connection connectionFactory() {
		 
		 Properties props = new Properties();
		 InputStream in=null;
		 
		 try {
			 in=DbConnection.class.getClassLoader().getResourceAsStream("database.properties"); // carico driver e dati di login da file
			 props.load(in);
			 in.close();
			 
		 }catch(IOException e) {
			 e.printStackTrace();
			 //System.out.println("file DB non trovato o errore durante la chiusura del file");
		 }
		
		 String drivers = props.getProperty("jdbc.drivers");
		 String url = props.getProperty("jdbc.url");
		 String username = props.getProperty("jdbc.username");
		 String password = props.getProperty("jdbc.password");
		     
		 
		 Connection conn=null;
		 try{
			Class.forName(drivers);
			conn= DriverManager.getConnection(url, username, password);
		 } catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	 }

}
