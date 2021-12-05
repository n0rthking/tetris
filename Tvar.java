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

    public Tvar(Mriezka mriezka, Random generator, int pocSurX, int pocSurY) {
        this.obsadeneBunky = new ArrayList<>();
        this.mriezka = mriezka;
        this.generator = generator;
        this.pocSurX = pocSurX;
        this.pocSurY = pocSurY;
        this.vygenerujTvar();
    }

    private boolean jeVMriezke(int surX, int surY) {
        if (surX < 0 || surX >= this.mriezka.getVyska()) {
            return false;
        }
        if (surY < 0 || surY >= this.mriezka.getSirka()) {
            return false;
        }
        return true;
    }

    public boolean posunTvar(int deltaX, int deltaY) {
        ArrayList<Bunka> posunute = new ArrayList<>();

        for (Bunka aktualna : this.obsadeneBunky) {
            int novaSurX = aktualna.getMriezkaRiadok() + deltaX;
            int novaSurY = aktualna.getMriezkaStlpec() + deltaY;
            if (this.jeVMriezke(novaSurX, novaSurY)) {
                Bunka novaBunka = this.mriezka.getBunka(novaSurX, novaSurY);
                if (this.obsadeneBunky.contains(novaBunka) || !novaBunka.jeObsadena()) {
                    posunute.add(this.mriezka.getBunka(novaSurX, novaSurY));
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        for (Bunka aktualna : this.obsadeneBunky) {
            aktualna.zmenFarbu("black");
        }
        for (Bunka aktualna : posunute) {
            aktualna.zmenFarbu(this.farba);
        }

        this.obsadeneBunky = posunute;
        return true;
    }

    private void zafarbiBunku(int posunX, int posunY) {
        this.mriezka.setBunka(this.pocSurX + posunX, this.pocSurY + posunY, this.farba);
        this.obsadeneBunky.add(this.mriezka.getBunka(this.pocSurX + posunX, this.pocSurY + posunY));
    }

    private void nastavTvar1() {
        this.farba = "cyan";
        for (int i = 0; i < 4; i++) {
            this.zafarbiBunku(i, 0);
        }
    }

    private void nastavTvar2() {
        this.farba = "magenta";
        for (int i = 0; i < 3; i++) {
            this.zafarbiBunku(i, 0);
        }
        this.zafarbiBunku(1, 1);
    }

    private void nastavTvar3() {
        this.farba = "yellow";
        for (int i = 0; i < 2; i++) {
            this.zafarbiBunku(i, 0);
            this.zafarbiBunku(i, 1);
        }
    }

    private void nastavTvar4() {
        this.farba = "orange";
        for (int i = 0; i < 3; i++) {
            this.zafarbiBunku(i, 0);
        }
        this.zafarbiBunku(2, 1);
    }

    private void nastavTvar5() {
        this.farba = "blue";
        for (int i = 0; i < 3; i++) {
            this.zafarbiBunku(i, 1);
        }
        this.zafarbiBunku(2, 0);
    }

    private void nastavTvar6() {
        this.farba = "green";
        for (int i = 0; i < 2; i++) {
            this.zafarbiBunku(i, 0);
        }
        for (int i = 1; i < 3; i++) {
            this.zafarbiBunku(i, 1);
        }
    }

    private void nastavTvar7() {
        this.farba = "red";
        for (int i = 0; i < 2; i++) {
            this.zafarbiBunku(i, 1);
        }
        for (int i = 1; i < 3; i++) {
            this.zafarbiBunku(i, 0);
        }
    }

    private void vygenerujTvar() {
        switch (generator.nextInt(7)) {
            case 0:
                nastavTvar1();
                break;
            case 1:
                nastavTvar2();
                break;
            case 2:
                nastavTvar3();
                break;
            case 3:
                nastavTvar4();
                break;
            case 4:
                nastavTvar5();
                break;
            case 5:
                nastavTvar6();
                break;
            case 6:
                nastavTvar7();
                break;
        }
    }
}
