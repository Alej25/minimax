/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package minimax;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.Scanner;

/**
 *
 *  
 */
public class Minimax {
    
    public static int tablero=3; // constante 
    public int Matriz[][];
    int Profundidad =1; 
    boolean  estado; // para ver el cambio de jugador 
    
     
    public Minimax(int [][] Matriz1){ // constructor 
     Matriz = Matriz1;
     estado =false;
     while(true){
          IngresarNumeros(); // valor de numeros por teclado
          // si victoria no tiene ningun ganador  y si no esta vacio el tablero 
         if((esVictoria(Matriz) !=1 && esVictoria(Matriz)!=2) && estado && !EsLLeno(Matriz) ){ //para ver el ganador 
          miniMax(Matriz); 
         }
         printBoard(Matriz);//imprimir el tablero de juego 
        }
    }
    
    public boolean EsLLeno(int m[][]){ // comprobar si aun hay espacio en el tablero
   for(int i=0;i<tablero; i++){
                for(int j=0; j<tablero; j++) {  
                    if(m[j][i] == 0 ){
                        return false; // para el caso en el que este Vacio 
                        }
                    }
            }
   
   return true; // para el caso en el que esta lleno 
    }
    
    public static void main(String[] args) { //funcion principal 
        int Matriz[][];
        Matriz = new int [tablero][tablero];
            for(int i=0;i<tablero; i++){
                for(int j=0; j<tablero; j++) {
                   Matriz [i][j]=0;
                    }
            }
            new Minimax(Matriz);//insta el construcctor  
    }
    
    private void IngresarNumeros (){  // funcion para ingresar  los numeros 
        
        Scanner reader = new Scanner (System.in);
        int i=0, j=0;
        boolean ver = true;
        
        System.out.println("bienvenido a Tic Tac Toe \n primero la fila  \n depues la columna  \n solo se puede introducir 0,1 y 2 ");
        
        do{
         j= reader.nextInt();
         i=reader.nextInt();
              if(i<3&&j<3){
               if(this.Matriz[j][i]==0){ //si la matriz esta vacia asignar
                   this.Matriz[j][i]=1;
                   ver=false;
               }
              }    
                 }while(ver);
        estado =true;//jugada del humano 
    }

    public int esVictoria(int m[][]) { 
        for(int i=0;i<tablero; i++){
                for(int j=0; j<tablero; j++) { 
                    if(j+2<tablero){    //1
                         if(m[j][i]==1 && m[j+1][i]==1 && m[j+2][i]==1){ 
                         return 1;}} // el uno es una victoria para el humano 
                      if(i+2<tablero){ //2 victoria en vertical 
                         if(m[j][i]==1 && m[j][i+1]==1 && m[j][i+2]==1){ 
                         return 1;} }   
                      if(i+2<tablero&& j+2< tablero){ //3 victoria en diagonal principal 
                         if(m[j][i]==1 && m[j+1][i+1]==1 && m[j+2][i+2]==1){ 
                         return 1;}}
                      if(i-3>-1&& j+3< tablero ){ //4 diagonal contraria
                          if(m[j][i]==1 && m[j+1][i-1]==1 && m[j+2][i-2]==1){ 
                         return 1;}}
                      if( i+2< tablero && j-2>-1){ //5 vertical inferior
                         if(m[j][i]==1 && m[j-1][i+1]==1 && m[j-2][i+2]==1){ 
                         return 1;}} 
                      
                      // para el ganar la maquina 
                      if(j+2< tablero){ //1
                          if(m[j][i]==2 && m[j+1][i]==2 && m[j+2][i]==2){
                          return 2;} } 
                      if(i+2< tablero){//2
                          if(m[j][i]==2 && m[j ][i+1]==2 && m[j ][i+2]==2){
                          return 2;} } //3
                      if(i+2< tablero && j+2< tablero){
                          if(m[j][i]==2 && m[j+1 ][i+1]==2 && m[j+1 ][i+2]==2){
                          return 2;} }
                      
                      if(i-2>-1&& j+2< tablero ){//4
                          if(m[j][i]==2 && m[j+1 ][i-1]==2 && m[j+2 ][i-2]==2){
                          return 2;} }
                      if(i+2< tablero && j-2>-1 ){//5
                          if(m[j][i]==2 && m[j-1 ][i+1]==2 && m[j-2 ][i+2]==2){
                          return 2;} }
                    }
                 } 
    return 0;    
    }

    private void miniMax(int [][] m) { 
        
     int mejorfila=-1,MejorColumna=-1; // posiciones de las mejores jugadas del juego 
     int max,maxActual; // vairables temporales
     max =Integer.MIN_VALUE;// creamos con el valor menor posible
      
     for(int i=0;i< tablero; i++){
                for(int j=0; j< tablero; j++) { 
                if(m[j][i]==0){// posicion vacia 
                    int tempfila,tempc; // variable temporables almacenan datos 
                    m[j][i]=2;// matriz ejecuta un movimiento
                    tempfila=i;
                    tempc=j;
                    
                    maxActual=ValorMin(m,0,Integer.MIN_VALUE,Integer.MAX_VALUE);// le voy a pasar la matriz y profundidad de arbol ,tambien  menos infinito y infinito
                     
                    m[tempc][tempfila]=0;// quiero que me regrese el 
            // funcion para buscar la mejor jugada posible  
                    if(max<maxActual){ //preguta max que es valor menor si es mas grande
                max=maxActual; // me garantiza cual de la s2 es mayor porsible 
                mejorfila=tempfila;
                MejorColumna=tempc;
                    }
                  }
                } 
            } // cuando mayor posible
      m[MejorColumna][mejorfila]=2;
      estado = false;
    } 
    
