package api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.ws.rs.FormParam;
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
			
			//System.out.println();
			
			
			
			String token=db.autenticar(nom,passwd);
			
			GenericEntity<String> genericEntity = new GenericEntity<String>(token){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	
	@Path("/obtenirProducte/{idUser}/{idProduct}")	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response obtenirProducte(@PathParam("idUser") String user,@PathParam("idProduct" )String product) {
		try {
			
			
			//String token=db.autenticar(nom,passwd);
			
			if(db.getToken(user)==false) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
			
			Producte p=db.obtenirProducte( product);
			
			GenericEntity<Producte> genericEntity = new GenericEntity<Producte>(p){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@Path("/obtenirProductes/{idUser}")	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response obtenirProductes(@PathParam("idUser") String user) {
		try {
			
			
			//String token=db.autenticar(nom,passwd);
			
			if(db.getToken(user)==false) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
			
			List<Producte> p=db.obtenirProductes();
			
			GenericEntity<List<Producte>> genericEntity = new GenericEntity<List<Producte>>(p){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@Path("/afegirProducte/{idUser}/{producte}")	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response afegirProducte(@PathParam("idUser") String user,@PathParam("producte") String producte ) {
		try {
			
			
			//String token=db.autenticar(nom,passwd);
			
			
			if(db.getToken(user)==false) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
			
			//Producte[] ptmp=producte.split(",");
			
		System.out.println(producte);
		
			List<Producte> p=db.obtenirProductes();
			
			GenericEntity<List<Producte>> genericEntity = new GenericEntity<List<Producte>>(p){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	
	@Path("/obtenirProductes/data/{id}/{inici}/{fi}")	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response otenirProductesperData(@PathParam("id") String user,@PathParam("inici") String inici,@PathParam("fi") String fi) {
		try {
			
			
			//String token=db.autenticar(nom,passwd);
			
			//LocalDateTime datetime = LocalDateTime.parse(inici, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
			
			DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
			Date datai=format.parse(inici);
			Date dataf=format.parse(fi);
			if(db.getToken(user)==false) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
			
			//Producte[] ptmp=producte.split(",");
			
		
		
			Collection<Producte> p=db.obtenirProductesPerData(datai.toString(), dataf.toString());
			
			GenericEntity<Collection<Producte>> genericEntity = new GenericEntity<Collection<Producte>>(p){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	/*@Path("/data/{fi}")	
	@GET	
	@Produces( MediaType.APPLICATION_JSON )
	public Response otenirProductesperData(@PathParam("fi") Date fi ) {
		try {
			
			
			//String token=db.autenticar(nom,passwd);
			
			
//			if(db.getToken(user)==false) {
//				return Response.status(Response.Status.UNAUTHORIZED).build();
//			}
			
			//Producte[] ptmp=producte.split(",");
			
		
		
			List<Producte> p=db.obtenirProductes();
			
			GenericEntity<List<Producte>> genericEntity = new GenericEntity<List<Producte>>(p){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	*/
	
	
	
	
	
	
	
	
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
