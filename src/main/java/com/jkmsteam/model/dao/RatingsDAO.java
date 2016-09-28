package com.jkmsteam.model.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.jkmsteam.model.dto.Rating;

public class RatingsDAO {
	private static SessionFactory factory;
	
	private static void setupFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			;//this is silliness!
		}
	    
		 Configuration configuration = new Configuration();

		 // Pass hibernate configuration file
		 configuration.configure("hibernate.cfg.xml");
		 
		 // pass in setup file for User class
		 configuration.addResource("rating.hbm.xml");
		 
		 // Since version 4.x, service registry is being used
		 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
		 applySettings(configuration.getProperties()).build(); 

		 // Create session factory instance
		 factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static int addRating(Rating r) {
		if (factory == null)
			setupFactory();
		 // Get current session
		 Session hibernateSession = factory.openSession();

		 // Begin transaction
		 hibernateSession.getTransaction().begin();

		 //save this specific record
		 int i = (Integer)hibernateSession.save(r);  
		
		 // Commit transaction
		 hibernateSession.getTransaction().commit();
		 
		 hibernateSession.close();  

		 return i;  
	}
	
	 public static List<Rating> getRatingsByPlace(String place){
	        if (factory == null)
	            setupFactory();
	         // Get current session
	         Session hibernateSession = factory.openSession();

	         // Begin transaction
	         hibernateSession.getTransaction().begin();
	         
	         //deprecated method & unsafe cast
	         List<Rating> ratings = hibernateSession.createQuery("FROM Rating where placeId ='"+ place +"'").list(); 
	         
	         // Commit transaction
	         hibernateSession.getTransaction().commit();
	               
	           hibernateSession.close();  
	                          
	        return ratings;
	    }

	 public static List getAggregateRatings(){
	        if (factory == null)
	            setupFactory();
	         // Get current session
	         Session hibernateSession = factory.openSession();

	         // Begin transaction
	         hibernateSession.getTransaction().begin();

	         List ratings = hibernateSession.createCriteria(Rating.class)
	        		 .setProjection(Projections.projectionList()
	        				// .add(Projections.id(), "id")
	        				// .add(Projections.property("userId"))
	        				 .add(Projections.property("placeId"))
	        				 .add(Projections.sum("dead"), "dead")
	        				 .add(Projections.sum("justRight"), "justRight")
	        				 .add(Projections.sum("jumping"), "jumping")
	        				 .add(Projections.sum("coverCharge"), "coverCharge")
	        				 .add(Projections.sum("crowded"), "crowded")
	        				 .add(Projections.sum("expensive"), "expensive")
	        				 .add(Projections.sum("loud"), "loud")
	        				 .add(Projections.sum("bigGroups"), "bigGroups")
	        				 .add(Projections.sum("smallGroups"), "smallGroups")
	        				 .add(Projections.sum("safePlace"), "safePlace")
	        				 .add(Projections.sum("goodParking"), "goodParking")
	        				 .add(Projections.groupProperty("placeId"))
	        				 ).list();
	         
	         // Commit transaction
	         hibernateSession.getTransaction().commit();
	               
	         hibernateSession.close();  
	                          
	        return ratings;
	    }
}