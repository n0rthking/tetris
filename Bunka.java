package fun.n0rthking;

public class Bunka {
    private Obdlznik bunka;
    private int mriezkaRiadok;
    private int mriezkaStlpec;
    private boolean zaplnene;

    public Bunka(int polohaX, int polohaY, int velkost) {
        this.bunka = new Obdlznik();
        this.bunka.zmenPolohu(polohaX, polohaY);
        this.bunka.zmenStrany(velkost, velkost);
        this.bunka.zmenFarbu("black");
        this.bunka.zobraz();
        this.zaplnene = false;
    }

    public void setMriezkaRiadok(int riadok) {
        this.mriezkaRiadok = riadok;
    }

    public void setMriezkaStlpec(int stlpec) {
        this.mriezkaStlpec = stlpec;
    }

    public int getMriezkaRiadok() {
        return this.mriezkaRiadok;
    }

    public int getMriezkaStlpec() {
        return this.mriezkaStlpec;
    }

    public boolean jeObsadena() {
        return this.zaplnene;
    }

    public void zmenFarbu(String farba) {
        if (farba.equals("black")) {
            this.zaplnene = false;
        } else {
            this.zaplnene = true;
        }
        this.bunka.zmenFarbu(farba);
    }
}
