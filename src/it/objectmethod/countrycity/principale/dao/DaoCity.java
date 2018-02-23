package it.objectmethod.countrycity.principale.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.pojo.CityBean;


public interface DaoCity {
	public List<CityBean> getCitiesByCode(String code);
	public int deleteCityById(String id);
	public CityBean getCity(String id);
	public int addCity(String nome,String codiceStato,String popolazione,String distretto);
	public int updateCity(String id,String nome,String codiceStato,String popolazione,String distretto);
}
