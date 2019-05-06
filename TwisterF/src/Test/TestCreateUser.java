package Test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class TestCreateUser {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException, SQLException {
		// TODO Auto-generated method stub
			JSONObject json=Service.User.createUser("ric", "123");
			System.out.println(json);

	}

}
