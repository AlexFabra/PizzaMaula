import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Historial extends JFrame implements ActionListener{
    private JMenuBar mb;
    private JMenu finestra;
    private JMenuItem comanda,clients,historial,pizzes;
    private JLabel llistaClients, treureClient;
    private JTextField textTelf;
    private JScrollPane scrollpane1;
    private static JTextArea textAreaHistorial;
    private JButton botoEsborrarComanda,botoEsborrarComandes;
    private int SIZELETRA=18;
    //creació de l'objecte que gestiona la base de dades:
    private static PizzaMaulaConnBdd bd = new PizzaMaulaConnBdd();

    public Historial(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pizza Maula");
        getContentPane().setBackground(new Color(51,0,51));
        setIconImage(new ImageIcon(getClass().getResource("images/favicon.png")).getImage());

        mb = new JMenuBar();
        mb.setBackground(new Color(255, 127, 0));
        setJMenuBar(mb);

        finestra = new JMenu("Finestres");
        finestra.setBackground(new Color(255, 127, 0));
        finestra.setFont(new Font("Andale Mono", 1, SIZELETRA));
        finestra.setForeground(new Color(0, 0, 0));
        mb.add(finestra);

        comanda = new JMenuItem("Comandes");
        comanda.addActionListener(this);
        finestra.add(comanda);

        clients = new JMenuItem("Clients");
        clients.addActionListener(this);
        finestra.add(clients);

        historial = new JMenuItem("Historial");
        historial.addActionListener(this);
        finestra.add(historial);

        pizzes = new JMenuItem("Pizzes");
        pizzes.addActionListener(this);
        finestra.add(pizzes);

        llistaClients = new JLabel("Historial de comandes");
        llistaClients.setBounds(25,25,300,25);
        llistaClients.setFont(new Font("Andale Mono", 1, SIZELETRA));
        llistaClients.setForeground(new Color(255, 255, 255));
        add(llistaClients);

        textAreaHistorial = new JTextArea();
        textAreaHistorial.setEditable(true);
        textAreaHistorial.setBackground(new Color(255, 255, 255));
        textAreaHistorial.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textAreaHistorial.setForeground(new Color(0, 0, 0));
        //textAreaHistorial.setText(""); //aqui va la comanda de la base de dades.
        scrollpane1 = new JScrollPane(textAreaHistorial);
        scrollpane1.setBounds(25, 70, 300, 480);
        add(scrollpane1);

        treureClient = new JLabel("Esborrar comanda (introduir telèfon)");
        treureClient.setBounds(350,25,320,25);
        treureClient.setFont(new Font("Andale Mono", 1, 14));
        treureClient.setForeground(new Color(255, 255, 255));
        add(treureClient);

        textTelf = new JTextField();
        textTelf.setBounds(350,70,230,25);
        textTelf.setBackground(new Color(255, 255, 255));
        textTelf.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textTelf.setForeground(new Color(0, 0, 0));
        add(textTelf);

        botoEsborrarComanda = new JButton("Esborrar comanda");
        botoEsborrarComanda.setBounds(350,110,230,25);
        botoEsborrarComanda.setBackground(new Color(255,127,0));
        botoEsborrarComanda.setFont(new Font("Andale Mono", 1, SIZELETRA));
        botoEsborrarComanda.setForeground(new Color(51,0,51));
        botoEsborrarComanda.addActionListener(this);
        add(botoEsborrarComanda);

        botoEsborrarComandes = new JButton("Esborrar comandes");
        botoEsborrarComandes.setBounds(350,525,230,25);
        botoEsborrarComandes.setBackground(new Color(255,127,0));
        botoEsborrarComandes.setFont(new Font("Andale Mono", 1, SIZELETRA));
        botoEsborrarComandes.setForeground(new Color(51,0,51));
        botoEsborrarComandes.addActionListener(this);
        add(botoEsborrarComandes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == comanda){
            Principal finestraComandes = new Principal();
            finestraComandes.setBounds(0,0,650,650);
            finestraComandes.setVisible(true);
            finestraComandes.setResizable(true);
            finestraComandes.setLocationRelativeTo(null);
            this.setVisible(false);
        }

        if(e.getSource() == clients){
            Clients finestraClients = new Clients();
            finestraClients.setBounds(0,0,650,650);
            finestraClients.setVisible(true);
            finestraClients.setResizable(true);
            finestraClients.setLocationRelativeTo(null);
            this.setVisible(false);
            finestraClients.actualitza();
        }

        if(e.getSource() == pizzes){
            Pizzes finestraPizzes = new Pizzes();
            finestraPizzes.setBounds(0,0,650,650);
            finestraPizzes.setVisible(true);
            finestraPizzes.setResizable(true);
            finestraPizzes.setLocationRelativeTo(null);
            this.setVisible(false);
            finestraPizzes.actualitza();
        }

        if(e.getSource() == botoEsborrarComanda){
            String telf = textTelf.getText();
            bd.esborraComanda(telf);
            actualitza();
        }

        if(e.getSource()== botoEsborrarComandes){
           bd.esborraComandes();
           actualitza();
        }
    }

    public static void actualitza(){
        String historial = bd.actualitzaHistorial();
        textAreaHistorial.setText(historial);
    }

    public static void main(String args[]) {
            Historial finestraHistorial = new Historial();
            finestraHistorial.setBounds(0,0,650,650);
            finestraHistorial.setVisible(true);
            finestraHistorial.setResizable(true);
            finestraHistorial.setLocationRelativeTo(null);
            actualitza();
    }
}
