
package eft_s9_abdul_saroukhan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EFT_S9_Abdul_Saroukhan {
    
    static Scanner teclado = new Scanner(System.in);
    
    static List<String> ubicaciones = new ArrayList<>();
    static List<String> descuentos = new ArrayList<>();
    static List<Double> precios = new ArrayList<>();
    static List<Double> preciosFinal = new ArrayList<>();
    static List<Boolean> boletasGeneradas = new ArrayList<>();
    
    static int cantidad = 0;
    static double  ventasTotales = 0;
    
    static int vip = 10,plateaBaja = 15,plateaAlta = 20,galeria =10;
    
    
    static void mostrarMenu(){
        System.out.println("\n==============================");        
        System.out.println("        MENU PRINCIPAL");
        System.out.println("==============================");
        System.out.println("1.- Comprar Entrada");
        System.out.println("2.- Modificar Asiento.");
        System.out.println("3.- Generar Boleta");
        System.out.println("4.- Ventas totales");
        System.out.println("0.- Salir");
        System.out.println("\nSeleccion una Opcion: ");  
    }
    
    static int opcion(){
        int opcion = 0;
        boolean valido;
        
        
        valido = false;
        while(!valido){
            try{
                opcion = teclado.nextInt();
                if (opcion > 0){
                    valido  = true;
                }else{
                    System.out.println("Opcion  invalida.Intente nuevamente.");
                }
            }catch(Exception e){
                System.out.println("Opcion invalida.Intente nuevamente.");
                teclado.nextLine();
            }
        }
        return opcion;
    }
    
    static void comprarEntrada(){
        double total = 0;
        boolean boleta = false;
        int cantidadEntradas = 0;
        
        if(vip + plateaBaja + plateaAlta + galeria == 0){
            System.out.println("No quedan entradasdisponibles.");
            return;
        }
        
        System.out.println("¿Cuántas entradas desea comprar?");
        cantidadEntradas = opcion();
        
        for(int i = 0; i < cantidadEntradas; i++){
            
            System.out.println("\n=== Entrada #" + (i + 1) + " ===");
            
            if (vip + plateaBaja + plateaAlta + galeria == 0) {
            System.out.println("Se agotaron las entradas antes de completar su compra.");
            break;
            }

            double precio = seleccionEntrada();
        
            double descuento = aplicaDescuento();
        
            total = precio * (1 - descuento);
            preciosFinal.add(total);
            boletasGeneradas.add(boleta);
            cantidad ++;
        }
    }
    
    static double seleccionEntrada(){
        int opcion;
        String ubicacion;
        double precio;
        
        System.out.println("\nSeleccione ubicacion de la entrada");
        System.out.println("1.- VIP");
        System.out.println("2.- Platea Baja");
        System.out.println("3.- Platea Alta");
        System.out.println("4.- Galeria");
        
        do{
            precio = 0;
            opcion = opcion();
            
            switch(opcion){
                
                case 1 ->{
                    if(vip > 0){
                        ubicacion = "vip";
                        precio = 15000;
                        vip--;
                        ubicaciones.add(ubicacion);
                        precios.add(precio);
                    }else{
                        System.out.println("No quedan entradas VIP.");
                    }
                }
                case 2 ->{
                    if (plateaBaja > 0){
                        ubicacion = "Platea Baja";
                        precio = 10000;
                        plateaBaja--;
                        ubicaciones.add(ubicacion);
                        precios.add(precio);
                    }else{
                        System.out.println("No quedan entradas en Platea Baja.");
                    }
                }
                case 3 ->{
                    if (plateaAlta > 0){
                        ubicacion = "Platea Alta";
                        precio = 8000;
                        plateaAlta--;
                        ubicaciones.add(ubicacion);
                        precios.add(precio);
                    }else{
                        System.out.println("No quedan entradas en Platea Alta.");
                    }
                }
                case 4 ->{
                    if (galeria > 0){
                        ubicacion = "Galeria";
                        precio = 6000;
                        galeria--;
                        ubicaciones.add(ubicacion);
                        precios.add(precio);
                    }else{
                        System.out.println("No quedan entradas en Galeria.");
                    }
                }default->{System.out.println("\n️ Opcion invalida. Intente nuevamente.");
            }  
            }
        }while(opcion < 1 || opcion > 4 &&(precio > 0));
        return precio;
    }
    
    static double aplicaDescuento(){
        int opcion;
        int edad;
        String genero =  "", tipoDescuento;
        double descuento = 0;
        
        
        System.out.println("Ingrese su edad");
        edad = opcion();
        
        System.out.println("Genero");
        System.out.println("[1] Masculino");
        System.out.println("[2] Femenino");
        
        do{
        opcion  = opcion();
        
        if (opcion == 1){
            genero = "Masculino";
        }else if  (opcion ==  2){
            genero = "Femenino";
        }else{
            System.out.println("Opcion invalida.Intente de nuevo.");
        } 
        }while(opcion < 1 || opcion > 2);
        
        if (genero.equals("Femenino")){
            descuento = 0.20;
            tipoDescuento  = "Mujer";
            descuentos.add(tipoDescuento);
        }else{
            if (edad <  18){
                descuento  = 0.10;
                tipoDescuento = "Niño";
                descuentos.add(tipoDescuento);
            }else if (edad < 25 && edad > 18){
                descuento = 0.15;
                tipoDescuento  = "Estudiante";
                descuentos.add(tipoDescuento);
            }else if (edad > 65){
                descuento = 0.25;
                tipoDescuento  = "Tercera Edad";
                descuentos.add(tipoDescuento);
            }      
        }
        
        if(descuento == 0){
            tipoDescuento  = "Ninguno";
            descuentos.add(tipoDescuento);
        }
        return descuento;
    }
    
    static void generarBoleta(){
        double totalCompra = 0;
        
        totalCompra = 0;
        if(!boletasGeneradas.contains(false)){
            System.out.println("Todas las boletas ya fueron generadas.");
            return;
        }
         
        if (ubicaciones.isEmpty()){
            System.out.println("Todavia no se ha vendido ninguna entrada.");
            return;
        }else{
            
            System.out.println("\n==================================");
            System.out.println("           TEATRO MORO");
            System.out.println("         BOLETA DE COMPRA");
            System.out.println("==================================");
            
            for(int i=0; i < cantidad; i++){
                if (boletasGeneradas.get(i)== false){
                    System.out.println("Entrada #" + (i + 1));
                    System.out.println("Ubicacion:    " + ubicaciones.get(i) );
                    System.out.println("Descuento:    " + descuentos.get(i));
                    System.out.println("Precio base:  $" + precios.get(i));
                    System.out.println("Precio final: $" + preciosFinal.get(i));
                    System.out.println("==============================");
                    totalCompra +=  preciosFinal.get(i);
                    boletasGeneradas.set(i, true);
                } 
            }
        
        ventasTotales  += totalCompra;
        System.out.println("TOTAL A PAGAR: $" + totalCompra);
        System.out.println("¡Gracias por su compra!");
        System.out.println("==================================\n");            
        }
    }
    
    static void ventasTotales(){
        System.out.println("\n=== RESUMEN DE VENTAS ===");
        System.out.println("VIP: " + (10 - vip) + " vendidas");
        System.out.println("Platea Baja: " + (15 - plateaBaja) + " vendidas");
        System.out.println("Platea Alta: " + (20 - plateaAlta) + " vendidas");
        System.out.println("Galería: " + (10 - galeria) + " vendidas");
        System.out.println("Total recaudado: $" + ventasTotales);
    }
    
    static void modificarAsiento(){
        int indice ;
        int seleccion;
        String ubicacionAnterior;
        double nuevoPrecio;
        String tipoDescuento;
        double descuento;
        double nuevoTotal;
        
        if (!boletasGeneradas.contains(false)) {
            System.out.println("No hay entradas modificables. Todas ya tienen boleta.");
            return;
        }
        
        System.out.println("\n=== ENTRADAS DISPONIBLES PARA MODIFICAR ===");
        for (int i = 0; i < boletasGeneradas.size(); i++){
            if(boletasGeneradas.get(i)== false){
                System.out.println("Entrada #" + (i + 1));
                System.out.println("Ubicacion:    " + ubicaciones.get(i) );
                System.out.println("Precio base:  $" + precios.get(i));
            }
        }
        
        System.out.println("Seleccione el numero de la entrada que desea modificar:");
        seleccion = opcion();
        
        indice = seleccion -  1;
        
        if(indice < 0 || indice >= boletasGeneradas.size() || boletasGeneradas.get(indice)){
            System.out.println("Entrada invalida o ya procesada.");
        }
        
        ubicacionAnterior = ubicaciones.get(indice);
        switch(ubicacionAnterior){
            case "vip" -> vip++;
            case "Platea Baja" -> plateaBaja++;
            case "Platea Alta" -> plateaAlta++;
            case "Galeria" -> galeria++;
        }
        
        System.out.println("\nSeleccione nueva ubicacion para la entrada");
        nuevoPrecio = seleccionEntrada();
        ubicaciones.set(indice, ubicaciones.get(ubicaciones.size() - 1));
        ubicaciones.remove(ubicaciones.size() - 1);
        
        precios.set(indice, nuevoPrecio);
        precios.remove(precios.size() - 1);
        
        tipoDescuento = descuentos.get(indice);
        
        switch (tipoDescuento){
            case "Mujer" -> descuento = 0.20;
            case "Niño" -> descuento = 0.10;
            case "Estudiante" -> descuento = 0.15;
            case "Tercera Edad" -> descuento = 0.25;
            default -> descuento = 0;
        }
        
        nuevoTotal = nuevoPrecio * (1 - descuento);
        preciosFinal.set(indice, nuevoTotal);
        
        System.out.println("Asiento modificado correctamente.");
    }
    
    public static void main(String[] args) {
        int opcion;
        mostrarMenu();
        do{
            
            opcion = teclado.nextInt();
            
            switch(opcion){
                case 1 -> comprarEntrada();
                case 2 -> modificarAsiento();
                case 3 -> generarBoleta();
                case 4 -> ventasTotales();
                case 0 -> System.out.println("Saliendo del sistema.");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }while(opcion != 0);
        teclado.close();
    }
}
