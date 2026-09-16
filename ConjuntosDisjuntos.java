
public class ConjuntosDisjuntos{

    // padre[i] indica el padre del elemento i.
    private final int[] padre;

    // rango[i] se utiliza solamente cuando i es una raíz.
    private final int[] rango;

    /**
     * Crea n conjuntos disjuntos.
     * Inicialmente, cada elemento forma su propio conjunto.
     */
    public ConjuntosDisjuntosOptimizado(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("La cantidad de elementos debe ser positiva");
        }

        padre = new int[n];
        rango = new int[n];

        // MakeSet: cada elemento es su propio padre y comienza con rango 0.
        for (int i = 0; i < n; i++) {
            padre[i] = i;
            rango[i] = 0;
        }
    }

    /**
     * Buscar (Find): devuelve el representante del conjunto de x.
     *
     * Path compression:
     * después de encontrar la raíz, hacemos que x apunte directamente a ella.
     * De esta forma, las búsquedas siguientes son más rápidas.
     */
    public int buscar(int x) {
        validar(x);

        // Si x no es la raíz, buscamos recursivamente la raíz.
        if (padre[x] != x) {
            // Compresión de caminos: x apunta directamente al representante.
            padre[x] = buscar(padre[x]);
        }

        return padre[x];
    }

    /**
     * Fusionar (Union): une los conjuntos que contienen a a y b.
     */
    public void fusionar(int a, int b) {
        int raizA = buscar(a);
        int raizB = buscar(b);

        // Si tienen la misma raíz, ya pertenecen al mismo conjunto.
        if (raizA == raizB) {
            return;
        }

        // Union by rank:
        // la raíz del árbol de menor rango apunta a la de mayor rango.
        if (rango[raizA] < rango[raizB]) {
            padre[raizA] = raizB;
        } else if (rango[raizA] > rango[raizB]) {
            padre[raizB] = raizA;
        } else {
            // Si los rangos son iguales, elegimos raizA como nueva raíz.
            padre[raizB] = raizA;
            rango[raizA]++;
        }
    }

    /**
     * Devuelve true si a y b pertenecen al mismo conjunto.
     */
    public boolean estanUnidos(int a, int b) {
        return buscar(a) == buscar(b);
    }

    /**
     * Comprueba que el elemento exista en el arreglo.
     */
    private void validar(int x) {
        if (x < 0 || x >= padre.length) {
            throw new IndexOutOfBoundsException(
                    "El elemento debe estar entre 0 y " + (padre.length - 1));
        }
    }

    /**
     * Ejemplo de uso.
     */
    public static void main(String[] args) {
        ConjuntosDisjuntosOptimizado conjuntos =
                new ConjuntosDisjuntosOptimizado(8);

        conjuntos.fusionar(0, 1);
        conjuntos.fusionar(1, 2);
        conjuntos.fusionar(3, 4);
        conjuntos.fusionar(2, 4);

        System.out.println("Representante de 0: " + conjuntos.buscar(0));
        System.out.println("Representante de 4: " + conjuntos.buscar(4));
        System.out.println("¿0 y 4 están unidos? " + conjuntos.estanUnidos(0, 4));
        System.out.println("¿0 y 7 están unidos? " + conjuntos.estanUnidos(0, 7));
    }
}
