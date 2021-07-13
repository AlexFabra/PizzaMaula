import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pizzes extends JFrame implements ActionListener {
    private JMenuBar mb;
    private JMenu finestra;
    private JMenuItem comanda, clients, historial, pizzes;
    private JLabel Pizza, Ingredient, AfegirPizza, TreurePizza, AfegirIngredient, TreureIngredient;
    private static JTextField textPizza;
    private static JTextField textIngredient;
    private JScrollPane scrollpanePizzes,scrollpaneIngredients;
    private static JTextArea textAreaPizzes,textAreaIngredients;
    private JButton botoEsborrarPizza,botoAfegirPizza,botoAfegirIngredient,botoEsborrarIngredient;
    private int SIZELETRA=18;
    //creació de l'objecte que gestiona la base de dades:
    private static PizzaMaulaConnBdd bd = new PizzaMaulaConnBdd();
    public Pizzes(){
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

        Pizza = new JLabel("Pizzes");
        Pizza.setBounds(25,35,300,25);
        Pizza.setFont(new Font("Andale Mono", 1, SIZELETRA));
        Pizza.setForeground(new Color(255, 255, 255));
        add(Pizza);

        Ingredient = new JLabel("Ingredients");
        Ingredient.setBounds(25,300,300,25);
        Ingredient.setFont(new Font("Andale Mono", 1, SIZELETRA));
        Ingredient.setForeground(new Color(255, 255, 255));
        add(Ingredient);

        AfegirPizza = new JLabel("Afegir o treure pizza");
        AfegirPizza.setBounds(350,35,320,25);
        AfegirPizza.setFont(new Font("Andale Mono", 1, SIZELETRA));
        AfegirPizza.setForeground(new Color(255, 255, 255));
        add(AfegirPizza);


        AfegirIngredient = new JLabel("Afegir o treure ingredient");
        AfegirIngredient.setBounds(350,300,300,25);
        AfegirIngredient.setFont(new Font("Andale Mono", 1, SIZELETRA));
        AfegirIngredient.setForeground(new Color(255, 255, 255));
        add(AfegirIngredient);

        textAreaPizzes = new JTextArea();
        textAreaPizzes.setEditable(false);
        textAreaPizzes.setBackground(new Color(255, 255, 255));
        textAreaPizzes.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textAreaPizzes.setForeground(new Color(0, 0, 0));
        //textAreaHistorial.setText(""); //aqui va la comanda de la base de dades.
        scrollpanePizzes = new JScrollPane(textAreaPizzes);
        scrollpanePizzes.setBounds(25, 70, 300, 225);
        add(scrollpanePizzes);

        textAreaIngredients = new JTextArea();
        textAreaIngredients.setEditable(false);
        textAreaIngredients.setBackground(new Color(255, 255, 255));
        textAreaIngredients.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textAreaIngredients.setForeground(new Color(0, 0, 0));
        //textAreaHistorial.setText(""); //aqui va la comanda de la base de dades.
        scrollpaneIngredients = new JScrollPane(textAreaIngredients);
        scrollpaneIngredients.setBounds(25, 330, 300, 225);
        add(scrollpaneIngredients);

        botoEsborrarPizza = new JButton("Esborrar pizza");
        botoEsborrarPizza.setBounds(350,140,230,25);
        botoEsborrarPizza.setBackground(new Color(255,127,0));
        botoEsborrarPizza.setFont(new Font("Andale Mono", 1, SIZELETRA));
        botoEsborrarPizza.setForeground(new Color(51,0,51));
        botoEsborrarPizza.addActionListener(this);
        add(botoEsborrarPizza);

        botoAfegirPizza = new JButton("Afegir pizza");
        botoAfegirPizza.setBounds(350,105,230,25);
        botoAfegirPizza.setBackground(new Color(255,127,0));
        botoAfegirPizza.setFont(new Font("Andale Mono", 1, SIZELETRA));
        botoAfegirPizza.setForeground(new Color(51,0,51));
        botoAfegirPizza.addActionListener(this);
        add(botoAfegirPizza);

        botoEsborrarIngredient = new JButton("Esborrar ingredient");
        botoEsborrarIngredient.setBounds(350,400,230,25);
        botoEsborrarIngredient.setBackground(new Color(255,127,0));
        botoEsborrarIngredient.setFont(new Font("Andale Mono", 1, SIZELETRA));
        botoEsborrarIngredient.setForeground(new Color(51,0,51));
        botoEsborrarIngredient.addActionListener(this);
        add(botoEsborrarIngredient);

        botoAfegirIngredient = new JButton("Afegir ingredient");
        botoAfegirIngredient.setBounds(350,365,230,25);
        botoAfegirIngredient.setBackground(new Color(255,127,0));
        botoAfegirIngredient.setFont(new Font("Andale Mono", 1, SIZELETRA));
        botoAfegirIngredient.setForeground(new Color(51,0,51));
        botoAfegirIngredient.addActionListener(this);
        add(botoAfegirIngredient);

        textPizza = new JTextField();
        textPizza.setBounds(350,70,230,25);
        textPizza.setBackground(new Color(255, 255, 255));
        textPizza.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textPizza.setForeground(new Color(0, 0, 0));
        add(textPizza);

        textIngredient = new JTextField();
        textIngredient.setBounds(350,330,230,25);
        textIngredient.setBackground(new Color(255, 255, 255));
        textIngredient.setFont(new Font("Andale Mono", 1, SIZELETRA));
        textIngredient.setForeground(new Color(0, 0, 0));
        add(textIngredient);
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

          if(e.getSource() == historial){
            Historial finestraHistorial = new Historial();
            finestraHistorial.setBounds(0,0,650,650);
            finestraHistorial.setVisible(true);
            finestraHistorial.setResizable(true);
            finestraHistorial.setLocationRelativeTo(null);
            this.setVisible(false);
            finestraHistorial.actualitza();
        }

        if(e.getSource()== botoAfegirPizza){
            bd.guardaPizza(textPizza.getText());
            actualitza();
        }
        if(e.getSource()== botoEsborrarPizza){
            bd.esborraPizza(textPizza.getText());
            actualitza();
        }
        if(e.getSource()== botoAfegirIngredient){
            bd.guardaIngredient(textIngredient.getText());
            actualitza();
        }
        if(e.getSource()== botoEsborrarIngredient){
            bd.esborraIngredient(textIngredient.getText());
            actualitza();
        }
    }
    public static void actualitza(){
            String ingredients = bd.actualitzaIngredients();
            textAreaIngredients.setText(ingredients);
            String pizzes = bd.actualitzaPizzes();
            textAreaPizzes.setText(pizzes);
            textIngredient.setText("");
            textPizza.setText("");
    }
    public static void main(String args[]) {
        Pizzes finestraPizzes = new Pizzes();
        finestraPizzes.setBounds(0,0,650,650);
        finestraPizzes.setVisible(true);
        finestraPizzes.setResizable(true);
        finestraPizzes.setLocationRelativeTo(null);
        actualitza();
    }

}
