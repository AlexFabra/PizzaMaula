import javax.swing.*;
import java.sql.*;
public class PizzaMaulaConnBdd{

    //Dades de connexió a la base de dades
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_ROUTE = "jdbc:mysql://localhost:3306" +
            "/pizzamaulabdd";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "choripan1997";

    //Sentències
    private static final String SELECT_FROM_CLIENTS = "SELECT * FROM clients ORDER BY nom";
    private static final String SELECT_CLIENT_PER_TELEFON = "SELECT nom,direccio FROM clients WHERE telf=?";
    private static final String SELECT_NOM_CLIENT_PER_TELEFON = "SELECT nom FROM clients WHERE telf=?";
    private static final String SELECT_NOM_CLIENT = "SELECT nom FROM clients WHERE nom=?";
    private static final String SELECT_TELEFON_CLIENT = "SELECT telf FROM clients WHERE telf=?";
    private static final String SELECT_TELEFON_CLIENT_PER_NOM = "SELECT telf FROM clients WHERE nom=?";
    private static final String SELECT_CLIENT_PER_NOM = "SELECT telf,direccio FROM clients WHERE nom=?";

    private static final String GUARDA_CLIENT = "INSERT INTO clients (nom,telf,direccio) VALUES (?,?,?)";
    private static final String ESBORRA_CLIENT = "DELETE FROM clients WHERE telf=?";

//    private static final String UPDATE_DIRECCIO = "UPDATE clients SET direccio=? WHERE telf=?";

    private static final String ESBORRA_COMANDA = "DELETE FROM historial WHERE telf=?";
    private static final String ESBORRA_COMANDES = "DELETE from historial";
    private static final String SELECT_FROM_HISTORIAL = "SELECT horaEntrega,nom,telf FROM historial ORDER BY horaEntrega";
    private static final String GUARDA_COMANDA = "INSERT INTO historial (horaEntrega,nom,telf) VALUES (?,?,?)";

    private static final String SELECT_PIZZES = "SELECT * FROM pizzes";
    private static final String SELECT_INGREDIENTS = "SELECT * FROM ingredients";
    private static final String GUARDA_PIZZA = "INSERT INTO pizzes (pizza) VALUES (?)";
    private static final String GUARDA_INGREDIENT = "INSERT INTO ingredients (ingredient) VALUES (?)";
    private static final String ESBORRA_PIZZA = "DELETE FROM pizzes where pizza=?";
    private static final String ESBORRA_INGREDIENT = "DELETE from ingredients where ingredient=?";

    //Connexió a la base de dades
    private Connection conn;

