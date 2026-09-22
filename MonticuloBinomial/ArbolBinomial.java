package MonticuloBinomial;

public class ArbolBinomial{

    private NodoBinomial raiz;

    public ArbolBinomial() {
        this.raiz = null;
    }

    public boolean pertenece(Object elem) {

        boolean pertenece = false;

        if (this.raiz != null) {
            pertenece = buscarElem(this.raiz, elem);

        }

        return pertenece;

    }



     public boolean insertar(Object elem, Object padre) {
        //Insertamos el nodo con elem al nodo con el elem padre

        NodoBinomial hijo = new NodoBinomial(elem, null, null);
        boolean exito = false;
        if (this.raiz == null) {
            this.raiz = hijo;
            exito = true;
        } else {
            NodoBinomial nodoAux = obtenerNodo(this.raiz, padre);
            if (nodoAux != null) {
                hijo.setHermano(nodoAux.getHijo());
                nodoAux.setHijo(hijo);
                exito = true;
            }
        }
        return exito;
    }

    private NodoBinomial obtenerNodo(NodoBinomial nodo, Object elem) {
        NodoBinomial aux = null;

        if (nodo != null) {

            if (nodo.getElem().equals(elem)) {
                //Sí el nodo es el que buscamos, lo retornamos
                aux = nodo;
            } else {
                //Si no, por cada hijo del nodo actual, buscamos al elem
                //entre él y sus hermanos
                NodoBinomial hijo = nodo.getHijo();
                while (hijo != null && aux == null) {
                    aux = obtenerNodo(hijo, elem);
                    hijo = hijo.getHermano();
                }
            }
        }
        return aux;
    }


    private boolean buscarElem(NodoBinomial nodo, Object elem) {
        boolean exito = false;
        int comparable;

        if (nodo != null) {

            if (nodo.getElem().equals(elem)) {
                exito = true;

            } else {

                comparable = nodo.compareTo(elem);

                if (comparable < 0) {
                    exito = buscarElem(nodo.getHermano(), elem);

                } else {
                    exito = buscarElem(nodo.getHijo(), elem);

                }

            }

        }
        return exito;

    }


    public String toString() {
        String cadena = "";
        if (this.raiz != null) {
            cadena = generarToString(this.raiz);

        } else {

            cadena = " Arbol vacío. ";
        }
        return cadena;

    }

    private String generarToString(NodoBinomial nodo) {

        String cadena = "";

        if (nodo != null) {
            cadena = " P: " + nodo.getElem() + " HI:" + (nodo.getHijo() != null ? nodo.getHijo().getElem() : " null ") + " HD: "
                    + (nodo.getHermano() != null ? nodo.getHermano().getElem() : " null. ") + "\n";

            cadena = cadena + generarToString(nodo.getHijo());

            cadena = cadena + generarToString(nodo.getHermano());

        }

        return cadena;

    }
    


    public Object maximoElem() {
        Object maximo = null;
        if (this.raiz != null) {
            maximo = maximoElemAux(this.raiz);

        }
        return maximo;

    }

    private Object maximoElemAux(NodoBinomial nodo) {
        Object maximo = null;

        if (nodo != null) {

            if (nodo.getHermano() != null) {

                maximo = maximoElemAux(nodo.getHermano());

            } else {
                maximo = nodo.getElem();
            }

        }
        return maximo;

    }

    public Object minimoElem() {
        Object minimo = null;

        if (this.raiz != null) {

            minimo = buscarMinimo(this.raiz);

        }
        return minimo;

    }

    private Object buscarMinimo(NodoBinomial nodo) {
        Object minimoElem = null;

        if (nodo != null) {

            if (nodo.getHijo() != null) {

                minimoElem = buscarMinimo(nodo.getHijo());

            } else {

                minimoElem = nodo.getElem();
            }

        }

        return minimoElem;

    }


    public boolean merge(ArbolBinomial arbol) {
        boolean exito = false;

        if (this.raiz != null && arbol.raiz != null) {

            if (this.raiz.getOrden() == arbol.raiz.getOrden() ){ //Sí son dos árboles del mismo orden
                if(((Comparable<Object>) this.raiz.getElem()).compareTo(arbol.raiz.getElem()) <= 0){
                    //Sí la comparación es menor a 0, entonces 
                    //this.raiz < arbol.raiz
                    //Sí es = 0 entonces son iguales
                    arbol.raiz.setHijo(this.raiz);
                    exito = true;
                }else{

                    //Si es > 0, entonces this.raiz > arbol.raiz

                    this.raiz.setHijo(arbol.raiz);
                    exito = true;
                }
            }

        }
        return exito;

    }

    public boolean vacio() {

        return (this.raiz == null);
    }

    public void vaciar() {

        this.raiz = null;

    }
}