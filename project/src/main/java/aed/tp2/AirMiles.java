package aed.tp2;
import static java.lang.Math.min;
import java.util.ArrayList;

public class AirMiles implements SistemaAirMiles {

    private ABB<Integer, Handle<Usuario>> usuarios; 
    private MaxHeap<Usuario> ranking; 
    
    private Usuario acumulador;
    private Usuario canjeador;
    
    private Trie<String, Promocion> promociones;
    private ListaEnlazada<ParTransferidor> paresTransferidoresExclusivos; 
    private ArrayList<Handle<ParTransferidor>> handlesParesTransferidores;

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
        this.paresTransferidoresExclusivos = new ListaEnlazada<ParTransferidor>();

        
        // Los metodos de consulta ahora van a devolver 0 hasta que se llene de verdad
        this.canjeador = new Usuario(0);
        this.acumulador = new Usuario(0);
    }

    private Handle<Usuario> registrarUsuarioConOutput(int id) {
        Usuario usuario = new Usuario(id);
        Handle<Usuario> handle_usuario = this.ranking.insertar(usuario);
        usuarios.insertar(id, handle_usuario);
        actualizarAcumulador(usuario);
        actualizarCanjeador(usuario);
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
        //TODO: nos dimos cuenta que no actualizamos el ABB, habría que actualizarlo
        // no llegamos a ver bien cómo hacerlo.
        // Es la misma historia en todos los lugares donde se modifican los handles del heap
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

    public void transferirMillas(int origen, int destino, double cantidad) {
        Handle<Usuario> handle_destino = this.usuarios.obtener(destino);
        Handle<Usuario> handle_origen = this.usuarios.obtener(origen);
        Usuario destino_modificado = handle_destino.valor();
        Usuario origen_modificado = handle_origen.valor();

        // Acorde a la aclaración del TP también se modifican las Canjeadas para el que pierde
        destino_modificado.sumarMillasDisponibles(cantidad);
        origen_modificado.sumarMillasDisponibles(-cantidad);
        origen_modificado.sumarMillasCanjeadas(cantidad);
        
        int indice_destino = destino_modificado.indiceParTransferidor();
        int indice_origen = origen_modificado.indiceParTransferidor();

        boolean inhabilitado_para_exclusividad  = (
            origen_modificado.fuisteTransferidor() || destino_modificado.fuisteTransferidor()
        );
        
        if (inhabilitado_para_exclusividad){
            if (destino_modificado.indiceParTransferidor()!=-1){
                handlesParesTransferidores.get(indice_destino).eliminar();
                destino_modificado.definirIndiceParTransferidor(-1);
            }
            else if (origen_modificado.indiceParTransferidor()!=-1){
                handlesParesTransferidores.get(indice_origen).eliminar();
                origen_modificado.definirIndiceParTransferidor(-1);
            }
            origen_modificado.modificarTranferidor();
            destino_modificado.modificarTranferidor();
            handle_destino.modificar(destino_modificado);
            handle_origen.modificar(origen_modificado);
            return;
        }
        if (indice_destino!=indice_origen){
            if (indice_destino==-1){
                handlesParesTransferidores.get(indice_origen).eliminar();
            }
            else if (indice_origen==-1){
                handlesParesTransferidores.get(indice_destino).eliminar();
            }
            else{
                handlesParesTransferidores.get(indice_destino).eliminar();
                handlesParesTransferidores.get(indice_origen).eliminar();
            }
            destino_modificado.definirIndiceParTransferidor(-1);
            origen_modificado.definirIndiceParTransferidor(-1);
            origen_modificado.modificarTranferidor();
            destino_modificado.modificarTranferidor();
        }
        else if (indice_destino==indice_origen&&indice_destino==-1){
                // Agregamos un par transferidor a la lista enlazada y al arreglo de handlees
                Handle<ParTransferidor> info_transferidores = this.paresTransferidoresExclusivos.agregarAtras(
                    new ParTransferidor(origen_modificado, destino_modificado)
                );
                handlesParesTransferidores.add(info_transferidores);
                int indice_handle = handlesParesTransferidores.size()-1;
                destino_modificado.definirIndiceParTransferidor(indice_handle);
                origen_modificado.definirIndiceParTransferidor(indice_handle);
            }
        handle_destino.modificar(destino_modificado);
        handle_origen.modificar(origen_modificado);
        return;
    }
    
    public int[][] paresTransferidoresExclusivos() {
        int n = this.paresTransferidoresExclusivos.longitud();
        int[][] res = new int[n][2];
        
        for (int i=0; i<n;i++) {
            int[] arreglo = this.paresTransferidoresExclusivos.obtener(i).toArreglo();
            res[i] = arreglo;
        }
        return res;
    }

    public void iniciarPromocion(String nombre, double factor) {
        Promocion promoNueva = new Promocion(nombre, 0, factor, 0);
        promociones.insertar(nombre, promoNueva);
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        Promocion promoNueva = new Promocion(nombre, 0, factor, tope);
        promociones.insertar(nombre, promoNueva);
    }

    public void finalizarPromocion(String nombre) {
        promociones.insertar(
            nombre, new Promocion(nombre, 0, 1.0, 0)
        );
    }
}
