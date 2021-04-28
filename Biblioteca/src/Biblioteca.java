import java.util.*;

public class Biblioteca {
    private static Biblioteca instance;
    private Map<String, Client> clienti = new Hashtable<String, Client>();
    private Map<String, Admin> admini = new Hashtable<String, Admin>();
    private Set<Sectiune> sectiuni = new HashSet<Sectiune>();
    private Map<String, Autor> autori = new Hashtable<String, Autor>();
    private Map<String, Editura> editura = new Hashtable<String, Editura>();
    private String usernameLog;
    private CitireFisiere citireFisiere;
    private Audit audit;
    private Scanner scanner = new Scanner(System.in);

    private Biblioteca() {
        System.out.println("Citind construim o lume mai buna!");
        Admin a = new Admin("admin", "admin", "admin");
        admini.put("admin", a);
        audit = Audit.getInstance("Audit.csv");
        citireFisiere = CitireFisiere.getInstance();
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    public void inregistrare() {
        audit.scrieAudit();
        System.out.println("Inregistrare client");
        System.out.println("Introduceti username");
        String username = scanner.next();
        if (clienti.containsKey(username)) {
            System.out.println("Username invalid.");
            inregistrare();
            return;
        }
        System.out.println("Introduceti parola:");
        String parola = scanner.next();
        System.out.println("Reintroduceti parola pentru confirmare:");
        String confirmare = scanner.next();
        if (!Objects.equals(parola, confirmare)) {
            System.out.println("Parolele nu coincid");
            inregistrare();
            return;
        }
        System.out.println("Introduceti nume:");
        scanner.next();
        String nume = scanner.nextLine();
        System.out.println("Sunteti student?A/F");
        String student = scanner.next();
        if (Objects.equals(student, "A")) {
            Student cl = new Student();
            cl.setNume(nume);
            cl.setParola(parola);
            cl.setUsername(username);
            clienti.put(username, cl);
            System.out.println("Acum puteti sa inchiriati o carte");
            System.out.println("Date client");
            System.out.println(cl);
            meniu3();
        } else {
            Client cl = new Client();
            cl.setNume(nume);
            cl.setParola(parola);
            cl.setUsername(username);
            clienti.put(username, cl);
            System.out.println("Acum puteti sa inchiriati o carte");
            System.out.println("Date client");
            System.out.println(cl);
            meniu3();
        }

    }

    public void logare() {
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            System.out.println("Deja sunteti logat");
            return;
        }
        System.out.println("Log in");
        System.out.println("Sunteti admin?A/F");
        String raspuns = scanner.next();
        System.out.println("Username: ");
        String username = scanner.next();
        if (Objects.equals(raspuns, "A")) {
            if (!admini.containsKey(username)) {
                System.out.println("NU exista acest utilizator");
                logare();
                return;
            }
            System.out.println("introduceti parola:");
            String parola = scanner.next();
            if (Objects.equals(parola, admini.get(username).getParola())) {
                usernameLog = username;
                System.out.println("Zona administrativa");
                meniu2();
            } else {
                System.out.println("Date incorecte");
                logare();
                return;
            }
        } else {
            if (!clienti.containsKey(username)) {
                System.out.println("NU exista acest utilizator");
                logare();
                return;
            }
            System.out.println("introduceti parola:");
            String parola = scanner.next();
            if (Objects.equals(parola, clienti.get(username).getParola())) {
                usernameLog = username;
                System.out.println("Buna ziua draga cititorule");
                meniu3();
            } else {
                System.out.println("Date incorecte");
                logare();
                return;
            }
        }

    }

