package Modele;

public class Editura {
    protected Integer id_editura;
    protected String nume;
    protected String adresa;

    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public Editura(String nume, String adresa) {
        this.adresa = adresa;
        this.nume = nume;
        this.id_editura = idCounter;
    }
    public Editura(String nume, String adresa, Integer id) {
        this.adresa = adresa;
        this.nume = nume;
        this.id_editura = id;
    }


    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public Integer getId_editura() {
        return id_editura;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Editura{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
