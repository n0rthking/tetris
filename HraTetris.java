package fun.n0rthking;

import java.util.Random;
import java.util.Scanner;

public class HraTetris {
    private Mriezka mriezka;
    private Manazer manazer;
    private Random generator;
    private Tvar aktualnyTvar;
    private int pocet;
    private boolean koniecHry;
    private int skore;
    private boolean pauza;
    private int rychlost;
    private int predchadzajucaRychlost;
    private Displej displej;

    public HraTetris() {
        this.mriezka = new Mriezka(20, 10);
        this.manazer = new Manazer();
        this.generator = new Random();
        this.aktualnyTvar = null;
        this.pocet = 0;
        this.koniecHry = false;
        this.skore = 0;
        this.pauza = false;
        this.rychlost = 5;
        this.predchadzajucaRychlost = 5;
        this.displej = new Displej(50, 570);
    }

    public void start() {
        this.manazer.spravujObjekt(this);
        this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
    }

    private void restartujHru() {
        for (int riadok = 0; riadok < this.mriezka.getVyska(); riadok++) {
            for (int stlpec = 0; stlpec < this.mriezka.getSirka(); stlpec++) {
                if (!this.mriezka.getBunka(riadok, stlpec).getFarba().equals("black")) {
                    this.mriezka.setBunka(riadok, stlpec, "black");
                }
            }
        }
        this.koniecHry = false;
        this.skore = 0;
        this.rychlost = 5;
        this.predchadzajucaRychlost = 5;
        this.displej.setHodnota(this.skore);
        this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
    }

    public void aktivuj() {
        this.pauza = !this.pauza;
    }

    private int vypocitajSkore(int pocetOdstranenych) {
        switch (pocetOdstranenych) {
            case 1:
                return 100;
            case 2:
                return 200;
            case 3:
                return 400;
            case 4:
                return 800;
        }
        return 0;
    }

    private void vypisMoznosti() {
        System.out.println("vyber si moznost:");
        System.out.println("[n]ova hra");
        System.out.println("[u]koncit program");

        Scanner nacitavac = new Scanner(System.in);
        boolean nacitavanieUkoncene = false;

        while (!nacitavanieUkoncene) {
            switch (nacitavac.next().toLowerCase()) {
                case "n":
                    System.out.println("bola spustena nova hra");
                    this.restartujHru();
                    nacitavanieUkoncene = true;
                    break;
                case "u":
                    System.out.println("program ukonceny");
                    this.manazer.prestanSpravovatObjekt(this);
                    nacitavanieUkoncene = true;
                    break;
                default:
                    System.out.println("neznama moznost, skus to znova:");
                    System.out.println("[n]ova hra");
                    System.out.println("[u]koncit program");
                    break;
            }
        }
    }

    private void zvysRychlost() {
        if (this.rychlost == 5 && this.skore >= 3000) {
            this.rychlost = 4;
            this.predchadzajucaRychlost = 4;
        } else if (this.rychlost == 4 && this.skore >= 7000) {
            this.rychlost = 3;
            this.predchadzajucaRychlost = 3;
        } else if (this.rychlost == 3 && this.skore >= 11000) {
            this.rychlost = 2;
            this.predchadzajucaRychlost = 2;
        } else if (this.rychlost == 2 && this.skore >= 17000) {
            this.rychlost = 1;
            this.predchadzajucaRychlost = 1;
        }
    }

    public void tik() {
        if (this.koniecHry || this.pauza) {
            return;
        }
        this.pocet++;
        if (this.pocet >= this.rychlost) {
            this.pocet = 0;
            if (!this.aktualnyTvar.posunTvar(1, 0)) {
                this.rychlost = this.predchadzajucaRychlost;
                this.skore += this.vypocitajSkore(this.mriezka.odstranZaplneneRiadky());
                this.displej.setHodnota(this.skore);
                this.zvysRychlost();
                this.aktualnyTvar = new Tvar(this.mriezka, this.generator, 0, 4);
                if (!this.aktualnyTvar.getStav()) {
                    this.koniecHry = true;
                    System.out.println("koniec hry");
                    System.out.println("skore " + this.skore);
                    this.vypisMoznosti();
                }
            }
        }
    }

    public void posunHore() {
        if (this.koniecHry || this.pauza) {
            return;
        }
        this.aktualnyTvar.otocTvar();
    }

    public void posunDole() {
        if (this.koniecHry || this.pauza) {
            return;
        }
        if (this.rychlost == this.predchadzajucaRychlost) {
            this.rychlost = 1;
        } else {
            this.rychlost = this.predchadzajucaRychlost;
        }
    }

    public void posunVlavo() {
        if (this.koniecHry || this.pauza) {
            return;
        }
        this.aktualnyTvar.posunTvar(0, -1);
    }

    public void posunVpravo() {
        if (this.koniecHry || this.pauza) {
            return;
        }
        this.aktualnyTvar.posunTvar(0, 1);
    }
}
