/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import java.util.Random;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author enzof
 */
public class TelaPrincipal extends JFrame {
    private JMapViewer map;
    private List<Lugar> lugares = new ArrayList<>();
    private List<Rota> rotas = new ArrayList<>();
    private HashCentroRecursos hashCentros = new HashCentroRecursos();

    public TelaPrincipal() {
        setTitle("Mapa - Cadastro de Lugares e Rotas (JMapViewer)");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        map = new JMapViewer();
        map.setZoom(10);
        map.setDisplayPosition(new Coordinate(-12.5, -39.3), 9); // Centraliza na região entre feira e salvador

        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    Point p = e.getPoint();
                    Coordinate coord = (Coordinate) map.getPosition(p);
                    JOptionPane.showMessageDialog(TelaPrincipal.this,
                            "Coordenadas: " + coord.getLat() + ", " + coord.getLon());
                    cadastrarLugar(coord);
                }
            }
        });
        
        inserirCentros();

        JButton rotaBtn = new JButton("Cadastrar Rota");
        rotaBtn.addActionListener(e -> cadastrarRota());

        add(map, BorderLayout.CENTER);
        add(rotaBtn, BorderLayout.SOUTH);
        
        // 2. Criação da Barra de Menu (JMenuBar)
JMenuBar menuBar = new JMenuBar();
        
// 3. Criação do Menu principal (JMenu)
JMenu menuAcoes = new JMenu("Ações de Emergência");
menuAcoes.setMnemonic('A'); // Atalho Alt+A para acessar o menu

// 4. Criação e Adição dos Itens do Menu (JMenu como Submenu)
        
// --- Submenu 1: Centros de Recursos (Substitui o JMenuItem) ---
JMenu menuCentros = new JMenu("Centros de Recursos");
menuCentros.setMnemonic('C'); // Atalho Alt+A, depois C
menuAcoes.add(menuCentros); // Adiciona o JMenu como um item do menuAcoes

// Adiciona 4 itens genéricos ao Submenu 1
adicionarItensGenericos(menuCentros, "Centro");


// --- Submenu 2: Equipes de Apoio (Substitui o JMenuItem) ---
JMenu menuEquipes = new JMenu("Equipes de Apoio");
menuEquipes.setMnemonic('E'); // Atalho Alt+A, depois E
menuAcoes.add(menuEquipes);

// Adiciona 4 itens genéricos ao Submenu 2
adicionarItensGenericos(menuEquipes, "Equipe");


// Adiciona um separador visual
menuAcoes.addSeparator(); 

// --- Submenu 3: Solicitar Resgate (Substitui o JMenuItem) ---
JMenu menuResgate = new JMenu("Solicitar Resgate");
menuResgate.setMnemonic('R'); // Atalho Alt+A, depois R
menuAcoes.add(menuResgate);

// Adiciona 4 itens genéricos ao Submenu 3
adicionarItensGenericos(menuResgate, "Resgate");
        
// 5. Adiciona o Menu principal à Barra de Menu
menuBar.add(menuAcoes);

// 6. Define a Barra de Menu no Frame
setJMenuBar(menuBar);


// --- Método Auxiliar para Adicionar Itens Genéricos ---

// Este método deve ser criado dentro da sua classe principal (ex: TelaPrincipal)

    }
    
    
private void adicionarItensGenericos(JMenu submenu, String prefixo) {
    for (int i = 1; i <= 4; i++) {
        JMenuItem item = new JMenuItem(prefixo + " - Opção " + i);
        
        // Adiciona um ActionListener para mostrar o que foi selecionado
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A ação agora é do item genérico, não do menu principal
                String nomeItem = ((JMenuItem)e.getSource()).getText();
                JOptionPane.showMessageDialog(TelaPrincipal.this, 
                    "Opção selecionada: " + nomeItem, 
                    "Sub-menu", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Ação: Item de sub-menu selecionado: " + nomeItem);
            }
        });
        submenu.add(item);
    }
}

