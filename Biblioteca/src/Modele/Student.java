package Modele;
import Metode.ImprumutaCarte;

public class Student extends Client {
    private  double discount = 0.4;

    public Student(String nume, String username, String parola){
        super(nume,username,parola);
        ImprumutActual.setDiscount(discount);
    }
    public Student(){
        super();
        ImprumutActual.setDiscount(discount);
    }
    public void adaugaIstoricImprumuturi(){
        this.Imprumuturi.add(this.ImprumutActual);
        this.ImprumutActual = new ImprumutaCarte();
        this.ImprumutActual.setDiscount(discount);
    }
}
