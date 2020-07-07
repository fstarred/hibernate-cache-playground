package it.infocert.mytest.rest;

import it.infocert.mytest.entity.Article;
import it.infocert.mytest.entity.Property;
import it.infocert.mytest.model.ArticleDTO;
import it.infocert.mytest.model.PropertyDTO;
import it.infocert.mytest.service.ArticleService;
import it.infocert.mytest.service.PropertyService;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/articles")
public class ArticlesApi {

    @Inject
    ArticleService service;

    @Inject
    PropertyService propertyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ArticleDTO get(@PathParam("id") String id) {
        return service.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleDTO> list(@NotBlank @QueryParam("type") String type) {
        return service.list(type);
    }

    @GET
    @Path("/{id}/properties/{property}")
    @Produces(MediaType.APPLICATION_JSON)
    public PropertyDTO get(@PathParam("id") String id,
                           @PathParam("property") String property) {
        return propertyService.get(id, property);
    }
}
