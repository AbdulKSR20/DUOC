/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;
import java.util.Scanner;


public class Exp2_S4_Abdul_Saroukhan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        
        //Variables
        String Zona;
        int entrada = 7000;
        int edad;
        int asiento;
        int opcion;
        int fila;
        double descuento = 0;
        double precioFinal;
        
        //Matriz
        boolean [][] asientos = new boolean [3][3];
        String [] filas = {"A","B","C"};
        
        
        //MENU PRINCIPAL
        for (int i = 0; i != 2;) {
            System.out.println("Menu principal");
            System.out.println("1.- Comprar entrada");
            System.out.println("2.- Salir");
            
            System.out.println("\nSeleccione una opcion");
            opcion = teclado.nextInt();
            teclado.nextLine();
           
            
            // 
            switch (opcion){
                case 1 -> {
                    //MUESTRA DE ACIENTOS DISPONIBLES
                    System.out.println("\n--- Teatro Moro ---");                                       
                    for (int n = 0; n < 3;n++){
                        System.out.print("Zona " + filas[n] + " " );                        
                        for (int j = 0; j <  3;j++){
                            System.out.print("[" + (asientos[n][j] ? "*" : (j+1)) + "]");
                        }
                        System.out.println("");
                    }
                    
                    //SELECCION DE ZONA
                    System.out.println("\nSeleccione una fila (A, B o C)");
                    Zona = teclado.nextLine().toUpperCase();
                   
                    //VALIDACION DE ZONA Y ASIGNACION EN LA MATRIZ
                    if (Zona.equals("A")){
                        fila = 0;
                    }else if (Zona.equals("B")){
                        fila  = 1;
                    }else if (Zona.equals("C")){
                        fila = 2;
                    }else{
                        System.out.println("\nZona invalida. Intente nuevamente");
                        continue;
                    }
                    
                    //SELECCION DE ASIENTO
                    System.out.println("Seleccion un asiento 1,2 o 3 ");
                    asiento = teclado.nextInt();
                    
                    //VALIDACION DE ASIENTO
                    if (asiento < 1 || asiento > 3 ){
                        System.out.println("\nAsiento invalido. Intente nuevamente.");
                        continue;
                    }
                    
                    //CONDICION SI LA UBICACION EN LA MATRIZ ES TRUE EL ASIENTO YA ESTA OCUPADO
                    if (asientos[fila][asiento - 1]){
                        System.out.println("\nEl asiento "+ Zona +  asiento +" ya esta ocupado\n");
                        continue;
                    }
                    
                    //ACTUALIZACION DEL ESTADO DEL ASIENTO
                    asientos[fila][asiento - 1] = true;
                    
                    //VALIDACION DE EDAD VALIDA
                    do{
                        System.out.println("Ingrese su edad : ");
                    edad = teclado.nextInt();
                    if (edad < 0){
                        System.out.println("Edad invalida.Debe ser mayor que 0.");
                    }
                    }while (edad < 0);
                    
                    //DESCUENTOS SEGUN LA EDAD
                    if (edad >= 60) {
                        descuento = 0.15;
                    }else if (edad < 18 ){
                        descuento = 0.10;
                    }
                    
                    // APLICACION DE DESCUENTOS Y RESUMEN DE COMPRA
                    precioFinal = entrada * (1 - descuento);
                    System.out.println("\nEntrada comprada exitosamente");
                    System.out.println("Su asiento es el :" + Zona + asiento );
                    System.out.println("Precio: "+entrada+"CLP");
                    System.out.println("Descuento aplicado: "+(descuento * 100) + "%");
                    System.out.println("Precio final: "+  precioFinal + "CLP");
                    
                    //PREGUNTA SI DESEA REALIZAR OTRA COMPRA
                    System.out.println("\n¿Desea realizar otra compra?  ");
                    System.out.println("1.-SI               2.-NO");
                    i = teclado.nextInt();
                    if (i == 2){
                        System.out.println("\nGracias por usar el sistema");
                        return;
                    }

                }
                case 2 -> {
                    System.out.println("\nGracias por usar el sistema");
                    return;
                } 
                default -> System.out.println("\nOpcion invalida. Intente  de nuevo");
            } 
        }
        teclado.close();
    }
    
}
