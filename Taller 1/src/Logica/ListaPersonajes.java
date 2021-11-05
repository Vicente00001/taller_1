
package Logica;
import Dominio.*;


public class ListaPersonajes 
{
    private Personaje[] lista;
    private int max;
    private int cant;
    
    public ListaPersonajes (int max) 
    {
        lista = new Personaje[max];
        this.max = max;
        this.cant = 0;
    }

    public Personaje[] getLista() {
        return lista;
    }

    public void setLista(Personaje[] lista) {
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
    
    public boolean ingresarPersonaje (Personaje p) 
    {
        if (cant<max) 
        {
            lista[cant] = p;
            cant++;
            return true;
        }
        else 
        {
            return false;
        }   
    }
    
    public Personaje buscarNombre(String nombre) 
    {
        int i;
        for(i=0;i<cant;i++) 
        {
            if (lista[i].getNombre().equals(nombre)) 
            {
                break;
            }
        }
        
        if(i == cant) 
        {
            return null;
        }
        else 
        {
            return lista[i];
        }
    }
    
}
