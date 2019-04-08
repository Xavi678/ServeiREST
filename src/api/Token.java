package api;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Tokens")
public class Token {
	
	private String id;
	private Date data;
	
	public Token() {
		
	}
	public Token(String id, Date data) {
		super();
		this.id = id;
		this.data = data;
	}
	@XmlElement(name = "token")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(name = "data")
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	

}
