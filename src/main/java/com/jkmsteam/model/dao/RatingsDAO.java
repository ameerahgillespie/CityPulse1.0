package com.jkmsteam.model.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.jkmsteam.model.dto.Rating;


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

//	 public static List<Rating> getAggregateRatings(){
//	        if (factory == null)
//	            setupFactory();
//	         // Get current session
//	         Session hibernateSession = factory.openSession();
//
//	         // Begin transaction
//	         hibernateSession.getTransaction().begin();
//	         
//	         //deprecated method & unsafe cast
//	         String query = "select id, userId, placeId,  sum(dead) as dead, "
//	         		+ "sum(justRight) as justRight, sum(jumping) as jumping, sum(coverCharge) as coverCharge, "
//	         		+ "sum(crowded) as crowded, sum(expensive) as expensive, sum(loud) as loud, sum(bigGroups) as bigGroups, "
//	         		+ "sum(smallGroups) as smallGroups, sum(safePlace) as safePlace, sum(goodParking) as goodParking "
//	         		+ "FROM Rating group by placeId";
//	         List<Rating> ratings = (List<Rating>)hibernateSession.createQuery(query, Rating.class).getResultList();
//	         
//
//	         List results = session.createCriteria(Cat.class)
//	        		    .setProjection( Projections.projectionList()
//	        		        .add( Projections.rowCount() )
//	        		        .add( Projections.avg("weight") )
//	        		        .add( Projections.max("weight") )
//	        		        .add( Projections.groupProperty("color") )
//	        		    )
//	        		    .list();
//	         // Commit transaction
//	         hibernateSession.getTransaction().commit();
//	               
//	           hibernateSession.close();  
//	                          
//	        return ratings;
//	    }

	public static Rating updateRating(Rating rating) {
		if (factory == null)
            setupFactory();
         // Get current session
         Session hibernateSession = factory.openSession();

         // Begin transaction
         hibernateSession.getTransaction().begin();
         
         //step 1: get current rating from database for this specified bar by ID
         Criteria cr = hibernateSession.createCriteria(Rating.class);
         cr.add(Restrictions.eq("placeId", rating.getPlaceId()));
         List results = cr.list();
         Rating tempRating;
		//Rating tempRating = hibernateSession.get(Rating.class, rating.getId());
         if  (results.isEmpty()) {//if id does not exist do insert
        	 tempRating = rating;     	      			 
         }
         else {//if record does exist do update
        	 tempRating  = (Rating) results.get(0);
        	 //step 2: add/increment count by 1 to the rating "dead"
             tempRating.setDead(tempRating.getDead() + rating.getDead());
             
         }
         
        
         //step 3: update database with new value
         hibernateSession.saveOrUpdate(tempRating);
         // Commit transaction
         hibernateSession.getTransaction().commit();
               
           hibernateSession.close();  
		return tempRating;
	}

	private static Rating rating() {
		// TODO Auto-generated method stub
		return null;
	}
/*
 return hashmap of ratings for all place IDs in database
 */
	public static List<Rating> getAllRatings() {
	       if (factory == null)
	            setupFactory();
	         // Get current session
	         Session hibernateSession = factory.openSession();

	         // Begin transaction
	         hibernateSession.getTransaction().begin();
	         
	         //deprecated method & unsafe cast
	         List<Rating> ratings = hibernateSession.createQuery("FROM Rating").list(); 
	         
	         // Commit transaction
	         hibernateSession.getTransaction().commit();
	               
	         hibernateSession.close();  
		return ratings;
	}
}