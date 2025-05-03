
package exp3_s8_abdul_saroukhan;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Random;

public class Exp3_S8_Abdul_Saroukhan {

    static Scanner  teclado = new Scanner(System.in);
    
    static boolean[][] asientos = new boolean [3][3];
    static String[] zonas = {"A","B","C"};
    
    static ArrayList<Integer> clienteIDs = new ArrayList<>();
    static ArrayList<String> clienteNombres = new ArrayList<>();
    static ArrayList<Integer> clienteEdades = new ArrayList<>();
    static ArrayList<Boolean> clienteEstudiantes = new ArrayList<>();
    static int clienteContador = 0;
    
    static ArrayList<Integer> ventaClienteIDs = new ArrayList<>();
    static ArrayList<Double> ventaPrecios = new ArrayList<>();
    static ArrayList<String> asientosVendidos = new ArrayList<>();
    static int ventaContador = 0;
    
    static ArrayList<Integer> reservaIDs = new ArrayList<>();
    static ArrayList<String> reservaAsiento = new ArrayList<>();
    static int reservaContador = 0;
    
    static double[] descuentos = {0.10, 0.15}; // ESTUDIANTE, TERCERA EDAD
    static String[] promociones = {"Polera","Gorra"};

    
    
    static Integer seleccionadorIDs(){
        
        Iterator<Integer> iterador = ventaClienteIDs.iterator();
        boolean  valido;
        int id = 0;
        int indice = 0;
        
        while(iterador.hasNext()){
            int venta = iterador.next();
            System.out.println("ID : " + venta  );
        }
        
        valido = false;
        while(!valido){
            try{
                System.out.println("Ingrese el ID  de la venta que desea modificar.");
                id  = teclado.nextInt();
                if (ventaClienteIDs.contains(id)){
                    valido = true;
                    indice = ventaClienteIDs.indexOf(id);
                }else{
                    System.out.println("Venta inexsistente.Intente de nuevo");
                }
            }catch(Exception e){
                System.out.println("Opcion invaldio .Ingrese un numero");
            }
        }
    return indice;
    }
    
    
    static void mostrarMenu(){
        System.out.println("\n==============================");        
        System.out.println("        MENU PRINCIPAL");
        System.out.println("==============================");
        System.out.println("1.- Reservar Entrada");
        System.out.println("2.- Vender Entrada");
        System.out.println("3.- Cancelar Venta");
        System.out.println("4.- Modificar Venta");
        System.out.println("5.- Mostrar Ventas");
        System.out.println("0.- Salir");
        System.out.println("\nSeleccion una Opcion: ");
    }
   
    static void mostrarAsientos(){
        long inicio = System.nanoTime();
        
        System.out.println("\n--- Teatro Moro ---");
        for (int n = 0; n < 3; n++){
            System.out.print("Zona " + zonas[n] + " " );                        
            for (int j = 0; j <  3;j++){
                System.out.print("[" + (asientos[n][j] ? "*" : (j+1)) + "]");
            }
            System.out.println(" ");
        }
        long fin = System.nanoTime();
        long duracion = fin - inicio ;
        System.out.println("Tiempo de cargar: " + duracion);
        System.out.println("\nLos asientos marcados con *,  no estan disponibles \n");
    }
    
