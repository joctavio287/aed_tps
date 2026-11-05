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
    
    public void agregar(T elem);
    
    public T sacarMaximo();
    
 
    // TODO: falta hacer la clase entera, para eso, qué operaciones tiene? Y qué estructura?
    // Para mí no debería ser muy distinto de un ABB, viendo los dibujitos, es un arbol, 
    // pero con otro invariante
    // TODO: define un handle interno, pero no lo guarda
    // Serían usuarios los elementos, ordenados por disponivles

    // implementa una cola no?
}
