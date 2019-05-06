package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mysql.jdbc.PreparedStatement;
import com.mongodb.client.*;

import java.util.*;
import java.util.stream.Collectors;

public class BDTools {
	public static boolean checkUserExist(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c=Database.getMySQLConnection();
		Statement st=c.createStatement();
		String query="SELECT * FROM user WHERE user_login='"+login+"';";
		
		ResultSet rs=st.executeQuery(query);
		boolean userExists=false;
		while(rs.next()) {
			userExists=true;
		}
		st.close();
		c.close();
		return userExists;
	}

	public static boolean checkCleExists(String cle) throws SQLException {
		// TODO Auto-generated method stub
		Connection c=Database.getMySQLConnection();
		Statement st=c.createStatement();
		String query="SELECT user_id FROM  user WHERE user.user_password=KEY("+cle+");";
		ResultSet rs=st.executeQuery(query);
		String cleExists="";
		while(rs.next()) {
			cleExists=rs.getString(cle);
		}
		st.close();
		c.close();
		return cle.equals(cleExists);
	}
	public static int checkUserPassword(String login,String password) throws SQLException {
		Connection c=Database.getMySQLConnection();
		Statement st=c.createStatement();
		int user_id=-1;
		String query="SELECT user_id FROM  user WHERE user.user_password=PASSWORD("+password+");";
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			user_id=rs.getInt("user_id");
		}
		st.close();
		c.close();
		return user_id;
	}
	
	public static int identification(String login, String mail, String password) throws SQLException {
		Connection c = Database.getMySQLConnection();
		Statement st = c.createStatement();
		int user_id=-1;
		String query="SELECT user_id FROM user"
				+ " WHERE user.user_password=PASSWORD('password')"
				+ " AND user.user_mail="+mail+"OR user.user.login="+login+";";
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
			user_id=rs.getInt("user_id");
		st.close();
		c.close();
		return user_id;
	}
	public static void createUser(String login, String password) throws SQLException {
		Connection c = Database.getMySQLConnection();
		Statement st = c.createStatement();

	
		String query = "INSERT INTO user(user_login,user_password) "
				+ "VALUES ('"+login+"','"+password+"');";
		
		st.executeUpdate(query);
		st.close();
		c.close();
	}


	public static String InsertSession(int user_id,boolean root) throws SQLException {
		Connection c=Database.getMySQLConnection();
		String query="INSERT INTO session(user_id,session_key,session_root)"
				+ " VALUES ("+user_id+",?,"+root+");";
		java.sql.PreparedStatement pst=c.prepareStatement(query);
		boolean success=false;
	    int length = 8;
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                 + "abcdefghijklmnopqrstuvwxyz"
	                 + "0123456789";
	    String key = new Random().ints(length, 0, chars.length())
	                             .mapToObj(i -> "" + chars.charAt(i))
	                             .collect(Collectors.joining());	
	    //String key=""+(int)(Math.random()*1000000000)+"";
		while(!success) {
			try {
				pst.setString(1, key);
				pst.executeUpdate();
				success=true;
			}catch(SQLException e) {
				if(e.getMessage() != null)
					key="0";
				else
					e.printStackTrace();
			}
		}
		c.close();
		pst.close();
		
		//pst.executeQuery(query);

		return key;
	}
	public static String Deconnexion(String cle) throws SQLException {
		Connection conn=Database.getMySQLConnection();
		String query="DELETE FROM session WHERE session_key='"+cle+"';";
		Statement st=conn.createStatement();
		st.executeUpdate(query);
		st.close();
		conn.close();

		return "clé retiré";
	}
	public static boolean checkFriendExist(int id) throws SQLException {
		Connection conn=Database.getMySQLConnection();
		String query="SELECT * FROM  user WHERE user_id='"+id+"';";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(query);
		boolean friendExist= false;
		while(rs.next())
			friendExist=(rs.getInt("user_id")==id);
		st.close();
		conn.close();
		return friendExist;
	}
	public static void addFriend(String cle,int id_friend) throws SQLException {
		Connection conn=Database.getMySQLConnection();
		Statement st=conn.createStatement();
		int id_user=getUserIDParcle(cle);
		System.out.println(id_user);
		String query="INSERT INTO follow(user_id1,user_id2) VALUES ('"+id_user+"','"+id_friend+"');";
		
		st.executeUpdate(query);
		st.close();
		conn.close();

	}	
	public static void removeFriend(String cle,int id_friend) throws SQLException {
		Connection conn=Database.getMySQLConnection();
		Statement st=conn.createStatement();
		int id_user=getUserIDParcle(cle);
		String query="DELETE FROM follow WHERE user_id1='"+id_user+"' AND user_id2='"+id_friend+"';";
		
		st.executeUpdate(query);
		st.close();
		conn.close();
	}
	

	//Message
	public static String InsertMessage(String cle ,String message) {
		
		MongoClient mongo= new MongoClient("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("3i017");
		MongoCollection<Document> message_collection = db.getCollection("message");
		Document query = new Document();
		query.append("user_id", 3);
		query.append("date", 3);
		query.append("content", 3);
		message_collection.insertOne(query);
		mongo.close();
		return message;
	}
	public static String deleteMessage(String cle) {
		return "effacé";
	}

	public static void searchMessage(String messa) {
		MongoClient mongo= new MongoClient("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("3i017");
		MongoCollection<Document> message_collection = db.getCollection("message");
		
	}

	public static int getUserId(String login) throws SQLException {
		Connection c = Database.getMySQLConnection();
		Statement st = c.createStatement();
		String query="SELECT user_id FROM  user WHERE user_login='"+login+"';";
		int user_id=-1;
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
			user_id = rs.getInt("user_id");
		
		st.close();
		c.close();
		return user_id;
	}
	public static int getUserIDParcle(String cle) throws SQLException {
		Connection c = Database.getMySQLConnection();
		Statement st = c.createStatement();
		String query="SELECT user_id FROM session WHERE session_key='"+cle+"';";
		int user_id=-1;
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
			user_id = rs.getInt("user_id");
		
		st.close();
		c.close();
		return user_id;
	}


}
