package br.com.emersonluiz.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.emersonluiz.model.Person;

public class DefaultStartService implements StartService {

    @Override
    public String helloworld() {
        return "Hello World";
    }

    @Override
    public Person getPerson(UUID id) {
        Person person = new Person();
        person.setId(id);
        person.setName("Clark Kent");
        person.setAge(30);
        return person;
    }

    @Override
    public List<Person> getPersonList() {
        Person person1 = new Person();
        person1.setId(UUID.randomUUID());
        person1.setName("Clark Kent");
        person1.setAge(30);
        Person person2 = new Person();
        person2.setId(UUID.randomUUID());
        person2.setName("Bruce Wayne");
        person2.setAge(30);
        return Arrays.asList(person1, person2);
    }

    @Override
    public Response createPerson(UriInfo uriInfo,   Person person) {
        person.setId(UUID.randomUUID());
        String id = String.valueOf(person.getId());
        URI uri = uriInfo.getRequestUriBuilder().path(id).build();
        return Response.created(uri).entity(person).build();
    }
}
