package com.variacode.bancointeligente.core.rest;

import javax.ws.rs.core.Response;

/**
 *
 * @author miguel@variacode.com
 */
public class BancoInteligenteRESTException extends Exception {

    private final Response.Status status;

    public BancoInteligenteRESTException(Response.Status status) {
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }
}
