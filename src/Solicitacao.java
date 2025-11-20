/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guilh
 */

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Solicitacao {
    
    private int ID;
    private Coordinate coordenadas;
    private String tipoDeEmergencia;
    private int grauDeEmergencia;
    
    public Solicitacao(int ID, Coordinate coordenadas, String tipoDeEmergencia, int grauDeEmergencia) {
        
        this.ID = ID;
        this.coordenadas = coordenadas;
        this.tipoDeEmergencia = tipoDeEmergencia;
        this.grauDeEmergencia = grauDeEmergencia;
        
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the coordenadas
     */
    public Coordinate getCoordenadas() {
        return coordenadas;
    }

    /**
     * @param coordenadas the coordenadas to set
     */
    public void setCoordenadas(Coordinate coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     * @return the tipoDeEmergencia
     */
    public String getTipoDeEmergencia() {
        return tipoDeEmergencia;
    }

    /**
     * @param tipoDeEmergencia the tipoDeEmergencia to set
     */
    public void setTipoDeEmergencia(String tipoDeEmergencia) {
        this.tipoDeEmergencia = tipoDeEmergencia;
    }

    /**
     * @return the grauDeEmergencia
     */
    public int getGrauDeEmergencia() {
        return grauDeEmergencia;
    }

    /**
     * @param grauDeEmergencia the grauDeEmergencia to set
     */
    public void setGrauDeEmergencia(int grauDeEmergencia) {
        this.grauDeEmergencia = grauDeEmergencia;
    }
    
    
    
}
