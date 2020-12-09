package fachlogik;

import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mhdal001
 */
public abstract class Medium implements Comparable<Medium>, Serializable {
    protected static int anzahlID = 0;

    private int id;
    private String titel;
    private int jahr;

    public Medium(String titel, int jahr) {
        this.id = anzahlID++;
        this.jahr = jahr;
        this.titel = titel;
    }
    public Medium(){   
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public int getJahr() {
        return jahr;
    }

    public int alter() {
        return LocalDateTime.now().getYear() - this.jahr;
    }


    public abstract void druckeDaten(OutputStream s);

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Medium) || obj == null) {
            return false;
        }
        Medium m = (Medium) obj;
        return this.titel.equals(m.titel) && this.jahr == m.jahr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.titel, this.jahr);
    }

    //P03
    @Override

    public int compareTo(Medium m) {
        /* if (this.getJahr() < M.getJahr()) {
            return 1;
        } else if (this.getJahr() > M.getJahr()) {
            return -1;
        }
        return 0;
         */
        // beide sind richtig
        return Integer.compare(this.getJahr(), m.getJahr());
    }
}
