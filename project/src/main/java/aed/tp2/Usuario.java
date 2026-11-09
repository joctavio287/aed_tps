package aed.tp2;

class Usuario implements Comparable<Usuario>{
    private int ID;
    private double millasDisponibles;
    private double millasCanjeadas;
    private double millasTotales;
    
    private int indiceHandleTransferidor;
    
    // imposibilidad de estar en transferidoresExclusivos ya sea porque estuve
    // o porque transferí a dos o mas que estaban en esta condicion
    private boolean fuisteTransferidor;

    public Usuario(int ID){
        this.ID = ID;
        this.millasCanjeadas = 0;
        this.millasDisponibles = 0;
        this.millasTotales = 0;
        this.indiceHandleTransferidor = -1;
        this.fuisteTransferidor = false;
    }
    public boolean fuisteTransferidor(){
        return this.fuisteTransferidor;
    }
    public void modificarTranferidor(){
        this.fuisteTransferidor = !this.fuisteTransferidor();
    }
    public int indiceParTransferidor(){
        return this.indiceHandleTransferidor;
    }
    public void definirIndiceParTransferidor(int ind){
        this.indiceHandleTransferidor = ind;
        return;
    }
    public double MillasDisponibles(){
        return this.millasDisponibles;
    }
    public double MillasCanjeadas(){
        return this.millasCanjeadas;
    }
    public double MillasTotales(){
        return this.millasTotales;
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