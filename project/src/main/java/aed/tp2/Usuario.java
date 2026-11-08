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