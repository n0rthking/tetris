package fun.n0rthking;

public class Mriezka {
    private int vyska;
    private int sirka;
    private Bunka[][] mriezka;
    private int[] zaplnenost;

    /**
     * Vytvori mriezku z buniek zadanej vysky a sirky
     */
    public Mriezka(int vyska, int sirka) {
        this.mriezka = new Bunka[vyska][sirka];
        this.zaplnenost = new int[vyska];
        this.vyska = vyska;
        this.sirka = sirka;

        int surX;
        int surY = 5;
        int velkost = 25;

        for (int riadok = 0; riadok < vyska; riadok++) {
            surX = 5;
            for (int stlpec = 0; stlpec < sirka; stlpec++) {
                this.mriezka[riadok][stlpec] = new Bunka(surX, surY, velkost, this);
                this.mriezka[riadok][stlpec].setMriezkaRiadok(riadok);
                this.mriezka[riadok][stlpec].setMriezkaStlpec(stlpec);
                surX += velkost + 1;
            }
            surY += velkost + 1;
        }
    }

    /**
     * Vrati vysku mriezky
     */
    public int getVyska() {
        return this.vyska;
    }

    /**
     * Vrati sirku mriezky
     */
    public int getSirka() {
        return this.sirka;
    }

    /**
     * Vrati objekt bunky na zadanych suradniciach
     */
    public Bunka getBunka(int riadok, int stlpec) {
        return this.mriezka[riadok][stlpec];
    }

    /**
     * Zafarbi bunku na zadanych suradniciach
     */
    public void setBunka(int riadok, int stlpec, String farba) {
        this.mriezka[riadok][stlpec].zmenFarbu(farba);
    }

    /**
     * Zvysi (znizi) pocet obsadenych buniek v zadanom riadku
     */
    public void zmenZaplnenostRiadku(int riadok, int zmena) {
        this.zaplnenost[riadok] += zmena;
    }

    /**
     * Od zadaneho riadku posunie riadky nad nim aby bola
     * hracia plocha bez prazdnych riadkov
     * Vrati velkost medzeri medzi aktualnym riadkom a najblizsim
     * neprazdnym riadkom
     */
    private int posunRiadok(int riadok, int rozdiel) {
        int pocet = 0;
        int najdeny = -1;

        for (int aktualny = riadok - rozdiel; aktualny >= 0; aktualny--) {
            if (this.zaplnenost[aktualny] == 0) {
                pocet++;
            } else if (this.zaplnenost[aktualny] > 0) {
                najdeny = aktualny;
                break;
            }
            if (pocet == 5) {
                break;
            }
        }

        if (najdeny == -1) {
            return -1;
        }

        this.zaplnenost[riadok] = 0;
        pocet = 0;

        for (int stlpec = 0; stlpec < this.sirka; stlpec++) {
            String farba = this.getBunka(najdeny, stlpec).getFarba();
            this.setBunka(riadok, stlpec, farba);
            if (farba.equals("black")) {
                pocet++;
            } else {
                this.setBunka(najdeny, stlpec, "black");
            }
        }

        this.zaplnenost[riadok] += pocet;
        return riadok - najdeny;
    }

    /**
     * Upravi hraciu plochu tak aby riadky, ktore su cele zaplnene
     * boli odstranene a posunie riadky, ktore sa nachadzaju nad nimi
     * aby v hracej ploche neboli medzeri medzi riadkami
     * Vrati pocet odstranenych riadkov
     */
    public int odstranZaplneneRiadky() {
        int pocetRiadkov = 0;
        int prvy = -1;

        for (int riadok = this.vyska - 1; riadok >= 0; riadok--) {
            if (this.zaplnenost[riadok] == 0) {
                break;
            } else if (this.zaplnenost[riadok] == this.sirka) {
                if (pocetRiadkov == 0) {
                    prvy = riadok;
                }
                pocetRiadkov++;
                for (Bunka aktualna : this.mriezka[riadok]) {
                    aktualna.zmenFarbu("black");
                }
            }
        }

        if (pocetRiadkov == 0) {
            return pocetRiadkov;
        }

        int rozdiel = 1;
        for (int riadok = prvy; riadok >= 0; riadok--) {
            rozdiel = this.posunRiadok(riadok, rozdiel);
            if (rozdiel == -1) {
                break;
            }
        }

        return pocetRiadkov;
    }
}
