package Metode;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Audit {
    private static Audit instance;
    private String locatiefisier;

    private Audit(String locatiefisier){
        this.locatiefisier = locatiefisier;
        File file = new File(locatiefisier);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {

            String[] header = {"TipActiune", "Data"};
            writer.writeNext(header);


        } catch (IOException e) {
            System.out.println("Nu s-a gasit fisierul CVS");
        }

    }
    public static Audit getInstance(String locatiefisier) {
        if (instance == null) {
            instance = new Audit(locatiefisier);
        }
        return instance;
    }

    public void scrieAudit(){

        try (CSVWriter writer = new CSVWriter(new FileWriter(locatiefisier, true))) {
            String numeMetoda = new Throwable().getStackTrace()[1].getMethodName();
            Timestamp data = new Timestamp(System.currentTimeMillis());
            String[] linie = {numeMetoda, String.valueOf(data)};
            writer.writeNext(linie);

        } catch (IOException e) {
            System.out.println("Nu s-a gasit fisierul CVS!");
        }



    }

}

