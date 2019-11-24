/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assgin1.service;

import Assgin1.Credential;
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
@Path("assgin1.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "Assgin1PU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credential entity) {
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
    public Credential find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
     @GET
    @Path("findByUserName/{userName}")
    @Produces({"application/json"})
    public List<Credential> findByName(@PathParam("userName") String userName) {
        Query query = em.createNamedQuery("Credential.findByUserName");
        query.setParameter("userName", userName);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPassword/{password}")
    @Produces({"application/json"})
    public List<Credential> findByPassword(@PathParam("password") String password) {
        Query query = em.createNamedQuery("Credential.password");
        query.setParameter("password", password);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySignUpDate/{signUpDate}")
    @Produces({"application/json"})
    public List<Credential> findBySignUpDate(@PathParam("signUpDate") Date signUpDate) {
        Query query = em.createNamedQuery("Credential.findBySignUpDate");
        query.setParameter("signUpDate", signUpDate);
        return query.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