    public void logout() {
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            System.out.println("Delogare");
        }
        usernameLog = null;
        meniu1();
    }

    public void AdaugaSectiune() {
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            if (admini.containsKey(usernameLog)) {
                System.out.println("Nume Sectiune:");
                String nume = scanner.next();
                Sectiune s = new Sectiune(nume);
                if (sectiuni.contains(s)) {
                    System.out.println("Sectiunea deja exista");
                } else {
                    sectiuni.add(s);
                    System.out.println("Sectiune adaugata");
                    meniu2();
                }
            } else {
                System.out.println("Nu aveti aceste drepturi!");
            }
        } else {
            System.out.println("Nu sunteti logat!");
        }

    }

    public  void AdaugaAutor(){
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            if (admini.containsKey(usernameLog)) {
                System.out.println("Nume Autor:");
                scanner.nextLine();
                String nume = scanner.nextLine();
                System.out.println("Nationalitate:");
                String nat = scanner.next();
                Autor a = new Autor(nume,nat);
                if (autori.containsKey(nume)) {
                    System.out.println("Autorul deja exista");
                } else {
                    autori.put(nume,a);
                    System.out.println("Autor adaugat");
                    meniu2();
                }
            } else {
                System.out.println("Nu aveti aceste drepturi");
            }
        } else {
            System.out.println("Nu sunteti logat!");
        }

    }

    public  void AdaugaEditura(){
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            if (admini.containsKey(usernameLog)) {
                System.out.println("Nume Editura:");
                scanner.nextLine();
                String nume = scanner.nextLine();
                System.out.println("Adresa:");
                String adress = scanner.nextLine();
                Editura a = new Editura(nume,adress);
                if (editura.containsKey(nume)) {
                    System.out.println("Editura deja exista");
                } else {
                    editura.put(nume,a);
                    System.out.println("Editura adaugata");
                    meniu2();
                }
            } else {
                System.out.println("Nu aveti aceste drepturi");
            }
        } else {
            System.out.println("Nu sunteti logat!");
        }

    }

    public void adaugaDate() {
//        Autor a1 = new Autor("Ion Creanga", "Roman");
//        Autor a2 = new Autor("I. L. Caragiale", "Roman");
//        Autor a3 = new Autor("Jules Vernes", "Francez");
//        Autor a4 = new Autor("Agatha Christie", "Englez");
//        autori.put(a1.getNume(), a1);
//        autori.put(a2.getNume(), a2);
//        autori.put(a3.getNume(), a3);
//        autori.put(a4.getNume(), a4);
        autori.putAll(citireFisiere.citesteAutori());
        editura.putAll(citireFisiere.citesteEditura());
        sectiuni.addAll(citireFisiere.citesteSectiuni());
        Student stud = new Student("Ion", "master", "parola");
        clienti.put(stud.username, stud);
//        Editura e1 = new Editura("Humanitas", "adresa...");
//        Editura e2 = new Editura("Aramis", "adresa...");
//        editura.putAll(citireFisiere.citesteEditura());
//        Carte c1 = new Carte("2000 de leghe sub mari", e1);
//        c1.addAutor(a3);
//        Carte c2 = new Carte("Insula misterioasa", e1);
//        c2.addAutor(a3);
//        Sectiune s1 = new Sectiune("Aventura");
//        s1.addCarte(c1);
//        s1.addCarte(c2);
//        Sectiune s2 = new Sectiune("Mister");
//        Sectiune s3 = new Sectiune("Comedie");
//        sectiuni.add(s1);
//        sectiuni.add(s2);
//        sectiuni.add(s3);


    }

    public void AdaugaCarte() {
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            if (admini.containsKey(usernameLog)) {
                System.out.println("Nume carte:");
                scanner.next();
                String nume = scanner.nextLine();
                System.out.println();
                System.out.println("Editura:");
                String e = scanner.next();
                if (editura.containsKey(e)) {
                    Carte c = new Carte(nume, editura.get(e));
                    int ok = 1;
                    while (ok == 1) {
                        System.out.println("Adaugati autori:");
                        scanner.nextLine();
                        String numeAutori = scanner.nextLine();
                        if (autori.containsKey(numeAutori)) {
                            c.addAutor(autori.get(numeAutori));

                            System.out.println("Doriti sa adaugati si alti autori?A/F");
                            String verificare = scanner.next();
                            if (!Objects.equals(verificare, "A")) {
                                ok = 0;
                            }
                        } else {
                            System.out.println("Autorul nu exista");
                            AdaugaCarte();
                            return;
                        }
                    }
                    System.out.println("In ce sectiune doriti sa adugati cartea?");
                    String numeSectiune = scanner.next();
                    ok = 1;
                    for (Sectiune sectiune : sectiuni) {
                        if (Objects.equals(sectiune.nume, numeSectiune)) {
                            sectiune.addCarte(c);
                            System.out.println("Carte adaugata");
                            ok = 0;
                            meniu2();
                        }
                    }
                    if (ok == 1) {
                        System.out.println("Sectiunea nu exista");
                        AdaugaCarte();
                        return;
                    }

                } else {
                    System.out.println("Editura nu exista");
                    AdaugaCarte();
                    return;
                }

            } else {
                System.out.println("Nu aveti aceste drepturi");
            }
        } else {
            System.out.println("Nu sunteti logat!");
        }

    }

    public void afisareSec1(){
        audit.scrieAudit();
        System.out.println("Sectiuni:");
        for (Sectiune sectiune : sectiuni) {
            System.out.println(sectiune.getNume());

        }
    }
    public void afisareSectiuni() {
        audit.scrieAudit();
        System.out.println("Sectiuni:");
        for (Sectiune sectiune : sectiuni) {
            System.out.println(sectiune.getNume());

        }
        if (admini.containsKey(usernameLog)) {
            meniu2();
        }else {
            meniu3();
        }
    }

    public void afiseazaAutor(){
        audit.scrieAudit();
        System.out.println("Autori:");
        Iterator<String> itr = autori.keySet().iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        if (admini.containsKey(usernameLog)) {
            meniu2();
        }else {
            meniu3();
        }
    }

    public void afiseazaEdituri(){
        audit.scrieAudit();
        System.out.println("Edituri:");
        Iterator<String> itr = editura.keySet().iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        if (admini.containsKey(usernameLog)) {
            meniu2();
        }else {
            meniu3();
        }

    }

    public void meniu1(){
        System.out.println("Alegeti un numar:");
        System.out.println("1. Logativa");
        System.out.println("2. Inregistrativa");
        System.out.println("3. Exit");
        int optiune = scanner.nextInt();
        switch (optiune){
            case 1 -> {
                logare();
                break;
            }
            case 2 ->{
                inregistrare();
                break;
            }
            default -> {
                return;
            }

        }
    }
    public void meniu2(){
        System.out.println("Alegeti un numar:");
        System.out.println("1. Adaugati sectiune");
        System.out.println("2. Adaugati autor");
        System.out.println("3. Adaugati editura");
        System.out.println("4. Adaugati carte");
        System.out.println("5. Vizualizati autorii");
        System.out.println("6. Vizualizati editurile");
        System.out.println("7. Vizualizati sectiunile");
        System.out.println("8. Delogare");
        int optiune = scanner.nextInt();
        switch (optiune){
            case 1 -> {
                AdaugaSectiune();
                break;
            }
            case 2 ->{
                AdaugaAutor();
                break;
            }
            case 3 ->{
                AdaugaEditura();
                break;
            }
            case 4 ->{
                AdaugaCarte();
                break;
            }
            case 5 ->{
                afiseazaAutor();
                break;
            }
            case 6 ->{
                afiseazaEdituri();
                break;
            }
            case 7 ->{
                afisareSectiuni();
                break;
            }
            case 8 ->{
                logout();
                break;
            }
            default -> {
                return;
            }

        }

    }
    public void meniu3() {
        System.out.println("Alegeti un numar:");
        System.out.println("1. Inchiriati o carte");
        System.out.println("2. Vizualizare istoric inchirieri");
        System.out.println("3. Vizualizati autorii");
        System.out.println("4. Vizualizati sectiunile");
        System.out.println("5. Delogare");
        int optiune = scanner.nextInt();
        switch (optiune) {
            case 1 -> {
                InchiriereCarte();
                break;
            }
            case 2 -> {
                afiseazaIstoricImprumuturi();
                break;
            }
            case 3 -> {
                afiseazaAutor();
                break;
            }
            case 4->{
                afisareSectiuni();
                break;
            }
            case 5 -> {
                logout();
                break;
            }
            default -> {
                return;
            }
        }
    }

    public void InchiriereCarte() {
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            if (clienti.containsKey(usernameLog)) {
                int ok = 1;
                System.out.println("Alege o perioada de inchiriere: ");
                Integer q = scanner.nextInt();
                clienti.get(usernameLog).getImprumutActual().setDurata(q);
                while (ok == 1) {
                    System.out.println("Alege o sectiune: ");
                    afisareSec1();
                    String x = scanner.next();
                    for (Sectiune sectiune : sectiuni) {
                        if (Objects.equals(sectiune.getNume(), x)) {
                            System.out.println("Alege o carte: ");
                            sectiune.showCarti();
                            scanner.nextLine();
                            x = scanner.nextLine();
                            for (Carte c : sectiune.getCarti()) {
                                if (Objects.equals(c.getDenumire(), x)) {
                                    System.out.println("Cartea a fost aleasa");
                                    clienti.get(usernameLog).getImprumutActual().adaugaCarte(c);
                                    System.out.println("Adauga si alta carte?A/F");
                                    x = scanner.next();
                                    if (!Objects.equals(x, "A")) {
                                        ok = 0;
                                    }
                                }
                            }
                        }

                    }
                }
                System.out.println("Inchirierea a fost finalizata");
                clienti.get(usernameLog).adaugaIstoricImprumuturi();
                meniu3();
            }
        }

    }

    public void afiseazaIstoricImprumuturi() {
        audit.scrieAudit();
        if (usernameLog != null && !usernameLog.isEmpty()) {
            if (clienti.containsKey(usernameLog)) {
                System.out.println(clienti.get(usernameLog).Imprumuturi);
                meniu3();
            }
        }
    }


}



