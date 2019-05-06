package Service;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class Relation {
	
	public static JSONObject addFriend(String cle,int id_friend) throws JSONException, SQLException {
		if (cle==null)
			return ServicesTools.ServiceRefused("Wrong argument", -1);
		JSONObject retour=new JSONObject();
		try {
			if(!ServicesTools.checkFriendExist(id_friend))
				return (ServicesTools.ServiceRefused("Unknown friend", 1));
			BD.BDTools.addFriend(cle,id_friend);
			
			retour.put("friend_ajoute","OK");
		}	
		catch(JSONException e){
			return(ServicesTools.ServiceRefused("Json pb"+e.getMessage(),100));
		}
		return retour;
			
	}
	
	public static JSONObject removeFriend(String cle,int id_friend) throws JSONException, SQLException {
		if (cle==null)
			return ServicesTools.ServiceRefused("Wrong argument", -1);
		JSONObject retour=new JSONObject();
		try {
			if(!ServicesTools.checkFriendExist(id_friend))
				return (ServicesTools.ServiceRefused("Unknown friend", 1));
			BD.BDTools.removeFriend(cle,id_friend);
			
			retour.put("friend_remove","OK");
		}	
		catch(JSONException e){
			return(ServicesTools.ServiceRefused("Json pb"+e.getMessage(),100));
		}
		return retour;
			
	}
	
}
