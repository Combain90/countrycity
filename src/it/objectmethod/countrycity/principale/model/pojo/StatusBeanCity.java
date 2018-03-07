package it.objectmethod.countrycity.principale.model.pojo;

public class StatusBeanCity {
	private String status,msg;
	private CityBean cb;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public CityBean getCb() {
		return cb;
	}

	public void setCb(CityBean cb) {
		this.cb = cb;
	}
}
