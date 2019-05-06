package Test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class TestLogin {

	public static void main(String[] args) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		JSONObject json=Service.User.login("rivdddoc", "123", false);
		System.out.println(json);
	}

}
