package smart.plantpot.cot.boundaries;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import smart.plantpot.cot.entities.Pot;
import smart.plantpot.cot.repositories.PotRepository;


import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
@Path("/pot")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PotEndpoint {
    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);
    @Inject
    private PotRepository repository;

    @GET
    public List<Pot> finAll(){
        return repository.findAll().collect(toList());
    }

    @POST
    public Pot save(Pot pot){
        repository.save(pot);
        return pot;

    }

}
