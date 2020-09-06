/**
 * 
 */
package edu.saby.msec.catalogserver.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author abhisheksa
 *
 */
@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	MongoClient mongoClient;
	
	@Autowired
	private Environment env;
	
	@RequestMapping
    public String getAll() {
		System.out.println("getting all here");
		
		MongoDatabase database = mongoClient.getDatabase(env.getProperty("data.mongodb.database"));
		MongoCollection<Document> collection = database.getCollection("customers");
		
		FindIterable<Document> docs = collection.find();
		 
		for(Document doc : docs) {
		    System.out.println(doc);
		}
		
		return "got all";
    }
	
	@RequestMapping("/customers/key={field}&val={value}")
    public String getCustomersByField(@PathVariable("field") String field, @PathVariable("value") String value) {
		System.out.println("getting all here");
		
		MongoDatabase database = mongoClient.getDatabase(env.getProperty("data.mongodb.database"));
		MongoCollection<Document> collection = database.getCollection("customers");
		
		FindIterable<Document> docs = collection.find(new Document(field, value));
		 
		for(Document doc : docs) {
		    System.out.println(doc);
		}
		
		return "got all";
    }
	
	@RequestMapping("/customers/add/name={name}&company={company}")
    public String add(@PathVariable("name") String name, @PathVariable("company") String company) {
		System.out.println("adding here");
		
		MongoDatabase database = mongoClient.getDatabase(env.getProperty("data.mongodb.database"));
		MongoCollection<Document> collection = database.getCollection("customers");
		
		Document document = new Document();
		document.put("name", name);
		document.put("company", company);
		collection.insertOne(document);
		
		return "Added";
    }

}
