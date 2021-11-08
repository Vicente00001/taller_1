
package Logica;


public interface Sistema 
{
    public boolean ingresarCuenta (String nombreCuenta, String contraseña,String nick,int nivel,int rp,String region);
    public boolean ingresarPersonaje (String nombrePersonaje, String rol);
    public boolean ingresarSkin (String nombreSkin, String calidad);
    public void asociarEstadistica (String nombrePersonaje, int monto);
    public void asociarPersonajeCuenta (String nombreCuenta, String nombrePersonaje);
    public void asociarSkinPersonaje (String nombrePersonaje, String nombreSkin);
    public void asociarSkinPersonajeDeCuenta (String nombreCuenta, String nombrePersonaje, String nombreSkin);
    public boolean verificarInicio (String nombreCuenta, String contraseña);
    public String obtenerSkinDePersonaje (String nombrePersonaje);
    public boolean comprarSkin (String nombreCuenta, String nombrePersonaje, String nombreSkin);
    public String obtenerPersonajes ();
    public boolean comprarPersonaje (String nombreCuenta, String nombrePersonaje);
    public String obtenerSkinsDisponibles (String nombreCuenta);
    public String obtenerInventario (String nombreCuenta);
    public void recargarRP (String nombreCuenta, int monto);
    public String obtenerDatosCuenta (String nombreCuenta);
    public boolean confirmarContraseña (String nombreCuenta, String contraseña);
    public void cambiarContraseña (String nombreCuenta, String contraseña);
    public String obtenerRecaudacionRol ();
    public String obtenerRecaudacionRegion ();
    public String obtenerRecaudacionPersonaje ();
    public void bloquearJugador (String nombreCuenta);
    public String obtenerCuentasOrdenadas ();
    public String obtenerCuentas ();
    public String obtenerPersonajesYSkin ();
    public String obtenerEstadisticas ();
    public String obtenerPersonajesRol ();
 
    
    
}
