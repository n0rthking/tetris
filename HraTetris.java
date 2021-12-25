package fun.n0rthking;

import java.util.Random;

public class HraTetris {
    private Mriezka mriezka;
    private Manazer manazer;
    private Random generator;
    private Tvar aktualnyTvar;
    private int pocet;
    private boolean koniecHry;

    public HraTetris() {
        this.mriezka = new Mriezka(20, 10);
        this.manazer = new Manazer();
        this.generator = new Random();
        this.aktualnyTvar = null;
        this.pocet = 0;
        this.koniecHry = false;
    }

    public void start() {
        this.manazer.spravujObjekt(this);
        this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
    }

    public void zrus() {
        if (!this.koniecHry) {
            return;
        }
        for (int riadok = 0; riadok < this.mriezka.getVyska(); riadok++) {
            for (int stlpec = 0; stlpec < this.mriezka.getSirka(); stlpec++) {
                if (!this.mriezka.getBunka(riadok, stlpec).getFarba().equals("black")) {
                    this.mriezka.setBunka(riadok, stlpec, "black");
                }
            }
        }
        this.koniecHry = false;
        this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
    }

    public void tik() {
        if (this.koniecHry) {
            return;
        }
        this.pocet++;
        if (this.pocet == 5) {
            this.pocet = 0;
            if (!this.aktualnyTvar.posunTvar(1, 0)) {
                int pocet = this.mriezka.odstranZaplneneRiadky();
                if (pocet > 0) {
                    System.out.println("pocet odstranenych riadkov: " + pocet);
                }
                this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
                if (!this.aktualnyTvar.getStav()) {
                    this.koniecHry = true;
                    System.out.println("koniec hry");
                }
            }
        }
    }

    public void posunHore() {
        if (this.koniecHry) {
            return;
        }
        this.aktualnyTvar.otocTvar();
    }

    public void posunDole() {
        if (this.koniecHry) {
            return;
        }
        this.aktualnyTvar.posunTvar(1, 0);
    }

    public void posunVlavo() {
        if (this.koniecHry) {
            return;
        }
        this.aktualnyTvar.posunTvar(0, -1);
    }

    public void posunVpravo() {
        if (this.koniecHry) {
            return;
        }
        this.aktualnyTvar.posunTvar(0, 1);
    }
}
