public class Client {
    private String nom;
    private String telefon;
    private String direccio;

    public Client() {
        this.nom = null;
        this.telefon = null;
        this.direccio = null;
    }

    public Client(String nom, String telefon, String direccio){
        this.nom=nom;
        this.telefon=telefon;
        this.direccio=direccio;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCarrer() {
        return this.direccio;
    }

    public void setCarrer(String carrer) {
        this.direccio = carrer;
    }

    @Override
    public String toString() {
        return "Dades client: \n" +
            getNom() + "\n" +
            getTelefon() + "\n" +
            getCarrer() + "\n";
    }
}
