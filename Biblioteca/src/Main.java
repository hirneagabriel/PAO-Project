import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
       /* Autor Autor1 = new Autor("Ion", "Roman");
        System.out.println(Autor1);

        Editura editura1 = new Editura("Humanitas", "str. Barajului nr 12");

        Carte Carte1 = new Carte("Amintiri din copilarie", editura1, 3);
        Carte1.addAutor(Autor1);
        System.out.println(Carte1);*/
       /* ;
        biblioteca.adaugaDate();
        biblioteca.logare();
        biblioteca.AdaugaSectiune();
        biblioteca.AdaugaCarte();*/
        /*Map<String, Client> clienti = new Hashtable<String, Client>();
        Student s= new Student();
        clienti.put(s.username, s);
        if(clienti.get(s.username) instanceof Student){
            System.out.println("student");*/
        Biblioteca biblioteca = Biblioteca.getInstance();
        biblioteca.adaugaDate();
        biblioteca.meniu1();
       /* biblioteca.logare();
        biblioteca.AdaugaEditura();
        biblioteca.afiseazaEdituri();*/
       // biblioteca.AdaugaEditura();
        /*biblioteca.InchiriereCarte();
        biblioteca.afiseazaIstoricImprumuturi();*/
        }

}

