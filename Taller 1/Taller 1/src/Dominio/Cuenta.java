
package Dominio;
import Logica.*;


public class Cuenta 
{
    private String nombre;
    private String contraseña;
    private String nick;
    private int nivel;
    private int rp;
    private ListaPersonajes lp;
    private String region;

    public Cuenta(String nombre, String contraseña, String nick, int nivel, int rp, String region) 
    {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.nick = nick;
        this.nivel = nivel;
        this.rp = rp;
        this.region = region;
        lp = new ListaPersonajes(155);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ListaPersonajes getLp() {
        return lp;
    }

    public void setLp(ListaPersonajes lp) {
        this.lp = lp;
    }
    
    
    
    
}
