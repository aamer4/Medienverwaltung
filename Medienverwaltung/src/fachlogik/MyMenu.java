package fachlogik;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mhdal001
 */
public class MyMenu {

    Medienverwaltung_1 mv;

    public MyMenu(Medienverwaltung_1 mv) {
        this.mv = mv;
    }
    // Methoden schreiben do while und switch

    public void run() {
        Scanner input = new Scanner(System.in);
        int x = 0;
        while (x != 9) {
            System.out.print("\nBitte waehlen Sie aus\n1. Audio aufnehmen\n2. Bild aufnehmen\n3. Zeige alle Medien\n4. Medienliste in Datei schreiben\n5. Zeige neues Medium\n6. "
                    + "Berechne durchschnittliches Erscheinungsjahr\n7. Speichern\n8. Laden\n9. Beenden\nBitte Menuepunkt waehlen: ");
            try {
                x = input.nextInt();
                switch (x) {
                    case 1:
                        mv.aufnehmenAudio();
                        break;
                    case 2:
                        mv.aufnehmenBild();
                        break;
                    case 3:
                        mv.zeigeMedien(System.out);
                        break;
                    case 4:
                        mv.dateiSchreiben();
                        break;
                    case 5:
                        mv.sucheNeuesMedium(); // parameter wurde gelöscht
                        break;
                    case 6:
                        mv.berechneErscheinungsjahr();
                        break;
                    case 7:
                        mv.speichern();
                        break;
                    case 8:
                        mv.laden();
                    case 9:
                        break;
                    default:
                        System.out.println("falsche Eingabe");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Bitte geben Sie ein Zahl zwischen 1 und 7");
                input = new Scanner(System.in);
            }
        }
    }
}
