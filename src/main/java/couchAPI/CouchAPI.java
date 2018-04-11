package couchAPI;

import couchDocumentRepository.*;

public class CouchAPI {

	public static void main(String[] args) {
		CouchConect couchConect = new CouchConect();
		
		couchConect.setUrl("http://127.0.0.1:5984");
		couchConect.setUsername("GiRi");
//		couchConect.setPassword("SaKi");
		couchConect.setDbName("person");
		
		CouchCRUD couchCRUD = new CouchCRUD();
		CouchDocuments couchDocs = new CouchDocuments();
		CouchViews couchViews = new CouchViews();
//		couchCRUD.viewAllDbs();
//		couchCRUD.createDatabase("test");
//		couchCRUD.deleteDatabase("test");
		
//		couchDocs.viewAllDocument();
//		couchDocs.getDocument("18496a5fd24dbc43e8edd6586501c55a");
		
//		Person person = new Person();
//		person.setName("test101");
//		person.setAge(40);
	
//		couchDocs.createDocument(person);
		
//		couchDocs.deleteDocument("18496a5fd24dbc43e8edd6586501801f","1-ad4913a66e3e9de24aba62f97e503c74");
//		couchDocs.updateDocument("18496a5fd24dbc43e8edd6586501c55a", person);
		
//		couchViews.getView("newDesignDoc", "new-view");
	}

}
