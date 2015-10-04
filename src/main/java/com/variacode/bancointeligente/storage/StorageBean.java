package com.variacode.bancointeligente.storage;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 *
 * @author miguel@variacode.com
 */
@Singleton
@Startup
public class StorageBean implements StorageBeanLocal {

    private DB db;

    public StorageBean() {
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(StorageBean.class.getName()).log(Level.INFO, "Init map storage");
        db = DBMaker.fileDB(new File("storage.db")).make();
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger(StorageBean.class.getName()).log(Level.INFO, "Closing map storage");
        if (db != null) {
            db.close();
        }
    }

    @Override
    public void put(Class table, String key, Object value) {
        db.hashMap(table.getName()).put(key, value);
        db.commit();
    }

    @Override
    public <T> T get(Class<T> table, String key) {
        return (T) db.hashMap(table.getName()).get(key);
    }
    
    @Override
    public long seq(String key){
        return db.atomicLong(key).incrementAndGet();
    }
    
}
