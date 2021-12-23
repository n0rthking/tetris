package fun.n0rthking;

public class Bunka {
    private Obdlznik bunka;
    private int mriezkaRiadok;
    private int mriezkaStlpec;
    private boolean zaplnena;
    private Mriezka mriezka;
    private String farba;

    public Bunka(int polohaX, int polohaY, int velkost, Mriezka mriezka) {
        this.bunka = new Obdlznik();
        this.bunka.zmenPolohu(polohaX, polohaY);
        this.bunka.zmenStrany(velkost, velkost);
        this.bunka.zmenFarbu("black");
        this.bunka.zobraz();
        this.zaplnena = false;
        this.mriezka = mriezka;
        this.farba = "black";
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
        return this.zaplnena;
    }

    public String getFarba() {
        return this.farba;
    }

    public void zmenFarbu(String farba) {
        this.zaplnena = !farba.equals("black");
        this.bunka.zmenFarbu(farba);
        this.farba = farba;
        this.mriezka.zmenZaplnenostRiadku(this.mriezkaRiadok, farba.equals("black") ? -1 : 1);
    }
}
