package MonticuloBinomial;


public class HeapBinomial {

    private ArbolBinomial raiz;

    public  HeapBinomial() {
        this.raiz =null;
        
    }



    public boolean insertar(ArbolBinomial arbol) {
        boolean exito = false;

        if (arbol != null) {

            if (this.raiz == null) {

                this.raiz = arbol;

            } else {

                this.raiz = unir(this.raiz, arbol);

            }

            exito = true;
        }

        return exito;
    }

    public boolean unir( HeapBinomial heap) {
        boolean exito = false;

        if (heap != null) {

            if (this.raiz == null) {

                this.raiz = heap.raiz;

            } else {

                this.raiz = unir(this.raiz, heap.raiz);

            }

            exito = true;
        }

        return exito;
    }


    public boolean buscarMin(){

    }


    public boolean extraerMin(){


    }



    public boolean disminuirClave(Object elem, Object nuevoElem) {
        boolean exito = false;

        if (this.raiz != null) {

            exito = disminuirClaveAux(this.raiz, elem, nuevoElem);

        }

        return exito;
    }


    public boolean eliminar(x){


    }

  
}