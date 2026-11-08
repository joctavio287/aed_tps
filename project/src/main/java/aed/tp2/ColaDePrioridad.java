package aed.tp2;

interface ColaDePrioridad <T>{
    /**
     * Accede a la raíz del árbol
     * 
     * 
     */
    public T maximo();
    
    /**
     * Ubica el elemento respetando el orden de prioridad
     * 
     * 
     */
    public Handle<T> insertar(T elem);
    
    /**
     * Reemplaza el máximo por el "último" elemento
     * respetando el orden de prioridad y devuelve el máximo
     * 
     */
    public void eliminarMaximo();

    /**
     * Ubica el elemento respetando el orden de prioridad
     * 
     * 
     */
    
}
