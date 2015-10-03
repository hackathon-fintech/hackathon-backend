
package com.variacode.bancointeligente.entity;

public class UserAccount {

    private String rut;
    private String firstName;
    private boolean specialAssistance;
    private boolean premium;

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
    
}
