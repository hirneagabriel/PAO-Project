package Modele;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    protected Integer id_carte;
    protected String denumire;
    protected Editura editura;
    protected List<Autor> autori;

    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public Carte(String denumire, Editura editura) {
        this.denumire = denumire;
        this.editura = editura;
        this.autori = new ArrayList<Autor>();
        this.id_carte = idCounter;
    }
    public Carte(String denumire, Editura editura, Integer id) {
        this.denumire = denumire;
        this.editura = editura;
        this.autori = new ArrayList<Autor>();
        this.id_carte = id;

    }

    public Integer getId_carte() {
        return id_carte;
    }

    public void addAutor(Autor autor) {
        this.autori.add(autor);
    }

    public String getDenumire() {
        return denumire;
    }

    public Editura getEditura() {
        return editura;
    }

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }

    public void setEditura(Editura editura) {
        this.editura = editura;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "denumire='" + denumire + '\'' +
                ", editura=" + editura +
                ", autori=" + autori +
                '}';
    }
}
