package aed.tp2;

// [      .....   256]



// - Te doy una palabra "Casa"

// - Creas el nodo raíz.

// - Los nodos tienen un atributo privado que es un arreglo de 256 valores vacio


// (insertar)

// - Recorro la palabra con un loop

// (Primer loop)

// - Lleno el arreglo del nodo raiz en la el espacio  "(int) chrAt("C")" con otro nodo

// - Siguiente iteración desde el nodo de la letra C del primer nivel lleno el valor "(int) chrAt("A")"  con otro nodo

// - Para cada iteración busca su integer


public class Trie <K, V> implements Diccionario<K, V>{
    // podemos poner implements Diccionario<String, V> tambien
    // nombrePromo→Promocion

    private class Nodo {
        int[] arreglo = new int[256];
        K clave;
        V valor;

        Nodo padre;
        Nodo[] hijos = new Nodo[256];

        Nodo(K c, V v) {
            K clave = c;
            V valor = v;
            padre = null;
        }
    }




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
