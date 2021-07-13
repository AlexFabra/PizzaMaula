import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Clients extends JFrame implements ActionListener{
    private JMenuBar mb;
    private JMenu finestra;
    private JMenuItem comanda,clients,historial,pizzes;
    private JLabel llistaClients;
    private JLabel treureClient;
    private static JLabel labelInfo;
    private JTextField textTelf;
    private JScrollPane scrollpane1;
    private static JTextArea textAreaClients;
    private JButton botoTreureClient,botoGuardarCopiaSeguretat;
    private int SIZELETRA=18;

    //creació de l'objecte que gestiona la base de dades:
    private static PizzaMaulaConnBdd bd = new PizzaMaulaConnBdd();

    public Clients(){
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

        llistaClients = new JLabel("Llista de clients");
        llistaClients.setBounds(25,25,180,25);
        llistaClients.setFont(new Font("Andale Mono", 1, SIZELETRA));
        llistaClients.setForeground(new Color(255, 255, 255));
        add(llistaClients);

        textAreaClients = new JTextArea();
        textAreaClients.setEditable(true);
        textAreaClients.setBackground(new Color(255, 255, 255));
        textAreaClients.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textAreaClients.setForeground(new Color(0, 0, 0));
        textAreaClients.setText(""); //possibilitat de posar un text per defecte.
        scrollpane1 = new JScrollPane(textAreaClients);
        scrollpane1.setBounds(25,70,300,480);
        add(scrollpane1);

        treureClient = new JLabel("Esborrar client (introduir telèfon)");
        treureClient.setBounds(350,25,320,25);
        treureClient.setFont(new Font("Andale Mono", 1, 14));
        treureClient.setForeground(new Color(255, 255, 255));
        add(treureClient);

        labelInfo = new JLabel("");
        labelInfo.setBounds(350,180,320,25);
        labelInfo.setFont(new Font("Andale Mono", 1, 14));
        labelInfo.setForeground(new Color(255, 255, 255));
        add(labelInfo);

        textTelf = new JTextField();
        textTelf.setBounds(350,70,230,25);
        textTelf.setBackground(new Color(255, 255, 255));
        textTelf.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textTelf.setForeground(new Color(0, 0, 0));
        add(textTelf);

        botoTreureClient = new JButton("Esborrar");
        botoTreureClient.setBounds(350,110,230,25);
        botoTreureClient.setBackground(new Color(255,127,0));
        botoTreureClient.setFont(new Font("Andale Mono", 1, SIZELETRA));
        botoTreureClient.setForeground(new Color(51,0,51));
        botoTreureClient.addActionListener(this);
        add(botoTreureClient);

        botoGuardarCopiaSeguretat = new JButton("Guardar còpia de seguretat");
        botoGuardarCopiaSeguretat.setBounds(350,145,230,25);
        botoGuardarCopiaSeguretat.setBackground(new Color(255,127,0));
        botoGuardarCopiaSeguretat.setFont(new Font("Andale Mono", 1, 14));
        botoGuardarCopiaSeguretat.setForeground(new Color(51,0,51));
        botoGuardarCopiaSeguretat.addActionListener(this);
        add(botoGuardarCopiaSeguretat);


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

        if(e.getSource() == historial){
            Historial finestraHistorial = new Historial();
            finestraHistorial.setBounds(0,0,650,650);
            finestraHistorial.setVisible(true);
            finestraHistorial.setResizable(true);
            finestraHistorial.setLocationRelativeTo(null);
            this.setVisible(false);
            finestraHistorial.actualitza();
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

        if(e.getSource() == botoTreureClient){
            String telf = textTelf.getText();
            bd.esborraClient(telf);
            actualitza();
        }

        if(e.getSource() == botoGuardarCopiaSeguretat){
            copiaSeguretat();
        }

    }

    public static void actualitza(){
        String clients = bd.actualitzaClients();
        textAreaClients.setText(clients);
    }
    public static void copiaSeguretat(){
        Writer writer = null;
        try {
            //creem un arxiu per guardar la còpia de seguretat:
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:/Users/Public/Documents/ClientsPizzaMaula.txt")), "utf-8"));
            writer.write(bd.copiaSeguretat());
            labelInfo.setText("S'ha fet la còpia correctament.");
        } catch (IOException e) {
            //printStackTrace() serveix per veure l'error:
            e.printStackTrace();
            labelInfo.setText("No s'ha fet la còpia corectament.");
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

    public static void main(String args[]) {
        Clients finestraClients = new Clients();
        finestraClients.setBounds(0,0,650,650);
        finestraClients.setVisible(true);
        finestraClients.setResizable(true);
        finestraClients.setLocationRelativeTo(null);
        actualitza();
    }
}
