package Test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class TestLogout {

	public static void main(String[] args) throws JSONException, SQLException {
		// TODO Auto-generated method stub
		JSONObject json=Service.User.logout("50");
		System.out.println(json);
	}

}
