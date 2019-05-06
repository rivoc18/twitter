package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import BD.BDTools;

public class Message {
	//a finir
	
	
	public static JSONObject addComment(String cle, String texte) throws JSONException, SQLException {
		if (cle==null||texte==null)
			return ServicesTools.ServiceRefused("Wrong argument", -1);
		JSONObject retour=new JSONObject();
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("test");
		MongoCollection<Document> col = db.getCollection("Messages");
		Document d = new Document();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		String s = date.toString();
		d.append("date",s);
		d.append("text", texte);
		d.append("user", BDTools.getUserIDParcle(cle));
		d.append("type", "commment");
		System.out.println(d);
		col.insertOne(d);	
		retour.put("status","message envoye");
		return retour;
			
	}
	
	
	public static JSONObject getMessage() throws JSONException, SQLException {
		JSONObject retour=new JSONObject();
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("test");
		MongoCollection<Document> col = db.getCollection("Messages");
		
		try (MongoCursor<Document> cur = col.find().iterator()) {
			ArrayList<Object> list = new ArrayList<Object>();
            while (cur.hasNext()) {

                Document doc = cur.next();
                list.add(( doc.values()));
                
            }
            retour.put("mess", list);
            retour.put("size", list.size());
            
        }
		
		mongo.close();
		return retour;
    }
			
		
	public static JSONObject search(String key, String m) throws JSONException {
		if (key==null)
			return ServicesTools.ServiceRefused("Wrong argument", -1);
		JSONObject retour=new JSONObject();
		BD.BDTools.searchMessage(m);
		retour.put("message","trouve");
		return retour;
	}
}
