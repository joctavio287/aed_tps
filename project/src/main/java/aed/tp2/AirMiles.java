package aed.tp2;

import static java.lang.Math.min;

public class AirMiles implements SistemaAirMiles {

    private ABB<Integer, Handle<Usuario>> usuarios; 
    private MaxHeap<Usuario> ranking; 
    
    private Usuario acumulador;
    private Usuario canjeador;
    
    private Trie<String, Promocion> promociones;
    
    
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
        this.promociones = new Trie<String, Promocion>();
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
        
        Handle<Usuario> handle_usuario = registrarUsuarioConOutput(id);
        Usuario usuario_modificado = handle_usuario.valor();
        usuario_modificado.sumarMillasDisponibles(500);
        usuario_modificado.sumarMillasTotales(500);
        
        referidor.modificar(referidor_modificado);
        handle_usuario.modificar(usuario_modificado);
    }
    
    public String consultarCategoria(int id) {
       double millasTotales = this.usuarios.obtener(id).valor().MillasTotales();
       if (millasTotales>=1000000){
        return "Platino";
       }
       else if (millasTotales>=50000){
        return "Oro";
       }
       else if (millasTotales>10000){
        return "Plata";
       }
       else{
        return "Bronce";
       }
    }

    private double factorCategoria(int id){
        String categoria = consultarCategoria(id);
        if (categoria=="Platino"){
            return 0.0;
        }
        else if (categoria=="Oro"){
            return 2.0;
        }
        else if (categoria=="Plata"){
            return 1.5;
        }
        else{
            return 1.0;
        }

    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        Handle<Usuario> handle_usuario = this.usuarios.obtener(id);
        Usuario usuario_modificado = handle_usuario.valor();

        Promocion promocion = this.promociones.obtener(nombrePromo);
        double factorP = promocion.factorP();
        double ganancia = distancia * factorCategoria(id) * factorP;

        if (promocion.tope()!=0){
            // Verifica si no aplica. En el caso de que se pase se aplica, 
            // pero después se borra para siempre
            if (promocion.acumulado()+ganancia > promocion.tope()){
                finalizarPromocion(nombrePromo);
            }
            else{
                promocion.sumarAcumulado(ganancia);
            }
        }
        usuario_modificado.sumarMillasDisponibles(ganancia);
        usuario_modificado.sumarMillasTotales(ganancia);
        handle_usuario.modificar(usuario_modificado);
        actualizarAcumulador(usuario_modificado);
        
    }

    public void canjearMillas(int id, double cantidad) {
        Handle<Usuario> handle_usuario = this.usuarios.obtener(id);
        Usuario usuario_modificado = handle_usuario.valor();
        if (usuario_modificado.MillasDisponibles()>=cantidad){
            usuario_modificado.sumarMillasDisponibles(-cantidad);
            usuario_modificado.sumarMillasCanjeadas(cantidad);
            handle_usuario.modificar(usuario_modificado);
            actualizarCanjeador(usuario_modificado);
        }
        return;
    }

    public double consultarSaldo(int id) {
        return this.usuarios.obtener(id).valor().MillasDisponibles();
    }

    public int[] rankingMillas(int n) {
        Usuario[] contenedor_usuarios = new Usuario[n];
        int[] res = new int[n];
        int m = min(n, ranking.size());
        
        for (int i=0; i<m; i++){
            Usuario usuario = ranking.eliminarMaximo().valor();
            contenedor_usuarios[i] = usuario;
            res[i] = usuario.ID();
        }
        for (int i=0; i<m; i++){
            ranking.insertar(contenedor_usuarios[i]);
        }
        return res;
    }
    
    public int elMasAcumulador() {
        return this.acumulador.ID();
    }

    public int elMasCanjeador() {
        return this.canjeador.ID();
    }

    // TODO: nos falta hacer promociones (Trie) y la lista de transferidores Exclusivos
    public void transferirMillas(int origen, int destino, double cantidad) {
        // actualiza parestransferidores exclusivos
        //   si origen o destino tiene un handle prexsitente sin el par destino u origen correspondiente
        //   se elimina el par de la lista vía el handle
        //   si no tiene un handle y su par tampoco, lo crea y lo guarda en ambos.
        
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

    public int[][] paresTransferidoresExclusivos() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
