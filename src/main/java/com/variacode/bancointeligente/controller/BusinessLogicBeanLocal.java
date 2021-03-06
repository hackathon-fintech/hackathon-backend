package com.variacode.bancointeligente.controller;

import com.variacode.bancointeligente.core.rest.BancoInteligenteRESTException;
import com.variacode.bancointeligente.entity.DepositSlip;
import com.variacode.bancointeligente.entity.UserAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miguel@variacode.com
 */
@Local
public interface BusinessLogicBeanLocal {

    public UserAccount login(String rut, String pin) throws BancoInteligenteRESTException ;
    
    public void tokenCheck(String rut, String token) throws BancoInteligenteRESTException;
    
    public DepositSlip depositSlipPut(DepositSlip depositSlip);
    
    public List<DepositSlip> depositSlipGet(String rut);
    
    public List<UserAccount> userAccountGet(String branch, String action);
}
