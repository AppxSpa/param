package com.param.param.config;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private String usuarioUrl;
    private String direccionUrl;

    
    private String personaUrl;


    

    public String getUsuarioUrl() {
        return usuarioUrl;
    }

    public void setUsuarioUrl(String usuarioUrl) {
        this.usuarioUrl = usuarioUrl;
    }

    public String getDireccionUrl() {
        return direccionUrl;
    }

    public void setDireccionUrl(String apiDireccion) {
        this.direccionUrl = apiDireccion;
    }

    public String getPersonaUrl() {
        return personaUrl;
    }

    public void setPersonaUrl(String personaUrl) {
        this.personaUrl = personaUrl;
    }

}
