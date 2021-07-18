import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame implements ActionListener{

  private JMenuBar mb;
  private JMenu finestra;
  private JMenuItem comanda,clients,historial,pizzes;
  private JLabel labelTitulo;
  private JLabel labelDescripcion;
  private JLabel nomClient;
  private JLabel Direccio;
  private static JLabel labelMostraErrors;
  private JLabel telf;
  private JLabel labelPizza;
  private JLabel labelExtra;
  private JLabel labelResta;
  private JLabel labelTemps;
  private JLabel labelFooter;
  private JTextField textNom,textDireccio,textTelf,textHora;
  private JComboBox comboPizzes,comboAfegit,comboRestar;
  private JScrollPane scrollpane1;
  private JTextArea textareaComanda;
  private JRadioButton recollir, emportar;
  private JButton botoAfegirPizza, botoAfegirIngredient,botoTreureIngredient, botoBuscarClient,
          botoGuardarClient, botoImprimirComanda, botoEsborrarComanda, botoAfegirHora;
  private int SIZELETRA=18;
  //creació de l'objecte que gestiona la base de dades:
  private static PizzaMaulaConnBdd bd = new PizzaMaulaConnBdd();

  public Principal() {
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

      labelTitulo = new JLabel("COMANDES");  
      labelTitulo.setBounds(25,25,300,30);
      labelTitulo.setFont(new Font("Tele-Marines", 1, 32));
      labelTitulo.setForeground(new Color(255, 255, 255));
      add(labelTitulo);

      nomClient = new JLabel("Nom i cognom");
      nomClient.setBounds(25,75,180,25);
      nomClient.setFont(new Font("Andale Mono", 1, SIZELETRA));
      nomClient.setForeground(new Color(255, 255, 255));
      add(nomClient);

      textNom = new JTextField();
      textNom.setBounds(25,110,150,25);
      textNom.setBackground(new Color(255, 255, 255));
      textNom.setFont(new Font("Andale Mono", 1, SIZELETRA));
      textNom.setForeground(new Color(0, 0, 0));
      add(textNom);

      telf = new JLabel("Telèfon");
      telf.setBounds(25,145,180,25);
      telf.setFont(new Font("Andale Mono", 1, SIZELETRA));
      telf.setForeground(new Color(255, 255, 255));
      add(telf);

      textTelf = new JTextField();
      textTelf.setBounds(25,180,150,25);
      textTelf.setBackground(new Color(255, 255, 255));
      textTelf.setFont(new Font("Andale Mono", 1, SIZELETRA));
      textTelf.setForeground(new Color(0, 0, 0));
      add(textTelf);

      Direccio = new JLabel("Direcció");
      Direccio.setBounds(25,215,180,25);
      Direccio.setFont(new Font("Andale Mono", 1, SIZELETRA));
      Direccio.setForeground(new Color(255, 255, 255));
      add(Direccio);

      textDireccio = new JTextField();
      textDireccio.setBounds(25,250,150,25);
      textDireccio.setBackground(new Color(255, 255, 255));
      textDireccio.setFont(new Font("Andale Mono", 1, SIZELETRA));
      textDireccio.setForeground(new Color(0, 0, 0));
      add(textDireccio);

      botoBuscarClient = new JButton("Buscar client");
      botoBuscarClient.setBounds(25,295,150,25);
      botoBuscarClient.setBackground(new Color(255,127,0));
      botoBuscarClient.setFont(new Font("Andale Mono", 1, 15));
      botoBuscarClient.setForeground(new Color(51,0,51));
      botoBuscarClient.addActionListener(this);
      add(botoBuscarClient);

      botoGuardarClient = new JButton("Guardar client");
      botoGuardarClient.setBounds(25,340,150,25);
      botoGuardarClient.setBackground(new Color(255,127,0));
      botoGuardarClient.setFont(new Font("Andale Mono", 1, 15));
      botoGuardarClient.setForeground(new Color(51,0,51));
      botoGuardarClient.addActionListener(this);
      add(botoGuardarClient);

    labelTemps= new JLabel("Hora");
    labelTemps.setBounds(25,385,150,25);
    labelTemps.setFont(new Font("Andale Mono", 1, SIZELETRA));
    labelTemps.setForeground(new Color(255, 255, 255));
    add(labelTemps);

    textHora = new JTextField();
    textHora.setBounds(25,420,150,25);
    textHora.setBackground(new Color(255, 255, 255));
    textHora.setFont(new Font("Andale Mono", 1, SIZELETRA));
    textHora.setForeground(new Color(0, 0, 0));
    add(textHora);

      labelPizza = new JLabel("Pizza");
      labelPizza.setBounds(220,75,180,25);
      labelPizza.setFont(new Font("Andale Mono", 1, SIZELETRA));
      labelPizza.setForeground(new Color(255, 255, 255));
      add(labelPizza);

      comboPizzes = new JComboBox();
      comboPizzes.setBounds(220,110,220,25);
      comboPizzes.setBackground(new Color(255, 255, 255));
      comboPizzes.setFont(new Font("Andale Mono", 1, SIZELETRA));
      comboPizzes.setForeground(new Color(0, 0, 0));
      add(comboPizzes);
      comboPizzes = bd.addPizzes(comboPizzes);
//Si no està conectat a la base de dades:
//      comboPizzes.addItem("Margarita");
//      comboPizzes.addItem("Pernil dolç i bolets");
//      comboPizzes.addItem("4 formatges");
//      comboPizzes.addItem("Barbacoa");
//      comboPizzes.addItem("Tonyina");
//      comboPizzes.addItem("Salmó fumat");
//      comboPizzes.addItem("Pernil ibèric de gla");
//      comboPizzes.addItem("Catalana - iaia Maria");
//      comboPizzes.addItem("Can Maula");
//      comboPizzes.addItem("Clot d'Espolla");
//      comboPizzes.addItem("Focaccia");

      //per mostrar tots els elements al desplegar:
      comboPizzes.setMaximumRowCount(15);

      labelExtra = new JLabel("Ingredient extra");
      labelExtra.setBounds(220,145,180,25);
      labelExtra.setFont(new Font("Andale Mono", 1, SIZELETRA));
      labelExtra.setForeground(new Color(255, 255, 255));
      add(labelExtra);

      comboAfegit = new JComboBox();
      comboAfegit.setBounds(220,180,220,25);
      comboAfegit.setBackground(new Color(255, 255, 255));
      comboAfegit.setFont(new Font("Andale Mono", 1, SIZELETRA));
      comboAfegit.setForeground(new Color(0, 0, 0));
      add(comboAfegit);
      comboAfegit = bd.addIngredients(comboAfegit);

      //per mostrar tots els elements al desplegar:
      comboAfegit.setMaximumRowCount(36);

      labelExtra = new JLabel("Restar ingredient");
      labelExtra.setBounds(220,215,180,25);
      labelExtra.setFont(new Font("Andale Mono", 1, SIZELETRA));
      labelExtra.setForeground(new Color(255, 255, 255));
      add(labelExtra);

      comboRestar = new JComboBox();
      comboRestar.setBounds(220,250,220,25);
      comboRestar.setBackground(new Color(255, 255, 255));
      comboRestar.setFont(new Font("Andale Mono", 1, SIZELETRA));
      comboRestar.setForeground(new Color(0, 0, 0));
      add(comboRestar);
      comboRestar = bd.addIngredients(comboRestar);

    //per mostrar tots els elements al desplegar:
      comboRestar.setMaximumRowCount(36);

      emportar =new JRadioButton("Per portar",false);
      emportar.setBounds(260,30,150,25);
      emportar.setBackground(new Color(255,127,0));
      emportar.setFont(new Font("Andale Mono", 1, SIZELETRA));
      emportar.setForeground(new Color(51,0,51));
      emportar.addActionListener(this);
      add(emportar);

      recollir =new JRadioButton("Per recollir",false);
      recollir.setBounds(450,30,150,25);
      recollir.setBackground(new Color(255,127,0));
      recollir.setFont(new Font("Andale Mono", 1, SIZELETRA));
      recollir.setForeground(new Color(51,0,51));
      recollir.addActionListener(this);
      add(recollir);

      botoAfegirPizza = new JButton("Afegir pizza");
      botoAfegirPizza.setBounds(450,110,150,25);
      botoAfegirPizza.setBackground(new Color(255,127,0));
      botoAfegirPizza.setFont(new Font("Andale Mono", 1, SIZELETRA));
      botoAfegirPizza.setForeground(new Color(51,0,51));
      botoAfegirPizza.addActionListener(this);
      add(botoAfegirPizza);

      botoAfegirIngredient = new JButton("Afegeix");
      botoAfegirIngredient.setBounds(450,180,150,25);
      botoAfegirIngredient.setBackground(new Color(255,127,0));
      botoAfegirIngredient.setFont(new Font("Andale Mono", 1, SIZELETRA));
      botoAfegirIngredient.setForeground(new Color(51,0,51));
      botoAfegirIngredient.addActionListener(this);
      add(botoAfegirIngredient);

      botoTreureIngredient = new JButton("Resta");
      botoTreureIngredient.setBounds(450,250,150,25);
      botoTreureIngredient.setBackground(new Color(255,127,0));
      botoTreureIngredient.setFont(new Font("Andale Mono", 1, SIZELETRA));
      botoTreureIngredient.setForeground(new Color(51,0,51));
      botoTreureIngredient.addActionListener(this);
      add(botoTreureIngredient);

      labelResta = new JLabel("Comanda Actual:");
      labelResta.setBounds(220,285,180,25);
      labelResta.setFont(new Font("Andale Mono", 1, SIZELETRA));
      labelResta.setForeground(new Color(255, 255, 255));
      add(labelResta);

      labelMostraErrors = new JLabel("");
      labelMostraErrors.setBounds(25,500,180,50);
      labelMostraErrors.setFont(new Font("Andale Mono", 1, 10));
      labelMostraErrors.setForeground(new Color(255, 255, 255));
      add(labelMostraErrors);

    textareaComanda = new JTextArea();
    textareaComanda.setEditable(true);
    textareaComanda.setBackground(new Color(255, 255, 255));
    textareaComanda.setFont(new Font("Andale Mono", 1, 14));
    textareaComanda.setForeground(new Color(0, 0, 0));
    textareaComanda.setText(""); //possibilitat de posar un text per defecte.
    scrollpane1 = new JScrollPane(textareaComanda);
    scrollpane1.setBounds(220,285,385,230);
    add(scrollpane1);

    botoImprimirComanda = new JButton("Imprimeix");
    botoImprimirComanda.setBounds(450,535,150,25);
    botoImprimirComanda.setBackground(new Color(255,127,0));
    botoImprimirComanda.setFont(new Font("Andale Mono", 1, 14));
    botoImprimirComanda.setForeground(new Color(51,0,51));
    botoImprimirComanda.addActionListener(this);
    add(botoImprimirComanda);

    botoEsborrarComanda = new JButton ("Esborra comanda");
    botoEsborrarComanda.setBounds(220,535,200,25);
    botoEsborrarComanda.setBackground(new Color(255,127,0));
    botoEsborrarComanda.setFont(new Font("Andale Mono", 1, 14));
    botoEsborrarComanda.setForeground(new Color(51,0,51));
    botoEsborrarComanda.addActionListener(this);
    add(botoEsborrarComanda);

  }

  public void actionPerformed(ActionEvent e) {
      String afegirAComanda = "";

      if(e.getSource() == clients){
        Clients finestraClients = new Clients();
        finestraClients.setBounds(0,0,650,650);
        finestraClients.setVisible(true);
        finestraClients.setResizable(true);
        finestraClients.setLocationRelativeTo(null);
        this.setVisible(false);
        finestraClients.actualitza();
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


      if(e.getSource() == botoAfegirPizza){
        afegirAComanda = "* ";
        afegirAComanda += comboPizzes.getSelectedItem().toString();
        afegirAComanda += "\n";
        textareaComanda.setText(textareaComanda.getText() + afegirAComanda);
      }

      if(e.getSource() == botoAfegirIngredient){
        afegirAComanda = "+ ";
        afegirAComanda += comboAfegit.getSelectedItem().toString();
        afegirAComanda += "\n";
        textareaComanda.setText(textareaComanda.getText() + afegirAComanda);
      }

      if(e.getSource() == botoTreureIngredient){
        afegirAComanda = "- ";
        afegirAComanda += comboRestar.getSelectedItem().toString();
        afegirAComanda += "\n";
        textareaComanda.setText(textareaComanda.getText() + afegirAComanda);
      }

      if(e.getSource()== botoGuardarClient){
        String nom = textNom.getText();
        String telf = textTelf.getText();

        if(nom.equals("")){
          setLabelMostraErrors("cal posar nom.");
        } else {
          if(bd.trobaNom(nom).equals(nom)){
            setLabelMostraErrors("<html><br/>Aquest nom ja hi és a la base<br/>de dades. Posa un altre</html>");
          } else {
            if (!bd.trobaTelf(telf).equals("")) {
              setLabelMostraErrors("<html><br/>Aquest telèfon ja hi és a la base <br/>de dades.</html>");
              afegirAComanda += bd.trobaDades(telf);
              textareaComanda.setText(textareaComanda.getText() + afegirAComanda);
            } else {
              String direccio = textDireccio.getText();
              afegirAComanda = "Dades client: \n" + nom + " \n" + telf + " \n" + direccio + "\n" + "****************" + "\n";
              Client x = new Client(nom, telf, direccio);
              bd.guardaDades(x);
              textareaComanda.setText(afegirAComanda + textareaComanda.getText());
            }
          }
        }
      }

      if(e.getSource()== botoBuscarClient){

        String telf = ""+ textTelf.getText();
        String nombre= ""+textNom.getText();
        //si el telèfon està vuit, significa que s'ha posat el nom
        if(telf.equals("")) {
          afegirAComanda += bd.trobaDadesPerNom(nombre);
          textTelf.setText(bd.telefonPerNom(nombre));
          afegirAComanda += "****************" + "\n";
        } else if (nombre.equals("")) {
          //per afegir a la comanda les dades del client mitjaçant el telèfon:
          afegirAComanda += bd.trobaDades(telf);
          //per que el nom pasi a l'historial de comandes ha de estar apuntat en 'textNom':
          textNom.setText(bd.nomPerTelefon(telf));
          afegirAComanda += "****************" + "\n";
        }
        if(!telf.equals("")&&!nombre.equals("")){
          setLabelMostraErrors("<html>Per buscar client, posar només <br/>telf o només nom</html>");
        }


        textareaComanda.setText(afegirAComanda + textareaComanda.getText());
      }

      if(e.getSource()== botoEsborrarComanda){
        afegirAComanda = "";
        textareaComanda.setText("");
      }
      //si es clica al botó 'recollir' es treu el clic del botó 'emportar':
      if(e.getSource()==recollir && emportar.isSelected()==true){
        emportar.setSelected(false);
      }
      if(e.getSource()==emportar && recollir.isSelected()==true){
        recollir.setSelected(false);
      }

      if(e.getSource()==botoImprimirComanda){
        String nom = textNom.getText();
        String telf = textTelf.getText();
        String hora = textHora.getText();
        //gestionamos la entrada de la hora para la base de datos:
        String horaSinPunto = hora.replace(".","");
        int horaInt=0;
        horaInt = Integer.valueOf(horaSinPunto);
        bd.guardaComanda(nom,telf,horaInt);

        afegirAComanda = "Per les " + hora + "   ";

        if(recollir.isSelected()==true){
          recollir.setSelected(false);
          afegirAComanda += "RECOLLIR";
        }
        if(emportar.isSelected()==true){
          emportar.setSelected(false);
          afegirAComanda += "PORTAR";
        }
        afegirAComanda += "\n" + "****************" + "\n";

        textareaComanda.setText(afegirAComanda + textareaComanda.getText());

        Impresora.imprimir(textareaComanda.getText());

        esborrarDadesComanda();
      }
  }

  public static void setLabelMostraErrors(String text) {
    labelMostraErrors.setText(text);
  }

  public static String getLabelMostraErrors() {
    String x = labelMostraErrors.getText();
    return x;
  }

  private void esborrarDadesComanda() {
    textNom.setText("");
    textDireccio.setText("");
    textTelf.setText("");
    textHora.setText("");
    textareaComanda.setText("");
  }
  public static void main(String args[]) {
    Principal finestraComandes = new Principal();
    finestraComandes.setBounds(0,0,650,650);
    finestraComandes.setVisible(true);
    finestraComandes.setResizable(true);
    finestraComandes.setLocationRelativeTo(null);
  }
}

