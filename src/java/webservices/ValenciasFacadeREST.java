package webservices;

import entities.Valencia;
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

@Stateless
@Path("valencias")
public class ValenciasFacadeREST extends AbstractFacade<Valencia> {
    @PersistenceContext(unitName = "ChemistryServicesPU")
    private EntityManager em;

    public ValenciasFacadeREST() {
        super(Valencia.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Valencia entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Valencia entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Valencia find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Valencia> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Valencia> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("values/{elem}")
    @Produces("text/plain")
    public String getValuesOf(@PathParam("elem") String elem) {
        String sql = "SELECT v.valor FROM Valencia v WHERE v.elemento.simbolo=:elem";
        Query query = em.createQuery(sql);
        query.setParameter("elem", elem);
        return query.getResultList().toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
