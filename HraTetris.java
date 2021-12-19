package fun.n0rthking;

import java.util.Random;

public class HraTetris {
    private Mriezka mriezka;
    private Manazer manazer;
    private Random generator;
    private Tvar aktualnyTvar;
    private int pocet;

    public HraTetris() {
        this.mriezka = new Mriezka(20, 10);
        this.manazer = new Manazer();
        this.generator = new Random();
        this.aktualnyTvar = null;
        this.pocet = 0;
    }

    public void start() {
        this.manazer.spravujObjekt(this);
        this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
    }

    public void tik() {
        this.pocet++;
        if (this.pocet == 3) {
            this.pocet = 0;
            if (!this.aktualnyTvar.posunTvar(1, 0)) {
                this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
            }
        }
    }

    public void posunHore() {
        this.aktualnyTvar.otocTvar();
    }

    public void posunDole() {
        this.aktualnyTvar.posunTvar(1, 0);
    }

    public void posunVlavo() {
        this.aktualnyTvar.posunTvar(0, -1);
    }

    public void posunVpravo() {
        this.aktualnyTvar.posunTvar(0, 1);
    }
}
