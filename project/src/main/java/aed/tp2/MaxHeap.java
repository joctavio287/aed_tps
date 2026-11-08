package aed.tp2;
import java.util.ArrayList;

class MaxHeap <T extends Comparable<T>> implements ColaDePrioridad<T>{
    private ArrayList<HandleMH> arreglo_handles;

    public MaxHeap() {
        arreglo_handles = new ArrayList<HandleMH>();
    }

    private class HandleMH implements Handle<T>{
        private int indice;
        private T valor;

        private HandleMH(int ind, T val){
            this.indice = ind;
            this.valor = val;
        }

        public T valor(){
            return this.valor;
        }

        public void eliminar(){
            int n = MaxHeap.this.arreglo_handles.size();
            int indice_a_eliminar = this.getIndice();

            if (indice_a_eliminar==n-1){
                MaxHeap.this.arreglo_handles.remove(n-1);        
            }
            else{
                intercambio(indice_a_eliminar, n-1);
                MaxHeap.this.arreglo_handles.remove(n-1);        
                // En verdad tiene que subir ó bajar, pero sea cual sea el caso
                // la  operación complementaria es gratis (solo una comparación)
                subir(indice_a_eliminar);
                bajar(indice_a_eliminar);
            }
            // Le asignamos un índice inexistente para que no se genere conflicto si 
            // usamos el indice del hanfle borrado
            this.indice = -1;
            return ;
        }

        public void modificar(T valor_a_modificar){ 
            int indice_actual = this.getIndice();
            this.valor = valor_a_modificar;
            // En verdad tiene que subir ó bajar, pero sea cual sea el caso
            // la  operación complementaria es gratis (solo una comparación)
            subir(indice_actual);
            bajar(indice_actual);

            return;
        }

        public void setIndice(int indice){
            this.indice = indice;
        }
        
        public int getIndice(){
            return this.indice;
        }
        
        public boolean equals(Handle<T> handle_a_comparar){
            return this.valor() == handle_a_comparar.valor();
        }

        public String toString(){
            return this.valor().toString();
        }
    }
    
    private void intercambio(int indice1, int indice2){
        HandleMH handle1 = this.arreglo_handles.get(indice1);
        HandleMH handle2 = this.arreglo_handles.get(indice2);
        this.arreglo_handles.set(indice1, handle2);
        this.arreglo_handles.set(indice2, handle1);
        handle1.setIndice(indice2);
        handle2.setIndice(indice1);
    }
    private int comparacion(int indice1, int indice2){
        return this.arreglo_handles.get(indice1).valor().compareTo(this.arreglo_handles.get(indice2).valor());
    }

    private void subir(int indice_a_subir){
        // El índice del padre es siempre (indice_a_subir-1)/2
        while (indice_a_subir>0 && comparacion(indice_a_subir, (int) (indice_a_subir-1)/2)>0){
            intercambio(indice_a_subir, (int)(indice_a_subir-1)/2);
            indice_a_subir = (int) (indice_a_subir-1)/2;
        }
        return;
    }
    private void bajar(int indice_a_bajar){
        // Hacemos un while hasta llegar al borde del arreglo
        // si se rompe es porque tenía que bajar hasta abajo de todo
        // Los hijos de un nodo están en 2*indice_a_bajar+1 y 2*indice_a_bajar+2
        int n = this.arreglo_handles.size();

        // Lo chequeamos con el hijo izquierdo para ver si tiene que baja o no
        // porque si lo hicieramos con el derecho la comparación con el izquierdo quedaría afuera
        while (2*indice_a_bajar+1<n){
            // Lo definimos como el hijo izquierdo, y si el derecho es mayor, lo cambiamos al derech
            int indice_hijo_mayor = 2*indice_a_bajar+1;
            if (indice_hijo_mayor+1<n && comparacion(indice_hijo_mayor+1, indice_hijo_mayor)>0){
                indice_hijo_mayor++;
            }
            // Si el mayor es más grande que el padre se cambian
            if (comparacion(indice_hijo_mayor, indice_a_bajar)>0){
                intercambio(indice_a_bajar, indice_hijo_mayor);
                indice_a_bajar = indice_hijo_mayor;
            } 
            else {
                break;
            }
        }
        // En caso de que 2*indice_a_bajar+1>=n es que no tiene hijos y no se puede bajar más
    }

    public Handle<T> insertar(T elem){
        int n = this.arreglo_handles.size();

        HandleMH handle_nuevo = new HandleMH(n, elem);
        this.arreglo_handles.add(handle_nuevo);
        subir(n);
        return handle_nuevo;
    };
    
    public T maximo(){
        return this.arreglo_handles.get(0).valor();
    };

    public Handle<T> eliminarMaximo(){
        Handle<T> maximo = this.arreglo_handles.get(0);
        maximo.eliminar();
        return maximo;
    };

    public int size(){
        return this.arreglo_handles.size();
    }
}
