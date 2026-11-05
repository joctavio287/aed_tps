package aed.tp2;

interface ColaDePrioridad <T>{
    /**
     * Accede a la raíz del árbol
     * 
     * 
     */
    public void maximo();
    
    /**
     * Ubica el elemento respetando el orden de prioridad
     * 
     * 
     */
    public void agregar(T elem);
    
    /**
     * Reemplaza el máximo por el "último" elemento
     * respetando el orden de prioridad y devuelve el máximo
     * 
     */
    public T sacarMaximo();
    
}
