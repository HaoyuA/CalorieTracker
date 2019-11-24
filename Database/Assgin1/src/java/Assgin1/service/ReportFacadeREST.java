/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assgin1.service;

import Assgin1.Report;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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
@Path("assgin1.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "Assgin1PU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Report entity) {
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
    public Report find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findByReportDate/{reportDate}")
    @Produces({"application/json"})
    public List<Report> findByReportDate(@PathParam("reportDate") Date reportDate) {
        Query query = em.createNamedQuery("Report.findByReportDate");
        query.setParameter("reportDate", reportDate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalCaloriesConsumed/{totalCaloriesConsumed}")
    @Produces({"application/json"})
    public List<Report> findByTotalCaloriesConsumed(@PathParam("totalCaloriesConsumed") int totalCaloriesConsumed) {
        Query query = em.createNamedQuery("Report.findByTotalCaloriesConsumed");
        query.setParameter("totalCaloriesConsumed", totalCaloriesConsumed);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalCaloriesBurned/{totalCaloriesBurned}")
    @Produces({"application/json"})
    public List<Report> findByTotalCaloriesBurned(@PathParam("totalCaloriesBurned") int totalCaloriesBurned) {
        Query query = em.createNamedQuery("Report.findByTotalCaloriesBurned");
        query.setParameter("totalCaloriesBurned", totalCaloriesBurned);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalStepsTaken/{totalStepsTaken}")
    @Produces({"application/json"})
    public List<Report> findByTotalStepsTaken(@PathParam("totalStepsTaken") int totalStepsTaken) {
        Query query = em.createNamedQuery("Report.findByTotalStepsTaken");
        query.setParameter("totalStepsTaken", totalStepsTaken);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySetCalorieGoal/{setCalorieGoal}")
    @Produces({"application/json"})
    public List<Report> findBySetCalorieGoal(@PathParam("setCalorieGoal") int setCalorieGoal) {
        Query query = em.createNamedQuery("Report.findBySetCalorieGoal");
        query.setParameter("setCalorieGoal", setCalorieGoal);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Report> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Report.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserIdANDReportDate/{userId}/{reportDate}")
    @Produces({"application/json"})
    public List<Report> findByUserIdANDReportDate(@PathParam("userId") int userId, @PathParam("reportDate") Date reportDate) {
        Query query = em.createNamedQuery("Report.findByUserIdANDReportDate");
        query.setParameter("userId", userId);
        query.setParameter("reportDate", reportDate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserNameANDSetCalorieGoal/{name}/{setCalorieGoal}")
    @Produces({"application/json"})
    public List<Report> findByUserNameANDSetCalorieGoal(@PathParam("name") String
name, @PathParam("setCalorieGoal") int setCalorieGoal) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r "
                + "WHERE r.userId.name = :name AND r.setCalorieGoal = :setCalorieGoal",Report.class);
        q.setParameter("name", name);
        q.setParameter("setCalorieGoal", setCalorieGoal);
        return q.getResultList();
}
    
    @GET
    @Path("findByUserNameANDReportDate/{userName}/{reportDate}")
    @Produces({"application/json"})
    public List<Report> findByUserNameANDReportDate(@PathParam("userName") String userName, @PathParam("reportDate") Date reportDate) {
        Query query = em.createNamedQuery("Report.findByUserNameANDReportDate");
        query.setParameter("name", userName);
        query.setParameter("reportDate", reportDate);
        return query.getResultList();
    }
    
//    @GET
//    @Path("calorieBurnedByStep/{userId}/{reportDate}")
//    @Produces({"text/plain"})
//    public Double calorieBurnedByStep(@PathParam("userId") int userId, @PathParam("reportDate") Date reportDate) {
//        Query query = em.createQuery("SELECT r FROM Report r WHERE r.reportDate = :reportDate AND r.userId.userId = :userId",Object[].class);
//        query.setParameter("userId", userId);
//        query.setParameter("reportDate", reportDate);
//        List<Report> reports = query.getResultList();
//        UserTableFacadeREST u = new UserTableFacadeREST();
//        int totalStepsTaken = reports.get(0).getTotalStepsTaken();
//        Double caloriePerStep = u.calculateCaloriesBurnedPerStep(userId);
//        return totalStepsTaken*caloriePerStep;
//    }
    
    @GET
    @Path("calorieCalculator/{userId}/{reportDate}")
    @Produces({"application/json"})
    public Object calorieCalculator(@PathParam("userId") int userId, @PathParam("reportDate") Date reportDate) {
        Query query = em.createQuery("SELECT r.totalCaloriesBurned,r.totalCaloriesConsumed,r.setCalorieGoal FROM Report r WHERE r.reportDate = :reportDate AND r.userId.userId = :userId",Object[].class);
        query.setParameter("userId", userId);
        query.setParameter("reportDate", reportDate);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject personObject = Json.createObjectBuilder().
            add("totalCaloriesConsumed", (int)row[1])
            .add("totalCaloriesBurned", (int)row[0])
            .add("remainingCalorie",(int)((int)row[2]+(int)row[0]-(int)row[1])).build();
        arrayBuilder.add(personObject);
 }
 JsonArray jArray = arrayBuilder.build();
 return jArray;
    }
    
    
    @GET
    @Path("addUpCalorieAndStep/{userId}/{startingDate}/{endingDate}")
    @Produces({"application/json"})
    public Object addUpCalorieAndStep(@PathParam("userId") int userId, @PathParam("startingDate") Date startingDate, @PathParam("endingDate") Date endingDate) {
        Query q = em.createQuery("SELECT r FROM Report r WHERE r.userId.userId = :userId AND r.reportDate BETWEEN :startingDate AND :endingDate",Report.class);
        q.setParameter("userId", userId);
        q.setParameter("startingDate", startingDate, TemporalType.DATE);
        q.setParameter("endingDate", endingDate, TemporalType.DATE);
        List<Report> reports = q.getResultList();
        //for(Report r:reports){
        //   if(false==r.betweenStartAndEnd(r.getReportDate(), startingDate, endingDate)){
        //    reports.remove(r);
        //    }
        //}
        int totalCaloriesBurned = 0;
        int totalCaloriesConsumed = 0;
        int totalStepsTaken = 0;
        for(Report r:reports){
            totalCaloriesBurned += r.getTotalCaloriesBurned();
            totalCaloriesConsumed += r.getTotalCaloriesConsumed();
            totalStepsTaken += r.getTotalStepsTaken();
        }
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObject personObject = Json.createObjectBuilder().
            add("totalCaloriesConsumed", totalCaloriesBurned)
            .add("totalCaloriesBurned",totalCaloriesConsumed)
            .add("totalStepsTaken",totalStepsTaken).build();
        arrayBuilder.add(personObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
         
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
