/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variacode.bancointeligente.controller;

import com.variacode.bancointeligente.core.rest.BancoInteligenteRESTException;
import com.variacode.bancointeligente.entity.DepositSlip;
import com.variacode.bancointeligente.entity.DepositSlipDetail;
import com.variacode.bancointeligente.entity.UserAccount;
import com.variacode.bancointeligente.storage.StorageBean;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miguel
 */
public class BusinessLogicBeanTest {

    private final static String DB_NAME = "test.db";

    public BusinessLogicBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        File dbFile = new File(DB_NAME);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    /**
     * Test of tokenCheck method, of class BusinessLogicBean.
     */
    @Test
    public void testTokenCheck() {
        System.out.println("tokenCheck");
        String rut = "19";
        StorageBean storage = new StorageBean(DB_NAME);
        storage.init();
        BusinessLogicBean instance = new BusinessLogicBean(storage);
        try {
            instance.tokenCheck(rut, instance.login(rut, "134"));
        } catch (BancoInteligenteRESTException e) {
            fail(e.getMessage());
        } finally {
            storage.destroy();
        }
        assertTrue(true);
    }

    /**
     * Test of login method, of class BusinessLogicBean.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String rut = "";
        String pin = "";
        StorageBean storage = new StorageBean(DB_NAME);
        storage.init();
        BusinessLogicBean instance = new BusinessLogicBean(storage);
        try {
            String result = instance.login(rut, pin);
            assertTrue(result != null && !result.isEmpty());
        } finally {
            storage.destroy();
        }
    }

    /**
     * Test of getRandomString method, of class BusinessLogicBean.
     */
    @Test
    public void testGetRandomString() {
        System.out.println("getRandomString");
        String result = BusinessLogicBean.getRandomString();
        assertTrue(result != null && !result.isEmpty());
    }

    /**
     * Test of depositSlipPut method, of class BusinessLogicBean.
     */
    @Test
    public void testDepositSlipPut() {
        System.out.println("depositSlipPut");
        DepositSlip depositSlip = new DepositSlip();
        depositSlip.setDepositId(0L);
        depositSlip.setFromName("asdf");
        depositSlip.setFromPhone("asdf");
        depositSlip.setDetail(new ArrayList<DepositSlipDetail>());
        StorageBean storage = new StorageBean(DB_NAME);
        storage.init();
        BusinessLogicBean instance = new BusinessLogicBean(storage);
        try {
            DepositSlip result = instance.depositSlipPut(depositSlip);
            assertTrue(result.getDepositId() != null && result.getDepositId() > 0L);
            DepositSlip result2 = instance.depositSlipPut(depositSlip);
            assertEquals(result2, result);
        } finally {
            storage.destroy();
        }
    }

    /**
     * Test of depositSlipGet method, of class BusinessLogicBean.
     */
    @Test
    public void testDepositSlipGet() {
        System.out.println("depositSlipGet");
        DepositSlip depositSlip = new DepositSlip();
        depositSlip.setDepositId(0L);
        depositSlip.setUserRut("19");
        depositSlip.setFromName("asdf");
        depositSlip.setFromPhone("asdf");
        StorageBean storage = new StorageBean(DB_NAME);
        storage.init();
        BusinessLogicBean instance = new BusinessLogicBean(storage);
        try {
            DepositSlip result = instance.depositSlipPut(depositSlip);
            assertTrue(result.getDepositId() != null && result.getDepositId() > 0L);
            List<DepositSlip> result2 = instance.depositSlipGet("19");
            assertEquals((result2 != null && !result2.isEmpty()) ? result2.get(0) : false, result);
        } finally {
            storage.destroy();
        }
    }

    /**
     * Test of userAccountGet method, of class BusinessLogicBean.
     */
    @Test
    public void testUserAccountGet() {
        System.out.println("userAccountGet");
        StorageBean storage = new StorageBean(DB_NAME);
        UserAccount ua = new UserAccount();
        ua.setRut("19");
        ua.setAction("TELLER");
        ua.setBranchCode("MONEDA");
        storage.init();
        storage.put(UserAccount.class, ua.getRut(), ua);
        BusinessLogicBean instance = new BusinessLogicBean(storage);
        try {
            List<UserAccount> ual = instance.userAccountGet("MONEDA", null);
            assertTrue(ual != null && !ual.isEmpty());
            assertEquals(ual.get(0), ua);
        } finally {
            storage.destroy();
        }
    }

}
