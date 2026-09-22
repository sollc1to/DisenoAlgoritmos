package Diccionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArbolTrie {
    private NodoTrie raiz;

    private static final String ALFABETO = "aáéíóúbcdefghijklmnñopqrstuvwxyz";

    public ArbolTrie() {
        raiz = null;
    }

    public boolean agregarPalabra(String palabra) {
        String palabraNormalizada = normalizar(palabra);
        boolean palabraAgregada = false;

        int longitud = palabraNormalizada.length();

        if (raiz == null) {
            raiz = new NodoTrie();
        }

        NodoTrie nodoActual = raiz;

        int i = 0;

        while (i < longitud && nodoActual != null) {

            nodoActual = nodoActual.agregarCaracter(palabraNormalizada.charAt(i));
            i++;
        }
        if (nodoActual != null) { // El nodo actual sería null si se ingresó un caracter no válido
            nodoActual.marcarComoPalabra();
            palabraAgregada = true;
        }

        return palabraAgregada;
    }

    public boolean existePalabra(String palabra) {
        NodoTrie nodo = buscarNodo(palabra);
        return nodo != null && nodo.esPalabra();
    }


    private NodoTrie buscarNodo(String palabra) {
       
        String palabraNormalizada = normalizar(palabra); //Convertimos a minusculass.
    
        NodoTrie nodoActual = raiz;

        int i = 0;

        while (i < palabraNormalizada.length() && nodoActual != null) {
            nodoActual = nodoActual.obtenerHijo(palabraNormalizada.charAt(i));
            i++;
        }
        return nodoActual;
    }




    public boolean agregarSinonimo(String palabra, String sinonimo) {
        NodoTrie nodo = buscarNodo(palabra);
        String sinonimoNormalizado = normalizar(sinonimo);
        boolean exito = false;

        if (nodo != null && nodo.esPalabra()) {


            exito =nodo.agregarSinonimo(sinonimoNormalizado);

        }
        return exito;
    }

  

    private void listarDesde(NodoTrie nodoActual, StringBuilder prefijo,
            List<String> palabras) {


        if (nodoActual.esPalabra()) {
//Sí es una palabra, agregamos lo que está en "prefijo" al listado de palabras.

            palabras.add(prefijo.toString());
        }

        //Por cada nodoActual, recorremos todos sus hijos.
        NodoTrie[] hijos = nodoActual.obtenerHijos();

        int i = 0;
        while (i < hijos.length) {

            if (hijos[i] != null) {

                prefijo.append(ALFABETO.charAt(i)); 

                listarDesde(hijos[i], prefijo, palabras);
                // Regresa al prefijo del padre.
                prefijo.deleteCharAt(prefijo.length() - 1);
            }
            i++;
        }
    }

    public void listarPalabras() {
        List<String> palabras = new ArrayList<>();
        listarDesde(raiz, new StringBuilder(), palabras);

//El stringbuilder es una secuencia mutable de caracteres.

        if (palabras.isEmpty()) {
            System.out.println("El diccionario está vacío.");
        } else {
            System.out.println("Palabras del diccionario:");
            for (String palabra : palabras) {
                System.out.println("- " + palabra);
            }
        }
    }

    public void mostrarSinonimos(String palabra) {
        NodoTrie nodo = buscarNodo(palabra);

        if (nodo == null || !nodo.esPalabra()) {
            System.out.println("La palabra no existe en el diccionario.");
        } else if (nodo.obtenerSinonimos().isEmpty()) {
            System.out.println("La palabra no tiene sinónimos cargados.");
        } else {
            System.out.println("Sinónimos de '" + normalizar(palabra) + "':");
            for (String sinonimo : nodo.obtenerSinonimos()) {
                System.out.println("- " + sinonimo);
            }
        }
    }

    private String normalizar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto no puede estar vacío");
        }
        // Quita espacios de los extremos y convierte a minúsculas.
        return texto.trim().toLowerCase(Locale.ROOT);
    }



    
}
