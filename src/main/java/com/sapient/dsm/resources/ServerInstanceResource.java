package com.sapient.dsm.resources;


import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sapient.dsm.dao.ServerInstanceDao;
import com.sapient.dsm.exception.DSMException;
import com.sapient.dsm.model.ServerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/serviceinstance")
@Produces(MediaType.APPLICATION_JSON)
public class ServerInstanceResource {

    @Autowired
    private ServerInstanceDao serverInstanceDao;

    /************************************ CREATE ************************************/
    /**
     * Adds a new resource (ServerInstance) from the given json format (at least title
     * and feed elements are required at the DB level)
     *
     * It has been enhanced to illustrate handling of exceptions
     *
     * @param serverInstance
     * @return
     */
    @POST
    @Timed
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public Response createServerInstance(ServerInstance serverInstance) throws DSMException {
        serverInstanceDao.create(serverInstance);

        if ("1234".equals(serverInstance.getId())) {
            final DSMException exception = new DSMException(500, "Instance with Id 1234 cannot be created.");
            throw exception;
        }

        return Response.status(201)
                .entity("A new ServerInstance/resource has been created").build();
    }

    /************************************ READ ************************************/
    /**
     * Returns all resources (ServerInstances) from the database
     *
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    @GET
    @Timed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ServerInstance> getServerInstances() throws JsonGenerationException, JsonMappingException, IOException {

        List<ServerInstance> ServerInstances = serverInstanceDao.getAllServerInstance();

        return ServerInstances;
    }

    @GET
    @Timed
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") String id) throws JsonGenerationException, JsonMappingException, IOException {

        ServerInstance ServerInstanceById = serverInstanceDao.getServerInstance(id);

        if (ServerInstanceById != null) {
            return Response
                    .status(200)
                    .entity(ServerInstanceById)
                    .header("Access-Control-Allow-Headers", "X-extra-header")
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("The ServerInstance with the id " + id + " does not exist")
                    .build();
        }
    }

    /************************************ UPDATE ************************************/
    /**
     * Updates the attributes of the ServerInstance received via JSON for the given @param
     * id
     * <p>
     * If the ServerInstance does not exist yet in the database (verified by
     * <strong>id</strong>) then the application will try to create a new
     * ServerInstance resource in the db
     *
     * @param serverInstance
     * @return
     */
    @PUT
    @Timed
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public Response updateServerInstanceById(ServerInstance serverInstance) {
        String message;
        int status;
        if (serverInstance.getId() != null) {
            serverInstanceDao.update(serverInstance);
            status = 200; // OK
            message = "ServerInstance has been updated";
        } else {
            status = 406; // Not acceptable
            message = "ServerInstance contains null Id. Update Operation not performed";
        }

        return Response.status(status).entity(message).build();
    }


    /************************************ DELETE ************************************/
    @DELETE
    @Timed
    @Path("{id}")
    @Produces({MediaType.TEXT_HTML})
    public Response deleteServerInstanceById(@PathParam("id") String id) {
        if (serverInstanceDao.deleteById(id) == 1) {
            return Response.status(204).build();
        } else {
            return Response
                    .status(404)
                    .entity("ServerInstance with the id " + id
                            + " is not present in the database").build();
        }
    }

    public void setServerInstanceDao(ServerInstanceDao serverInstanceDao) {
        this.serverInstanceDao = serverInstanceDao;
    }

}


