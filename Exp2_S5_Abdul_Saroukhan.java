/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.numero.pkg4;
import java.util.Scanner;



public class Exp2_S5_Abdul_Saroukhan {
    static Scanner teclado = new Scanner(System.in);
    static String descuentos [] = new String[65] ;
    static String  ubicaciones [] =  new String[65];
    static Double  totales [] = new Double[65];
    static int vip = 15;
    static int platea = 20;
    static int general = 30;     
    static int entradasVendidas = 0;  
    
            static void mostrarMenu(){
            
            System.out.println("\nMENU");
            System.out.println("1.-Comprar Entrada");
            System.out.println("2.-Promociones");
            System.out.println("3.-Busqueda de Entradas");
            System.out.println("4.-Entradas Vendidas");
            System.out.println("0.-Salir");
            System.out.println("\nSeleccion una Opcion: ");  
        }
        
        static void comprarEntrada(){
            int seleccion, seleccion2;
            String ubicacion = "",tipoDescuento = "";
            double precio  = 0,precioFinal = 0;
            
            if (vip + platea + general == 0) {
                System.out.println("No quedan entradas disponibles.");
                return;
            }

            System.out.println("\nSeleccione ubicacion de la entrada");
            System.out.println("1.- VIP");
            System.out.println("2.- Platea");
            System.out.println("3.- General");
                    
                    
            do{
                seleccion = teclado.nextInt();
                        
                if (seleccion == 1){
                    if (vip  > 0){
                      ubicacion = "VIP";
                      precio = 15000;
                      vip--;
                    }else{
                      System.out.println("No quedan entradas VIP");
                      continue;
                    }
                }else if (seleccion == 2){
                    if(platea > 0){
                       ubicacion = "Platea";
                       precio = 10000;
                       platea--;
                    }else{
                       System.out.println("No quedan entradas en Platea");
                    }
                }else if (seleccion ==3){
                    if(general > 0){
                        ubicacion = "General";
                        precio = 5000;
                        general--;    
                    }else{
                        System.out.println("No quedan entradas en General");
                    }
                }else{
                    System.out.println("\nSeleccion invalida.Intente nuevamente");
                }
            }while (seleccion < 1 || seleccion > 3 );

                    
                    System.out.println("\nSeleccione una opcion");
                    System.out.println("1.- Tercera Edad");
                    System.out.println("2.- Estudiante");
                    System.out.println("3.- General");
                    
                    do{
                        seleccion2 = teclado.nextInt();
                    
                        if (seleccion2 == 1){
                            precioFinal = precio * (1 - 0.15);
                            tipoDescuento = "Tercera Edad";
                        }else if (seleccion2 == 2){
                            precioFinal = precio * (1- 0.10);
                            tipoDescuento = "Estudiante";
                        }else if(seleccion2 == 3){
                            precioFinal = precio;
                            tipoDescuento = "Ninguno"; 
                        }else{
                            System.out.println("Opcion invalida.Intente nuevamente");
                        }      
                    }while(seleccion2 < 1 || seleccion2 > 3 );
                    
                    System.out.println("\nEntrada comprada con exito:");
                    System.out.println("Ubicacion: " + ubicacion);
                    System.out.println("Descuento: " + tipoDescuento);
                    System.out.println("Precio Final: $" + precioFinal);

                    
                    ubicaciones[entradasVendidas] = ubicacion;
                    descuentos[entradasVendidas] = tipoDescuento;
                    totales[entradasVendidas] = precioFinal;
                    entradasVendidas++;
        }
        
        static void  mostrarPromociones(){
            
            System.out.println("Promociones Disponibles");
            System.out.println("1.- Por la Compra de dos Entradas Reciben una Bebida Gratis");
            System.out.println("2.- Por la Compra de tres Entrardas Recibe una Adicional");
            System.out.println("3.- Por la Compra de cinco Entradas Recibe un Descuento de 15% Para su Proxima Compra");
        }
        
        static void mostrarDisponibilidad(){
            int totalEntradas = vip + platea + general;
            
            System.out.println("De un Total de 65 Entradas, Quedan Disponibles " + totalEntradas + " Entradas"  );
            System.out.println("VIP: " + vip);
            System.out.println("Platea: "+ platea);
            System.out.println("General: " + general);
        }
        
        static void mostrarVendidas(){
            if(entradasVendidas == 0){
                System.out.println("\nAun no se han vendido entradas");
                return;
            }
            System.out.println("Entradas Vendidas:");
            for(int i = 0; i < entradasVendidas; i++ ){
                System.out.println("Entrada #" + (i+1));
                System.out.println("Ubicacion: " + ubicaciones[i]);
                System.out.println("Descuento: " + descuentos[i]);
                System.out.println("Precio Final: "  + totales[i]);
                System.out.println("---------------------------");
                    }
            }
    
    
    
    
    
    
    public static void main(String[] args) {
        
        int opcion;
   

        do{
            mostrarMenu();
            opcion = teclado.nextInt();
        
            switch (opcion){
                case 1 ->{
                    comprarEntrada();
                }

                case 2 ->{
                    mostrarPromociones();
                }
                case 3->{   
                    mostrarDisponibilidad();
                }
                case 4->{
                    mostrarVendidas();
                }
                default-> System.out.println("Seleccion invalida.Intente nuevamente");
          
            }  
        }while (opcion != 0);   
        


    }
    
}
