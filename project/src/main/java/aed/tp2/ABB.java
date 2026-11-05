package aed.tp2;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
class ABB<T extends Comparable<T>> { //esto significa que come T genérico y que T es comaprable
    // TODO: falta adaptar a diccionario → que operaciones modificamos?
    // class ABB< // habria que escribir algo similar tipo diccionario
    // onda llaves comparables, valores cualquier cosa
    // T extends Comparable<T> significa Cualquier tipo T que además pueda ser comparable con obj
    // del mismo tipo
    // despues podemos forzar el implements Diccionario para asegurarnos de hacer las operaciones que queremos
    
    // Agregar atributos privados del Conjunto
    private int _cardinal;
    private Nodo _raiz;

    private class Nodo {
        T valor;
        Nodo padre;
        Nodo izq;
        Nodo der;

        Nodo(T v) {
            valor = v;
            padre = null;
            izq = null;
            der = null;
        }
    }
    
    public ABB() {
        _raiz = null;
        _cardinal = 0;
    }

    public int cardinal() {
        return this._cardinal;
    }
    
    private Nodo buscarNodo(T elem){
        Nodo resultNodo = _raiz;
        while (resultNodo != null) {
            int comparacion = elem.compareTo(resultNodo.valor);
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

    private Nodo maximoNodo(Nodo n){
        Nodo resultNodo = n; 
        while (resultNodo != null && resultNodo.der != null) {
            resultNodo = resultNodo.der;
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

    public T minimo(){
        Nodo minimoNodo = minimoNodo(_raiz);
        if (minimoNodo == null){
            return null;
        }
        else{
            return minimoNodo.valor;
        }
    }

    public T maximo(){
        Nodo maximoNodo = maximoNodo(_raiz);
        if (maximoNodo == null){
            return null;
        }
        else{
            return maximoNodo.valor;
        }
    }

    public void insertar(T elem){
        if (this._raiz == null){
            Nodo nuevoNodo = new Nodo(elem);
            this._raiz = nuevoNodo;
            this._cardinal++;
            return;
        }
        Nodo nodoCandidatoPrevio = buscarNodo(elem);
        int comparacion = elem.compareTo(nodoCandidatoPrevio.valor);
        
        if (comparacion==0){
            return;
        }
        else{
            Nodo nuevoNodo = new Nodo(elem);
            nuevoNodo.padre = nodoCandidatoPrevio;
            this._cardinal++;
            if (comparacion>0){
                nodoCandidatoPrevio.der = nuevoNodo;
            }
            else {
                nodoCandidatoPrevio.izq = nuevoNodo;
            }
        }
    }

    public boolean pertenece(T elem){
        Nodo nodoCandidato = buscarNodo(elem);
        return nodoCandidato!=null && elem.compareTo(nodoCandidato.valor)==0;
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

    public void eliminar(T elem){
        Nodo candidatoNodo = buscarNodo(elem);
        if (candidatoNodo==null||elem.compareTo(candidatoNodo.valor)!=0){
            return;
        }
        else{
            if (candidatoNodo.izq==null || candidatoNodo.der==null){
                reemplazar(candidatoNodo);
            }
            else{
                Nodo sucesor = minimoNodo(candidatoNodo.der);
                candidatoNodo.valor = sucesor.valor;
                reemplazar(sucesor);
            }
            this._cardinal --;
        }
    }

    public String toString(){
        String result = "{";
        ABB_Iterador iter = this.iterador();
        while (iter.haySiguiente()){
            result += iter.siguiente().toString();
            if (iter.haySiguiente()){
                result += ",";
            }
        }
        return result + "}";
    }

    public class ABB_Iterador {
        private Nodo _actual=ABB.this.minimoNodo(_raiz);

        public boolean haySiguiente() {            
            return _actual != null;
        }
    
        public T siguiente() {
            T valor_actual = _actual.valor;
            _actual = ABB.this.sucesor(_actual);
            return valor_actual;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
