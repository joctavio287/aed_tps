package aed.tp2;

class Usuario implements Comparable<Usuario>{
    private int ID;
    private int numeroBonos;
    private double millasDisponibles;
    private double millasCanjeadas;
    private double millasTotales;

    public Usuario(int ID, int numeroBonos){
        this.ID = ID;
        this.numeroBonos = numeroBonos;
        this.millasCanjeadas = 0;
        this.millasDisponibles = 0;
        this.millasTotales = 0;
    }
    public double MillasDisponible(){
        return this.millasDisponibles;
    }
    public double MillasCanjeadas(){
        return this.millasCanjeadas;
    }
    public double MillasTotales(){
        return this.millasTotales;
    }
    public int numeroBonos(){
        return this.numeroBonos;
    }
    public int ID(){
        return this.ID;
    }

    public void sumarMillasDisponibles(double millas){
        this.millasDisponibles+=millas;
    }
    public void sumarMillasCanjeadas(double millas){
        this.millasCanjeadas+=millas;
    }
    public void sumarMillasTotales(double millas){
        this.millasTotales+=millas;
    }
    public void sumarUnBono(){
        this.numeroBonos ++;
    }

    @Override
    public int compareTo(Usuario otroUsuario){
        if (this.millasDisponibles>otroUsuario.millasDisponibles){
            return 1;
        }
        else if (this.millasDisponibles==otroUsuario.millasDisponibles){
            return 0;
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object otroUsuario){
		boolean otroEsNull = (otroUsuario == null);
        if (otroEsNull){
            return false;
        }
        boolean claseDistinta = otroUsuario.getClass() != this.getClass();
        if (claseDistinta) {
			return false;
		}
        Usuario UsuarioComparacion = (Usuario) otroUsuario;
        return this.ID == UsuarioComparacion.ID;
    }

}