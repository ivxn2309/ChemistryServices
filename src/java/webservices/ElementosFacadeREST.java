package webservices;

import entities.Elemento;
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
import javax.ws.rs.QueryParam;

@Stateless
@Path("elementos")
public class ElementosFacadeREST extends AbstractFacade<Elemento> {
    @PersistenceContext(unitName = "ChemistryServicesPU")
    private EntityManager em;

    public ElementosFacadeREST() {
        super(Elemento.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Elemento entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Elemento entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Elemento find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Elemento> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Elemento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("find/{type}")
    @Produces({"application/xml", "application/json"})
    public List<Elemento> findByType(@PathParam("type") String type, @QueryParam("exclude") String typeEx) {
        if (typeEx == null) typeEx = "";
        String sql = "SELECT e FROM Elemento e WHERE e.caracter LIKE :type AND e.caracter NOT LIKE :typeEx";
        if(type.startsWith("¬")){
            type = type.replace("¬", "");
            sql = "SELECT e FROM Elemento e WHERE e.caracter NOT LIKE :type AND e.caracter NOT LIKE :typeEx";
        }
        Query query = em.createQuery(sql);
        query.setParameter("type", type + "%").setParameter("typeEx", typeEx);
        return query.getResultList();
    }
    
    @GET
    @Path("weight/{elem}")
    @Produces("text/plain")
    public double getWeight(@PathParam("elem") String elem) {
        String sql = "SELECT e.peso FROM Elemento e WHERE e.simbolo LIKE :elem";
        Query query = em.createQuery(sql);
        query.setParameter("elem", elem);
        return query.getFirstResult();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
