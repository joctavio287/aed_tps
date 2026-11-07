package aed.tp2;
import java.util.ArrayList;
import static java.lang.Math.floor;

class MaxHeap <T extends Comparable<T>> implements ColaDePrioridad<T>{
    private ArrayList<Handle<T>> arreglo_handles;
    private int size;

    public MaxHeap() {
        arreglo_handles = new ArrayList<Handle<T>>();
        size = 0;
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
            // cambiar con el arreglo[size] y sift down el cambiado
            Handle<T> valor = MaxHeap.this.arreglo_handles.get(MaxHeap.this.size);
            MaxHeap.this.arreglo_handles.set(this.indice, valor);
            // ListaEnlazada.this.eliminarNodo(this.puntero);
            // this.puntero=null;
        }

        public void modificar(T valor_a_modificar){ 
            this.valor = valor_a_modificar;
            return;
        }

        public int compareTo(Handle<T> handle_a_comparar){
            return this.valor().compareTo(handle_a_comparar.valor());
        }

        public boolean equals(Handle<T> handle_a_comparar){
            return this.valor().compareTo(handle_a_comparar.valor())== 0;
        }

        public String toString(){
            return this.valor().toString();
        }
    }

    private void subir(int indice_a_subir){
        int indice_padre = (int) Math.floor((double) (indice_a_subir-1)/2);
        T valor_indice = this.arreglo_handles.get(indice_a_subir);
        T valor_padre = this.arreglo_handles.get(indice_padre);

        int comparacion = valor_indice.compareTo(valor_padre);
        if (comparacion>0){
            this.arreglo_handles.set(
                indice_padre, valor_indice
            );
            this.arreglo_handles.set(
                indice_a_subir, valor_padre
            );
        }        
        return;
    }
    private void bajar(int indice_a_bajar){
        //TODO: tal vez nos quedo ineficiente
        int indice_hijo_izq = 2*indice_a_bajar+1;
        int indice_hijo_der = 2*indice_a_bajar+2;
        
        T valor_hijo_der = this.arreglo_handles.get(indice_hijo_der);
        T valor_hijo_izq = this.arreglo_handles.get(indice_hijo_izq);
        
        int comparacion_hijos = valor_hijo_der.compareTo(valor_hijo_izq);
        T valor_indice = this.arreglo_handles.get(indice_a_bajar);

        if (comparacion_hijos>0){
            boolean comparacion = valor_indice.compareTo(valor_hijo_der)<0;
            if (comparacion){
                this.arreglo_handles.set(
                  indice_a_bajar, valor_hijo_der
                );
                this.arreglo_handles.set(
                  indice_hijo_der, valor_indice
                );
            }
        }
        else{
            boolean comparacion = valor_indice.compareTo(valor_hijo_izq)<0;
            if (comparacion){
                this.arreglo_handles.set(
                  indice_a_bajar, valor_hijo_izq
                );
                this.arreglo_handles.set(
                  indice_hijo_izq, valor_indice
                );
            }
        }
    }

    public T maximo(){
        return this.arreglo_handles.get(0);
    };
    
    public void insertar(T elem){
        return ;
    };
    
    public void insertarHandle(Handle<T> hand){
        return ;
    };
    
    public void eliminarMaximo(){
        return;
    };
    public Handle<T> eliminarMaxHandle(){
        return;
    };
    
 
    // TODO: falta hacer la clase entera, para eso, qué operaciones tiene? Y qué estructura?
    // Para mí no debería ser muy distinto de un ABB, viendo los dibujitos, es un arbol, 
    // pero con otro invariante
    // TODO: define un handle interno, pero no lo guarda
    // Serían usuarios los elementos, ordenados por disponivles

    // implementa una cola no?

}
