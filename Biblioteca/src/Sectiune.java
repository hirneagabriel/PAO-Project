import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Sectiune {
    protected Integer id_sectiune;
    protected String nume;
    protected Set<Carte> carti;

    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public Sectiune(String nume){
        this.nume = nume;
        this.id_sectiune=idCounter;
        carti = new HashSet<Carte>();
    }

    public Set<Carte> getCarti() {
        return carti;
    }

    public void addCarte(Carte carte){
        carti.add(carte);
    }

    public void showCarti(){
        for(Carte carte: carti){
            System.out.println(carte);
        }
    }

    public String getNume() {
        return nume;
    }

    @Override
    public String toString() {
        return "Sectiune{" +
                "nume='" + nume + '\'' +
                ", carti=" + carti +
                '}';
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
