package com.variacode.bancointeligente.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author miguel@variacode.com
 */
public class DepositSlip implements Serializable {

    private String userRut;
    private Long depositId;
    private String fromName;
    private String fromPhone;
    private String toName;
    private String toAccount;
    private String status;
    private List<DepositSlipDetail> detail;

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DepositSlipDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<DepositSlipDetail> detail) {
        this.detail = detail;
    }

    public String getUserRut() {
        return userRut;
    }

    public void setUserRut(String userRut) {
        this.userRut = userRut;
    }

}