    static void reservarEntrada(){
        String zona = "";
        int asiento = -1;
        boolean valido  = false;
        int fila = -1;
        
        mostrarAsientos();
        
        teclado.nextLine();
        while(!valido){
            try{
                System.out.println("Seleccione una zona (A - C)");
                zona = teclado.nextLine().toUpperCase();
                if(zona.equals("A")|| zona.equals("B") || zona.equals("C")){
                    valido = true;
                }else{
                    System.out.println("Seleccion invalida.Intente nuevamente.\n");
                }        
            }catch(Exception  e){
                System.out.println("Error.Debes ingresar una zona valida.\n");
                teclado.nextLine();
            }    
        }//SELECCION DE ZONA Y VALIDACION
        
        valido = false;
        while(!valido){
            
            try{
                System.out.println("Seleccione un asiento (1 - 3)");
                asiento =  teclado.nextInt();
                if(asiento == 1 ||  asiento == 2 || asiento == 3){
                    valido  = true;
                }else{
                    System.out.println("Seleccion invalida.Intente nuevamente.\n");
                }
            }catch(Exception e){
                System.out.println("Error.Debes ingresar un asiento valido.\n");
                teclado.nextLine();
            }
        }//SELECCION DE ASIENTO Y VALIDACION
        
        switch (zona){
            case "A" -> fila  = 0;
            case "B" -> fila  = 1;
            case "C" -> fila = 2;
            default -> System.out.println("Zona invalida.Intente nuevamente (A - C): ");
        }//TRANSFORMA EL ASIENTO EN EL NUMNERO DEL ARREGLO
        
        if (asientos[fila][asiento - 1]){
            System.out.println("\nEl asiento "+ zona +  asiento +" ya esta ocupado\n");
            return;
        }else{
            String ubicacion = zona + asiento;
            System.out.println("\nSu asiento ha sido reservado exitosamente.");
            asientos[fila][asiento-1] = true;
            reservaIDs.add(reservaContador);
            reservaAsiento.add(ubicacion);
            System.out.println("El ID de su reserva es " + reservaContador );
            reservaContador++; 
         }
    }
    
    static void venderEntrada(){
        
        boolean valido = false;
        int seleccion = -1;
        final double precio = 15000;
        double total = 0;
        String zona = "";
        int asiento = -1;
        valido = false;
        int fila = -1;
        int cantidad = 0;
        String nombre = "";
        int edad = 0;
        boolean estudiante = false;
        boolean asientoValido = false;
        int id = clienteContador;
        String asientoVendido = "";
        Random random = new Random();
        int promocion = random.nextInt(2);
        
        if(asientosVendidos.size() == 9){
            System.out.println("No quedan asientos disponibles");
            return;
        }
        
        teclado.nextLine();
        valido = false;
        while(!valido){

            System.out.print("Nombre: ");
            nombre = teclado.nextLine().toLowerCase();
            if(nombre == null || nombre.length()< 3 || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")){
                System.out.println(" Seleccion invalida.Intente nuevamente.\n");
            }else{
                valido = true;
            }
        }
        
        valido = false;
        while(!valido){
            try{
                System.out.print("Edad: ");
                edad = teclado.nextInt();
                if (edad > 0){
                    valido = true;
                }else{
                    System.out.println("Seleccion invalida.Intente nuevamente.\n");
                }     
            }catch(Exception e){
                System.out.println("Seleccion invalida.Intente nuevamente.\n");
                teclado.nextLine();
            }
        }
        
        teclado.nextLine();
        valido = false;
        while(!valido){
            try{
                System.out.print("¿Es estudiante? (true/false): ");
                estudiante = teclado.nextBoolean();
                if(estudiante == true || estudiante == false){
                    valido = true;
                }
            }catch(Exception e){
                System.out.println("Seleccion invalida.Intente nuevamente.\n");
                teclado.nextLine();
            }  
        }
        
        teclado.nextLine();
        asientoValido =  false;
        while(!asientoValido){
                mostrarAsientos();
                
                valido = false;
                while(!valido){
                    try{
                        System.out.println("Seleccione una zona (A - C)");
                        zona = teclado.nextLine().toUpperCase();
                        if(zona.equals("A")|| zona.equals("B") || zona.equals("C")){
                            valido = true;
                        }else{
                            System.out.println("Ingrese una zona valida.\n");
                        }        
                    }catch(Exception  e){
                        System.out.println("Seleccion invalida.Intente nuevamente.\n");
                        teclado.nextLine();
                    }    
                }//SELECCION DE ZONA Y VALIDACION
                
                valido = false;
                while(!valido){
                    try{
                        System.out.println("Seleccione un asiento (1 - 3)");
                        asiento =  teclado.nextInt();
                        if(asiento == 1 ||  asiento == 2 || asiento == 3){
                            valido  = true;
                        }else{
                            System.out.println("Seleccion invalida.Intente nuevamente.\n");
                        }
                    }catch(Exception e){
                        System.out.println("Seleccion invalida.Intente nuevamente.\n");
                        teclado.nextLine();
                    }
                }//SELECCION DE ASIENTO Y VALIDACION
        
                switch (zona){
                    case "A" -> fila  = 0;
                    case "B" -> fila  = 1;
                    case "C" -> fila = 2;
                    default -> System.out.println("Zona invalida.Intente nuevamente (A - C): ");
                }//TRANSFORMA EL ASIENTO EN EL NUMNERO DEL ARREGLO
            
                if (asientos[fila][asiento - 1]){
                System.out.println("\nEl asiento "+ zona +  asiento +" ya esta ocupado\n");
                }else{
                    asientoValido = true;
                    asientos[fila][asiento - 1] = true;
                    asientoVendido = zona + asiento;
                    asientosVendidos.add(asientoVendido);
                }
            }
        
        total = precio;
        if(estudiante){
            total = precio * (1 - descuentos[0]);
        }else if(edad >= 60){
            total  = precio * (1 - descuentos[1]);
        }
       
        System.out.println("Total a  pagar: $" + total);
        System.out.println("Por la compra  de su entrada usted se lleva una " + promociones[promocion] + ".");
        
        
        
        if(!clienteNombres.contains(nombre)){
            clienteIDs.add(clienteContador);
            clienteNombres.add(nombre);
            clienteEdades.add(edad);
            clienteEstudiantes.add(estudiante); 
            clienteContador++;
        }
        
        ventaClienteIDs.add(ventaContador);
        ventaPrecios.add(total);
        ventaContador++;        
    }

