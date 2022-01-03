package fun.n0rthking;

import java.util.ArrayList;
import java.util.Random;

public class Tvar {
    private ArrayList<Bunka> obsadeneBunky;
    private Mriezka mriezka;
    private Random generator;
    private String farba;
    private int pocSurX;
    private int pocSurY;
    private boolean stav;

    /**
     * Nahodne vygeneruje jeden zo siedmich moznych tvarov a vlozi
     * ho na zadane suradnice
     */
    public Tvar(Mriezka mriezka, Random generator, int pocSurX, int pocSurY) {
        this.obsadeneBunky = new ArrayList<>();
        this.mriezka = mriezka;
        this.generator = generator;
        this.pocSurX = pocSurX;
        this.pocSurY = pocSurY;
        this.stav = this.vygenerujTvar();
    }

    /**
     * Vrati false ak nebolo mozne umiestnit tvar na zadanych suradniciach
     */
    public boolean getStav() {
        return this.stav;
    }

    /**
     * Vrati true ak sa obidve suradnice nachadzaju v rozsahu mriezky
     */
    private boolean jeVMriezke(int surX, int surY) {
        if (surX < 0 || surX >= this.mriezka.getVyska()) {
            return false;
        }
        if (surY < 0 || surY >= this.mriezka.getSirka()) {
            return false;
        }
        return true;
    }

    /**
     * Otoci tvar o 90 stupnov, ak to nie je mozne tvar ostava nezmeneny
     */
    public void otocTvar() {
        ArrayList<Bunka> otocene = new ArrayList<>();

        for (Bunka aktualna : this.obsadeneBunky) {
            int novaSurX = this.pocSurY - aktualna.getMriezkaStlpec() + this.pocSurX + 1;
            int novaSurY = aktualna.getMriezkaRiadok() - this.pocSurX + this.pocSurY - 1;
            if (this.farba.equals("blue")) {
                novaSurX += 1;
                novaSurY += 1;
            } else if (this.farba.equals("cyan")) {
                novaSurX += 1;
            }
            if (this.jeVMriezke(novaSurX, novaSurY)) {
                Bunka novaBunka = this.mriezka.getBunka(novaSurX, novaSurY);
                if (this.obsadeneBunky.contains(novaBunka) || !novaBunka.jeObsadena()) {
                    otocene.add(novaBunka);
                } else {
                    return;
                }
            } else {
                return;
            }
        }

        for (Bunka aktualna : this.obsadeneBunky) {
            aktualna.zmenFarbu("black");
        }
        for (Bunka aktualna : otocene) {
            aktualna.zmenFarbu(this.farba);
        }

        this.obsadeneBunky = otocene;
    }

    /**
     * Posunie tvar o zadany pocet buniek na osi x a y a vrati true,
     * ak nie je mozne tvar posunut (bol by mimo hracej plochy alebo by zasahoval do ineho tvaru),
     * tak vrati false
     */
    public boolean posunTvar(int deltaX, int deltaY) {
        ArrayList<Bunka> posunute = new ArrayList<>();

        for (Bunka aktualna : this.obsadeneBunky) {
            int novaSurX = aktualna.getMriezkaRiadok() + deltaX;
            int novaSurY = aktualna.getMriezkaStlpec() + deltaY;
            if (this.jeVMriezke(novaSurX, novaSurY)) {
                Bunka novaBunka = this.mriezka.getBunka(novaSurX, novaSurY);
                if (this.obsadeneBunky.contains(novaBunka) || !novaBunka.jeObsadena()) {
                    posunute.add(novaBunka);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        this.pocSurX += deltaX;
        this.pocSurY += deltaY;

        for (Bunka aktualna : this.obsadeneBunky) {
            aktualna.zmenFarbu("black");
        }
        for (Bunka aktualna : posunute) {
            aktualna.zmenFarbu(this.farba);
        }

        this.obsadeneBunky = posunute;
        return true;
    }

    /**
     * Ak je bunka volna zafarbi ju na farbu aktualneho tvaru,
     * suradnice v parametre su posunute o pociatocne suradnice
     * aktualneho tvaru
     */
    private boolean zafarbiBunku(int posunX, int posunY) {
        if (this.mriezka.getBunka(this.pocSurX + posunX, this.pocSurY + posunY).jeObsadena()) {
            return false;
        }
        this.mriezka.setBunka(this.pocSurX + posunX, this.pocSurY + posunY, this.farba);
        this.obsadeneBunky.add(this.mriezka.getBunka(this.pocSurX + posunX, this.pocSurY + posunY));
        return true;
    }

    /**
     * Vygeneruje tvar I
     */
    private boolean nastavTvar1() {
        this.farba = "cyan";
        for (int i = 0; i < 4; i++) {
            if (!this.zafarbiBunku(i, 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vygeneruje tvar T
     */
    private boolean nastavTvar2() {
        this.farba = "magenta";
        for (int i = 0; i < 3; i++) {
            if (!this.zafarbiBunku(i, 0)) {
                return false;
            }
        }
        return this.zafarbiBunku(1, 1);
    }

    /**
     * Vygeneruje tvar O
     */
    private boolean nastavTvar3() {
        this.farba = "yellow";
        for (int i = 0; i < 2; i++) {
            if (!this.zafarbiBunku(i, 0)) {
                return false;
            }
            if (!this.zafarbiBunku(i, 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vygeneruje tvar L
     */
    private boolean nastavTvar4() {
        this.farba = "orange";
        for (int i = 0; i < 3; i++) {
            if (!this.zafarbiBunku(i, 0)) {
                return false;
            }
        }
        return this.zafarbiBunku(2, 1);
    }

    /**
     * Vygeneruje tvar J
     */
    private boolean nastavTvar5() {
        this.farba = "blue";
        for (int i = 0; i < 3; i++) {
            if (!this.zafarbiBunku(i, 1)) {
                return false;
            }
        }
        return this.zafarbiBunku(2, 0);
    }

    /**
     * Vygeneruje tvar S
     */
    private boolean nastavTvar6() {
        this.farba = "green";
        for (int i = 0; i < 2; i++) {
            if (!this.zafarbiBunku(i, 0)) {
                return false;
            }
        }
        for (int i = 1; i < 3; i++) {
            if (!this.zafarbiBunku(i, 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vygeneruje tvar Z
     */
    private boolean nastavTvar7() {
        this.farba = "red";
        for (int i = 0; i < 2; i++) {
            if (!this.zafarbiBunku(i, 1)) {
                return false;
            }
        }
        for (int i = 1; i < 3; i++) {
            if (!this.zafarbiBunku(i, 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Nahodne vygeneruje tvar a vlozi ho do hracej plochy
     */
    private boolean vygenerujTvar() {
        switch (this.generator.nextInt(7)) {
            case 0:
                return this.nastavTvar1();
            case 1:
                return this.nastavTvar2();
            case 2:
                return this.nastavTvar3();
            case 3:
                return this.nastavTvar4();
            case 4:
                return this.nastavTvar5();
            case 5:
                return this.nastavTvar6();
            case 6:
                return this.nastavTvar7();
        }
        return false;
    }
}
