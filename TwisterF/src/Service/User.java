package Service;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import BD.BDTools;

public class User {
	
	public static JSONObject login(String login, String password, boolean root) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (login==null || password==null)
			return ServicesTools.ServiceRefused("Wrong argument", -1);
		JSONObject retour=new JSONObject();
		
		try {
			if(!ServicesTools.checkUserExist(login))
				return (ServicesTools.ServiceRefused("Unknown user", 1));
			
			if(ServicesTools.checkUserPassword(login,password)!=-1)
				return (ServicesTools.ServiceRefused("bad password or not correspond ", 2));
			int id_user=ServicesTools.getUserId(login);
			String key=BDTools.InsertSession(id_user,false);
			
			retour.put("status","connecte");
			retour.put("key =", key);
		}	
		catch(JSONException e){
			return(ServicesTools.ServiceRefused("Json pb"+e.getMessage(),100));
		}
		return retour;
			
	}
	
	//createUser
	public static JSONObject createUser(String login, String password) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if ( login==null || password==null)
			return ServicesTools.ServiceRefused("Wrong argument", -1);
		JSONObject retour=new JSONObject();
		System.out.println("on crée le json");
		try {
			if(ServicesTools.checkUserExist(login)) {
				System.out.println("user existe");
				return (ServicesTools.ServiceRefused("Already exist", 1));
			}
			System.out.println("user existe pas");
			BDTools.createUser(login,password);
			retour.put("statue ","compte cree");
			retour.put("login", login);
			retour.put("password", password);
		}
		catch(JSONException e){
			return(ServicesTools.ServiceRefused("Json pb"+e.getMessage(),100));
		}
		
		return retour;
	}
		
	
	
	//logout
	public static JSONObject logout(String cle) throws JSONException, SQLException {
		JSONObject retour=new JSONObject();
		try {
			if(cle==null)
				return (ServicesTools.ServiceRefused("Unknown cle", 1));
			BD.BDTools.Deconnexion(cle);
			retour.put("status","D2CONNEXION");
		}
		
		catch(JSONException e){
			return(ServicesTools.ServiceRefused("Json pb"+e.getMessage(),100));
		}
		return retour;
			
	}
}
