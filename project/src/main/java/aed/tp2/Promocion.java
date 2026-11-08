package aed.tp2;

class Promocion {
    private String NombrePromo; 
    private double acumulado;
    private double factorP;
    private double tope;

    public Promocion(String NombreP, double acum, double factorp, double top){
        this.NombrePromo = NombreP; 
        this.acumulado = acum;
        this.factorP = factorp;
        this.tope = top;
    }

    public String NombrePromo(){
        return this.NombrePromo;
    }
    public double acumulado(){
        return this.acumulado;
    }
    public double factorP(){
        return this.factorP;
    }
    public double tope(){
        return this.tope;
    }
    
    public void sumarAcumulado(double millas){
        this.acumulado+=millas;
    }
}