private void adicionarItensMenuRecursos(JMenu submenu, String prefixo){
    JMenuItem item = new JMenuItem("Cadastrar Centro de Recursos");
        
    // Adiciona um ActionListener para mostrar o que foi selecionado
    item.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // A ação agora é do item genérico, não do menu principal
            String nomeItem = ((JMenuItem)e.getSource()).getText();
            JOptionPane.showMessageDialog(TelaPrincipal.this, 
                "Opção selecionada: " + nomeItem, 
                "Sub-menu", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ação: Item de sub-menu selecionado: " + nomeItem);
        }
    });
    submenu.add(item);
}
    
    private void cadastrarCentroRecursos(Coordinate coord){
        String nome = JOptionPane.showInputDialog(this, "Nome do Centro de Recursos:");
        if (nome == null || nome.isEmpty()) return;
        
        CentroRecursos centro = new CentroRecursos(nome, coord);
        
        while(true){
            String[] tipos = Recurso.tipos;
                    String tipo = (String) JOptionPane.showInputDialog(
                            this, "Selecione recurso disponível no centro:",
                            "Tipo de Recurso",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            tipos,
                            tipos[0]
                    );
                    int qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do recurso:"));
                    String[] options = {"Não", "Sim"};
                    String quit = (String) JOptionPane.showInputDialog(
                            this, "O centro possui mais recursos?",
                            "Sim/Não",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
                    if(quit == "Não")
                        break;
        }
        // Marcador no mapa
        map.addMapMarker(new MapMarkerDot("Centro de Recursos" + nome, coord));
        
        lugares.add(centro);
        
        hashCentros.addCentroRec(centro);

        JOptionPane.showMessageDialog(this,
                "Centro cadastrado:\n" + centro);
    }

    private void cadastrarLugar(Coordinate coord) {
        
        String[] tipos = {"Centro de Recursos", "Lugar 2", "Lugar 3"};
                String tipo = (String) JOptionPane.showInputDialog(
                        this, "Selecione o tipo:",
                        "Tipo do lugar",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        tipos,
                        tipos[0]
                );
        
        if(tipo == tipos[0])
            this.cadastrarCentroRecursos(coord);
        
        else{
            String nome = JOptionPane.showInputDialog(this, "Nome do lugar:");
            if (nome == null || nome.isEmpty()) return;
            Lugar lugar = new Lugar(nome, tipo, coord);
            lugares.add(lugar);

            // Marcador no mapa
            map.addMapMarker(new MapMarkerDot(nome, coord));

            JOptionPane.showMessageDialog(this,
                    "Lugar cadastrado:\n" + lugar);
        }
    }

    private void cadastrarRota() {
        if (lugares.size() < 2) {
            JOptionPane.showMessageDialog(this,
                    "É necessário pelo menos 2 lugares cadastrados.");
            return;
        }

        Lugar origem = (Lugar) JOptionPane.showInputDialog(
                this, "Selecione o lugar de origem:",
                "Origem",
                JOptionPane.PLAIN_MESSAGE,
                null,
                lugares.toArray(),
                lugares.get(0)
        );

        Lugar destino = (Lugar) JOptionPane.showInputDialog(
                this, "Selecione o lugar de destino:",
                "Destino",
                JOptionPane.PLAIN_MESSAGE,
                null,
                lugares.toArray(),
                lugares.get(0)
        );

        if (origem == null || destino == null || origem.equals(destino)) {
            JOptionPane.showMessageDialog(this, "Seleção inválida.");
            return;
        }

        double distancia = calcularDistancia(origem.getCoord(), destino.getCoord());
        Rota rota = new Rota(origem, destino, distancia);
        rotas.add(rota);

        // Desenhar a rota no mapa
        List<Coordinate> coords = new ArrayList<>();
        coords.add(origem.getCoord());
        coords.add(destino.getCoord());
        map.addMapPolygon(new MapPolygonImpl(coords));

        JOptionPane.showMessageDialog(this,
                "Rota cadastrada:\n" + rota);
    }

    // Fórmula de Haversine para calcular distância em km
    public static double calcularDistancia(Coordinate c1, Coordinate c2) {
        final double R = 6371.0; // raio da Terra em km
        double lat1 = Math.toRadians(c1.getLat());
        double lon1 = Math.toRadians(c1.getLon());
        double lat2 = Math.toRadians(c2.getLat());
        double lon2 = Math.toRadians(c2.getLon());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
    
    public void inserirCentros(){
        Random rand = new Random();
        
        for(int i=0; i<200; i++){
            double x = -40.5 +2.0*(rand.nextDouble());
            double y = -13.0 +1.0*(rand.nextDouble());
            Coordinate coord = new Coordinate(y,x);
            CentroRecursos centro = new CentroRecursos("Centro "+i, coord);
            this.hashCentros.addCentroRec(centro);
            map.addMapMarker(new MapMarkerDot("Centro "+i, coord));
            lugares.add(centro);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
