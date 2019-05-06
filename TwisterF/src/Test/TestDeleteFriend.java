package Test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class TestDeleteFriend {

	public static void main(String[] args) throws JSONException, SQLException {
		JSONObject json=Service.Relation.removeFriend("76",2);
		System.out.println(json);
	}

}
