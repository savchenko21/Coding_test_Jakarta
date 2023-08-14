package org.acme.ressource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.User;
import org.acme.model.UserRequest;
import org.acme.service.UserService;
import java.util.List;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject                     // Injecting an instance of a UserService
    UserService userService;

    @POST
    @Path("/addUser")
    @Transactional
    public Response add(@Valid UserRequest request){  // requiring a valid instance of a UserRequest class
        try{
            if(request == null){                      // if no instance provided return an error
                return Response.status(400).entity("Request body not provided").build();
            }                                         // call addUser() method and pass the provided UserRequest request to it
            return Response.ok(userService.addUser(request)).build();
        }
        catch (Exception e){
            return  Response.serverError().build();
        }
    }
    @PUT
    @Path("/updateUser")
    @Transactional
    public Response update(@Valid UserRequest request){  // requiring a valid instance of a UserRequest class
        try{
            if(request == null){                         // if no instance provided return an error
                return Response.status(400).entity("Request body not provided").build();
            }                                            // call updateUser() method and pass the provided UserRequest request to it
            return Response.ok(userService.updateUser(request)).build();
        }
        catch (Exception e){
            return  Response.serverError().build();
        }
    }
}
