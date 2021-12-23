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

    public boolean otocTvar() {
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
                    return false;
                }
            } else {
                return false;
            }
        }

        for (Bunka aktualna : this.obsadeneBunky) {
            aktualna.zmenFarbu("black");
        }
        for (Bunka aktualna : otocene) {
            aktualna.zmenFarbu(this.farba);
        }

        this.obsadeneBunky = otocene;
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
        switch (this.generator.nextInt(7)) {
            case 0:
                this.nastavTvar1();
                break;
            case 1:
                this.nastavTvar2();
                break;
            case 2:
                this.nastavTvar3();
                break;
            case 3:
                this.nastavTvar4();
                break;
            case 4:
                this.nastavTvar5();
                break;
            case 5:
                this.nastavTvar6();
                break;
            case 6:
                this.nastavTvar7();
                break;
        }
    }
}
