
package com.variacode.bancointeligente.storage;

import javax.ejb.Local;

/**
 *
 * @author miguel@variacode.com
 */
@Local
public interface StorageBeanLocal {
    
    public void put(Class table, String key, Object value);
    
    public <T> T get(Class<T> table, String key);
    
    public long seq(String key);
}
