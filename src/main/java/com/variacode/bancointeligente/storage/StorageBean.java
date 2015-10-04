package com.variacode.bancointeligente.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.mapdb.Atomic;
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
    private static final String TABLE_PREFIX = "TABLE-";
    private static final String INCREMENTAL_PREFIX = "INCREMENTAL-";
    private String dbFileName = "storage.db";

    public StorageBean() {
    }

    public StorageBean(String dbFileName) {
        this.dbFileName = dbFileName;
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(StorageBean.class.getName()).log(Level.INFO, "Init map storage");
        db = DBMaker.fileDB(new File(dbFileName))
                .closeOnJvmShutdown()
                .checksumEnable()
                .cacheLRUEnable()
                .cacheSoftRefEnable()
                .make();
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
        db.hashMap(TABLE_PREFIX + table.getName()).put(key, value);
        db.commit();
    }

    @Override
    public <T> T get(Class<T> table, String key) {
        return (T) db.hashMap(TABLE_PREFIX + table.getName()).get(key);
    }

    @Override
    public long seq(String key) {
        Atomic.Long a = db.atomicLong(INCREMENTAL_PREFIX + key);
        return a.incrementAndGet();
    }

    @Override
    public <T> List<T> getAll(Class<T> table) {
        return new ArrayList<>((Collection<T>) db.hashMap(TABLE_PREFIX + table.getName()).values());
    }

}
