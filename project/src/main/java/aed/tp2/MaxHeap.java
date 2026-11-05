package aed.tp2;

class MaxHeap <Integer extends Comparable<Integer>>{
    private int[] elems;
    private int head;
    private int size;

    public MaxHeap(int capacidad) {
        elems = new int[capacidad];
        head = 0;
        size = 0;
    }

    private void siftUp(Nodo nodo){
        return;
    }
    private void siftDown(Nodo nodo){
        return;
    }
    
    
    public void maximo();
    
    public HandleMH agregar(T elem);
    
    public HandleMH sacarMaximo();
    
 
    // TODO: falta hacer la clase entera, para eso, qué operaciones tiene? Y qué estructura?
    // Para mí no debería ser muy distinto de un ABB, viendo los dibujitos, es un arbol, 
    // pero con otro invariante
    // TODO: define un handle interno, pero no lo guarda
    // Serían usuarios los elementos, ordenados por disponivles

    // implementa una cola no?

        private class HandleMH implements Handle<T>{
        private Nodo puntero;

        private HandleMH(Nodo n){
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
            // TODO
            return;
        }

        public int compareTo(Handle<T> handle_a_comparar){
            return this.valor().compareTo(handle_a_comparar.valor());
        }

        public boolean equals(Handle<T> handle_a_comparar){
            // TODO
            return true;
        }

        public String toString(){
            return this.valor().toString();
        }
    }
}
