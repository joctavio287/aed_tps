package aed.tp2;

class Usuario implements Comparable<Usuario>{
    private int ID;
    private int numeroBonos;
    private double millasDisponibles;
    
    //estas ahora no me queda claro que sean necesarias
    private double millasTotales;
    private double millasCanjeadas;

    // private HandleLE handleExclusivo;

    @Override
    public int compareTo(Usuario otroUsuario){
        return (int) this.millasDisponibles - (int) otroUsuario.millasDisponibles;
    }

}
