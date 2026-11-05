package aed.tp2;

class Usuario {
    private int ID;
    private int numeroBonos;
    private double millasDisponibles;
    
    //estas ahora no me queda claro que sean necesarias
    private double millasTotales;
    private double millasCanjeadas;

    // esta clase no genera handles, guarda para vincularse 
    // a otras estructuras
    // este es un handle al heap
    private handleRanking;
    // este es un handle a la listaenlazada
    private handleExclsuivo;  
}
