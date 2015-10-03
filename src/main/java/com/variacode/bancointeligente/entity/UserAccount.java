
package com.variacode.bancointeligente.entity;

public class UserAccount {

    private String rut;
    private String firstName;
    private boolean specialAssistance;
    private boolean premium;
    private String branchCode;
    private String branchStatus;
    private String action;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isSpecialAssistance() {
        return specialAssistance;
    }

    public void setSpecialAssistance(boolean specialAssistance) {
        this.specialAssistance = specialAssistance;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(String branchStatus) {
        this.branchStatus = branchStatus;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
}
