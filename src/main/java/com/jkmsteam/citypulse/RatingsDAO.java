package com.jkmsteam.citypulse;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


//ALL STATIC METHODS HERE!
//write a data access object (DOA) with static methods (STATIC) to do the database functions you want. 
//Note that hibernate users a query language distinct from SQL - 
//Look up how to do specific tasks you want
//Reference you hibernate configuration XML files for POJOs properly.
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
}