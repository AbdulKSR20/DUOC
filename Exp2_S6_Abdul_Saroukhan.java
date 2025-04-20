/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication7;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

public class Exp2_S6_Abdul_Saroukhan {
    static Scanner teclado = new Scanner(System.in);
    static boolean[][] asientos= new boolean [5][5];
    static String[] filas  = {"A","B","C","D","E"};
    static ArrayList<String> reservados = new ArrayList<>();
    static boolean reservaHecha = false;
    static int seleccion;
    static boolean tiempoAgotado  = false; 
    static int totalVentas;
    static int totalEntradas;
    static final int precio = 15000;
    static int venta;
    
    
    static void mostrarMenu(){
        System.out.println("\n==============================");        
        System.out.println("        MENU PRINCIPAL");
        System.out.println("==============================");
        System.out.println("1.- Reservar Entrada");
        System.out.println("2.- Comprar Entrada");
        System.out.println("3.- Modificar una venta existente");
        System.out.println("4.- Total vendido");
        System.out.println("0.- Salir");
        System.out.println("\nSeleccion una Opcion: ");  
    } //MOSTRAR MENU

    static void mostrarAsientos(){
        System.out.println("\n--- Teatro Moro ---");                                       
        for (int n = 0; n < 5;n++){
            System.out.print("Zona " + filas[n] + " " );                        
            for (int j = 0; j <  5;j++){
                System.out.print("[" + (asientos[n][j] ? "*" : (j+1)) + "]");
            }
            System.out.println("");
        }   
    }//IMPRESION DE ASIENTOS DISPONIBLES
    
    static void reservaEntrada(){
        teclado.nextLine();
        reservaHecha = false;
        int fila;
        tiempoAgotado = false;
        Timer timer = new Timer();
        
        //SE MUESTRAN LOS ASIENTOS
        mostrarAsientos();
        System.out.println("\nTiene 60 segundas para completar su reserva.");
        
        //TIMER DE 60 SEGUNDOS PARA COMPLETAR LA RESERVA
        TimerTask tarea = new TimerTask(){
            @Override
            public void run(){
                if(reservaHecha == false){
                    System.out.println("\nTiempo agotado. Presione una tecla para volver al menu principal.");
                    tiempoAgotado = true;
                    timer.cancel();
                    seleccion = -1;
                }
            }
        };
        timer.schedule(tarea, 60000);
        
        
        System.out.println("\nSeleccione una Zona (A-E): ");//SELECCION DE ZONA
        String zona="";
        fila = -1;
        boolean zonaValida = false;
        
        //VERIFICA QUE SEA UNA ZONA VALIDA
        while (tiempoAgotado == false && (fila < 0 || fila > 4)&& (zonaValida == false)){
            zona = teclado.nextLine().toUpperCase();
            if(zona == "A"||zona =="B"||zona=="C"||zona=="D"||zona=="E"){
                zonaValida = true;
            }
            switch (zona) {
                case "A" -> fila = 0;
                case "B" -> fila  = 1;
                case "C" -> fila = 2;
                case "D" -> fila = 3;
                case "E" -> fila = 4;
                default -> System.out.println("Zona invalida.Intente nuevamente (A-E): ");
            }
        }
        if(tiempoAgotado == true) { //REGRESO AL MENU POR TIEMPO
            return;
        }
        
        System.out.println("Selecciona un asiento (1-5): "); //SELECCION DE ASIENTO
        
        int asiento = 0;
        
        //VERIFICACION DE  ASIENTO
        while (tiempoAgotado == false &&(asiento < 1 || asiento > 5)){
            asiento = teclado.nextInt();
            if (asiento < 1 || asiento > 5 ){
                System.out.println("Asiento invalido.Intente nuevamente (1-5): ");
            }
        }
        
         if(tiempoAgotado == true) { //REGRESO AL MENU POR TIEMPO
            return;
        }
        
        //VERIFICA QUE EL ASIENTO NO ESTE OCUPADO 
        if (asientos[fila][asiento - 1]){
            System.out.println("\nEl asiento "+ zona +  asiento +" ya esta ocupado\n");
        }else{
            reservaHecha = true;                                //SI, NO ESTA OCUPADO
            timer.cancel();                                     //CIERRA EL  TIMER
            System.out.println("\nSu asiento ha sido reservado exitosamente.");
            asientos[fila][asiento-1] = true;                   //SE MODIFICA EL ARRAY PARA MOSTRAR EN EL MAPA //BREAK POINT PARA ASEGURAR QUE CAMBIARA EL ESTADO DEL ASIENTO
            String reserva = zona + asiento;                    //AUXILIAR PARA GUARDAR EL ASIENTO //BREAKPOINT PARA ASEGURAR QUE GUARDARA EL ASIENTO CORRECTO
            reservados.add(reserva);                            //SE AGREGA A LA LISTA CON EL AUXILIAR //BREAK POINT PARA ASEGURAR QUE SE AGREGE A LA LISTA
        }
        timer.cancel();
    }
    
