package com.variacode.bancointeligente.core.rest;

import com.variacode.bancointeligente.entity.UserAccount;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import javax.ws.rs.Produces;
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
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response getJson(@HeaderParam("rut") String rut) {
        try {
            checkNullsOrEmptyString(rut);
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName("Miguel");
        userAccount.setPremium(true);
        userAccount.setRut("1-9");
        userAccount.setSpecialAssistance(true);
        return Response.ok(userAccount).build();
    }

    @PUT
    @Produces("application/json")
    @ApiOperation(value = "modifica informacion del cliente, excepto rut",
            notes = "branchCode se puede poner si es null (ninguna) o MONEDA, branchStatus= null o vacio, NEAR, INSIDE, GOING",
            response = UserAccount.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response putJson(UserAccount userAccount) {
        try {
            checkNullsOrEmptyString(userAccount);
            checkNullsOrEmptyString(userAccount.getFirstName(), userAccount.getRut());
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        return Response.ok(userAccount).build();
    }

}
