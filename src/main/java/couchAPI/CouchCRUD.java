package couchAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

public class CouchCRUD {	
	CouchConect couchConect = new CouchConect();
	String url = couchConect.getUrl();
	String[] parts = url.split("//");
	String part1 = parts[0]; 
	String part2 = parts[1];
	String dbName = couchConect.getDbName();
	String userName = couchConect.getUsername();
	String password = couchConect.getPassword();
	
	
//	String adminURL = part1+"//"+userName+":"+password+"@"+part2;
	DefaultHttpClient httpClient = new DefaultHttpClient();
	
	public void viewAllDbs() {
		
		 try {
				HttpGet getAllDbs = new HttpGet(url+"/_all_dbs");
				getAllDbs.addHeader("accept", "application/json");

				HttpResponse response = httpClient.execute(getAllDbs);

//				if (response.getStatusLine().getStatusCode() != 200) {
//					throw new RuntimeException("Failed : HTTP error code : "
//					   + response.getStatusLine().getStatusCode());
//				}

				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

				String output;
				
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

//				httpClient.getConnectionManager().shutdown();

			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
	}
	

	public void createDatabase(String db) {
		if (userName != null && password!=null) {
			url = part1+"//"+userName+":"+password+"@"+part2;
		}
		try {
			HttpPut putRequest = new HttpPut(url+"/"+db);
			HttpResponse response = httpClient.execute(putRequest);

				BufferedReader br = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));

				String output;
				
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

//				httpClient.getConnectionManager().shutdown();

			  } catch (MalformedURLException e) {

				e.printStackTrace();
			
			  } catch (IOException e) {

				e.printStackTrace();

			  }
	}
	
	public void deleteDatabase(String db) {
		if (userName != null && password!=null) {
			url = part1+"//"+userName+":"+password+"@"+part2;
		}
		try {
			HttpDelete deleteRequest = new HttpDelete(url+"/"+db);
			deleteRequest.addHeader("accept", "application/json");
			
			HttpResponse response = httpClient.execute(deleteRequest);

				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

				String output;
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}


			  } catch (MalformedURLException e) {

				e.printStackTrace();
			
			  } catch (IOException e) {

				e.printStackTrace();

			  }
	}
}