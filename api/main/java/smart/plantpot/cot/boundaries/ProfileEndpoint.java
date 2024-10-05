package smart.plantpot.cot.boundaries;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.function.Supplier;

import smart.plantpot.cot.entities.Pot;
import smart.plantpot.cot.entities.User;
import smart.plantpot.cot.repositories.PotRepository;
import smart.plantpot.cot.repositories.UserRepository;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
@Path("profile") // returns details of the user for the profile page
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileEndpoint {
    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);

    @Inject
    private UserRepository repository;
    @Inject
    private PotRepository potRepository;
    @GET
    @Path("/{mail}")
    public  User get(@PathParam("mail") String username) {
        User user=repository.findById(username).orElseThrow();
        String passwordhash=""; // create user with empty string  instead of password
        User profile=new User(user.getName(),user.getfullname(),passwordhash,user.getPermissionLevel());
        return profile;

    }
    @RolesAllowed("Administrator") // only administrators can access this endpoint
    @GET
    public List<User> findAll() { //GET METHOD to receive a list of all users data from the database
        return repository.findAll()
                .collect(toList());
    }

    @RolesAllowed("Administrator") //
    @Path("/pots")
    @GET
    public List<Pot> findAllPots() { //GET METHOD to receive a list of all users data from the database
        return potRepository.findAll()
                .collect(toList());
    }

}