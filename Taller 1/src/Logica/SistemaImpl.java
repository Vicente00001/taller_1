
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
        
        if (c!= null) 
        {
            if (s!= null) 
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
                throw new NullPointerException("No se encontro la skin");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");
        }
        
    }

    @Override
    public boolean verificarInicio(String nombreCuenta, String contraseña) 
    {
        Cuenta c = listacuentas.buscarNombre(nombreCuenta);
        
        if(c!= null) 
        {
            if (c.getContraseña().equals(contraseña)) 
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        else 
        {
            return false;
        }
    }

    @Override
    public String obtenerSkinDePersonaje(String nombrePersonaje) 
    {
        String salida = "";
        Personaje p = listapersonajes.buscarNombre(nombrePersonaje);
        
        if(p!= null) 
        {
            salida += "\n"+ p.getNombre() + ": ";
            for(int i=0;i<p.getLs().getCant();i++) 
            {
                salida += "\n   -"+ p.getLs().getLista()[i].getNombre() ;
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el personaje");
        }
        
        
        return salida;
    }

    @Override
    public boolean comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin)
    {
        
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        
        if(cuenta!=null) 
        {
            Skin skin = listaskins.buscarNombre(nombreSkin);
            if(skin!=null)
            {
                if (skin.getCalidad().equals("N"))
                {
                    if(cuenta.getRp() >= 975) 
                    {
                        cuenta.getLp().buscarNombre(nombrePersonaje).getLs().ingresarSkin(skin);
                        cuenta.setRp(cuenta.getRp()-975);
                        listapersonajes.buscarNombre(nombrePersonaje).setRecaudacion(listapersonajes.buscarNombre(nombrePersonaje).getRecaudacion() + 975);
                        
                        return true;
                    }
                    else 
                    {
                        return false;
                    }
                }
                else if(skin.getCalidad().equals("E"))
                {
                    if(cuenta.getRp() >= 1350) 
                    {
                        cuenta.getLp().buscarNombre(nombrePersonaje).getLs().ingresarSkin(skin);
                        cuenta.setRp(cuenta.getRp()-1350);
                        listapersonajes.buscarNombre(nombrePersonaje).setRecaudacion(listapersonajes.buscarNombre(nombrePersonaje).getRecaudacion() + 1350);
                        
                        return true;
                    }
                    else 
                    {
                        return false;
                    }
                }
                else if(skin.getCalidad().equals("L"))
                {
                    if(cuenta.getRp() >= 1820) 
                    {
                        cuenta.getLp().buscarNombre(nombrePersonaje).getLs().ingresarSkin(skin);
                        cuenta.setRp(cuenta.getRp()-1820);
                        listapersonajes.buscarNombre(nombrePersonaje).setRecaudacion(listapersonajes.buscarNombre(nombrePersonaje).getRecaudacion() + 1820);
                        
                        return true;                        
                    }
                    else 
                    {
                        return false;
                    }
                }
                else if(skin.getCalidad().equals("D")) 
                {
                    if(cuenta.getRp() >= 2750) 
                    {
                        cuenta.getLp().buscarNombre(nombrePersonaje).getLs().ingresarSkin(skin);
                        cuenta.setRp(cuenta.getRp()-2750);
                        listapersonajes.buscarNombre(nombrePersonaje).setRecaudacion(listapersonajes.buscarNombre(nombrePersonaje).getRecaudacion() + 2750);
                        
                        return true;                       
                    }
                    else 
                    {
                        return false;
                    }
                }
                else 
                {
                    if(cuenta.getRp() >= 3250)
                    {
                        cuenta.getLp().buscarNombre(nombrePersonaje).getLs().ingresarSkin(skin);
                        cuenta.setRp(cuenta.getRp()-3250);
                        listapersonajes.buscarNombre(nombrePersonaje).setRecaudacion(listapersonajes.buscarNombre(nombrePersonaje).getRecaudacion() + 3250);
                        
                        return true;                        
                    }
                    else 
                    {
                        return false;
                    }
                }
            }
            else 
            {
                throw new NullPointerException("No se encontro la skin");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");
        }

    }

    @Override
    public String obtenerPersonajes() 
    {
        String salida = "";
        
        for(int i=0;i<listapersonajes.getCant();i++) 
        {
            salida += (i+1) + ")"+listapersonajes.getLista()[i].getNombre()+ "\n";
        }
        
        return salida;
    }

    @Override
    public boolean comprarPersonaje(String nombreCuenta, String nombrePersonaje) 
    {
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        if(cuenta!= null) 
        {
            Personaje p = listapersonajes.buscarNombre(nombrePersonaje);
            if(p!=null) 
            {
                if(cuenta.getRp() >= 975)
                {
                    Personaje p1= new Personaje(p.getNombre(),p.getRol());
                    cuenta.getLp().ingresarPersonaje(p1);
                    cuenta.setRp(cuenta.getRp()-975);
                    cuenta.setNivel(cuenta.getNivel()+1);
                    listapersonajes.buscarNombre(nombrePersonaje).setRecaudacion(listapersonajes.buscarNombre(nombrePersonaje).getRecaudacion() + 975);
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else 
            {
                throw new NullPointerException("No se encontro el personaje");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");
        }
    }

    @Override
    public String obtenerSkinsDisponibles(String nombreCuenta) 
    {
        String salida = "";
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        
        if(cuenta!= null) 
        {
            String[] lista = new String[listaskins.getCant()];
            int c=0;
            for(int a=0;a<cuenta.getLp().getCant();a++) 
            {
                Personaje p= cuenta.getLp().getLista()[a];
                for(int b=0;b<p.getLs().getCant();b++) 
                {
                    lista[c] = p.getLs().getLista()[b].getNombre();
                    c++;
                }
            }



            for(int i=0;i<listaskins.getCant();i++) 
            {
                String general = listaskins.getLista()[i].getNombre();
                for(int f=0;f<lista.length;) 
                {
                    if(general.equals(lista[f])) 
                    {
                        f++;
                    }
                    else 
                    {
                        salida += "\n   -" + i + general;
                        break;
                    }

                }    
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");
        }
        
        
        return salida;
    }

    @Override
    public String obtenerInventario(String nombreCuenta) 
    {
        String salida = "";
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        
        if(cuenta!= null) 
        {
            for(int a=0;a<cuenta.getLp().getCant();a++) 
            {
                Personaje p= cuenta.getLp().getLista()[a];
                salida += "\n"+ p.getNombre() + ": ";
                for(int b=0;b<p.getLs().getCant();b++) 
                {
                    salida += "\n   -" + p.getLs().getLista()[b].getNombre();
                }
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");
        }
        return salida;
    }

    @Override
    public void recargarRP(String nombreCuenta, int monto) 
    {
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        
        if(cuenta!= null) 
        {
            cuenta.setRp(cuenta.getRp()+ monto);
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");

        }
    }

    @Override
    public String obtenerDatosCuenta(String nombreCuenta) 
    {
        String salida = "";
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        
        if(cuenta!=null) 
        {
            salida += "\n- Nombre: "+ cuenta.getNombre() + "\n- Nick: " + cuenta.getNick();
            String[] partes = cuenta.getContraseña().split("");
            String contraseña = "";
            for(int i=0;i<partes.length;i++) 
            {
                if(i<partes.length-3) 
                {
                    partes[i] = "*";
                    contraseña += partes[i];
                }
                else 
                {
                    contraseña += partes[i];
                }
            }
            salida += "\n- Contraseña: " + contraseña; 
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");
        }
        
        return salida;
    }

    @Override
    public boolean confirmarContraseña(String nombreCuenta, String contraseña)
    {
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        
        if(cuenta!= null) 
        {
            return cuenta.getContraseña().equals(contraseña);
        }
        else 
        {
            return false;
        }

    }

    @Override
    public void cambiarContraseña(String nombreCuenta, String contraseña) 
    {
        Cuenta cuenta = listacuentas.buscarNombre(nombreCuenta);
        
        if(cuenta!= null) 
        {
            cuenta.setContraseña(contraseña);
        }
        else 
        {
            throw new NullPointerException("No se encontro la cuenta");
        }
    }

    @Override
    public String obtenerRecaudacionRol() 
    {
        String salida = "";
        double[] montos = new double[5];
        
        for(int i=0;i<listapersonajes.getCant();i++) 
        {
            if(listapersonajes.getLista()[i].getRol().equals("TOP")) 
            {
                montos[0] += listapersonajes.getLista()[i].getRecaudacion() * 6.15;
            }
            else if(listapersonajes.getLista()[i].getRol().equals("JG")) 
            {
                montos[1] += listapersonajes.getLista()[i].getRecaudacion() * 6.15;
            }
            else if(listapersonajes.getLista()[i].getRol().equals("MID")) 
            {
                montos[2] += listapersonajes.getLista()[i].getRecaudacion() * 6.15;
            }
            else if(listapersonajes.getLista()[i].getRol().equals("ADC")) 
            {
                montos[3] += listapersonajes.getLista()[i].getRecaudacion() * 6.15;
            }
            else 
            {
                montos[4] += listapersonajes.getLista()[i].getRecaudacion() * 6.15;
            }
        }
        
        salida += "\n -TOP: $" + montos[0] + "\n -JG: $" + montos[1] + "\n -MID: $" + montos[2] + "\n -ADC: $" + montos[3] + "\n -SUPP: $" + montos[4]; 
        
        
        return salida;
    }

    @Override
    public String obtenerRecaudacionRegion() 
    {
        String salida = "";
        double[] montos = new double[6];
        
        for (int i=0;i<listacuentas.getCant();i++) 
        {
            Cuenta c = listacuentas.getLista()[i];
            if(c.getRegion().equals("LAS")) 
            {
                for (int f=0;f<c.getLp().getCant();f++) 
                {
                    for(int a=0;a<listapersonajes.getCant();a++) 
                    {
                        if(c.getLp().getLista()[f].getNombre().equals(listapersonajes.getLista()[a].getNombre())) 
                        {
                            montos[0] += listapersonajes.getLista()[a].getRecaudacion() * 6.15;
                            break;
                        }
                    }
                }
            }
            else if(c.getRegion().equals("LAN")) 
            {
                for (int f=0;f<c.getLp().getCant();f++) 
                {
                    for(int a=0;a<listapersonajes.getCant();a++) 
                    {
                        if(c.getLp().getLista()[f].getNombre().equals(listapersonajes.getLista()[a].getNombre())) 
                        {
                            montos[1] += listapersonajes.getLista()[a].getRecaudacion() * 6.15;
                            break;
                        }
                    }
                }
            }
            else if(c.getRegion().equals("EUW")) 
            {
                for (int f=0;f<c.getLp().getCant();f++) 
                {
                    for(int a=0;a<listapersonajes.getCant();a++) 
                    {
                        if(c.getLp().getLista()[f].getNombre().equals(listapersonajes.getLista()[a].getNombre())) 
                        {
                            montos[2] += listapersonajes.getLista()[a].getRecaudacion() * 6.15;
                            break;
                        }
                    }
                }
            }
            else if(c.getRegion().equals("KR")) 
            {
                for (int f=0;f<c.getLp().getCant();f++) 
                {
                    for(int a=0;a<listapersonajes.getCant();a++) 
                    {
                        if(c.getLp().getLista()[f].getNombre().equals(listapersonajes.getLista()[a].getNombre())) 
                        {
                            montos[3] += listapersonajes.getLista()[a].getRecaudacion() * 6.15;
                            break;
                        }
                    }
                }
            }
            else if(c.getRegion().equals("NA")) 
            {
                for (int f=0;f<c.getLp().getCant();f++) 
                {
                    for(int a=0;a<listapersonajes.getCant();a++) 
                    {
                        if(c.getLp().getLista()[f].getNombre().equals(listapersonajes.getLista()[a].getNombre())) 
                        {
                            montos[4] += listapersonajes.getLista()[a].getRecaudacion() * 6.15;
                            break;
                        }
                    }
                }
            }
            else 
            {
                for (int f=0;f<c.getLp().getCant();f++) 
                {
                    for(int a=0;a<listapersonajes.getCant();a++) 
                    {
                        if(c.getLp().getLista()[f].getNombre().equals(listapersonajes.getLista()[a].getNombre())) 
                        {
                            montos[5] += listapersonajes.getLista()[a].getRecaudacion() * 6.15;
                            break;
                        }
                    }
                }
            }
        }
        salida += "\n -LAS: $" + montos[0] + "\n -LAN: $" + montos[1] + "\n -EUW: $" + montos[2] + "\n -KR: $" + montos[3] + "\n -NA: $" + montos[4] + "\n -RU: $" + montos[5]; 
        
        return salida;
    }

    @Override
    public String obtenerRecaudacionPersonaje() 
    {
        String salida = "";
        
        for(int i=0;i<listapersonajes.getCant();i++) 
        {
            double monto =0;
            monto = listapersonajes.getLista()[i].getRecaudacion() * 6.15;
            salida += "\n   -" + listapersonajes.getLista()[i].getNombre() + ": $" + monto;
        }
        
        return salida;
    }

    @Override
    public void bloquearJugador(String nombreCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerCuentasOrdenadas() 
    {
        String salida = "";
        for(int a=0;a<listacuentas.getCant()-1;a++) 
        {
            for(int b=a;b<listacuentas.getCant();b++) 
            {
                if(listacuentas.getLista()[a].getNivel() < listacuentas.getLista()[b].getNivel()) 
                {
                    Cuenta aux = listacuentas.getLista()[a];
                    listacuentas.getLista()[a] = listacuentas.getLista()[b];
                    listacuentas.getLista()[b] = aux;
                    
                }
            }
        }
        for (int c=0;c<listacuentas.getCant();c++) 
        {
            salida += "\n   - " + listacuentas.getLista()[c].getNick() + ", nivel " + listacuentas.getLista()[c].getNivel();
        }
        return salida;
    }

    @Override
    public String obtenerCuentas() 
    {
        String salida = "";
        
        for(int a=0;a<listacuentas.getCant();a++) 
        {
            salida += listacuentas.getLista()[a].getNombre() + "," + listacuentas.getLista()[a].getContraseña() + ","+ listacuentas.getLista()[a].getNick() + ","+ listacuentas.getLista()[a].getNivel() + "," 
                    + listacuentas.getLista()[a].getRp() + "," + listacuentas.getLista()[a].getLp().getCant();
            for(int b=0;b<listacuentas.getLista()[a].getLp().getCant();b++) 
            {
                salida += "," + listacuentas.getLista()[a].getLp().getLista()[b].getNombre() + "," + listacuentas.getLista()[a].getLp().getLista()[b].getLs().getCant();
                for(int c=0;c<listacuentas.getLista()[a].getLp().getLista()[b].getLs().getCant();c++) 
                {
                    salida += "," + listacuentas.getLista()[a].getLp().getLista()[b].getLs().getLista()[c].getNombre();
                }
            }
            salida += ","+ listacuentas.getLista()[a].getRegion() + "\n";
        }
        
        return salida;
    }

    @Override
    public String obtenerPersonajesYSkin() 
    {
        String salida = "";
        
        for(int i=0;i<listapersonajes.getCant();i++) 
        {
            salida += listapersonajes.getLista()[i].getNombre() + ","+ listapersonajes.getLista()[i].getRol() +"," + listapersonajes.getLista()[i].getLs().getCant();
            for(int f=0;f<listapersonajes.getLista()[i].getLs().getCant();f++) 
            {
                salida += ","+listapersonajes.getLista()[i].getLs().getLista()[f].getNombre() + "," + listapersonajes.getLista()[i].getLs().getLista()[f].getCalidad();
            }
            salida += "\n";
        }
        
        return salida;
    }

    @Override
    public String obtenerPersonajesRol() 
    {
        String salida = "";
        String[] salidas = new String[5]; salidas[0] = "";salidas[1] = "";salidas[2] = "";salidas[3] = "";salidas[4] = "";
        int[] cant = new int[5];
        for(int i=0;i<listapersonajes.getCant();i++) 
        {
            if(listapersonajes.getLista()[i].getRol().equals("TOP")) 
            {
                salidas[0] += "\n   -" + listapersonajes.getLista()[i].getNombre();
                cant[0]++;
            }
            else if(listapersonajes.getLista()[i].getRol().equals("JG")) 
            {
                salidas[1] += "\n   -" + listapersonajes.getLista()[i].getNombre();
                cant[1]++;
            }
            else if(listapersonajes.getLista()[i].getRol().equals("MID")) 
            {
                salidas[2] += "\n   -" + listapersonajes.getLista()[i].getNombre();
                cant[2]++;
            }
            else if(listapersonajes.getLista()[i].getRol().equals("ADC")) 
            {
                salidas[3] += "\n   -" + listapersonajes.getLista()[i].getNombre();
                cant[3]++;
            }
            else 
            {
                salidas[4] += "\n   -" + listapersonajes.getLista()[i].getNombre();
                cant[4]++;
            }
        }

        salida += "\n-TOP (" + cant[0] + ")" + salidas[0]+ "\n-JG (" + cant[1] + ")" + salidas[1]+ "\n-MID (" + cant[2] + ")" + salidas[2]+ "\n-ADC (" + cant[3] + ")" + salidas[3]+ "\n-SUPP (" + cant[4]  + ")" + salidas[4];
        
        return salida;
    }

    @Override
    public String obtenerEstadisticas() 
    {
        String salida = "";
        
        for(int i=0;i<listapersonajes.getCant();i++) 
        {
            salida += listapersonajes.getLista()[i].getNombre() + "," + listapersonajes.getLista()[i].getRecaudacion() + "\n";
        }
        
        return salida;
    }

    
    
}
