
package com.variacode.bancointeligente.entity;

import java.io.Serializable;

/**
 *
 * @author miguel@variacode.com
 */
public class DepositSlipDetail implements Serializable {
        private String type;
        private Double amount;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
        
    }