    static void compraEntradas(){
        int opcion = 0;
        venta = 0;            //SE REINICIA LA VARIABLE POR SI SE HACEN VARIAS COMPRAS EN EL MISMO CICLO

        if (reservados.isEmpty()){
            System.out.println("\nUsted no ha echo ninguna reserva.");  //SI NO HAY RESERVAS REGRESA AL MENU
            return;
        }else{
            venta = precio * reservados.size();                  //SE CALCULA EL VALOR TOTAL DE LA VENTA
            System.out.println("\nUsted a reservado " + reservados.size() + " entrada(s). Total: $" + venta);
            System.out.println("¿Desea proceder con la compra?");
            System.out.println("[1]Si      [2]No");
            do{
                opcion = teclado.nextInt();
                if  (opcion!=1  && opcion!=2){
                    System.out.println("Opcion invalida. Intente de nuevo: ");
                }
            }while(opcion!=1  && opcion!=2);
  
            /*SI SE PROCEDE A LA VENTA
            LA VENTA SE SUMA AL TOTAL
            LAS ENTRADAS SE SUMAN AL TOTAL DE ENTRADAS VENDIDAS
            SE IMPRIME LA BOLETA
            SE LIMPIA LA LISTA DE RESERVADOS POR SI SE QUIERE HACER OTRA VENTA EN EL MISMO CICLO*/
            if(opcion == 1){                                     
                totalVentas += venta;                            
                totalEntradas += reservados.size();
                impresionBoleta(venta);
                reservados.clear(); 
            }else{
                return;   //SI NO SE  REGRESA AL MENU
            }
        }
    }
    
    static void modificarVenta(){
        
        if (reservados.isEmpty()) {     //SI LA LISTA DE RESERVADOS ESTA VACIA SE REGRESA AL MENU
            System.out.println("\nNo hay reservas para modificar.");
            return;
        }
    
        System.out.println("\nReservas actuales:"); //SE IMPRIMEN TODAS LAS RESERVAS ACTUALES
        for (int i = 0; i < reservados.size(); i++) {
            System.out.println((i + 1) + ". " + reservados.get(i));
        }
    
        System.out.println("Seleccione el numero de reserva que desea eliminar: ");
        int seleccionReserva = -1;
    
        while (seleccionReserva < 1 || seleccionReserva > reservados.size()) {    //SE SELECCIONA EL NUMERO DE RESERVA QUE DESEA RETIRAR
            seleccionReserva = teclado.nextInt();
            if(seleccionReserva < 1 || seleccionReserva > reservados.size()){
                System.out.println("Numero invalido. Intente nuevamente: ");
            }
        }
        
        String reservaAnterior = reservados.get(seleccionReserva - 1);    //AUXILIAR QUE ALMACENA LA RESERVA //BREAKPOINT PARA ASEGURAR EL CAMBIO DE TODOS LOS AUXILIARES
        char letraZona = reservaAnterior.charAt(0);                       //AUXILIAR  QUE ALMACENA  LA ZONA DE LA RESERVA
        int numeroAsiento = Integer.parseInt(reservaAnterior.substring(1)); //AUXILIAR QUE TRANSFORMA EL STRING EN EL NUMERO DE ASIENTO Y LO ALMACENA  
        
        int filaAnterior = letraZona - 'A';                                //AUXILIAR QUE CALCULA EL  INDICE DE LA ZONA 
        asientos[filaAnterior][numeroAsiento - 1] = false;                 //SE ACTUALIZA EN EL ARRAY QUE MUESTRA LA DISPONIBILIDAD//BREAKPONT PARA  ASEGURAR EL CAMBIO DE DISPONIBILIDAD DEL ASIENTO
        reservados.remove(seleccionReserva - 1);                           //SE ACTUALIZA LA LISTA //BREAKPOINT PARA ASEGURAR ELIMINAR EL ASIENTO

        
        System.out.println("\nEl asiento " +  letraZona + numeroAsiento +" ha sido eliminado de las reservas."); //SE MUESTRA EL ASIENTO QUE FUE ELIMINADO
    }

    static void impresionBoleta(int venta){
        System.out.println("\n==============================");
        System.out.println("           TEATRO MORO");
        System.out.println("==============================");
        System.out.println(" Entradas compradas: " + reservados.size() );
        System.out.println(" Ubicacion de las entradas: ");
        for (int i = 0; i < reservados.size(); i++) {
            System.out.print(reservados.get(i) + " ,");
        }
        System.out.println(" ");
        System.out.println("Total a pagar: " + venta );
        System.out.println("Gracias por su compra. ¡Disfrute la funcion!");
        System.out.println("==============================\n");
    } //IMPRESION DE LA  BOLETA

    public static void main(String[] args) {
        
        do{
            mostrarMenu();
            seleccion = teclado.nextInt();
            
            switch(seleccion){
                case 1 ->{
                    reservaEntrada();
                }
                case 2 ->{
                    compraEntradas();                    
                }
                    
                case 3 ->{
                    modificarVenta();  
                }                
                case 4 ->{
                    System.out.println("\n=== VENTAS TOTALES ===");
                    System.out.println("Venta total de entradas : $" + totalVentas);
                    System.out.println("Numero de entradas vendidas: " + totalEntradas);   
                }
                case 0 ->{
                    System.out.println("\n Saliendo del sistema. ¡Hasta pronto!");
                }default -> { System.out.println("\n️ Opción inválida. Intente nuevamente.");; 
                }                  
            }
        }while(seleccion != 0);
    }
    
}