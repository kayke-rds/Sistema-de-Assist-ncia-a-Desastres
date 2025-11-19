/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cake
 */
public class Recurso {

    public Recurso(int tipo, int qtd){
        this.tipo  = this.tipos[tipo-1];
        this.qtd = qtd;
    }

    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    private String tipo;
    private int qtd;
    static String[] tipos = {"Água Potável","Alimentos","Higiene","Medicamentos", "Roupas", "Combustível"};
    
}
