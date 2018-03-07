package it.objectmethod.countrycity.principale.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.objectmethod.countrycity.principale.model.dao.IDaoCity;
import it.objectmethod.countrycity.principale.model.pojo.CityBean;
import it.objectmethod.countrycity.principale.model.pojo.StatusBeanCity;

@RestController
@CrossOrigin
@RequestMapping(value="/api/cities")
public class CityRestController {
	
	@Autowired
	private IDaoCity daoCity;
	
	@RequestMapping("/{codiceStato}/list")
	public List<CityBean> citiesByCountry (@PathVariable("codiceStato") String codice) {
		List<CityBean> lista=null;
		lista=daoCity.getCitiesByCode(codice);
		return lista;
	}
	
	@PostMapping(value="/add")
	public String addCity(@RequestBody CityBean json) { // il Json arrivato è direttamente Convertito in Oggeto. Thanks @RequestBody
		
		int succ=daoCity.addCity(json.getNome(), json.getCountryCode(), json.getPopolazione(), json.getDistretto());
		String stringa=null;
		ObjectMapper mapper=new ObjectMapper();
		StatusBeanCity cb=new StatusBeanCity();
		if(succ>0) { // aggiunta della nuova city con successo
			
			//Converto da oggetto in JSON. Jackson
			try {
			  cb.setCb(json);
			  cb.setStatus("201");
			  cb.setMsg("City "+json.getNome()+" creata correttamente");
			  stringa= mapper.writeValueAsString(cb);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else { // ERRORE NELL'AGGIUNTA
			cb.setCb(null);
			cb.setStatus("304");
			cb.setMsg("Relazione delle City non modificata");
			try {
				stringa=mapper.writeValueAsString(cb);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		//fine conversione
		return stringa;
	}
}
