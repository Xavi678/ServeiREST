package api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;



@Path("/serveiCarro")
public class ServeiCarro {
	
	GestorBd db= new GestorBd(Constant.dbserver,Constant.database,Constant.user,Constant.password);
	
	@Path("/JSON/{f}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response salutacio(@PathParam("f") String nom) throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
	
		jsonObject.put("salutacio", "Hola señor "+nom);
		
 
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
				
	}
	
	/*@Path("/getTasques")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tasca> obtenirTasques() throws JSONException {
		
		
 
		JSONObject jsonObject = new JSONObject();
	
		jsonObject.put("salutacio", "Hola señor ");
		
 
		String result = "" + jsonObject;
		return tasques
				
	}*/
	
	
	@Path("/Autenticar/{nom}/{passwd}")	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response Autenticar(@PathParam("nom") String nom,@PathParam("passwd" )String passwd) {
		try {
			
			
			String token=db.autenticar(nom,passwd);
			
			GenericEntity<String> genericEntity = new GenericEntity<String>(token){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@Path("/Tasques")	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response obtenirTasques() {
		try {
			List<Tasca> tasques= new ArrayList<Tasca>();
			
			tasques.add(new Tasca("Fer els deures"));
			tasques.add(new Tasca("Fer el llit"));
			tasques.add(new Tasca("Dormir"));
			GenericEntity<List<Tasca>> genericEntity = new GenericEntity<List<Tasca>>(tasques){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@Path("/TXT/{f}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response salutacioTXT(@PathParam("f") String nom) throws JSONException {
 
		/*JSONObject jsonObject = new JSONObject();
	
		jsonObject.put("salutacio", "Hola señor "+nom);
		
 
		String result = "" + jsonObject;*/
		String result="hola "+nom;
		return Response.status(200).entity(result).build();
				
	}
	
	
	@Path("/XML/{f}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response salutacioXML(@PathParam("f") String nom) throws JSONException {
 
		/*JSONObject jsonObject = new JSONObject();
	
		jsonObject.put("salutacio", "Hola señor "+nom);
		
 
		String result = "" + jsonObject;*/
		String result="<Persona> <nom>"+nom+"</nom> </Persona>";
		return Response.status(200).entity(result).build();
				
	}
	
	@Path("/HTML/{f}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response salutacioHTML(@PathParam("f") String nom) throws JSONException {
 
		/*JSONObject jsonObject = new JSONObject();
	
		jsonObject.put("salutacio", "Hola señor "+nom);
		
 
		String result = "" + jsonObject;*/
		String result="<!DOCTYPE> <html> <body> <div>"+nom+"</div><body></html>";
		return Response.status(200).entity(result).build();
				
	}
	
	
	
	

 

}
