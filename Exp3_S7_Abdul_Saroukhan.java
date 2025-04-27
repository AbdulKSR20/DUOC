
package exp3_s7_abdul_saroukhan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Exp3_S7_Abdul_Saroukhan {
    static Scanner teclado = new Scanner(System.in);
    static List<String> ubicaciones = new ArrayList<>();
    static List<String> descuentos = new ArrayList<>();
    static List<Double> precios = new LinkedList<>();
    static List<Double> preciosFinal = new LinkedList<>();
    static List<Boolean> boletasGeneradas = new ArrayList<>();
    static int platea = 20,general =  30,vip = 15;
    static String ubicacion = "",tipoDescuento="";
    static double precio  = -1, precioFinal;
    static boolean boleta = false;
    static double totalVenta = 0;
    
    static void actualizarListas(){
                ubicaciones.add(ubicacion);
                descuentos.add(tipoDescuento);
                precios.add(precio);
                preciosFinal.add(precioFinal);
                boletasGeneradas.add(boleta);
    }
    
    static void mostrarMenu(){
        System.out.println("\n==============================");        
        System.out.println("        MENU PRINCIPAL");
        System.out.println("==============================");
        System.out.println("1.- Comprar Entrada");
        System.out.println("2.- Visualizar resumen de ventas");
        System.out.println("3.- Generar Boleta");
        System.out.println("4.- Ventas totales");
        System.out.println("0.- Salir");
        System.out.println("\nSeleccion una Opcion: ");  
    } 
    
    static void comprarEntrada(){
        int opcion,opcion1 = 0;
             
        if (vip + platea + general == 0) {
            System.out.println("No quedan entradas disponibles.");
            return;
        }        
        
        System.out.println("\nSeleccione ubicacion de la entrada");
        System.out.println("1.- VIP");
        System.out.println("2.- Platea");
        System.out.println("3.- General");
        
        do{
            opcion = teclado.nextInt();
            
            switch(opcion){
                case 1 ->{
                    if (vip > 0){
                        ubicacion = "VIP";
                        precio = 15000;
                        vip--;
                    }else{
                        System.out.println("No quedan entradas VIP");
                        return;
                    }
                }
                case 2 ->{
                    if (platea > 0){
                        ubicacion = "Platea";
                        precio = 10000;
                        platea--;
                    }else{
                        System.out.println("No quedan entradas en Platea");
                        return;
                    }                    
                }
                case 3 ->{
                    if (general > 0){
                        ubicacion  = "General";
                        precio = 5000;
                        general--;
                    }else{
                        System.out.println("No quedan entradas en General");
                        return;
                    }
                    
                }default->{System.out.println("\n️ Opcion invalida. Intente nuevamente.");
                }  
            } 
        }while (opcion < 1 || opcion > 3 );
        
        
        System.out.println("\nSeleccione una opcion");
        System.out.println("1.- Tercera Edad");
        System.out.println("2.- Estudiante");
        System.out.println("3.- General");
        
        while((opcion1 < 1 || opcion1 > 3) && precio > 0){
            
            opcion1 = teclado.nextInt();
            
            switch(opcion1){
                case 1 ->{
                    precioFinal = precio * (1 - 0.15);
                    tipoDescuento = "Tercer Edad 15%";
                    actualizarListas();
                    totalVenta += precioFinal;
                }
                case 2 ->{
                    precioFinal = precio * (1 - 0.10);
                    tipoDescuento = "Estudiante 10%";
                    actualizarListas();
                    totalVenta += precioFinal;
                }
                case 3 ->{
                    precioFinal = precio;
                    tipoDescuento = "Ninguno";
                    actualizarListas();
                    totalVenta += precioFinal;
                    }default ->{System.out.println("\n️ Opcion invalida. Intente nuevamente.");
                }                
            }  
        }
    }
    
    static void resumenVenta(){
        
        if(ubicaciones.isEmpty()){
            System.out.println("Todavia no se ha vendido ninguna entrada.");
            return;
        }else{
            System.out.println("Ventas realizadas: ");
            for (int i = 0; i < ubicaciones.size(); i++){
                System.out.println("Venta N°" + (i+1));
                System.out.println("--------------------------------");
                System.out.println("Ubicacion: " + ubicaciones.get(i) );
                System.out.println("Descuentos: " + descuentos.get(i));
                System.out.println("Precio final: " + preciosFinal.get(i));
                System.out.println("--------------------------------");
            }
        }
    }
    
    static void generarBoleta(){
        
        if(!boletasGeneradas.contains(false)){
            System.out.println("Todas las boletas ya fueron generadas.");
            return;
        }
           
        if (ubicaciones.isEmpty()){
            System.out.println("Todavia no se ha vendido ninguna entrada.");
            return;
        }else{
            while(boletasGeneradas.contains(false)){
                for(int i=0; i < ubicaciones.size(); i++){
                    if (boletasGeneradas.get(i)== false){
                        System.out.println("\n==============================");
                        System.out.println("           TEATRO MORO");
                        System.out.println("==============================");
                        System.out.println("Ubicacion: " + ubicaciones.get(i) );
                        System.out.println("Descuentos: " + descuentos.get(i));
                        System.out.println("Precio base: " + precios.get(i));
                        System.out.println("Precio final: " + preciosFinal.get(i));
                        System.out.println("Gracias por su compra. ¡Disfrute la funcion!");
                        System.out.println("==============================\n");
                        boletasGeneradas.set(i, true);
                    } 
                }
            }
        }
    }

    public static void main(String[] args) {
        
        int seleccion;
        
        do{
            mostrarMenu();
            seleccion = teclado.nextInt();
            
            switch(seleccion){
                case 1 ->{comprarEntrada();                   
                }
                case 2 ->{resumenVenta();                    
                }
                case 3 ->{generarBoleta();                    
                }
                case 4 ->{
                    System.out.println("\n=== VENTAS TOTALES ===");
                    System.out.println("Venta total de entradas : $" + totalVenta);
                    System.out.println("Numero de entradas vendidas: " + ubicaciones.size() );
                }
                case 0 ->{System.out.println("\n Saliendo del sistema. ¡Hasta pronto!");
                    
                }default ->{System.out.println("\n️ Opcion invalida. Intente nuevamente.");  
                }                
            }
            
        }while(seleccion != 0);
    }
    
}
