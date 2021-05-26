package Modele;

public class Autor {
    protected Integer id_autor;
    protected String nume;
    protected String nationalitate;

    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public Autor(String nume,String nationalitate ){
        this.nume = nume;
        this.nationalitate = nationalitate;
        this.id_autor = idCounter;
    }

    public Integer getId_autor() {
        return id_autor;
    }

    public Autor(String nume, String nationalitate, Integer id ){
        this.nume = nume;
        this.nationalitate = nationalitate;
        this.id_autor = id;
    }

    public String getNume() {
        return nume;
    }

    public String getNationalitate() {
        return nationalitate;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nume='" + nume + '\'' +
                ", nationalitate='" + nationalitate + '\'' +
                '}';
    }
}
