package it.infocert.mytest.rest;

import it.infocert.mytest.entity.Customer;
import it.infocert.mytest.model.CustomerDTO;
import it.infocert.mytest.service.CustomerService;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/customers")
public class CustomerApi {

    @Inject
    CustomerService service;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> list(@NotBlank @QueryParam("name") String name) {
        return service.list(name);
    }

}
