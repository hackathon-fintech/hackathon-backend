package com.variacode.bancointeligente.core.rest;

import com.variacode.bancointeligente.entity.UserAccount;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author miguel@variacode.com
 */
@Path("usuario")
@Api(value = "/usuario", description = "USuario API")
public class UserAccountResource extends AbstractResource {

    public UserAccountResource() {
    }

    @GET
    @Produces("application/json")
    @ApiOperation(value = "trae información de cliente",
            notes = "",
            response = UserAccount.class)//,
    //responseContainer = "List")
    public Response getJson(@HeaderParam("rut") String rut) {
        try {
            checkNullsOrEmptyString(rut);
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        UserAccount ua = new UserAccount();
        ua.setFirstName("Miguel");
        ua.setPremium(true);
        ua.setRut("1-9");
        ua.setSpecialAssistance(true);
        return Response.ok(Collections.singletonList(ua)).build();
    }

}
