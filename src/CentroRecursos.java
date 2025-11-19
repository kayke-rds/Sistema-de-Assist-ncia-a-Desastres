/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.util.LinkedList;
/**
 *
 * @author Cake
 */


public class CentroRecursos extends Lugar{

    /**
     * @return the lista
     */
    public LinkedList<CentroRecursos> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(LinkedList<CentroRecursos> lista) {
        this.lista = lista;
    }

    private Recurso[] listaRecursos = new Recurso[6];
    private LinkedList<CentroRecursos> lista;
    
    public CentroRecursos(String nome, Coordinate coord){
        super(nome, "Centro de Recursos", coord);
        
    }
    
    public void addRecurso(int tipo, int qtd){
        
        if(this.getListaRecursos()[tipo-1] == null){
            Recurso recurso = new Recurso(tipo, qtd);
            this.getListaRecursos()[tipo-1] = recurso;
        }
        else{
             this.getListaRecursos()[tipo-1].setQtd(this.getListaRecursos()[tipo-1].getQtd() + qtd);
        }
        
    }

    /**
     * @return the listaRecursos
     */
    public Recurso[] getListaRecursos() {
        return listaRecursos;
    }

    /**
     * @param listaRecursos the listaRecursos to set
     */
    public void setListaRecursos(Recurso[] listaRecursos) {
        this.listaRecursos = listaRecursos;
    }
    
}
