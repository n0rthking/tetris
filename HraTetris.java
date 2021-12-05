package fun.n0rthking;

import java.util.Random;

public class HraTetris {
    private Mriezka mriezka;
    private Manazer manazer;
    private Random generator;
    private Tvar tvar;
    private int pocet;

    public HraTetris() {
        this.mriezka = new Mriezka(20, 10);
        this.manazer = new Manazer();
        this.generator = new Random();
        this.tvar = new Tvar(this.mriezka, this.generator, 0, 0);
        this.pocet = 0;
    }

    public void start() {
        this.manazer.spravujObjekt(this);
    }

    public void aktivuj() {
        //pocet++;
        this.tvar = new Tvar(this.mriezka, this.generator, 0, 0);
    }

    public void posunHore() {
        this.tvar.posunTvar(-1, 0);
    }

    public void posunDole() {
        this.tvar.posunTvar(1, 0);
    }

    public void posunVlavo() {
        this.tvar.posunTvar(0, -1);
    }

    public void posunVpravo() {
        this.tvar.posunTvar(0, 1);
    }
}
