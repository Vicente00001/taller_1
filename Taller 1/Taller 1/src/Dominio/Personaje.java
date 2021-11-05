
package Dominio;
import Logica.*;

public class Personaje 
{
    private String nombre;
    private String rol;
    private int recaudacion;
    private ListaSkins ls;
    
    public Personaje(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
        ls = new ListaSkins(100);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(int recaudacion) {
        this.recaudacion = recaudacion;
    }

    public ListaSkins getLs() {
        return ls;
    }

    public void setLs(ListaSkins ls) {
        this.ls = ls;
    }
    
    
    
    
    
}

