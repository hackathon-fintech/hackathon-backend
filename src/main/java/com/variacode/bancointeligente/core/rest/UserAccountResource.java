package com.variacode.bancointeligente.core.rest;

import com.variacode.bancointeligente.controller.BusinessLogicBeanLocal;
import com.variacode.bancointeligente.entity.UserAccount;
import com.variacode.bancointeligente.entity.UserToken;
import com.variacode.bancointeligente.storage.StorageBeanLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author miguel@variacode.com
 */
@Stateless
@Path("user")
@Api(value = "/user", description = "Usuario API")
public class UserAccountResource extends AbstractResource {

    @EJB
    private BusinessLogicBeanLocal businessLogicBean;
    
    @EJB
    private StorageBeanLocal storage;

    public UserAccountResource() {
    }

    @GET
    @Produces("application/json")
    @ApiOperation(value = "trae información de cliente",
            notes = "",
            response = UserAccount.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response getJson(@HeaderParam("rut") String rut) {
        try {
            checkNullsOrEmptyString(rut);
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        //TODO: login
        UserAccount userAccount = storage.get(UserAccount.class, rut);
        if(userAccount == null){
            return responseWithBodyAndLog(Status.BAD_REQUEST, "User doesn't exist");
        }
        return Response.ok(userAccount).build();
    }

    @PUT
    @Produces("application/json")
    @ApiOperation(value = "modifica informacion del cliente, excepto rut",
            notes = "branchCode se puede poner si es null (ninguna) o MONEDA, "
            + "branchStatus= null o vacio, NEAR, INSIDE, GOING, "
            + "action=TELLER,INFORMATION,EXECUTIVE",
            response = UserAccount.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response putJson(UserAccount userAccount) {
        try {
            checkNullsOrEmptyString(userAccount);
            checkNullsOrEmptyString(userAccount.getRut());
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        //TODO: login
        //TODO: autoriacion
        storage.put(UserAccount.class, userAccount.getRut(), userAccount);
        return Response.ok(userAccount).build();
    }

    @POST
    @Produces("application/json")
    @ApiOperation(value = "login",
            notes = "login pide rut y pin de 5 digitos",
            response = UserToken.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response login(@HeaderParam("rut") String rut, @HeaderParam("pin") String pin) {
        String token;
        try {
            checkNullsOrEmptyString(rut, pin);
            token = businessLogicBean.login(rut, pin);
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        if (token == null) {
            return responseWithBodyAndLog(Status.INTERNAL_SERVER_ERROR, "Login error");
        }
        UserToken t = new UserToken();
        t.setToken(token);
        return Response.ok(t).build();
    }

}
