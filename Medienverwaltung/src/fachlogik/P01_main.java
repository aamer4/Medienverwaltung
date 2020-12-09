package fachlogik;


import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mhdal001
 */
public class P01_main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Audio a = new Audio("It Means Nothing", "Streeophonics", 2002, 229);
        Audio b = new Audio("It Means ", "Streeos", 2006, 222);
        Bild c = new Bild("It", 1992, "dort");*/
        Medienverwaltung_1 one = new Medienverwaltung_1();
        /*one.aufnehmen(a);
        one.aufnehmen(b);
        one.aufnehmen(c);
        one.zeigeMedien();*/
        MyMenu m = new MyMenu(one);

        m.run();
        // one.sucheNeuesMedium();
        // one.berechneErscheinungsjahr();

    }
}
