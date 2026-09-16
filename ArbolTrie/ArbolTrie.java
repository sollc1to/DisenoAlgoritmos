package ArbolTrie;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ArbolTrie {

    private final NodoTrie raiz;

    public ArbolTrie() {
        raiz = new NodoTrie();
    }

    // Método principal: agregar palabra

    public void agregarPalabra(String palabra) {
        String palabraNormalizada = normalizar(palabra);
        NodoTrie nodoActual = raiz;

        for (int i = 0; i < palabraNormalizada.length(); i++) {
            char caracter = palabraNormalizada.charAt(i);
            nodoActual = nodoActual.obtenerOCrearHijo(caracter);
            // Sí el nodo ya está registrado con esa letra, accedemos a él. Si no, creamos
            // uno nuevo.

        }

        // El último nodo indica que la palabra terminó.
        nodoActual.marcarComoPalabra();
    }

    public boolean existePalabra(String palabra) {
        NodoTrie nodo = buscarNodo(palabra);
        return nodo != null && nodo.esPalabra();
    }

    public boolean agregarSinonimo(String palabra, String sinonimo) {
        boolean agregado = false;
        NodoTrie nodo = buscarNodo(palabra);

        if (nodo != null && nodo.esPalabra()) {
            nodo.agregarSinonimo(normalizar(sinonimo));
            // Sí el nodo existe y es una palabra, agregamos un sinónimo.
            agregado = true;
        }

        return agregado;
    }

    private NodoTrie buscarNodo(String palabra) {
        String palabraNormalizada = normalizar(palabra);
        char caracter;
        NodoTrie nodoActual = raiz;
        int i = 0;

        while (i < palabraNormalizada.length() && nodoActual != null) {

            caracter = palabraNormalizada.charAt(i);
            nodoActual = nodoActual.obtenerHijo(caracter);

            i++;
        }

        return nodoActual;
    }

    private void listarDesde(NodoTrie nodoActual, StringBuilder prefijo, List<String> palabras) {
        NodoTrie hijo;
        TreeMap<Character, NodoTrie> hijos = nodoActual.obtenerHijos();

        if (nodoActual.esPalabra()) {
            palabras.add(prefijo.toString());
            // Añadimos la palabra completa a la lista de palabras.
        }

        for (Character letra : hijos.keySet()) {
            // Para cada letra de cada par en "hijos"
            // La clave de cada nodo es la letra.

            hijo = nodoActual.obtenerHijo(letra);
            // OBtenemos el hijo con esa letra

            prefijo.append(letra);
            // Añadimos la letra a la palabra.

            listarDesde(hijo, prefijo, palabras);
            // Llamamos recursivamente desde el hijo, con el prefijo que estamos armando
            // ahora.

            prefijo.deleteCharAt(prefijo.length() - 1);
            // Se elimina el último caracter para cambiar la última letra y continuar
            // agregando palabras.

        }
    }

    public void listarPalabras() {

        List<String> palabras = new ArrayList<>();
        listarDesde(raiz, new StringBuilder(), palabras);

        if (!palabras.isEmpty()) {
            System.out.println("Palabras del diccionario:");

            for (String palabra : palabras) {
                System.out.println("- " + palabra);
            }

        }else{

            System.out.println("El diccionario está vacío.");
        }

    }


    public void mostrarSinonimos(String palabra) {

        String palabraNormalizada = normalizar(palabra);

        NodoTrie nodo = buscarNodo(palabraNormalizada);

        if (nodo != null && nodo.esPalabra()) {

            System.out.println("Sinónimos de '" + palabraNormalizada + "':");
            for (String sinonimo : nodo.obtenerSinonimos()) {
                System.out.println("- " + sinonimo);
            }

        } else {
            if (nodo == null || !nodo.esPalabra()) {
                System.out.println("La palabra no existe en el diccionario.");
            } else {
                if (nodo.obtenerSinonimos().isEmpty()) {
                    System.out.println("La palabra no tiene sinónimos cargados.");

                }

            }

        }

    }

    private String normalizar(String texto) {

        return texto.trim().toLowerCase();
        // Eliminamos espacios y convertimos a minúsculas.
    }

}
