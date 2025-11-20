/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.util.Set;
import java.util.HashSet;
/**
 *
 * @author Cake
 */
public class HashCentroRecursos {
    public HashCentroRecursos(){
        
    }
    
    private CentroRecursos[] hashRoot = new CentroRecursos[121];
    
    public void addCentroRec(CentroRecursos centro){
        double x = (centro.getCoord().getLon()+40.5)/0.2;
        int i = (int)x;
        double y = (centro.getCoord().getLat()+13.0)/0.1;
        int j = (int)y;
        int inc = 10*i;
        int pos = inc + j;
        if(this.hashRoot[pos] == null)
            this.hashRoot[pos] = centro;
        else
            this.hashRoot[pos].addCentroLista(centro);
    }
    
    public CentroRecursos searchCentro(int recurso, Coordinate coord){
        double x = (coord.getLon()+13)/0.1;
        int i = (int)x;
        double y = (coord.getLat()+40.5)/0.2;
        int j = (int)y;
        int pos = 10*i+j;
        
        if(this.hashRoot[pos] != null){
            CentroRecursos centro = this.hashRoot[pos];
            if(centro.getListaRecursos()[recurso] != null)
                return centro;
        }
        return null;
    }
    
    public CentroRecursos searchCentro(int recurso, int i, int j){
        int pos = 10*i+j;
        
        if(this.hashRoot[pos] != null){
            CentroRecursos centro = this.hashRoot[pos];
            if(centro.getListaRecursos()[recurso] != null)
                return centro;
        }
        return null;
    }
    
    public CentroRecursos searchCentro(int recurso, int pos){
        
        if(this.hashRoot[pos] != null){
            CentroRecursos centro = this.hashRoot[pos];
            if(centro.getListaRecursos()[recurso] != null)
                return centro;
        }
        return null;
    }
        
    public CentroRecursos buscarSimplificado(Coordinate local, int recursoDesejado) {
        int indiceInicial = (int)local.getLon()*10+(int)local.getLat();
        int iInicial = (int)local.getLon();
        int jInicial = (int)local.getLat();

        Set<Integer> indicesVerificados = new HashSet<>();

        // --- 1. BUSCA NO CHUNK INICIAL E COLISÕES ---
        CentroRecursos resultado = searchCentro(recursoDesejado, local);
        if (resultado != null) {
            return resultado;
        }
        indicesVerificados.add(indiceInicial);

        // --- 2. BUSCA NOS CHUNKS ADJACENTES (Distância D=1) ---
        int[][] vizinhos2D = {
            {iInicial + 1, jInicial}, {iInicial - 1, jInicial},
            {iInicial, jInicial + 1}, {iInicial, jInicial - 1}
        };

        for (int[] vizinho : vizinhos2D) {
            int iViz = vizinho[0];
            int jViz = vizinho[1];

            // Tratamento de Bordas: Verifica se (iViz, jViz) está dentro do limite [0, N-1]
            if (iViz >= 0 && iViz < 11 && jViz >= 0 && jViz < 10) {
                int indice1D = iViz*10 + jViz;
                if (indicesVerificados.add(indice1D)) {
                    resultado = searchCentro(recursoDesejado, iViz, jViz);
                    if (resultado != null) {
                        return resultado;
                    }
                }
            }
        }

        // --- 3. BUSCA LINEAR NO RESTANTE DO ARRAY (Fallback) ---
        System.out.println("\n3. Fazendo varredura simples do restante do array (Fallback)...");

        // Varre o array 1D completo, pulando os índices já verificados
        for (int indice1D = 0; indice1D < 121; indice1D++) {
            if (!indicesVerificados.contains(indice1D)) {
                // AQUI VOCÊ SÓ VERIFICA O HEAD/COLISÃO DO CHUNK
                resultado = searchCentro(recursoDesejado,indice1D);
                if (resultado != null) {
                    return resultado;
                }
                indicesVerificados.add(indice1D);
            }
        }

        return null;
    }
    
}
