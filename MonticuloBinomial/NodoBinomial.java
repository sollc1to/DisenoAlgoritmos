package MonticuloBinomial;

public class NodoBinomial{
    
    private int orden;
    private Object elem ;
    private NodoBinomial hijo;
    private NodoBinomial hermano;

    //constructor
    public NodoBinomial(Object elem, NodoBinomial hijo, NodoBinomial hermano){
        this.orden = 0;
        this.elem = elem;
        this.hijo = hijo;
        this.hermano = hermano;
    }
    //modificadoras 
    public void setElem (Object elem){
        this.elem = elem;
    }
    public void setHijo(NodoBinomial hijo){
        hijo.setHermano(this.getHermano()); //Primero ponemos el hermano para no perder el nodo

        this.hijo= hijo;
        this.orden++;
    }
    public void setHermano(NodoBinomial hermano){
        this.hermano= hermano;
    }
    // obvservadores
    public Object getElem (){
        return this.elem;
    }
    public NodoBinomial getHijo(){
        return this.hijo;
    }
    public NodoBinomial getHermano(){
        return this.hermano;
    }
    public int getOrden(){
        return this.orden;
    }

    public int compareTo(Object elem){
        int exito = -1;
        if (this.elem.equals(elem)){
            exito = 1;
        }
        return exito;
    }
}