package aed.tp2;

class ParTransferidor implements Comparable<ParTransferidor> {
    private Usuario usuario1; 
    private Usuario usuario2; 

    public ParTransferidor(Usuario us1, Usuario us2){
        this.usuario1 = us1; 
        this.usuario2 = us2; 
    }
    public Usuario[] par(){
        Usuario[] par = new Usuario[2];
        par[0] = this.usuario1;
        par[1] = this.usuario2;
        return par;
    }

    @Override
    public int compareTo(ParTransferidor par){
        boolean comparacionA = (par.usuario1.equals(this.usuario1)&&par.usuario2.equals(this.usuario2));
        boolean comparacionB = (par.usuario2.equals(this.usuario1)&&par.usuario1.equals(this.usuario2));
        if (comparacionA||comparacionB){
            return 0;
        }
        else{
            return 1;
        }
    }

    public boolean pertenece(Usuario u){
        return (u.equals(this.usuario1)||u.equals(this.usuario2));
    }

    public int[] toArreglo(){
        int[] arreglo = new int[2];
        arreglo[0] = this.usuario1.ID();
        arreglo[1] = this.usuario2.ID();
        return arreglo;
    }

}
