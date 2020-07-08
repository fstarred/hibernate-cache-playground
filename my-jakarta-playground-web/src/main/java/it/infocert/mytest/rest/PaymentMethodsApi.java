package it.infocert.mytest.rest;

import it.infocert.mytest.model.PaymentMethodDTO;
import it.infocert.mytest.service.PaymentMethodService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/payment-methods")
public class PaymentMethodsApi {

    @Inject
    PaymentMethodService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public PaymentMethodDTO get(@PathParam("id") Integer id) {
        return service.get(id);
    }

}
