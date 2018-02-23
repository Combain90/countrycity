package it.objectmethod.countrycity.principale.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.pojo.CityBean;


public interface DaoCity {
	public List<CityBean> getCitiesByStato(String stato);
	public int deleteRecord(String id);
	public CityBean infoCity(String id);
	public int AddRecordCity(String nome,String codiceStato,String popolazione,String distretto);
	public int UpdateRecordCity(String id,String nome,String codiceStato,String popolazione,String distretto);
}
