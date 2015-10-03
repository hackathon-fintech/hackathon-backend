package com.variacode.bancointeligente.core.rest;

import com.variacode.bancointeligente.controller.BusinessLogicBean;
import com.variacode.bancointeligente.controller.BusinessLogicBeanLocal;
import com.variacode.bancointeligente.entity.UserAccount;
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
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName("Miguel");
        userAccount.setPremium(true);
        userAccount.setRut("1-9");
        userAccount.setPhotoURL("http://mundoejecutivo.com.mx/sites/default/files/styles/large/public/cliente_1.jpg");
        userAccount.setSpecialAssistance(true);
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
            checkNullsOrEmptyString(userAccount.getFirstName(), userAccount.getRut());
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        return Response.ok(userAccount).build();
    }

    @POST
    @Produces("text/plain")
    @ApiOperation(value = "login",
            notes = "login pide rut y pin de 5 digitos",
            response = String.class)
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
        return Response.ok(token).build();
    }

}
