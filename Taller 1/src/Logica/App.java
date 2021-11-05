
package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class App 
{

    public static void LeerPersonajes (SistemaImpl sistema)  
    {
        try
        {
            Scanner scanner = new Scanner(new File("personajes.txt"));
            
            while (scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                sistema.ingresarPersonaje(partes[0], partes[1]);
                int cant = Integer.valueOf(partes[2]);
                int a = 3;
                int b = 4;
                for(int i=0;i<cant;i++) 
                {    
                    sistema.ingresarSkin(partes[a],partes[b]);
                    try
                    {    
                        sistema.asociarSkinPersonaje(partes[0], partes[a]);
                    }
                    catch (Exception ex) 
                    {
                        System.out.println(ex.getMessage());
                    }
                    a+=2;
                    b+=2;
                }

            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Archivo indicado no encontrado");
        }        
    }
    
    public static void LeerEstadisticas (SistemaImpl sistema) 
    {
        try
        {
            Scanner scanner = new Scanner(new File("estadisticas.txt"));
            
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(","); 
                int monto = Integer.valueOf(partes[1]);
                try
                {    
                    sistema.asociarEstadistica(partes[0], monto);
                }
                catch (Exception e) 
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Archivo indicado no encontrado");
        }
    }
    
    public static void LeerCuentas (SistemaImpl sistema) 
    {
        try
        {
            Scanner scanner = new Scanner(new File("cuentas.txt"));
            
            while (scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                int nivel = Integer.valueOf(partes[3]);
                int rp = Integer.valueOf(partes[4]);
                int region = partes.length -1;
                sistema.ingresarCuenta(partes[0],partes[1],partes[2],nivel,rp,partes[region]);
                
                int cant1 = Integer.valueOf(partes[5]);
                int a = 6; 
                int b = 7;
                int c = 8;
                for (int i=0;i<cant1;i++) 
                {   
                    try
                    {
                        sistema.asociarPersonajeCuenta(partes[0], partes[a]);
                        int cant2 = Integer.valueOf(partes[b]);
                        for (int f=0;f<cant2;f++) 
                        {
                            try
                            {
                                sistema.asociarSkinPersonajeDeCuenta(partes[0],partes[a], partes[c]);
                                c++;
                            }
                            catch (Exception exc) 
                            {
                                System.out.println(exc.getMessage());
                            }
                        }
                        a+= (cant2 +2); // personajes
                        b+= (cant2 +2); // cantidad de skins por personaje
                        c+= 2; // inicio desde la primera skin del personaje
                    }
                    catch (Exception ex) 
                    {
                        System.out.println(ex.getMessage());
                    }
                }
   
            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Archivo indicado no encontrado");
        }        
        
    }

    public static void main(String[] args) 
    {
        SistemaImpl sistema = new SistemaImpl();
        LeerPersonajes(sistema);
        LeerCuentas(sistema);
        LeerEstadisticas(sistema);
        
        
    }
    
}
