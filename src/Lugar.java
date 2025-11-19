
import org.openstreetmap.gui.jmapviewer.Coordinate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cake
 */
public class Lugar {

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param coord the coord to set
     */
    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }
    private String nome;
    private String tipo;
    private Coordinate coord;

    public Lugar(String nome, String tipo, Coordinate coord) {
        this.nome = nome;
        this.tipo = tipo;
        this.coord = coord;
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public Coordinate getCoord() { return coord; }

    @Override
    public String toString() {
        return nome + " (" + tipo + ") [" + coord.getLat() + "," + coord.getLon() + "]";
    }
}
