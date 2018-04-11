package couchAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class CouchDocuments {
	CouchConect couchConect = new CouchConect();
	String url = couchConect.getUrl();
	String[] parts = url.split("//");
	String part1 = parts[0]; 
	String part2 = parts[1];
	String dbName = couchConect.getDbName();
	String userName = couchConect.getUsername();
	String password = couchConect.getPassword();
	DefaultHttpClient httpClient = new DefaultHttpClient();
	ObjectMapper mapper = new ObjectMapper();
	
	public void viewAllDocument() {

		 try {
				HttpGet getAllDbs = new HttpGet(url+"/"+dbName+"/_all_docs");
				getAllDbs.addHeader("accept", "application/json");

				HttpResponse response = httpClient.execute(getAllDbs);

				BufferedReader br = new BufferedReader(
		                         new InputStreamReader((response.getEntity().getContent())));

				String output;
//				System.out.println("All Documents of "+dbName+" \n");
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
	
	public HttpResponse getDocument(String id) {
		try {
			HttpGet getDoc = new HttpGet(url+"/"+dbName+"/"+id);
			getDoc.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getDoc);

			String jsonString = EntityUtils.toString(response.getEntity());
			
			System.out.println(jsonString);
			

//			httpClient.getConnectionManager().shutdown();

		  } catch (ClientProtocolException e) {
		
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }
		HttpResponse response = null;
		return response;
	}
	
	public void createDocument(Object o) {
		if (userName != null && password!=null) {
			url = part1+"//"+userName+":"+password+"@"+part2;
		}
		try {

			HttpPost postRequest = new HttpPost(url+"/"+dbName);
			String jsonInString = mapper.writeValueAsString(o);
//			System.out.println(jsonInString);
			StringEntity input = new StringEntity(jsonInString);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

//			if (response.getStatusLine().getStatusCode() != 201) {
//				throw new RuntimeException("Failed : HTTP error code : "
//					+ response.getStatusLine().getStatusCode());
//			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

//			httpClient.getConnectionManager().shutdown();

		  } catch (MalformedURLException e) {

			e.printStackTrace();
		
		  } catch (IOException e) {

			e.printStackTrace();

		  }

		}
	
	public void deleteDocument(String id, String rev) {
		if (userName != null && password!=null) {
			url = part1+"//"+userName+":"+password+"@"+part2;
		}
		try {

			HttpDelete deleteRequest = new HttpDelete(url+"/"+dbName+"/"+id+"?rev="+rev);

			HttpResponse response = httpClient.execute(deleteRequest);

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (MalformedURLException e) {

			e.printStackTrace();
		
		  } catch (IOException e) {

			e.printStackTrace();

		  }

	}
	
public void updateDocument(String id, Object o) {
	if (userName != null && password!=null) {
		url = part1+"//"+userName+":"+password+"@"+part2;
	}
	
	
		try {
			HttpGet getRequest = new HttpGet(url+"/"+dbName+"/"+id);

			HttpResponse getResponse = httpClient.execute(getRequest);
			
			String jsonString = EntityUtils.toString(getResponse.getEntity());
			
			System.out.println(jsonString);
			
			HttpPut postRequest = new HttpPut(url+"/"+dbName+"/"+id);
			String jsonInString = mapper.writeValueAsString(o);
			StringEntity input = new StringEntity(jsonInString);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			
			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

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
	