    public PizzaMaulaConnBdd()
    {
        try
        {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_ROUTE, DB_USER, DB_PASS);
        }catch(Exception e)
        {
            System.out.println("S'ha produit un error en intentar connectar amb la base de dades. Revisa els paràmetres");
            Principal.setLabelMostraErrors("S'ha produit un error en intentar connectar \n" +
                    " amb la base de dades. Revisa els paràmetres");
        }
    }

    public JComboBox addPizzes(JComboBox comboPizzes){
        try {
            PreparedStatement pe = conn.prepareStatement(SELECT_PIZZES);
            ResultSet rs = pe.executeQuery();
            while (rs.next()) {
                comboPizzes.addItem(rs.getString("pizza"));
            }
        }catch(SQLException error){
            System.out.println(error);
        }
        return comboPizzes;
    }

    public JComboBox addIngredients(JComboBox comboIngredients){
        try {
            PreparedStatement pe = conn.prepareStatement(SELECT_INGREDIENTS);
            ResultSet rs = pe.executeQuery();
            while (rs.next()) {
                comboIngredients.addItem(rs.getString("ingredient"));
            }
        }catch(SQLException error){
            System.out.println(error);
        }
        return comboIngredients;
    }

    public String actualitzaPizzes(){
        String pizzes="";
        try {
            PreparedStatement pe = conn.prepareStatement(SELECT_PIZZES);
            ResultSet rs = pe.executeQuery();
            while (rs.next()) {
                pizzes+=rs.getString("pizza")+"\n";
            }
        }catch(SQLException error){
            pizzes = "no s'han actualitzat les pizzes";
        }
        return pizzes;
    }

    public String actualitzaIngredients(){
        String ingredients="";
        try {
            PreparedStatement pe = conn.prepareStatement(SELECT_INGREDIENTS);
            ResultSet rs = pe.executeQuery();
            while (rs.next()) {
                ingredients+=rs.getString("ingredient")+"\n";
            }

        }catch(SQLException error){
            ingredients="no s'han actualitzat els ingredients";
        }
        return ingredients;
    }

    public void guardaPizza(String pizza){
        try{
            PreparedStatement pe = conn.prepareStatement(GUARDA_PIZZA);
            pe.setString(1, pizza);
            pe.executeUpdate();
            System.out.println("S'ha guardat la pizza correctament.");
        }catch(SQLException error){
            System.out.println("No s'ha guardat la pizza.");
            System.out.println(error);
        }
    }
    public void guardaIngredient(String ingredient){
        try{
            PreparedStatement pe = conn.prepareStatement(GUARDA_INGREDIENT);
            pe.setString(1, ingredient);
            pe.executeUpdate();
            System.out.println("S'ha guardat el ingredient correctament.");
        }catch(SQLException error){
            System.out.println("No s'ha guardat el ingredient.");
            System.out.println(error);
        }
    }
    public void esborraPizza(String pizza){
        try{
            PreparedStatement pe = conn.prepareStatement(ESBORRA_PIZZA);
            pe.setString(1,pizza);
            pe.executeUpdate();
            System.out.println("S'ha esborrat correctament.");
        }catch(SQLException error){
            System.out.println("No s'ha esborrat.");
            System.out.println(error);
        }
    }
    public void esborraIngredient(String ingredient){
        try{
            PreparedStatement pe = conn.prepareStatement(ESBORRA_INGREDIENT);
            pe.setString(1,ingredient);
            pe.executeUpdate();
            System.out.println("S'ha esborrat correctament.");
        }catch(SQLException error){
            System.out.println("No s'ha esborrat.");
            System.out.println(error);
        }
    }

    public String trobaNom(String nom){
        try{
            PreparedStatement pe = conn.prepareStatement(SELECT_NOM_CLIENT);
            pe.setString(1,nom);
            ResultSet rs = pe.executeQuery();
            rs.next();
            nom = rs.getString("nom");
        } catch(Exception e){
            //Principal.setLabelMostraErrors("<html>No s'ha trobat el client<br/>pel nom.</html>");
            nom="";
        }
        return nom;
    }
    public String trobaTelf(String telf){
        try{
            PreparedStatement pe = conn.prepareStatement(SELECT_TELEFON_CLIENT);
            pe.setString(1,telf);
            ResultSet rs = pe.executeQuery();
            rs.next();
            telf = rs.getString("telf");
        } catch(Exception e){
            //Principal.setLabelMostraErrors("<html>No s'ha trobat el client<br/>pel telefon.</html>");
            telf="";
        }
        return telf;
    }

    public String nomPerTelefon(String tlf){
        String nom;
        try{
            PreparedStatement pe = conn.prepareStatement(SELECT_NOM_CLIENT_PER_TELEFON);
            pe.setString(1,tlf);
            ResultSet rs = pe.executeQuery();
            rs.next();
            nom = rs.getString("nom");
        } catch(Exception e){
            Principal.setLabelMostraErrors("<html>No s'ha trobat el client<br/>pel telefon.</html>");
            nom="";
        }
        return nom;
    }
    public String telefonPerNom(String nom){
        String telf;
        try{
            PreparedStatement pe = conn.prepareStatement(SELECT_TELEFON_CLIENT_PER_NOM);
            pe.setString(1,nom);
            ResultSet rs = pe.executeQuery();
            rs.next();
            telf = rs.getString("telf");
        } catch(Exception e){
            Principal.setLabelMostraErrors("<html>No s'ha trobat el client<br/>pel nom.</html>");
            telf="";
        }
        return telf;
    }

    public String trobaDades (String tlf)
    {
        String nom;
        String direccio;
        String dades;
        try
        {
            //Preparem la consulta a fer a la base de dades
            PreparedStatement pe = conn.prepareStatement(SELECT_CLIENT_PER_TELEFON);
            pe.setString(1,tlf);
            //Executem la consulta a fer a la base de dades, guardem la resposta en un ResultSet i el recorrem.
            ResultSet rs = pe.executeQuery();
            rs.next();
            nom = rs.getString("nom");
            direccio = rs.getString("direccio");
            dades= "Dades client: \n" +
            nom + "\n"+
            tlf + "\n" +
            direccio + "\n";

        } catch (SQLException error)
        {
            System.out.println("No s'ha trobat el client.");
            System.out.println(error);
            dades="";
        }
        return dades;
    }
    public String copiaSeguretat(){
        String dades="";
        try{
            //Preparem la consulta a fer a la base de dades
            PreparedStatement pe = conn.prepareStatement(SELECT_FROM_CLIENTS);
            ResultSet rs = pe.executeQuery();
            while (rs.next()) {
                dades+="INSERT INTO clients (`nom`,`telf`,`direccio`) VALUES ('";
                dades+=rs.getString("nom")+"','";
                dades+=rs.getString("telf")+"','";
                dades+=rs.getString("direccio")+"');\n";
            }

        }catch(SQLException error){
            dades="no s'han actualitzat els ingredients";
        }
        return dades;
    }

    public String trobaDadesPerNom (String nombre)
    {
        String telefon;
        String direccio;
        String dades;

        try
        {
            //Preparem la consulta a fer a la base de dades
            PreparedStatement pe = conn.prepareStatement(SELECT_CLIENT_PER_NOM);
            pe.setString(1,nombre);
            //Executem la consulta a fer a la base de dades, guardem la resposta en un ResultSet i el recorrem.
            ResultSet rs = pe.executeQuery();
            rs.next();
            telefon = rs.getString("telf");
            direccio = rs.getString("direccio");

            dades= "Dades client: \n" +
                    nombre + "\n"+
                    telefon + "\n" +
                    direccio + "\n";

        } catch (SQLException error)
        {
            System.out.println("No s'ha trobat el client.");
            System.out.println(error);
            Principal.setLabelMostraErrors("No s'ha trobat el client pel nom");
            dades="";
        }
        return dades;
    }

    public void guardaDades(Client x){
        try{
            PreparedStatement pe = conn.prepareStatement(GUARDA_CLIENT);
            pe.setString(1, x.getNom());
            pe.setString(2, x.getTelefon());
            pe.setString(3, x.getCarrer());
            pe.executeUpdate();
            System.out.println("S'ha guardat el client correctament.");
        }catch(SQLException error){
            Principal.setLabelMostraErrors("No s'ha guardat el client");
            System.out.println("No s'ha guardat el client.");
            System.out.println(error);
        }
    }

    public void esborraComanda(String telf){
        try{
            PreparedStatement pe = conn.prepareStatement(ESBORRA_COMANDA);
            pe.setString(1, telf);
            pe.executeUpdate();
            System.out.println("S'ha esborrat la comanda correctament.");
        }catch(SQLException error){
            System.out.println("No s'ha esborrat la comanda.");
            System.out.println(error);
        }
    }

    public void esborraComandes(){
        try{
            PreparedStatement pe=conn.prepareStatement(ESBORRA_COMANDES);
            pe.executeUpdate();
            System.out.println("S'ha esborrat tot el historial");
        }catch(SQLException error){
            System.out.println("No s'ha pogut esborrar el historial.");
        }
    }

    public void guardaComanda(String nom,String telf, int hora){
        try{
            PreparedStatement pe = conn.prepareStatement(GUARDA_COMANDA);
            pe.setInt(1, hora);
            pe.setString(2, nom);
            pe.setString(3, telf);
            pe.executeUpdate();
            System.out.println("S'ha guardat correctament.");
        }catch(SQLException error){
            System.out.println("No s'ha guardat el client a l'historial de comandes.");
            Principal.setLabelMostraErrors("No s'ha guardat el client al historial de comandes");
            System.out.println(error);
        }
    }

    public void esborraClient(String telefon){
        try{
            PreparedStatement pe = conn.prepareStatement(ESBORRA_CLIENT);
            pe.setString(1,telefon);
            pe.executeUpdate();
            System.out.println("S'ha esborrat correctament.");
        }catch(SQLException error){
            System.out.println("No s'ha esborrat el client.");
            System.out.println(error);
        }
    }

    public String actualitzaClients(){
        String clients="";
        try {
            PreparedStatement pe = conn.prepareStatement(SELECT_FROM_CLIENTS);
            ResultSet rs = pe.executeQuery();
            while (rs.next()) {
                clients+=rs.getString("nom")+" - ";
                clients+=rs.getString("telf")+"\n";
            }

            }catch(SQLException error){
            System.out.println("No s'han actualitzat els clients.");
            Principal.setLabelMostraErrors("No s'han actualitzat els clients");
            System.out.println(error);
        }
        return clients;
    }
    public String actualitzaHistorial(){
        String historialComandes="";
        try {
            PreparedStatement pe = conn.prepareStatement(SELECT_FROM_HISTORIAL);
            ResultSet rs = pe.executeQuery();
            while (rs.next()) {
                int horaInt=rs.getInt("horaEntrega");
                String hora=String.valueOf(horaInt);
                hora = new StringBuilder(hora).insert(hora.length()-2, ".").toString();
                historialComandes+=hora+"\n";
                historialComandes+=rs.getString("nom")+" - ";
                historialComandes+=rs.getString("telf")+"\n\n";
            }
        }catch(SQLException error){
            System.out.println("No s'han actualitzat els clients.");
            Principal.setLabelMostraErrors("No s'han actualitzat els clients del historial");
            System.out.println(error);
        }
        return historialComandes;
    }
}