    static void cancelarVenta(){
        
        if (ventaClienteIDs.isEmpty()){
            System.out.println("No existen Ventas.");
            return;
        }
        
        int indice = seleccionadorIDs();
        
        String ventaAnterior = asientosVendidos.get(indice);
        char letraZona = ventaAnterior.charAt(0);
        int numeroAsiento = Integer.parseInt(ventaAnterior.substring(1));
        int filaAnterior = letraZona - 'A';
        asientos[filaAnterior][numeroAsiento - 1] = false;
        asientosVendidos.remove(indice);
        
        ventaClienteIDs.remove(indice);
        ventaPrecios.remove(indice);
        
        System.out.println("Venta cancelada exitosamente.");   
    }
    
    static void modificarVenta(){
        
        if(ventaClienteIDs.isEmpty( )){
            System.out.println("No existen ventas.");
            return;
        }
                
        cancelarVenta();
        
        venderEntrada();
        
        
        
        
    }
    
    static void mostrarVenta(){
        int totalEntradas = ventaPrecios.size();
        int totalVendido = 0;
        
        
        if (ventaClienteIDs.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        
        System.out.println("\n--- Ventas Realizadas ---");
        for (int i = 0; i < ventaClienteIDs.size(); i++) {
            System.out.println("ID Venta: " + ventaClienteIDs.get(i));
            System.out.println("Asiento: " + asientosVendidos.get(i));
            System.out.println("Precio: $" + ventaPrecios.get(i));
            System.out.println("---------------------------");
            totalVendido += ventaPrecios.get(i);
        }
        
        System.out.println("Total de entradas vendidas: " + totalEntradas);
        System.out.println("Total Vendido: $" + totalVendido);   
    }
    
    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            
            opcion = teclado.nextInt();
            
            switch(opcion){
                case 1 -> reservarEntrada();
                case 2 -> venderEntrada();
                case 3 -> cancelarVenta();
                case 4 -> modificarVenta();
                case 5 -> mostrarVenta();
                case 0 -> System.out.println("Saliendo del sistema.");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while(opcion != 0);
        

    }
    
}
