package aed.tp2;

interface Diccionario <K, V> {
    /**
     * Verifica si la clave existe
     * 
     */
    public boolean pertenece(K clave);
    
    /**
     * Inserta una par (clave, valor)
     * Si existe lo pisa
     * 
     */
    public void insertar(K clave, V valor);
    
    /**
     * Devuelve el valor de la clave 
     * Si no existe devuelve null
     * 
     */
    public V obtener(K clave);

    /**
     * Elimina el par (clave, valor)
     * Si no existe no hace nada
     * TODO: verificar si necesitamos devolver el ítem borrado
     */
    public void eliminar(K clave);

    /**
     * Devuelve la cantidad de claves
     * 
     *  
     */
    public int tamaño();
} 
