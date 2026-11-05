package aed.tp2;

public class ABB<K extends Comparable<K>, V> implements Diccionario<K, V> { 
    private int _cardinal;
    private Nodo _raiz;

    private class Nodo {
        K clave;
        V valor;
        
        Nodo padre;
        Nodo izq;
        Nodo der;

        Nodo(K cl, V val) {
            clave = cl;
            valor = val;
            
            padre = null;
            izq = null;
            der = null;
        }
    }
    
    public ABB() {
        _raiz = null;
        _cardinal = 0;
    }

    public int tamaño() {
        return this._cardinal;
    }
    
    private Nodo buscarNodo(K clave_buscada){
        Nodo resultNodo = _raiz;
        while (resultNodo != null) {
            int comparacion = clave_buscada.compareTo(resultNodo.clave);
            
            if (comparacion==0){
                return resultNodo;
            }
            else if (comparacion>0){
                if (resultNodo.der==null){
                    return resultNodo;
                }
                else{
                    resultNodo = resultNodo.der;
                }
            }
            else {
                if (resultNodo.izq==null){
                    return resultNodo;
                }
                else{
                    resultNodo = resultNodo.izq;
                }
            }
        }
        return null;
    }

    private Nodo minimoNodo(Nodo n){
        Nodo resultNodo = n; 
        while (resultNodo != null && resultNodo.izq != null) {
            resultNodo = resultNodo.izq;
        }
        return resultNodo;
    }
    
    private Nodo sucesor(Nodo n){
        if (n.der != null) {
            return minimoNodo(n.der);
        }
        else {
            Nodo resultNodo = n.padre;
            Nodo hijo = n;
            while (resultNodo != null && hijo == resultNodo.der) {
                hijo = resultNodo;
                resultNodo = resultNodo.padre;
            }
            return resultNodo;
        }
    }

    private void reemplazar(Nodo nodo){ 
        // Para nodos con 0 o 1 hijos, quien ocupa el lugar de nodo:
        // si tiene izquierdo, ese, sino el derecho (puede ser null)
        Nodo hijo = (nodo.izq != null) ? nodo.izq : nodo.der;
        Nodo padre = nodo.padre;
        
        // Si nodo es raíz, entonces no tiene padre
        if (padre == null) { 
            _raiz = hijo;
        } 
        else{
            // nodo es hijo izquierdo de su padre
            if (nodo == padre.izq) {
                padre.izq = hijo;
            // nodo es hijo derecho de su padre
            } else {
                padre.der = hijo;
            }
        } 
        // el otro lado del enlace, si hijo existe, su padre ahora es el padre de nodo
        if (hijo != null) {
            hijo.padre = padre;
        }
    }
    
    public void insertar(K clave_insertada, V valor_insertado){
        
        if (this._raiz == null){
            Nodo nuevoNodo = new Nodo(clave_insertada, valor_insertado);
            this._raiz = nuevoNodo;
            this._cardinal++;
            return;
        }

        Nodo nodoExistenteOPrevio = buscarNodo(clave_insertada);

        int comparacion = clave_insertada.compareTo(nodoExistenteOPrevio.clave);
        if (comparacion==0){
            // TODO: deberíamos modificar el valor por el asignado o dejarlo como esta?
            return;
        }
        else{
            Nodo nuevoNodo = new Nodo(clave_insertada, valor_insertado);
            nuevoNodo.padre = nodoExistenteOPrevio;
            this._cardinal++;
            if (comparacion>0){
                nodoExistenteOPrevio.der = nuevoNodo;
            }
            else {
                nodoExistenteOPrevio.izq = nuevoNodo;
            }
        }
    }

    public boolean pertenece(K clave_buscada){
        Nodo nodoCandidato = buscarNodo(clave_buscada);
        return nodoCandidato!=null && clave_buscada.compareTo(nodoCandidato.clave)==0;
    }

    public V obtener(K clave_buscada){
        //no hay q hacer el chequeo de arriba?
        Nodo nodoCandidato = buscarNodo(clave_buscada);
        return nodoCandidato.valor;
    }

    public void eliminar(K clave_a_eliminar){
        
        Nodo candidatoNodo = buscarNodo(clave_a_eliminar);
        
        if (candidatoNodo==null||clave_a_eliminar.compareTo(candidatoNodo.clave)!=0){
            return;
        }
        else{
            if (candidatoNodo.izq==null || candidatoNodo.der==null){
                reemplazar(candidatoNodo);
            }
            else{
                Nodo reemplazo = minimoNodo(candidatoNodo.der);
                candidatoNodo.valor = reemplazo.valor;
                reemplazar(reemplazo);
            }
            this._cardinal --;
        }
    }

    public class ABB_Iterador {
        private Nodo _actual = ABB.this.minimoNodo(_raiz);

        public boolean haySiguiente() {            
            return _actual != null;
        }
    
        public K siguiente() {

            K clave_actual = _actual.clave;
            _actual = ABB.this.sucesor(_actual);
            return clave_actual;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
