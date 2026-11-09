package aed.tp2;

public class ListaEnlazada <T extends Comparable<T>>{
  
    private int _longitud;
    private Nodo _primero;
    private Nodo _ultimo;

    private class Nodo {
        T dato;
        Nodo prev;
        Nodo sig;

        Nodo(T v) {
            dato = v;
            prev = null;
            sig = null;
        }
    }

    private class HandleLE implements Handle<T>{
        private Nodo puntero;

        private HandleLE(Nodo n){
            this.puntero = n;
        }

        public T valor(){
            return this.puntero.dato;
        }

        public void eliminar(){
            ListaEnlazada.this.eliminarNodo(this.puntero);
            this.puntero=null;
        }

        public void modificar(T valor_a_modificar){ 
            this.puntero.dato = valor_a_modificar;
        }

        public boolean equals(Handle<T> handle_a_comparar){
            int comparacion = this.valor().compareTo(handle_a_comparar.valor());
            if (comparacion==0){
                return true;
            }
            else{
                return false;
            }
        }

        public String toString(){
            return this.valor().toString();
        }
    }

    public ListaEnlazada() {
        _longitud = 0;
        _primero = null;
        _ultimo = null;
    }

    public int longitud() {
        return _longitud;
    }

    public boolean pertenece(T valor){
        boolean pertenece = false;
        Nodo actual = _primero;
        while(actual != null && !pertenece){
            pertenece = actual.dato.compareTo(valor) == 0;
            actual = actual.sig;
        }
        return pertenece;
    }

    public Handle<T> agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);

        if (_longitud == 0) {
            _ultimo = nuevo;
        } else {
            nuevo.sig = _primero;
            _primero.prev = nuevo;
        }
        _primero = nuevo;
        _longitud++;

        return new HandleLE(nuevo);
    }

    public Handle<T> agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);

        if (_longitud == 0) {
            _primero = nuevo;
        } else {
            nuevo.prev = _ultimo;
            _ultimo.sig = nuevo;
        }
        _ultimo = nuevo;
        _longitud++;
        return new HandleLE(nuevo);
    }

    public Handle<T> insertarEn(int i, T elem){

        Nodo nodoNuevo = new Nodo(elem);
        Nodo nodoIterador = _primero;

        int j = 0;
        // al final del loop es el i-esimo nodo
        while(j < _longitud && j<i){
            nodoIterador = nodoIterador.sig;
            j++;
        }
        // el que antes era i-ésimo es i-ésimo + 1 y nuevo pasa a ser i-ésimo
        nodoNuevo.sig = nodoIterador;
        
        // actualizamos los vecinos del nuevo elemento y las referencias viejas
        if(nodoIterador != null){
            nodoNuevo.prev  = nodoIterador.prev;
            nodoIterador.prev = nodoNuevo;
            
        }else{
            nodoNuevo.prev = _ultimo;
            _ultimo = nodoNuevo;
        }
        
        // actualiar siguiente del vecino previo
        if(nodoNuevo.prev != null){
            nodoNuevo.prev.sig = nodoNuevo;
        }else{
            _primero = nodoNuevo;
        }

        _longitud++;
        return new HandleLE(nodoNuevo);   
    }

    public T obtener(int i) {
        Nodo actual = _primero;
        for (int j = 0; j < i; j++) {
            actual = actual.sig;
        }
        return actual.dato;
    }

    public T obtenerUltimo() {
        return _ultimo.dato;
    }

    public T obtenerPrimero() {
        return _primero.dato;
    }
    
    public void eliminar(int i) {
        Nodo actual = _primero;
        for (int j = 0; j < i; j++) {
            actual = actual.sig;
        }
        if (i == 0) {
            _primero = actual.sig;
        } else {
            actual.prev.sig = actual.sig;
        }
        if (i == _longitud - 1) {
            _ultimo = actual.prev;
        } else {
            actual.sig.prev = actual.prev;
        }
        _longitud--;
    }

    private void eliminarNodo(Nodo nodo) {
        if (nodo == _primero) {
            _primero = nodo.sig;
        } else {
            nodo.prev.sig = nodo.sig;
        }
        if (nodo == _ultimo) {
            _ultimo = nodo.prev;
        } else {
            nodo.sig.prev = nodo.prev;
        }
        _longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = _primero;
        for (int j = 0; j < indice; j++) {
            actual = actual.sig;
        }
        actual.dato = elem;
    }

    private ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        for (int i = 0; i < _longitud; i++) {
            copia.agregarAtras(obtener(i));
        }
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        ListaEnlazada<T> copia = lista.copiar();
        _longitud = copia._longitud;
        _primero = copia._primero;
        _ultimo = copia._ultimo;
    }

    @Override
    public String toString(){
        String str = "[";
        Nodo actual = _primero;
        for (int i = 0; i < _longitud; i++) {
            str += actual.dato;
            if (i < _longitud - 1) {
                str += ", ";
            }
            actual = actual.sig;
        }
        str += "]";
        return str;
    }

    public class ListaIterador implements Iterador<T>{
        private Nodo actual = _primero;
        private int indiceProx = 0;

        public boolean haySiguiente() {
            return indiceProx < _longitud;
        }
        
        public boolean hayAnterior(){
            return indiceProx > 0;
        }

        public T siguiente() {
            T data = actual.dato;
            actual = actual.sig;
            indiceProx++;
            return data;
        }

        public T anterior(){
            if (actual == null) {
                actual = _ultimo;
            } else {
                actual = actual.prev;
            }
            indiceProx--;
            return actual.dato;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }
}
