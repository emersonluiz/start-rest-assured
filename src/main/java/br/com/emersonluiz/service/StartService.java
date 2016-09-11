package br.com.emersonluiz.service;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.emersonluiz.model.Person;

@Path("/")
public interface StartService {

    @Path("/helloworld")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String helloworld();

    @Path("/person/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") UUID id);

    @Path("/person-list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersonList();

    @Path("/person-create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(@Context UriInfo uriInfo, Person person);
}
