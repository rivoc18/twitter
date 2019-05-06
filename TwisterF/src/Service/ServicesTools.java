package Service;


import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools{
	
	public static JSONObject ServiceRefused(String message, int id) throws JSONException {
		JSONObject json= new JSONObject();
		json.put("id-erreur",id);
		json.put("message",message);
		json.put("status","KO");
		return json;
	}
	
	public static JSONObject serviceAccepted() throws JSONException {
		JSONObject json= new JSONObject();
		json.put("status","OK");
		return json;
	}
	//User
	public static boolean checkUserExist(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		return BD.BDTools.checkUserExist(login);
	}
	public static boolean checkCleExist(String cle) throws SQLException {
		
		return BD.BDTools.checkCleExists(cle);
	}
	public static boolean checkFriendExist(int id_friend) throws SQLException {
		return BD.BDTools.checkFriendExist(id_friend); 
	}
	
	public static int checkUserPassword(String login,String pwd) throws SQLException {
		return BD.BDTools.checkUserPassword(login, pwd) ;
	}
	public static int getUserId(String login) throws SQLException {
		return BD.BDTools.getUserId(login);
	}




	
	//Message
	public static String InsertMessage(String message) {
		return message;
	}
	public static String deleteMessage(String cle) {
		return "effacé";
	}




	
}
