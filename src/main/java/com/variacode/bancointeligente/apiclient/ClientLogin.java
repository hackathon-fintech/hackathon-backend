/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.variacode.bancointeligente.apiclient;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ClientLogin {

    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("header")
    @Expose
    private Header header;

    /**
     *
     * @return The body
     */
    public Body getBody() {
        return body;
    }

    /**
     *
     * @param body The body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     *
     * @return The header
     */
    public Header getHeader() {
        return header;
    }

    /**
     *
     * @param header The header
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    @Generated("org.jsonschema2pojo")
    public static class Body {

        @SerializedName("apellidoPaterno")
        @Expose
        private String apellidoPaterno;
        @SerializedName("contacto")
        @Expose
        private Contacto contacto;
        @SerializedName("domicilio")
        @Expose
        private Domicilio domicilio;
        @SerializedName("estadoCivil")
        @Expose
        private String estadoCivil;
        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("rut")
        @Expose
        private String rut;

        /**
         *
         * @return The apellidoPaterno
         */
        public String getApellidoPaterno() {
            return apellidoPaterno;
        }

        /**
         *
         * @param apellidoPaterno The apellidoPaterno
         */
        public void setApellidoPaterno(String apellidoPaterno) {
            this.apellidoPaterno = apellidoPaterno;
        }

        /**
         *
         * @return The contacto
         */
        public Contacto getContacto() {
            return contacto;
        }

        /**
         *
         * @param contacto The contacto
         */
        public void setContacto(Contacto contacto) {
            this.contacto = contacto;
        }

        /**
         *
         * @return The domicilio
         */
        public Domicilio getDomicilio() {
            return domicilio;
        }

        /**
         *
         * @param domicilio The domicilio
         */
        public void setDomicilio(Domicilio domicilio) {
            this.domicilio = domicilio;
        }

        /**
         *
         * @return The estadoCivil
         */
        public String getEstadoCivil() {
            return estadoCivil;
        }

        /**
         *
         * @param estadoCivil The estadoCivil
         */
        public void setEstadoCivil(String estadoCivil) {
            this.estadoCivil = estadoCivil;
        }

        /**
         *
         * @return The nombre
         */
        public String getNombre() {
            return nombre;
        }

        /**
         *
         * @param nombre The nombre
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        /**
         *
         * @return The rut
         */
        public String getRut() {
            return rut;
        }

        /**
         *
         * @param rut The rut
         */
        public void setRut(String rut) {
            this.rut = rut;
        }

    }

    @Generated("org.jsonschema2pojo")
    public static class Contacto {

        @SerializedName("celular")
        @Expose
        private String celular;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("telefono")
        @Expose
        private String telefono;

        /**
         *
         * @return The celular
         */
        public String getCelular() {
            return celular;
        }

        /**
         *
         * @param celular The celular
         */
        public void setCelular(String celular) {
            this.celular = celular;
        }

        /**
         *
         * @return The email
         */
        public String getEmail() {
            return email;
        }

        /**
         *
         * @param email The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         *
         * @return The telefono
         */
        public String getTelefono() {
            return telefono;
        }

        /**
         *
         * @param telefono The telefono
         */
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

    }

    @Generated("org.jsonschema2pojo")
    public static class Domicilio {

        @SerializedName("comuna")
        @Expose
        private String comuna;
        @SerializedName("direccion")
        @Expose
        private String direccion;
        @SerializedName("provincia")
        @Expose
        private String provincia;
        @SerializedName("region")
        @Expose
        private String region;

        /**
         *
         * @return The comuna
         */
        public String getComuna() {
            return comuna;
        }

        /**
         *
         * @param comuna The comuna
         */
        public void setComuna(String comuna) {
            this.comuna = comuna;
        }

        /**
         *
         * @return The direccion
         */
        public String getDireccion() {
            return direccion;
        }

        /**
         *
         * @param direccion The direccion
         */
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        /**
         *
         * @return The provincia
         */
        public String getProvincia() {
            return provincia;
        }

        /**
         *
         * @param provincia The provincia
         */
        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

        /**
         *
         * @return The region
         */
        public String getRegion() {
            return region;
        }

        /**
         *
         * @param region The region
         */
        public void setRegion(String region) {
            this.region = region;
        }

    }

    @Generated("org.jsonschema2pojo")
    public static class Header {

        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("message")
        @Expose
        private String message;

        /**
         *
         * @return The code
         */
        public Integer getCode() {
            return code;
        }

        /**
         *
         * @param code The code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         *
         * @return The message
         */
        public String getMessage() {
            return message;
        }

        /**
         *
         * @param message The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

    }
}
