package couchAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class CouchViews {
	CouchConect couchConect = new CouchConect();
	String url = couchConect.getUrl();
	String[] parts = url.split("//");
	String part1 = parts[0]; 
	String part2 = parts[1];
	String dbName = couchConect.getDbName();
	String userName = couchConect.getUsername();
	String password = couchConect.getPassword();
	DefaultHttpClient httpClient = new DefaultHttpClient();
	
public void getView(String _design, String _view) {
	try {
		HttpGet getDoc = new HttpGet(url+"/"+dbName+"/_design/"+_design+"/_view/"+_view);
		getDoc.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getDoc);

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));

		String output;
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

//		httpClient.getConnectionManager().shutdown();

	  } catch (ClientProtocolException e) {
	
		e.printStackTrace();

	  } catch (IOException e) {
	
		e.printStackTrace();
	  }
}
}
