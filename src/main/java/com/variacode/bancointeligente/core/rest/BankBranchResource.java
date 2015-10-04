package com.variacode.bancointeligente.core.rest;

import com.variacode.bancointeligente.controller.BusinessLogicBeanLocal;
import com.variacode.bancointeligente.entity.DepositSlip;
import com.variacode.bancointeligente.entity.UserAccount;
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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author miguel@variacode.com
 */
@Stateless
@Path("branch")
@Api(value = "/branch", description = "Sucursal API")
public class BankBranchResource extends AbstractResource {

    @EJB
    private BusinessLogicBeanLocal businessLogicBean;
    
    @EJB
    private StorageBeanLocal storage;
    
    public BankBranchResource() {
    }

    @GET
    @Path("/user")
    @Produces("application/json")
    @ApiOperation(value = "usuarios en una sucursal con una accion especifica",
            notes = "branchName=MONEDA, "
                    + "action=TELLER,INFORMATION,EXECUTIVE",
            response = UserAccount.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response getJsonAll(@QueryParam("branchName") String branchName, @QueryParam("action") String action) {
        try {
            checkNullsOrEmptyString(branchName);
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        return Response.ok(businessLogicBean.userAccountGet(branchName, action)).build();
    }

    @GET
    @Path("/user/deposit")
    @Produces("application/json")
    @ApiOperation(value = "trae depositos de un cliente",
            notes = "",
            response = DepositSlip.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response getJson(@HeaderParam("rut") String rut) {
        try {
            checkNullsOrEmptyString(rut);
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        return Response.ok(businessLogicBean.depositSlipGet(rut)).build();
    }

    @PUT
    @Path("/user/deposit")
    @Produces("application/json")
    @ApiOperation(value = "cliente o ejecutivo graba o modifica un deposito",
            notes = "status=NEW,IGNORE,DONE "
            + "type=CHECK,CASH",
            response = DepositSlip.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid something")})
    public Response putJson(@HeaderParam("rut") String rut, DepositSlip depositSlip) {
        try {
            checkNullsOrEmptyString(depositSlip, rut);
        } catch (BancoInteligenteRESTException ex) {
            return responseWithBodyAndLog(ex.getStatus(), ex.getMessage());
        }
        //TODO: login
        depositSlip.setUserRut(rut);
        return Response.ok(businessLogicBean.depositSlipPut(depositSlip)).build();
    }

}
