package Test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class TestAddFriend {

	public static void main(String[] args) throws JSONException, SQLException {
		// TODO Auto-generated method stub
		JSONObject json=Service.Relation.addFriend("25",1);
		System.out.println(json);
	}

}
