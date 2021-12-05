package fun.n0rthking;

public class Mriezka {
    private int vyska;
    private int sirka;
    private Bunka[][] mriezka;

    public Mriezka(int vyska, int sirka) {
        this.mriezka = new Bunka[vyska][sirka];
        this.vyska = vyska;
        this.sirka = sirka;

        int surX;
        int surY = 5;
        int velkost = 25;

        for (int riadok = 0; riadok < vyska; riadok++) {
            surX = 5;
            for (int stlpec = 0; stlpec < sirka; stlpec++) {
                this.mriezka[riadok][stlpec] = new Bunka(surX, surY, velkost);
                this.mriezka[riadok][stlpec].setMriezkaRiadok(riadok);
                this.mriezka[riadok][stlpec].setMriezkaStlpec(stlpec);
                surX += velkost + 1;
            }
            surY += velkost + 1;
        }
    }

    public int getVyska() {
        return this.vyska;
    }

    public int getSirka() {
        return this.sirka;
    }

    public Bunka getBunka(int riadok, int stlpec) {
        return this.mriezka[riadok][stlpec];
    }

    public void setBunka(int riadok, int stlpec, String farba) {
        this.mriezka[riadok][stlpec].zmenFarbu(farba);
    }
}
