package Diccionario;

import java.util.ArrayList;
import java.util.List;

public class NodoTrie {
    // Cada carácter corresponde a una posición del arreglo.
    private static final String ALFABETO = "aáéíóúbcdefghijklmnñopqrstuvwxyz";

    private final NodoTrie[] hijos;
    private boolean esPalabra;

    private final List<String> sinonimos;

    public NodoTrie() {

        hijos = new NodoTrie[ALFABETO.length()];
        esPalabra = false;
        sinonimos = new ArrayList<>();
    }

    public NodoTrie agregarCaracter(char caracter) {
        int posicion = ALFABETO.indexOf(caracter); // El indice del alfabeto nos da la posición del arreglo de hijos.
        NodoTrie hijo = null;
        

        if (posicion >= 0) {

            hijo = hijos[posicion];
            if (hijo == null) { //Sí no existía ningún nodo, entonces lo creamos.
                
                hijo = new NodoTrie();

                hijos[posicion] = hijo;

            }

        }

        return hijo;
    }

    public NodoTrie obtenerHijo(char caracter) {
        int posicion = ALFABETO.indexOf(caracter);
        return posicion < 0 ? null : hijos[posicion];
    }

    public NodoTrie[] obtenerHijos() {
        return hijos;
    }

    public boolean esPalabra() {
        return esPalabra;
    }

    public void marcarComoPalabra() {
        esPalabra = true;
    }

    public boolean agregarSinonimo(String sinonimo) {
        boolean exito = false;

        if (!sinonimos.contains(sinonimo)) {
            sinonimos.add(sinonimo);
            exito = true;
        }
        
        return exito;
    }

    public List<String> obtenerSinonimos() {
        return sinonimos;
    }
}