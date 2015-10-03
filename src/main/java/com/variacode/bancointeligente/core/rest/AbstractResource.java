package com.variacode.bancointeligente.core.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author miguel@variacode.com
 */
public class AbstractResource {

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

    public Response responseWithBodyAndLog(Response.Status status, String log) {
        Logger.getLogger(AbstractResource.class.getName()).log(Level.FINE, log);
        return Response.status(status).type("text/plain").entity(log).build();
    }

}
