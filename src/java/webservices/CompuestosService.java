package webservices;

import core.NameResolver;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

@Path("compuestos")
public class CompuestosService {

    @Context
    private UriInfo context;

    public CompuestosService() {
    }

    @GET
    @Path("{tipo}/{compuesto}")
    @Produces("text/plain")
    public String getName(@PathParam("tipo") int tipo, @PathParam("compuesto") String compuesto) {
        NameResolver resolver = new NameResolver(tipo);
        return resolver.resolveName(compuesto);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
