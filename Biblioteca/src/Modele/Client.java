package Modele;

import java.util.ArrayList;
import java.util.List;
import Metode.*;

public class Client {
    protected Integer id_client;
    protected String nume;
    protected String username;
    protected String parola;
    protected ImprumutaCarte ImprumutActual;
    protected List<ImprumutaCarte> Imprumuturi;

    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public Client(String nume, String username, String parola){
        this.id_client = idCounter;
        this.nume = nume;
        this.username = username;
        this.parola = parola;
        this.ImprumutActual = new ImprumutaCarte();
        this.Imprumuturi = new ArrayList<ImprumutaCarte>();
    }
    public Client(){
        this.id_client = idCounter;
        this.username="u";
        this.ImprumutActual = new ImprumutaCarte();
        this.Imprumuturi = new ArrayList<ImprumutaCarte>();
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getParola() {
        return parola;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public ImprumutaCarte getImprumutActual() {
        return ImprumutActual;
    }

    public List<ImprumutaCarte> getImprumuturi() {
        return Imprumuturi;
    }

    public void adaugaIstoricImprumuturi(){
        this.Imprumuturi.add(this.ImprumutActual);
        this.ImprumutActual = new ImprumutaCarte();
    }

    @Override
    public String toString() {
        return "Client{" +
                "nume='" + nume + '\'' +
                ", username='" + username + '\'' +
                ", ImprumutActual=" + ImprumutActual +
                ", Imprumuturi=" + Imprumuturi +
                '}';
    }
}
