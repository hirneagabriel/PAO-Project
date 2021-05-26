package Metode;

import Modele.Autor;
import Modele.Carte;
import Modele.Editura;
import Modele.Sectiune;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CitireFisiere {

    private static CitireFisiere instance;

    private CitireFisiere() {

    }

    public static CitireFisiere getInstance() {
        if (instance == null){
            instance = new CitireFisiere();
        }
        return instance;
    }

    public Set<Sectiune> citesteSectiuni(){
        Set<Sectiune> sectiuni = new HashSet<Sectiune>();
        Map<String, Carte> carti = new HashMap<String, Carte>();
        try {
            CSVReader readSectiuni = new CSVReaderBuilder(new FileReader("Sectiuni.csv")).withSkipLines(1).build();
            CSVReader readCarti = new CSVReaderBuilder(new FileReader("Carti.csv")).withSkipLines(1).build();
            Map<String, Editura> edituri = new HashMap<String, Editura>(citesteEditura());
            Map<String, Autor> autori = new HashMap<String, Autor>(citesteAutori());
            List<String[]> Carti = readCarti.readAll();
            for(String[] linie : Carti){
                if(edituri.containsKey(linie[1])) {
                    Carte carte = new Carte(linie[0],edituri.get(linie[1]));
                    String[] autoriCarte = linie[2].split(",");
                    for(String a:autoriCarte){
                        if(autori.containsKey(a)){
                            carte.addAutor(autori.get(a));
                        }
                    }
                    carti.put(carte.getDenumire(),carte);
                }
            }
            List<String[]> Sectiuni = readSectiuni.readAll();
            for(String[] linie : Sectiuni){
                String[] cartiSectiune = linie[1].split(",");
                Sectiune sectiune = new Sectiune(linie[0]);
                for(String a:cartiSectiune){
                    if(carti.containsKey(a)){

                        sectiune.addCarte(carti.get(a));
                    }
                }
                sectiuni.add(sectiune);
            }

        }
        catch (IOException e)
        {
            System.out.println("Nu s-a gasit fisier intrare");
        }
        return sectiuni;
    }


    public Map<String, Autor> citesteAutori(){
        Map<String, Autor> autori = new HashMap<String, Autor>();
        try {
            CSVReader readAutori = new CSVReaderBuilder(new FileReader("Autori.csv")).withSkipLines(1).build();
            List<String[]> Autori = readAutori.readAll();
            for(String[] linie : Autori){
                Autor autor = new Autor(linie[0],linie[1]);
                autori.put(autor.getNume(),autor);
            }

        }
        catch (IOException e)
        {
            System.out.println("Nu s-a gasit fisier intrare");
        }
        return autori;
    }

    public Map<String, Editura> citesteEditura(){
        Map<String, Editura> edituri = new HashMap<String, Editura>();
        try {
            CSVReader readEdituri = new CSVReaderBuilder(new FileReader("Editura.csv")).withSkipLines(1).build();
            List<String[]> Edituri = readEdituri.readAll();
            for(String[] linie : Edituri){
                Editura editura = new Editura(linie[0],linie[1]);
                edituri.put(editura.getNume(),editura);
            }

        }
        catch (IOException e)
        {
            System.out.println("Nu s-a gasit fisier intrare");
        }
        return edituri;
    }
}
