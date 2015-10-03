/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variacode.bancointeligente.core.rest;

import com.variacode.bancointeligente.bo.Usuario;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.util.Collections;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author miguel
 */
@Path("usuario")
@Api(value = "/usuario", description = "USuario API")
public class RestResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestResource
     */
    public RestResource() {
    }

    /**
     * Retrieves representation of an instance of com.variacode.bancointeligente.core.rest.RestResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @ApiOperation(value = "All Users",
            notes = "",
            response = Usuario.class,
            responseContainer = "List")
    public Response getJson() {
        return Response.ok(Collections.singletonList(new Usuario("1-9"))).build();
    }

    /**
     * PUT method for updating or creating an instance of RestResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
