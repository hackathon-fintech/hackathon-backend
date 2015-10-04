/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variacode.bancointeligente.controller;

import com.variacode.bancointeligente.entity.DepositSlip;
import com.variacode.bancointeligente.entity.UserAccount;
import java.io.File;
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
    public void testTokenCheck() throws Exception {
        System.out.println("tokenCheck");
        String rut = "";
        String token = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BusinessLogicBeanLocal instance = new BusinessLogicBean(new Storage);
        instance.tokenCheck(rut, token);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class BusinessLogicBean.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String rut = "";
        String pin = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BusinessLogicBeanLocal instance = (BusinessLogicBeanLocal) container.getContext().lookup("java:global/classes/BusinessLogicBean");
        String expResult = "";
        String result = instance.login(rut, pin);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
    public void testDepositSlipPut() throws Exception {
        System.out.println("depositSlipPut");
        DepositSlip depositSlip = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BusinessLogicBeanLocal instance = (BusinessLogicBeanLocal) container.getContext().lookup("java:global/classes/BusinessLogicBean");
        DepositSlip expResult = null;
        DepositSlip result = instance.depositSlipPut(depositSlip);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of depositSlipGet method, of class BusinessLogicBean.
     */
    @Test
    public void testDepositSlipGet() throws Exception {
        System.out.println("depositSlipGet");
        String rut = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BusinessLogicBeanLocal instance = (BusinessLogicBeanLocal) container.getContext().lookup("java:global/classes/BusinessLogicBean");
        List<DepositSlip> expResult = null;
        List<DepositSlip> result = instance.depositSlipGet(rut);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of userAccountGet method, of class BusinessLogicBean.
     */
    @Test
    public void testUserAccountGet() throws Exception {
        System.out.println("userAccountGet");
        String branch = "";
        String action = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BusinessLogicBeanLocal instance = (BusinessLogicBeanLocal) container.getContext().lookup("java:global/classes/BusinessLogicBean");
        List<UserAccount> expResult = null;
        List<UserAccount> result = instance.userAccountGet(branch, action);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
