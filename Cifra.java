package fun.n0rthking;

public class Cifra {
    private Obrazok cifra;

    public Cifra(int polohaX, int polohaY) {
        this.cifra = new Obrazok("C:/Users/david/Downloads/cisla/0.png");
        this.cifra.zmenPolohu(polohaX, polohaY);
        this.cifra.zobraz();
    }

    public void setHodnota(int cislo) {
        if (cislo >= 0 && cislo <= 9) {
            this.cifra.zmenObrazok("C:/Users/david/Downloads/cisla/" + cislo + ".png");
        } else {
            this.cifra.zmenObrazok("C:/Users/david/Downloads/cisla/0.png");
        }
    }
}
