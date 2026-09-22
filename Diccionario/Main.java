package Diccionario;

/**
 * Programa de prueba para la clase ArbolTrie.
 */
public class Main {

    public static void main(String[] args) {

        ArbolTrie diccionario = new ArbolTrie();

        System.out.println("=== AGREGAR PALABRAS ===");

        diccionario.agregarPalabra("rápido");
        diccionario.agregarPalabra("casa");
        diccionario.agregarPalabra("cama");
        diccionario.agregarPalabra("casamiento");
        
        diccionario.agregarPalabra("c");

        // Agregamos una palabra repetida para comprobar que no se duplica.
        diccionario.agregarPalabra("casa");

        System.out.println("Palabras agregadas correctamente.");

        System.out.println();
        System.out.println("=== BUSCAR PALABRAS ===");

        System.out.println("¿Existe 'rápido'? "
                + diccionario.existePalabra("rápido"));

        System.out.println("¿Existe 'CASA'? "
                + diccionario.existePalabra("CASA"));

        // 'ca' es un prefijo, pero no fue agregada como palabra completa.
        System.out.println("¿Existe 'ca'? "
                + diccionario.existePalabra("ca"));

        // 'c' sí fue agregada como palabra completa.
        System.out.println("¿Existe 'c'? "
                + diccionario.existePalabra("c"));

        System.out.println("¿Existe 'perro'? "
                + diccionario.existePalabra("perro"));

        System.out.println();
        System.out.println("=== AGREGAR SINÓNIMOS ===");

        System.out.println("Agregar 'veloz' a 'rápido': "
                + diccionario.agregarSinonimo("rápido", "veloz"));

        System.out.println("Agregar 'ligero' a 'rápido': "
                + diccionario.agregarSinonimo("rápido", "ligero"));

        // Se prueba que un sinónimo repetido no se guarde dos veces.
        System.out.println("Agregar nuevamente 'veloz': "
                + diccionario.agregarSinonimo("rápido", "veloz"));

        // Se prueba agregar un sinónimo a una palabra inexistente.
        System.out.println("Agregar sinónimo a 'perro': "
                + diccionario.agregarSinonimo("perro", "can"));

        System.out.println();
        System.out.println("=== MOSTRAR SINÓNIMOS ===");

        diccionario.mostrarSinonimos("RÁPIDO");

        System.out.println();
        System.out.println("=== PALABRA SINÓNIMOS ===");

        // 'casa' existe, pero no tiene sinónimos cargados.
        diccionario.mostrarSinonimos("casa");

        System.out.println();
        System.out.println("=== LISTAR TODAS LAS PALABRAS ===");

        // Debe listar las palabras, pero no 'veloz' ni 'ligero',
        // porque esos son sinónimos y no palabras agregadas al Trie.
        diccionario.listarPalabras();
    }
}
