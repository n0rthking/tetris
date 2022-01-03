package fun.n0rthking;

public class Bunka {
    private Obdlznik bunka;
    private int mriezkaRiadok;
    private int mriezkaStlpec;
    private boolean zaplnena;
    private Mriezka mriezka;
    private String farba;

    /**
     * Vytvori bunku zadanej velkosti na zadanych suradniciach a nastavi jej farbu na ciernu
     */
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

    /**
     * Nastavi riadok mriezky
     */
    public void setMriezkaRiadok(int riadok) {
        this.mriezkaRiadok = riadok;
    }

    /**
     * Nastavi stlpec mriezky
     */
    public void setMriezkaStlpec(int stlpec) {
        this.mriezkaStlpec = stlpec;
    }

    /**
     * Vrati riadok mriezky v ktorom sa bunka nachadza
     */
    public int getMriezkaRiadok() {
        return this.mriezkaRiadok;
    }

    /**
     * Vrati stlpec mriezky v ktorom sa bunka nachadza
     */
    public int getMriezkaStlpec() {
        return this.mriezkaStlpec;
    }

    /**
     * Vrati true ak je bunka obsadena
     */
    public boolean jeObsadena() {
        return this.zaplnena;
    }

    /**
     * Vrati farbu bunky
     */
    public String getFarba() {
        return this.farba;
    }

    /**
     * Zmeni farbu bunky
     */
    public void zmenFarbu(String farba) {
        this.zaplnena = !farba.equals("black");
        this.bunka.zmenFarbu(farba);
        this.farba = farba;
        this.mriezka.zmenZaplnenostRiadku(this.mriezkaRiadok, farba.equals("black") ? -1 : 1);
    }
}
