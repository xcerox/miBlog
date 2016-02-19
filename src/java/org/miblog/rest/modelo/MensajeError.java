package org.miblog.rest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MensajeError {
    private String mensaje, otraInfo;
    private int codigo;

    public MensajeError() {
    }

    public MensajeError(String mensaje, String otraInfo, int codigo) {
        this.mensaje = mensaje;
        this.otraInfo = otraInfo;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getOtraInfo() {
        return otraInfo;
    }

    public void setOtraInfo(String otraInfo) {
        this.otraInfo = otraInfo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
     
}
