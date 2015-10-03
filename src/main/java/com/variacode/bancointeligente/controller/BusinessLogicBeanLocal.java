package com.variacode.bancointeligente.controller;

import javax.ejb.Local;

/**
 *
 * @author miguel@variacode.com
 */
@Local
public interface BusinessLogicBeanLocal {

    public String login(String rut, String pin);
}
