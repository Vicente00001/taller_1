
package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class App 
{
    /**
     * Method to read characters from entered text file
     * @param sistema 
     */
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
    /**
     * Method to read the statistics from the entered text file
     * @param sistema 
     */
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
    /**
     * Method to read the counts from the entered text file
     * @param sistema 
     */
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
                                sistema.asociarSkinPersonajeDeCuenta(partes[0],partes[a],partes[c]);
                            }
                            catch (Exception exc) 
                            {
                                System.out.println(exc.getMessage());
                            }
                            c++;
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
    
    public static boolean buscarBloqueados(String[] bloqueados, String bloqueado) 
    {
        int i;
        for(i=0;i<bloqueados.length;i++) 
        {
            if(bloqueados[i] !=null) 
            {
                if(bloqueados[i].equals(bloqueado)) 
                {
                    break;
                }
            }    
        }
        
        if(i==bloqueados.length) 
        {
            return false;
        }
        else 
        {
            return true;
        }
    }

    public static void main(String[] args) 
    {
        SistemaImpl sistema = new SistemaImpl();
        LeerPersonajes(sistema);
        LeerCuentas(sistema);
        LeerEstadisticas(sistema);
        
        Scanner read = new Scanner(System.in);
        String[] bloqueados = new String[1000];
        int z=0;
        
        System.out.print("Desea cerrar el sistema? (SI) (NO): ");
        String respuesta = "";
        respuesta = read.next().toUpperCase();
        
        while (!respuesta.equals("SI") && !respuesta.equals("NO")) 
        {
            System.out.println("\n--- El texto ingresado es incorrecto ---");
            System.out.print("\nDesea cerrar el sistema? (SI) (NO): ");
            respuesta = read.next().toUpperCase();
        }
        
        while (respuesta.equals("NO")) 
        {
            String nombre = "";
            String contraseña = "";
            System.out.println("\n    MENU DE INICIO DE SESION\n");
            System.out.println("Desea: \na)Iniciar sesion \nb)Registrarse");
            System.out.print("\nIngrese la opcion: ");
            String opcion = read.next().toLowerCase();
            
            while(!opcion.equals("a") && !opcion.equals("b")) 
            {
                System.out.println("\n--- La opcion ingresada no es correcta, ingrese otra\n");
                System.out.print("\nIngrese la opcion: ");
                opcion = read.next().toLowerCase();
            }
            
            if(opcion.equals("a")) 
            {
                System.out.println("\n  INICIO DE SESION\n");
                System.out.print("Ingrese su nombre de cuenta: ");
                nombre = read.next();
                System.out.print("Ingrese su contraseña: ");
                contraseña = read.next();
                
                while (!sistema.verificarInicio(nombre, contraseña) && !nombre.equals("ADMIN") && !contraseña.equals("ADMIN")) 
                {
                    System.out.println("--- El nombre y/o contraseña son incorrectos");
                    System.out.println("\n  INICIO DE SESION\n");
                    System.out.print("Ingrese su nombre de cuenta: ");
                    nombre = read.next();
                    System.out.print("Ingrese su contraseña: ");
                    contraseña = read.next();
                }
                
                while (buscarBloqueados(bloqueados,nombre)) 
                {
                    System.out.println("--- Esta cuenta se encuentra bloqueada ---");
                    System.out.println("\n  INICIO DE SESION\n");
                    System.out.print("Ingrese su nombre de cuenta: ");
                    nombre = read.next();
                    System.out.print("Ingrese su contraseña: ");
                    contraseña = read.next();
                }
    
            }
            
            else
            {
                System.out.println("\n  REGISTRO");
                System.out.print("Ingrese su nombre de cuenta: ");
                String nombreR = read.next();
                while (buscarBloqueados(bloqueados,nombreR)) 
                {
                    System.out.println("--- Este nombre de cuenta se encuentra bloqueado ---");
                    System.out.print("Ingrese su nombre de cuenta: ");
                    nombreR = read.next();
                }                
                System.out.print("Ingrese su contraseña: ");
                String contraseñaR = read.next();
                System.out.println("Ingrese su nick: ");
                String nick = read.next();
                System.out.println("Ingrese su region: ");
                String region = read.next();
                
                sistema.ingresarCuenta(nombreR, contraseñaR, nick, 0, 0, region);
                
                System.out.println("\n  INICIO DE SESION\n");
                System.out.print("Ingrese su nombre de cuenta: ");
                nombre = read.next();
                System.out.print("Ingrese su contraseña: ");
                contraseña = read.next();
                
                while (!sistema.verificarInicio(nombre, contraseña) && !nombre.equals("ADMIN") && !contraseña.equals("ADMIN")) 
                {
                    System.out.println("--- El nombre y/o contraseña son incorrectos");
                    System.out.println("\n  INICIO DE SESION\n");
                    System.out.print("Ingrese su nombre de cuenta: ");
                    nombre = read.next();
                    System.out.print("Ingrese su contraseña: ");
                    contraseña = read.next();
                }
                while (buscarBloqueados(bloqueados,nombre)) 
                {
                    System.out.println("--- Esta cuenta se encuentra bloqueada ---");
                    System.out.println("\n  INICIO DE SESION\n");
                    System.out.print("Ingrese su nombre de cuenta: ");
                    nombre = read.next();
                    System.out.print("Ingrese su contraseña: ");
                    contraseña = read.next();
                }
            }
            
            // MENU CLIENTE
            
            if (!nombre.equals("ADMIN") && !contraseña.equals("ADMIN")) 
            {
                String opc1 = "";
                while (!opc1.equals("g"))
                {
                    System.out.println("\n  MENU CLIENTE\n");
                    System.out.println("a)Comprar skin \nb)Comprar personaje \nc)Skin disponibles \nd)Mostrar inventario \ne)Recargar rp \nf)Mostrar datos \ng)Cerrar sesion");
                    System.out.print("\nIngrese una opcion: ");
                    opc1 = read.next().toLowerCase();

                    while(!opc1.equals("a") && !opc1.equals("b") && !opc1.equals("c") && !opc1.equals("d") && !opc1.equals("e") && !opc1.equals("f") && !opc1.equals("g") )
                    {
                        System.out.println("\n--- Opcion no encontrada, ingrese otra opcion ---\n");
                        System.out.print("\nIngrese una opcion: ");
                        opc1 = read.next().toLowerCase();
                    }

                    if(opc1.equals("a")) 
                    {
                        System.out.println("\n  COMPRAR SKIN\n");
                        System.out.println(sistema.obtenerPersonajes());
                        System.out.print("Ingrese el nombre del personaje del cual comprara una skin: ");
                        String personaje = read.next();
                        try
                        {
                            System.out.println(sistema.obtenerSkinDePersonaje(personaje) + "\n");
                            System.out.print("Ingrese el nombre de la skin a comprar: ");
                            String skin = read.nextLine();
                            skin = read.nextLine();
                            
                            if(sistema.comprarSkin(nombre, personaje, skin)) 
                            {
                                System.out.println("Ha comprado la skin "+ skin + "!");
                            }
                            else 
                            {
                                System.out.println("No tiene saldo suficiente para realizar esta compra");
                            }
                        }
                        catch(Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                        
                    }
                    else if(opc1.equals("b")) 
                    {
                        System.out.println("\n  COMPRAR PERSONAJE\n");
                        System.out.println(sistema.obtenerPersonajes());
                        System.out.print("Ingrese el nombre del personaje el cual comprara: ");
                        String personaje = read.next();
                        try
                        {
                            if(sistema.comprarPersonaje(nombre, personaje))
                            {
                                System.out.println("Ha comprado el personaje "+ personaje + "!");
                            }
                            else
                            {
                                System.out.println("No tiene saldo suficiente para realizar esta compra");
                            }
                        }
                        catch(Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
    
                    }
                    else if (opc1.equals("c"))
                    {
                        System.out.println("\n  SKIN DISPONIBLES");
                        try 
                        {
                            System.out.println(sistema.obtenerSkinsDisponibles(nombre));
                        }
                        catch(Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                        
                    }
                    else if(opc1.equals("d")) 
                    {
                        System.out.println("\n  INVENTARIO");
                        try
                        {
                            System.out.println(sistema.obtenerInventario(nombre));
                        }
                        catch(Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                        
                    }
                    else if(opc1.equals("e"))
                    {
                        System.out.println("\n  RECARGAR RP");
                        try 
                        {
                            System.out.print("Ingrese el monto a recargar: ");
                            int monto = read.nextInt();
                            try 
                            {
                                sistema.recargarRP(nombre, monto);
                            }
                            catch (Exception ex) 
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                        catch(Exception e) 
                        {
                            System.out.println("Ha ingresado un argumento incorrecto");
                        }
                        
                    }
                    else if(opc1.equals("f")) 
                    {
                        System.out.println("\n  DATOS");
                        try 
                        {
                            System.out.println(sistema.obtenerDatosCuenta(nombre));
                            
                            System.out.print("\n¿Desea cambiar su contraseña? : ");
                            String resp = read.next().toLowerCase();
                            
                            while (!resp.equals("si") && !resp.equals("no")) 
                            {
                                System.out.println("\n--- Ingrese si o no ---");
                                System.out.print("\n¿Desea cambiar su contraseña? : ");
                                resp = read.next().toLowerCase();
                            }
                            
                            if(resp.equals("si")) 
                            {
                                System.out.print("\nIngrese su contraseña antigua: ");
                                String antigua = read.next();
                                
                                while(!sistema.confirmarContraseña(nombre, antigua)) 
                                {
                                    System.out.println("\n--- Contraseña incorrecta ---");
                                    System.out.print("\nIngrese su contraseña antigua: ");
                                    antigua = read.next();
                                }
                                
                                System.out.print("\nIngrese su nueva contraseña: ");
                                String contra1 = read.next();
                                System.out.print("\nIngrese su nueva contraseña nuevamente: ");
                                String contra2 = read.next();
                                
                                while (!contra1.equals(contra2)) 
                                {
                                    System.out.println("\n--- Las contraseñas no coinciden, intente nuevamente ---");
                                    System.out.print("\nIngrese su nueva contraseña: ");
                                    contra1 = read.next();
                                    System.out.print("\nIngrese su nueva contraseña nuevamente: ");
                                    contra2 = read.next();
                                }
                                try 
                                {
                                    sistema.cambiarContraseña(nombre, contra1);
                                }
                                catch (Exception ex) 
                                {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
                        catch(Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            
            // MENU ADMIN
            
            else 
            {
                String opc2 = "";
                while (!opc2.equals("i"))
                {
                    System.out.println("\n  MENU ADMIN\n");
                    System.out.println("a)Recaudación por rol \nb)Recaudación por region \nc)Recaudacion por personaje \nd)Personajes por rol \ne)Agregar personaje \nf)Agregar skin \ng)Bloquear jugador \nh)Desplegar cuentas \ni)Cerrar sesión");
                    System.out.print("\nIngrese una opcion: ");
                    opc2 = read.next().toLowerCase();

                    while(!opc2.equals("a") && !opc2.equals("b") && !opc2.equals("c") && !opc2.equals("d") && !opc2.equals("e") && !opc2.equals("f") && !opc2.equals("g") && !opc2.equals("h") && !opc2.equals("i") )
                    {
                        System.out.println("\n--- Opcion no encontrada, ingrese otra opcion ---\n");
                        System.out.print("\nIngrese una opcion: ");
                        opc2 = read.next().toLowerCase();
                    }
                    
                    if(opc2.equals("a")) 
                    {
                        System.out.println("\n  RECAUDACION POR ROL");
                        System.out.println(sistema.obtenerRecaudacionRol());
                    }
                    
                    else if(opc2.equals("b")) 
                    {
                        System.out.println("\n  RECAUDACION POR REGION");
                        System.out.println(sistema.obtenerRecaudacionRegion());
                    }
                    else if (opc2.equals("c"))
                    {
                        System.out.println("\n  RECAUDACION POR PERSONAJE");
                        System.out.println(sistema.obtenerRecaudacionPersonaje());
                    }
                    else if(opc2.equals("d")) 
                    {
                        System.out.println("\n  PERSONAJES POR ROL");
                        System.out.println(sistema.obtenerPersonajesRol());
                    }
                    else if(opc2.equals("e")) 
                    {
                        System.out.println("\n  AGREGAR PERSONAJE");
                        System.out.print("\nIngrese el nombre: ");
                        String nombreP = read.next();
                        System.out.print("\nIngrese el rol: ");
                        String rolP = read.next().toUpperCase();
                        sistema.ingresarPersonaje(nombreP, rolP);
                        try
                        {
                            System.out.print("\nCantidad de skins disponibles: ");
                            int cantS = read.nextInt();
                            for(int i=0;i<cantS;i++) 
                            {
                                System.out.print("\nIngrese el nombre de la skin: ");
                                String nombreS = read.nextLine();
                                nombreS = read.nextLine();
                                sistema.asociarSkinPersonaje(nombreP, nombreS);
                            }
                        }
                        catch(Exception e) 
                        {
                            System.out.println("Ha ingresado un argumento incorrecto");
                        }
                    }
                    
                    else if(opc2.equals("f")) 
                    {
                        System.out.println("\n  AGREGAR SKIN");
                        System.out.print("\nIngrese el nombre del personaje de la skin: ");
                        String nombreP = read.next();
                        System.out.print("\nIngrese el nombre de la skin: ");
                        String nombreS = read.nextLine();
                        nombreS = read.nextLine();
                        System.out.print("\nIngrese la calidad de la skin: ");
                        String calidad = read.next().toUpperCase();
                        sistema.ingresarSkin(nombreS, calidad);
                        sistema.asociarSkinPersonaje(nombreP, nombreS);    
                    }
                    else if(opc2.equals("g"))
                    {
                        System.out.println("\n  BLOQUEAR JUGADOR");
                        System.out.print("\nIngrese el nombre de la cuenta a bloquear: ");
                        String nombreC = read.next();
                        try
                        {
                            if(sistema.bloquearJugador(nombreC)) 
                            {
                                bloqueados[z] = nombreC;
                                z++;
                            }
                        }
                        catch (Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else if(opc2.equals("h")) 
                    {
                        System.out.println("\n  CUENTAS");
                        System.out.println(sistema.obtenerCuentasOrdenadas());
                    }
                }    
            }
            
            
            
            System.out.println("\nHa cerrado sesion con exito!\n");
            System.out.print("Desea cerrar el sistema? (SI) (NO): ");
            respuesta = read.next().toUpperCase();
        
            while (!respuesta.equals("SI") && !respuesta.equals("NO")) 
            {
                System.out.println("\n--- El texto ingresado es incorrecto ---");
                System.out.print("\nDesea cerrar el sistema? (SI) (NO): ");
                respuesta = read.next().toUpperCase();
            }
        } // TERMINO DE SISTEMA
        SobreescribirPersonajes (sistema);
        SobreescribirCuentas (sistema);
        SobreescribirEstadisticas(sistema);
        
    } // TERMINO MAIN
    
    /**
     * Method that overwrites the worked data in your text file
     * @param sistema 
     */
    public static void SobreescribirPersonajes (SistemaImpl sistema) 
    {
        try
        {
            FileWriter fw = new FileWriter(new File("personajes.txt"));
            PrintWriter write = new PrintWriter(new BufferedWriter(fw));
            write.write(sistema.obtenerPersonajesYSkin());;   
            write.close();
        }
        catch(Exception e)
        {
            System.out.println("Archivo no encontrado");
        }
    }
    /**
     * Method that overwrites the worked data in your text file
     * @param sistema 
     */
    public static void SobreescribirCuentas (SistemaImpl sistema) 
    {
        try
        {
            FileWriter fw = new FileWriter(new File("cuentas.txt"));
            PrintWriter write = new PrintWriter(new BufferedWriter(fw));
            write.write(sistema.obtenerCuentas());;   
            write.close();
        }
        catch(Exception e)
        {
            System.out.println("Archivo no encontrado");
        }        
    }
    /**
     * Method that overwrites the worked data in your text file
     * @param sistema 
     */
    public static void SobreescribirEstadisticas(SistemaImpl sistema) 
    {
        try
        {
            FileWriter fw = new FileWriter(new File("estadisticas.txt"));
            PrintWriter write = new PrintWriter(new BufferedWriter(fw));
            write.write(sistema.obtenerEstadisticas());;   
            write.close();
        }
        catch(Exception e)
        {
            System.out.println("Archivo no encontrado");
        }
        
    }
}
