
package com.variacode.bancointeligente.apiclient;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Login {

@SerializedName("apiID")
@Expose
private String apiID;
@SerializedName("rut")
@Expose
private String rut;
@SerializedName("tipoLogin")
@Expose
private String tipoLogin;
@SerializedName("password")
@Expose
private String password;

/**
* 
* @return
* The apiID
*/
public String getApiID() {
return apiID;
}

/**
* 
* @param apiID
* The apiID
*/
public void setApiID(String apiID) {
this.apiID = apiID;
}

/**
* 
* @return
* The rut
*/
public String getRut() {
return rut;
}

/**
* 
* @param rut
* The rut
*/
public void setRut(String rut) {
this.rut = rut;
}

/**
* 
* @return
* The tipoLogin
*/
public String getTipoLogin() {
return tipoLogin;
}

/**
* 
* @param tipoLogin
* The tipoLogin
*/
public void setTipoLogin(String tipoLogin) {
this.tipoLogin = tipoLogin;
}

/**
* 
* @return
* The password
*/
public String getPassword() {
return password;
}

/**
* 
* @param password
* The password
*/
public void setPassword(String password) {
this.password = password;
}

}