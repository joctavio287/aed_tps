package aed.tp2;

public class AirMiles implements SistemaAirMiles {

    private ABB<Integer, Handle<Usuario>> usuarios; 
    
    private int _elMasAcumulador;
    private int _elMasCanjeador;
    
    private Trie<String, Promocion> _promociones;
    
    // // TODO: qué come este maxheap
    // private MaxHeap<Integer> _rankingMillasDisponibles; 
    
    // //TODO: es la pregunta de Daniela, si no necesitamos el comaprable esto estaría bien
    // private ListaEnlazada<int[]> _paresTransferidoresExclusivos; 


    public AirMiles() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void registrarUsuario(int id) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void canjearMillas(int id, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        // actualiza parestransferidores exclusivos
        //   si origen o destino tiene un handle prexsitente sin el par destino u origen correspondiente
        //   se elimina el par de la lista vía el handle
        //   si no tiene un handle y su par tampoco, lo crea y lo guarda en ambos.
        
        throw new UnsupportedOperationException("Sin implementar");
    }

    public double consultarSaldo(int id) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public String consultarCategoria(int id) {
       throw new UnsupportedOperationException("Sin implementar");
    }

    public int[] rankingMillas(int n) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocion(String nombre, double factor) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void finalizarPromocion(String nombre) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasAcumulador() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasCanjeador() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int[][] paresTransferidoresExclusivos() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
