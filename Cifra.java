package fun.n0rthking;

public class Cifra {
    private Obrazok cifra;

    /**
     * Vytvori jednu cifru displeja na zadanych suradniciach a nastavi na nej cislo 0
     */
    public Cifra(int polohaX, int polohaY) {
        this.cifra = new Obrazok("cisla/0.png");
        this.cifra.zmenPolohu(polohaX, polohaY);
        this.cifra.zobraz();
    }

    /**
     * Nastavi cifru displeja na zadane cislo v rozsahu 0 az 9 vratane
     */
    public void setHodnota(int cislo) {
        if (cislo >= 0 && cislo <= 9) {
            this.cifra.zmenObrazok("cisla/" + cislo + ".png");
        } else {
            this.cifra.zmenObrazok("cisla/0.png");
        }
    }
}
