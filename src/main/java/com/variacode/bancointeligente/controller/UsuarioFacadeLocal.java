/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variacode.bancointeligente.controller;

import com.variacode.bancointeligente.entity.UserAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miguel
 */
@Local
public interface UsuarioFacadeLocal {

    void create(UserAccount usuario);

    void edit(UserAccount usuario);

    void remove(UserAccount usuario);

    UserAccount find(Object id);

    List<UserAccount> findAll();

    List<UserAccount> findRange(int[] range);

    int count();
    
}
