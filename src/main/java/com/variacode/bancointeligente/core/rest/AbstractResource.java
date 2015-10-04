package com.variacode.bancointeligente.core.rest;

import com.variacode.bancointeligente.entity.UserAccount;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author miguel@variacode.com
 */
public class AbstractResource {
    
    private static final String ADMIN_SECRET = "4m9fvhgi58gqqa5akuiv0al6t9";
    
    public void checkAction(String action) throws BancoInteligenteRESTException{
        if(action == null){
            return;
        }
        if(!action.toUpperCase().equals("TELLER") &&
                !action.isEmpty() &&
                !action.toUpperCase().equals("INFORMATION") &&
                !action.toUpperCase().equals("EXECUTIVE")){
            throw new BancoInteligenteRESTException(Response.Status.BAD_REQUEST);
        }
    }
    
    public void checkNulls(Object... objs) throws BancoInteligenteRESTException {
        for (Object o : objs) {
            if (o == null) {
                Logger.getLogger(AbstractResource.class.getName()).log(Level.SEVERE, "Objeto  no debería ser nulo", new BancoInteligenteRESTException(Response.Status.BAD_REQUEST));
                throw new BancoInteligenteRESTException(Response.Status.BAD_REQUEST);
            }
        }
    }

    public void checkNullsOrEmptyString(Object... objs) throws BancoInteligenteRESTException {
        checkNulls(objs);
        for (Object o : objs) {
            if (o instanceof String && ((String) o).trim().isEmpty()) {
                Logger.getLogger(AbstractResource.class.getName()).log(Level.SEVERE, "String no puede ser vacío", new BancoInteligenteRESTException(Response.Status.BAD_REQUEST));
                throw new BancoInteligenteRESTException(Response.Status.BAD_REQUEST);
            }
        }
    }

    public void auth(String token, UserAccount user) throws BancoInteligenteRESTException{
        if(token != null && ADMIN_SECRET.equals(token)){
            return; //Caso admin
        }
        if(token == null || user == null || !token.equals(user.getToken())){
            throw new BancoInteligenteRESTException(Response.Status.UNAUTHORIZED);
        }
    }
    
    public Response responseWithBodyAndLog(Response.Status status, String log) {
        Logger.getLogger(AbstractResource.class.getName()).log(Level.INFO, log);
        return Response.status(status).type("text/plain").entity(log).build();
    }

}