    private int ValorMin(int[][] m, int profundidad, int alfa, int beta) { 
        //condicion de parada par aun gnador
        if(esVictoria(m)==1 || esVictoria(m)==2){
        return Evaluadora(m);
        }
        else if( EsLLeno(m)){
        return Evaluadora(m);
        }
        else if(Profundidad<profundidad){
            return Evaluadora(m);
        }
        //llam al tablero y pregunta las posiciones en cero
        for(int i=0;i< tablero; i++){
                for(int j=0; j<tablero; j++) { 
                if(m[j][i]==0){
                    int tempfila,tempc; // crea variable temporables almacenan datos 
                    m[j][i]=1;// se establece la jugada del humano 
                    tempfila=i;
                    tempc=j;
                    
                    // busca el valor minimo posible y lo almacena en beta
                    //compara cada uno de los valores con  beta 
                     // valor mayor
                    beta=min(beta,ValorMax(m, profundidad+1,alfa,beta));// le voy a pasar la matriz y profundidad de arbol ,tambien  menos infinito y infinito
                     
                    m[tempc][tempfila]=0;
                    if(alfa>=beta ){ //preguta a alfa sea mayor que beta 
                     return alfa;
                     }
                  }
                }
     
            } 
        
        return beta;
    }
 private int ValorMax(int[][] m, int profundidad , int alfa, int beta) {  
  //condiciones de parada
     if(esVictoria(m)==1 ||  esVictoria(m)==2){
        return Evaluadora(m);
        }
        else if(EsLLeno(m)){
        return Evaluadora(m);
        }
        else{
         //llam al tablero,pregunta las posiciones en cero
        for(int i=0;i< tablero; i++){
                for(int j=0; j<tablero; j++) { 
                if(m[j][i]==0){
                    int tempfila,tempc; // crea variable temporables almacenan datos 
                    m[j][i]=2;// matriz ejecuta un movimiento
                    tempfila=i;
                    tempc=j;
                    
                    // busca el valor minimo posible y lo almacena en beta
                    //compara cada uno de los valores de beta
                     // valor mayor
                    alfa=max(alfa,ValorMin(m, profundidad+1,alfa,beta));// le voy a pasar la matriz y profundidad de arbol ,tambien  menos infinito y infinito
                     
                    m[tempc][tempfila]=0;
                    if(alfa>=beta ){ //preguta max que es valor menor si es mas grande
                     return beta;
                     }
                  }
                }
     
            } 
        
        return alfa;
        }
     
   }
  
    private void printBoard(int m[][]) {
        
                System.out.println("");
                System.out.println("");
                
                if(esVictoria(m)==1){
                 System.out.println("Gano Humano");  
                 limpiarConsola(); 
                 System.out.println("Felicidades Humano \n\n\nFin del juego..................");  
                 System.exit(0);
                }
                if(esVictoria(m)==2){
                 System.out.println("Gano Maquina");
                  limpiarConsola(); 
                 System.out.println("Felicidades Humano \n\n\nFin del juego.................."); 
                 System.exit(0);
                }
                System.out.println("");
                
                 // recorrido para ingresar los valores cuando sean un espacio 
             for(int i=0;i< tablero; i++){
                for(int j=0; j< tablero; j++) { 
                    System.out.print(m[j][i]+"\t");
                    
                    }
                System.out.println("");
            }    
    }

    private int Evaluadora(int m [][]) { // condicion de parada
        int costo =0;
        // hacemos la resta evaluando la matriz cuando hacemos la resta  
        costo= Costo(m,2)-Costo(m,1); // humano retorna valores negativos  
      return costo;
    }

    private int Costo(int m[][], int jugador ) {// rsta de la matriz cuando juega el usuario
     int value =0;
     
     for(int i =0;i<tablero ; i++){
         for(int j =0; j<tablero;j++){
            if (j+2<tablero){
                if(m[j][i]==jugador && m[j+1 ][i] == jugador && m[j+2][i]==jugador) {
                 return 500; // lo tome como mi maximo valor posible
                }
            }
            if (i+2< tablero){
                if(m[j][i]==jugador && m[j ][i+1] == jugador && m[j ][i+2]==jugador) {
                 return 500;
                }
            }
            if (i+2<tablero &&j+2< tablero){
                if(m[j][i]==jugador && m[j+1 ][i+1] == jugador && m[j+2][i+2]==jugador) {
                 return 500;
                }
            }
            if (i-2>-1 && j+2< tablero){
                if(m[j][i]==jugador && m[j+1 ][i-1] == jugador && m[j+2][i-2]==jugador) {
                 return 500;
                }
            }
            if (i+2< tablero && j-2>-1) {
                if(m[j][i]==jugador && m[j-1 ][i+1] == jugador && m[j-2][i+2]==jugador) {
                 return 500;
                }
            // para otro valor     
                
            }  if (j+1< tablero){
                if(m[j][i]==jugador && m[j+1 ][i] == jugador  ) {
                 return 300;
                }
            }
            if (i+1< tablero){
                if(m[j][i]==jugador && m[j ][i+1] == jugador  ) {
                 return 300;
                }
            }
            if (i+1<tablero && j+1< tablero    ){
                if(m[j][i]==jugador && m[j+1 ][i+1] == jugador  ) {
                 return 300;
                }
            }
            if (i-1>-1 && j+1< tablero){
                if(m[j][i]==jugador && m[j+1 ][i-1] == jugador  ) {
                 return 300;
                }
            }
            
            if (i+1< tablero && j-1>-1 ){
                if(m[j][i]==jugador && m[j-1 ][i+1] == jugador  ) {
                 return 300;
                }
            }
            
         }
       }
     return value;
    }
    public static void limpiarConsola() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  
}
