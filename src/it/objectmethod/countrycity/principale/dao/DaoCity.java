package it.objectmethod.countrycity.principale.dao;

import java.util.List;

import it.objectmethod.countrycity.principale.pojo.CityBean;


public interface DaoCity {
	public List<CityBean> queryCityPopolation(String stato);
  	
}
