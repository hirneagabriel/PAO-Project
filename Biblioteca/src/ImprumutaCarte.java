import java.util.ArrayList;
import java.util.List;

public class ImprumutaCarte {

    protected Integer IdComanda;
    protected Double pret;
    protected Double discount;
    protected List<Carte> carti;
    protected  Integer durata;

    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public ImprumutaCarte (Integer durata,Double discount)
    {
        this.pret = 0.00;
        this.durata = durata;
        this.discount = discount;
        this.IdComanda = idCounter;
        carti = new ArrayList<Carte>();
    }

    public ImprumutaCarte(ImprumutaCarte p) {
        this.pret = p.pret;
        this.durata = p.durata;
        this.discount= p.discount;
        this.IdComanda = p.IdComanda;
        this.carti=p.carti;
    }

    public ImprumutaCarte() {
        this.pret = 0.00;
        this.IdComanda = idCounter;
        carti = new ArrayList<Carte>();
    }

    public void adaugaCarte(Carte p){
        carti.add(p);
        pret += durata*(1-discount);
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getPret() {
        return pret;
    }

    public Integer getDurata() {
        return durata;
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "ImprumutaCarte{" +
                "IdComanda=" + IdComanda +
                ", pret=" + pret +
                ", carti=" + carti +
                ", durata=" + durata +
                '}';
    }
}
