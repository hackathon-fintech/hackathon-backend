package com.variacode.bancointeligente.controller;

import com.variacode.bancointeligente.storage.StorageBeanLocal;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author miguel@variacode.com
 */
@Stateless
public class BusinessLogicBean implements BusinessLogicBeanLocal {

    public BusinessLogicBean(){
    }
    
    @EJB
    private StorageBeanLocal storage;

    @Override
    public String login(String rut, String pin) {
        //TODO: login
        String token = storage.get(String.class, rut);
        if (token == null) {
            storage.put(String.class, rut, getRandomString());
        }
        return storage.get(String.class, rut);
    }

    private static final SecureRandom random = new SecureRandom();

    public static String getRandomString() {
        return new BigInteger(130, random).toString(32);
    }
}
