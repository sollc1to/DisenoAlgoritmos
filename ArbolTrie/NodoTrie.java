package ArbolTrie;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Nodo de un árbol Trie.
 *
 * Cada nodo puede tener varios hijos: uno por cada carácter que continúe
 * el prefijo almacenado en ese nodo.
 */
public class NodoTrie {

//En treemap guardamos (clave,valor). 
//Los valores se almacenan guardados x clave.

    private final TreeMap<Character, NodoTrie> hijos;

    // Indica si el camino hasta este nodo forma una palabra completa.
    private boolean esPalabra;

    // Los sinónimos se guardan solamente en el nodo final de la palabra.
    private final List<String> sinonimos;

    public NodoTrie() {
        hijos = new TreeMap<>(); //EL treemap es un arbol negro-rojo
        esPalabra = false;
        sinonimos = new ArrayList<>();
    }


    public NodoTrie obtenerOCrearHijo(char caracter) {
        NodoTrie hijo = hijos.get(caracter);

        if (hijo == null) {
            hijo = new NodoTrie();
            hijos.put(caracter, hijo);
        }

        return hijo;
    }

   
    public NodoTrie obtenerHijo(char caracter) {
        return hijos.get(caracter);
    }

    public TreeMap<Character, NodoTrie> obtenerHijos() {
        return hijos;
    }

    public boolean esPalabra() {
        return esPalabra;
    }

    public void marcarComoPalabra() {
        esPalabra = true;
    }

    public boolean agregarSinonimo(String sinonimo) {
        boolean agregado = false;
        if (! sinonimos.contains(sinonimo)) {
            sinonimos.add(sinonimo);
            agregado = true;
        }
        return agregado;
    }

    public List<String> obtenerSinonimos() {
        return sinonimos;
    }
}
