
package Logica;

import Dominio.*;


public class ListaCuentas 
{
    private Cuenta[] lista;
    private int cant;
    private int max;
    
    public ListaCuentas (int max) 
    {
        lista = new Cuenta[max];
        this.cant = 0;
        this.max = max;
    }

    public Cuenta[] getLista() {
        return lista;
    }

    public void setLista(Cuenta[] lista) {
        this.lista = lista;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public boolean ingresarCuenta (Cuenta c) 
    {
        if (cant< max) 
        {
            lista[cant] = c;
            cant++;
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public Cuenta buscarNombre(String nombre)
    {
        int i;
        for (i=0;i<cant;i++) 
        {
            if (lista[i].getNombre().equals(nombre)) 
            {
                break;
            }
        }
        
        if (i == cant) 
        {
            return null;
        }
        else 
        {
            return lista[i];
        }
        
    }
    
    public boolean Eliminar(String nombre) 
    {
        int i ;
        for (i =0; i< cant;i ++) 
        {
            if (lista[i].getNombre().equals(nombre)) 
            {
                break;
            }
        }
        
        if ( i == cant) 
        {
            return false;
        }
        
        else 
        {
            for (int j = i; j< (cant -1); j++) 
            {
                lista[j] = lista[j+1];
            }
            cant --;
            return true;
        }
    }
    
}
