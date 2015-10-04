package com.variacode.bancointeligente.controller;

import com.variacode.bancointeligente.core.rest.BancoInteligenteRESTException;
import com.variacode.bancointeligente.entity.DepositSlip;
import com.variacode.bancointeligente.entity.UserAccount;
import com.variacode.bancointeligente.storage.StorageBeanLocal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

/**
 *
 * @author miguel@variacode.com
 */
@Stateless
public class BusinessLogicBean implements BusinessLogicBeanLocal {

    public BusinessLogicBean() {
    }

    @EJB
    private StorageBeanLocal storage;

    @Override
    public void tokenCheck(String rut, String token) throws BancoInteligenteRESTException {
        String t = storage.get(String.class, rut);
        if (t == null || !t.equals(token)) {
            throw new BancoInteligenteRESTException(Response.Status.UNAUTHORIZED);
        }
    }

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

    @Override
    public DepositSlip depositSlipPut(DepositSlip depositSlip) {
        if (depositSlip.getDepositId() == null || depositSlip.getDepositId() == 0L) {
            depositSlip.setDepositId(storage.seq(DepositSlip.class.getName()));
        }
        storage.put(DepositSlip.class, depositSlip.getDepositId().toString(), depositSlip);
        return depositSlip;
    }

    @Override
    public List<DepositSlip> depositSlipGet(String rut) {
        //TODO: mejorar :)
        List<DepositSlip> ds = new ArrayList<>();
        Collection<DepositSlip> c = storage.getAll(DepositSlip.class);
        for (DepositSlip d : c) {
            if (d.getUserRut().equals(rut)) {
                ds.add(d);
            }
        }
        return ds;
    }

    @Override
    public List<UserAccount> userAccountGet(String branch, String action) {
        //TODO: mejorar :)
        List<UserAccount> ua = new ArrayList<>();
        Collection<UserAccount> c = storage.getAll(UserAccount.class);
        for (UserAccount a : c) {
            if ((a.getBranchCode() != null && a.getBranchCode().equals(branch)) && (action == null || action.equals(a.getAction()))) {
                ua.add(a);
            }
        }
        return ua;
    }

}
