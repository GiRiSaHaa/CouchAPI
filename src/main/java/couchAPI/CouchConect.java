package couchAPI;

public class CouchConect {
	private static String url;
	private static int port;
	private static String username;
	private static String password;
	private static String dbName;
	
	public String getDbName() {
		return dbName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	public static String getUrl() {
		return url;
	}


	public static void setUrl(String url) {
		CouchConect.url = url;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public static String getUsername() {
		return username;
	}


	public static void setUsername(String username) {
		CouchConect.username = username;
	}


	public static String getPassword() {
		return password;
	}


	public static void setPassword(String password) {
		CouchConect.password = password;
	}

}
