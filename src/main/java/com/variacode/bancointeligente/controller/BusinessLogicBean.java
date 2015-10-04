package com.variacode.bancointeligente.controller;

import com.google.gson.Gson;
import com.variacode.bancointeligente.apiclient.ClientLogin;
import com.variacode.bancointeligente.apiclient.Login;
import com.variacode.bancointeligente.core.rest.BancoInteligenteRESTException;
import com.variacode.bancointeligente.entity.DepositSlip;
import com.variacode.bancointeligente.entity.UserAccount;
import com.variacode.bancointeligente.storage.StorageBeanLocal;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;
import static us.monoid.web.Resty.form;

/**
 *
 * @author miguel@variacode.com
 */
@Stateless
public class BusinessLogicBean implements BusinessLogicBeanLocal {

    public BusinessLogicBean() {
    }

    public BusinessLogicBean(StorageBeanLocal storage) {
        this.storage = storage;
    }

    @EJB
    private StorageBeanLocal storage;

    @Override
    public void tokenCheck(String rut, String token) throws BancoInteligenteRESTException {
        UserAccount t = storage.get(UserAccount.class, rut);
        if (t == null || t.getToken() == null || !t.getToken().equals(token)) {
            throw new BancoInteligenteRESTException(Response.Status.UNAUTHORIZED);
        }
    }

    @Override
    public UserAccount login(String rut, String pin) throws BancoInteligenteRESTException {
        Resty r = new Resty();
        r.withHeader("Content-Type", "application/json");
        Gson gson = new Gson();
        Login login = new Login();
        login.setApiID("04_ABI");
        login.setPassword(pin);
        login.setRut(rut);
        login.setTipoLogin("MOBILE");
        String loginBody = gson.toJson(login);
        UserAccount user = storage.get(UserAccount.class, rut);
        try {
            JSONObject jo = r.json("http://fintechbch:9001/BChHackatonAPI/webrest/seguridad/login", form(loginBody)).toObject();
            ClientLogin l = gson.fromJson(jo.toString(), ClientLogin.class);
            if (l.getBody() == null) {
                throw new BancoInteligenteRESTException(Response.Status.UNAUTHORIZED);
            }
            if (user == null || user.getToken() == null) {
                user = new UserAccount();
                user.setRut(rut);
                user.setFirstName(l.getBody().getNombre());
                user.setAccount("1234567890");
                user.setPhotoURL("https://pbs.twimg.com/profile_images/3653841604/6a93d8d4a5b1c17fbeb1dcbaf0c9061e.jpeg");
                user.setToken(getRandomString());
                storage.put(UserAccount.class, rut, user);
                
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(BusinessLogicBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoInteligenteRESTException(Response.Status.UNAUTHORIZED);
        }
        return storage.get(UserAccount.class, rut);
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
