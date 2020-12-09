package fachlogik;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
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
public class Bild extends Medium {

    private String ort;

    public Bild(String titel, int jahr, String ort) {
        super(titel, jahr);
        this.ort = ort;
    }

    public Bild() { // Standardkonstruktor zweite variante
        this(null, 0, null);
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public void druckeDaten(OutputStream stream) {
        PrintWriter output = new PrintWriter(stream);
        output.printf("ID: %d Titel: %s Jahr: %d Ort: %s\n", super.getId(), super.getTitel(), super.getJahr(), this.getOrt());
        output.flush();
    }

    /*
    @Override
    public void druckeDaten() {
        System.out.println("Id: " + super.getId() + " Titel: " + super.getTitel() + " Jahr: " + super.getJahr() + " Interpret: " + this.getOrt());
    }*/
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bild) || obj == null) {
            return false;
        }
        Bild b = (Bild) obj;
        return super.equals(b) && this.getOrt().equals(b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.ort);
    }

    @Override
    public String toString() {
        return String.format("ID = %d Titel: %s  aus %s Ort: %s \n", super.getId(), super.getTitel(), super.getJahr(), this.getOrt());
    }

}
