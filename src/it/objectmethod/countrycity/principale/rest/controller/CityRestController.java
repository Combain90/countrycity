package it.objectmethod.countrycity.principale.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import it.objectmethod.countrycity.principale.model.dao.IDaoCity;
import it.objectmethod.countrycity.principale.model.pojo.CityBean;

@Controller
@RequestMapping(value="/api/cities")
public class CityRestController {
	
	@Autowired
	private IDaoCity daoCity;
	
	@RequestMapping("/{codiceStato}/list")
	@ResponseBody
	public List<CityBean> citiesByCountry (@PathVariable("codiceStato") String codice) {
		List<CityBean> lista=null;
		lista=daoCity.getCitiesByCode(codice);
		return lista;
	}
}
