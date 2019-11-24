/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assgin1.service;

import Assgin1.UserTable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zhy
 */
@Stateless
@Path("assgin1.usertable")
public class UserTableFacadeREST extends AbstractFacade<UserTable> {

    @PersistenceContext(unitName = "Assgin1PU")
    private EntityManager em;

    public UserTableFacadeREST() {
        super(UserTable.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UserTable entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, UserTable entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserTable find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserTable> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserTable> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findByName/{name}")
    @Produces({"application/json"})
    public List<UserTable> findByName(@PathParam("name") String name) {
        Query query = em.createNamedQuery("UserTable.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySurname/{surname}")
    @Produces({"application/json"})
    public List<UserTable> findBySurname(@PathParam("surname") String surname) {
        Query query = em.createNamedQuery("UserTable.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    @GET
    @Path("findByEmail/{email}")
    @Produces({"application/json"})
    public List<UserTable> findByEmail(@PathParam("email") String email) {
        Query query = em.createNamedQuery("UserTable.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDob/{DoB}")
    @Produces({"application/json"})
    public List<UserTable> findByDoB(@PathParam("DoB") Date DoB) {
        Query query = em.createNamedQuery("UserTable.findByDob");
        query.setParameter("dob", DoB);
        return query.getResultList();
    }
    
    @GET
    @Path("findByHeight/{height}")
    @Produces({"application/json"})
    public List<UserTable> findByHeight(@PathParam("height") int height) {
        Query query = em.createNamedQuery("UserTable.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWeight/{weight}")
    @Produces({"application/json"})
    public List<UserTable> findByWeight(@PathParam("weight") int weight) {
        Query query = em.createNamedQuery("UserTable.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }
    
    @GET
    @Path("findByGender/{gneder}")
    @Produces({"application/json"})
    public List<UserTable> findByGender(@PathParam("gneder") String gneder) {
        Query query = em.createNamedQuery("UserTable.findByGender");
        query.setParameter("gneder", gneder);
        return query.getResultList();
    }
    
    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
    public List<UserTable> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("UserTable.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<UserTable> findByPostcode(@PathParam("postcode") String postcode) {
        Query query = em.createNamedQuery("UserTable.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    @GET
    @Path("findByLevelOfActivity/{levelOfActivity}")
    @Produces({"application/json"})
    public List<UserTable> findByLevelOfActivity(@PathParam("levelOfActivity") int levelOfActivity) {
        Query query = em.createNamedQuery("UserTable.findByLevelOfActivity");
        query.setParameter("levelOfActivity", levelOfActivity);
        return query.getResultList();
    }
    
    @GET
    @Path("findByStepPerMile/{stepPerMile}")
    @Produces({"application/json"})
    public List<UserTable> findByStepPerMile(@PathParam("stepPerMile") int stepPerMile) {
        Query query = em.createNamedQuery("UserTable.findByStepPerMile");
        query.setParameter("stepPerMile", stepPerMile);
        return query.getResultList();
    }
    
    @GET
    @Path("findByNameANDHeight/{name}/{height}")
    @Produces({"application/json"})
    public List<UserTable> findByNameANDHeight(@PathParam("name") String
name, @PathParam("height") int height) {
        TypedQuery<UserTable> q = em.createQuery("SELECT u FROM UserTable u "
                + "WHERE u.name = :name AND u.height = :height",UserTable.class);
        q.setParameter("name", name);
        q.setParameter("height", height);
        return q.getResultList();
}
    
    //4a
    @GET
    @Path("calculateCaloriesBurnedPerStep/{userId}")
    @Produces({"text/plain"})
    public double calculateCaloriesBurnedPerStep(@PathParam("userId") int userId) {
        Query q = em.createQuery("SELECT u FROM UserTable u WHERE u.userId = :userId",UserTable.class);
        q.setParameter("userId", userId);
       
        List<UserTable> users = q.getResultList();
        int stepPerMile = users.get(0).getStepPerMile();
        int weight = users.get(0).getWeight();
        double result = weight*2.2*0.49/stepPerMile;
        double d = (double)(Math.round(result*100)/100.0);
        return d;
        
    }
    
    //4b
    @GET
    @Path("calculateBMR/{userId}")
    @Produces({"text/plain"})
    public double calculateBMR(@PathParam("userId") int userId) {
        Query q = em.createQuery("SELECT u FROM UserTable u WHERE u.userId = :userId",UserTable.class);
        q.setParameter("userId", userId);
        List<UserTable> users = q.getResultList();
        int height = users.get(0).getHeight();
        int weight = users.get(0).getWeight();
        String sex = users.get(0).getGender();
        Date date = new Date(System.currentTimeMillis()); 
        String birthDate = new SimpleDateFormat("yyyy-MM-dd").format(users.get(0).getDob());
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(users.get(0).getDob()));                            
        int d2 = Integer.parseInt(formatter.format(date));                          
        int age = (d2 - d1) / 10000;  
        System.out.println(age);
        if(sex.equals("male"))
                {
                   
                 double result = (13.75*weight) + (5.003*height) - (6.755*age) + 66.5;
                 double d = (double)(Math.round(result*100)/100.0);
                 return d;
                }
        
                 double result = (9.563*weight) + (1.85*height) - (4.676*age) + 655.1;
                 double d = (double)(Math.round(result*100)/100.0);
                 return d;
        
    }
    
    //4c
    @GET
    @Path("calculateTotalDailyCaloriesBurned/{userId}")
    @Produces({"text/plain"})
    public double calculateTotalDailyCaloriesBurned(@PathParam("userId") int userId){
        Query q = em.createQuery("SELECT u FROM UserTable u WHERE u.userId = :userId",UserTable.class);
        q.setParameter("userId", userId);
        List<UserTable> users = q.getResultList();
        int levelOfActivity = users.get(0).getLevelOfActivity();
        double bmr = calculateBMR(userId);
        double result = 0.0;
        switch(levelOfActivity){
            case 1:
                result = 1.2 * bmr;
                break;
            case 2:
                result = 1.375 * bmr;
                break;
            case 3:
                result = 1.55 * bmr;
                break;
            case 4:
                result = 1.725 * bmr;
                break;
            case 5:
                result = 1.9 * bmr;
                break;
            default:
                }
    double d = (double)(Math.round(result*100)/100.0);
    return d;
    }
    
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
