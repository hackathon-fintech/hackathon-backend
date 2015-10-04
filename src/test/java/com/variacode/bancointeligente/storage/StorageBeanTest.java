/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variacode.bancointeligente.storage;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miguel@variacode.com
 */
public class StorageBeanTest {

    private final static String DB_NAME = "test.db";

    public StorageBeanTest() {
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
     * Test of put method, of class StorageBeanLocal.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Class table = String.class;
        String key = "test";
        Object value = "test";
        StorageBean instance = null;
        try {
            instance = new StorageBean(DB_NAME);
            instance.init();
            instance.put(table, key, value);
            instance.put(table, key + "2", value);
            assertTrue(true);
        } finally {
            if (instance != null) {
                instance.destroy();
            }
        }
    }

    /**
     * Test of get method, of class StorageBeanLocal.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Class table = String.class;
        String key = "test";
        Object value = "test";
        StorageBean instance = null;
        try {
            instance = new StorageBean(DB_NAME);
            instance.init();
            instance.put(table, key, value);
            instance.put(table, key + "2", value);
            assertEquals(instance.get(table, key), value);
            assertEquals(instance.get(table, key + "2"), value);
        } finally {
            if (instance != null) {
                instance.destroy();
            }
        }
    }

    /**
     * Test of seq method, of class StorageBeanLocal.
     */
    @Test
    public void testSeq() {
        System.out.println("seq");
        StorageBean instance = null;
        try {
            instance = new StorageBean(DB_NAME);
            instance.init();
            assertTrue(instance.seq("asdf") > 0L);
        } finally {
            if (instance != null) {
                instance.destroy();
            }
        }
    }

    /**
     * Test of getAll method, of class StorageBeanLocal.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        Class table = String.class;
        String key = "test";
        Object value = "test";
        StorageBean instance = null;
        try {
            instance = new StorageBean(DB_NAME);
            instance.init();
            instance.put(table, key, value);
            instance.put(table, key + "2", value);
            for (Object str : instance.getAll(table)) {
                assertEquals(str, value);
            }
        } finally {
            if (instance != null) {
                instance.destroy();
            }
        }
    }

}
