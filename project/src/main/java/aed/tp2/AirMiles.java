package aed.tp2;

public class AirMiles implements SistemaAirMiles {

    private ABB<Integer, Handle<Usuario>> usuarios; 
    private MaxHeap<Usuario> ranking; 
    
    private Usuario acumulador;
    private Usuario canjeador;
    
    private Trie<String, Promocion> _promociones;
    
    
    // //TODO: es la pregunta de Daniela, si no necesitamos el comaprable esto estaría bien
    // private ListaEnlazada<int[]> _paresTransferidoresExclusivos; 

    private void actualizarAcumulador(Usuario usuario_competidor){
        if (acumulador==null||usuario_competidor.MillasTotales()>=acumulador.MillasTotales()){
            this.acumulador = usuario_competidor;
        }
    }
    private void actualizarCanjeador(Usuario usuario_competidor){
        if (canjeador==null||usuario_competidor.MillasCanjeadas()>=canjeador.MillasCanjeadas()){
            this.canjeador = usuario_competidor;
        }
    }

    public AirMiles() {
        this._promociones = new Trie<String, Promocion>();
        iniciarPromocion("NoPromo", 1.0);
        this.ranking = new MaxHeap<Usuario>();
        this.usuarios = new ABB<Integer, Handle<Usuario>>();
        
        // Los metodos de consulta ahora van a devolver 0 hasta que se llene de verdad
        this.canjeador = new Usuario(0);
        this.acumulador = new Usuario(0);
    }
    private Handle<Usuario> registrarUsuarioConOutput(int id) {
        Usuario usuario = new Usuario(id);
        Handle<Usuario> handle_usuario = this.ranking.insertar(usuario);
        usuarios.insertar(id, handle_usuario);
        actualizarAcumulador(usuario);
        return handle_usuario;
    }
    public void registrarUsuario(int id) {
        registrarUsuarioConOutput(id);
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        Handle<Usuario> referidor = this.usuarios.obtener(ref);
        Usuario referidor_modificado = referidor.valor();
        referidor_modificado.sumarMillasDisponibles(500);
        referidor_modificado.sumarMillasTotales(500);
        actualizarAcumulador(referidor_modificado);
        
        Handle<Usuario> usuario = registrarUsuarioConOutput(id);
        Usuario usuario_modificado = usuario.valor();
        usuario_modificado.sumarMillasDisponibles(500);
        usuario_modificado.sumarMillasTotales(500);
        
        referidor.modificar(referidor_modificado);
        usuario.modificar(usuario_modificado);
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
        return this.acumulador.ID();
    }

    public int elMasCanjeador() {
        return this.canjeador.ID();
    }

    public int[][] paresTransferidoresExclusivos() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
