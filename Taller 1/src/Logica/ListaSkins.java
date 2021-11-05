
package Logica;
import Dominio.*;

public class ListaSkins 
{
    private Skin[] lista;
    private int max;
    private int cant;
    
    public ListaSkins (int max) 
    {
        lista = new Skin[max];
        this.max = max;
        this.cant=0;
    }

    public Skin[] getLista() {
        return lista;
    }

    public void setLista(Skin[] lista) {
        this.lista = lista;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    public boolean ingresarSkin (Skin s)
    {
        if (cant<max) 
        {
            lista[cant] = s;
            cant++;
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public Skin buscarNombre (String nombre) 
    {
        int i;
        for (i=0;i<cant;i++) 
        {
            if(lista[i].getNombre().equals(nombre)) 
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
}
