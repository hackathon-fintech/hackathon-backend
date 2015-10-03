/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variacode.bancointeligente.controller;

import com.variacode.bancointeligente.entity.UserAccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author miguel
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<UserAccount> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "com.variacode.bancointeligente.core_banco-inteligente_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(UserAccount.class);
    }
    
}
