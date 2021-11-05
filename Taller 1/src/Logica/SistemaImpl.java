
package Logica;

import Dominio.*;

public class SistemaImpl implements Sistema 
{
    private ListaCuentas listacuentas;
    private ListaPersonajes listapersonajes;
    private ListaSkins listaskins;
    
    public SistemaImpl ()
    {
        listacuentas = new ListaCuentas(1000);
        listapersonajes = new ListaPersonajes(1000);
        listaskins = new ListaSkins(1000);
    }

    @Override
    public boolean ingresarCuenta(String nombreCuenta, String contraseña, String nick, int nivel, int rp, String region) 
    {
        Cuenta c = new Cuenta(nombreCuenta,contraseña,nick,nivel,rp,region);
        boolean ingreso = listacuentas.ingresarCuenta(c);
        
        return ingreso;
    }

    @Override
    public boolean ingresarPersonaje(String nombrePersonaje, String rol) 
    {
        Personaje p = new Personaje(nombrePersonaje,rol);
        boolean ingreso = listapersonajes.ingresarPersonaje(p);
        
        return ingreso;
    }

    @Override
    public boolean ingresarSkin(String nombreSkin, String calidad) 
    {
        Skin s = new Skin(nombreSkin,calidad);
        boolean ingreso = listaskins.ingresarSkin(s);
        
        return ingreso;
    }

    @Override
    public void asociarEstadistica(String nombrePersonaje, int monto) 
    {
        Personaje p = listapersonajes.buscarNombre(nombrePersonaje);
        
        if (p!= null) 
        {
            p.setRecaudacion(monto);
        }
        else 
        {
            throw new NullPointerException("No se encontro el personaje");
        }
    }

    @Override
    public void asociarPersonajeCuenta(String nombreCuenta, String nombrePersonaje) 
    {
        Cuenta c = listacuentas.buscarNombre(nombreCuenta);
        Personaje p = listapersonajes.buscarNombre(nombrePersonaje);
        
        if (c!= null && p!= null) 
        {
            Personaje p1 = new Personaje(p.getNombre(),p.getRol());
            c.getLp().ingresarPersonaje(p1);
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta y/o el personaje indicado");
        }
    }

    @Override
    public void asociarSkinPersonaje(String nombrePersonaje, String nombreSkin) 
    {
        Personaje p = listapersonajes.buscarNombre(nombrePersonaje);
        Skin s = listaskins.buscarNombre(nombreSkin);
        
        if (p!= null && s!= null) 
        {
            p.getLs().ingresarSkin(s);
        }
        else 
        {
            throw new NullPointerException("No se encontro el personaje y/o la skin");
        }
    }

    @Override
    public void asociarSkinPersonajeDeCuenta(String nombreCuenta, String nombrePersonaje, String nombreSkin) 
    {
        Cuenta c = listacuentas.buscarNombre(nombreCuenta);
        Skin s = listaskins.buscarNombre(nombreSkin);
        
        if (c!= null && s!= null) 
        {
            Personaje p = c.getLp().buscarNombre(nombrePersonaje);
            
            if (p!= null) 
            {
                p.getLs().ingresarSkin(s);
            }
            else 
            {
                throw new NullPointerException("No se encontro el personaje indicado");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta y/o el personaje");
        }
        
    }

    @Override
    public boolean verificarInicio(String nombreCuenta, String contraseña) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerSkinDePersonaje(String nombrePersonaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerPersonajes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean comprarPersonaje(String nombreCuenta, String nombrePersonaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerSkinsDisponibles(String nombreCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerInventario(String nombreCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean recargarRP(String nombreCuenta, int monto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerDatosCuenta(String nombreCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean confirmarContraseña(String nombreCuenta, String contraseña) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cambiarContrasela(String nombreCuenta, String contraseña) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerRecaudacionRol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerRecaudacionRegion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerRecaudacionPersonaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void bloquearJugador(String nombreCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerCuentasOrdenadas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerCuentas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerSkins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
