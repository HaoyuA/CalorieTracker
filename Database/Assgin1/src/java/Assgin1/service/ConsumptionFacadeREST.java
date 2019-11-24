/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assgin1.service;

import Assgin1.Consumption;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("assgin1.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "Assgin1PU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
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
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findByConsumptionDate/{consumptionDate}")
    @Produces({"application/json"})
    public List<Consumption> findByConsumptionDate(@PathParam("consumptionDate") Date consumptionDate) {
        Query query = em.createNamedQuery("Consumption.findByConsumptionDate");
        query.setParameter("consumptionDate", consumptionDate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByQuantity/{quantity}")
    @Produces({"application/json"})
    public List<Consumption> findByQuantity(@PathParam("quantity") int quantity) {
        Query query = em.createNamedQuery("Consumption.findByQuantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFoodId/{foodId}")
    @Produces({"application/json"})
    public List<Consumption> findByFoodId(@PathParam("foodId") int foodId) {
        Query query = em.createNamedQuery("Consumption.findByFoodId");
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Consumption> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Consumption.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserIdANDConsumptionDate/{userId}/{consumptionDate}")
    @Produces({"application/json"})
    public List<Consumption> findByUserIdANDConsumptionDate(@PathParam("userId") int userId, @PathParam("consumptionDate") Date consumptionDate) {
        Query query = em.createNamedQuery("Consumption.findByUserIdANDConsumptionDate");
        query.setParameter("userId", userId);
        query.setParameter("consumptionDate", consumptionDate);
        return query.getResultList();
    }
    
    @GET
    @Path("calculateTotalCaloriesConsumed/{userId}/{consumptionDate}")
    @Produces({"text/plain"})
    public int calculateTotalCaloriesConsumed(@PathParam("userId") int userId, @PathParam("consumptionDate") Date consumptionDate) {
        Query q = em.createQuery("SELECT c FROM Consumption c WHERE c.userId.userId = :userId AND c.consumptionDate = :consumptionDate",Consumption.class);
        q.setParameter("userId", userId);
        q.setParameter("consumptionDate", consumptionDate);
        List<Consumption> consumptions = q.getResultList();
        int i = 0;
        for(Consumption c : consumptions){
          i += c.getFoodId().getCalorieAmount()*c.getQuantity();
        }
        return i;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
