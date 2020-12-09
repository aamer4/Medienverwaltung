package fachlogik;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mhdal001
 */

public class Audio extends Medium {

    private String interpret;
    private int dauer;
    //Standardkonstruktor

    public Audio(String titel, String interpret, int jahr, int dauer) {
        super(titel, jahr);
        this.interpret = interpret;
        this.dauer = dauer;
    }

    public Audio() { } // Standardkonstruktor erste variante
    
    public int getDauer() {
        return this.dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public String getInterpret() {
        return this.interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    @Override
    //System.out.printf("Hello %s!%n", "World");
    //neu für p05
    public void druckeDaten(OutputStream stream) {
        PrintWriter output = new PrintWriter(stream);
        output.printf("ID = %d Titel: %s von %s aus %s Spieldauer: %d min\n", super.getId(), super.getTitel(), this.getInterpret(), super.getJahr(), this.getDauer());
        output.flush();
    }

    /*
    alt
    public void druckeDaten() {
        System.out.println("ID = " + super.getId() + " " + super.getTitel() + "von " + this.getInterpret() + "aus " + super.getJahr() + " " + "Spieldauer: " + this.getDauer() + " sek");
    }
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Audio) || obj == null) {
            return false;
        }
        Audio a = (Audio) obj;
        return super.equals(a) && this.interpret.equals(a) && this.dauer == a.dauer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.interpret, this.dauer);
    }

    /*
    *P09
     */
    @Override
    public String toString() {
        return String.format("ID = %d Titel: %s von %s aus %s Spieldauer: %d sek\n", super.getId(), super.getTitel(), this.getInterpret(), super.getJahr(), this.getDauer());
    }
}