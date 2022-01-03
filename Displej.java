package fun.n0rthking;

public class Displej {
    private Cifra[] cifry;

    /**
     * Vytvori 6 ciferny displej na zadanych suradniciach
     */
    public Displej(int polohaX, int polohaY) {
        this.cifry = new Cifra[6];

        for (int i = 0; i < this.cifry.length; i++) {
            this.cifry[i] = new Cifra(polohaX + i * 30, polohaY);
        }
    }

    /**
     * Nastavi cislo na displeji na zadanu hodnotu
     */
    public void setHodnota(int cislo) {
        for (int i = this.cifry.length - 1; i >= 0; i--) {
            this.cifry[i].setHodnota(cislo % 10);
            cislo /= 10;
        }
    }
}
