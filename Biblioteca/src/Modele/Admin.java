package Modele;

public class Admin {
    private Integer id_admin;
    private String username;
    private String nume;
    private String parola;


    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public  Admin( String username, String nume, String parola){
        this.nume= nume;
        this.username = username;
        this.parola = parola;
        this.id_admin = idCounter;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "nume='" + nume + '\'' +
                '}';
    }

}
