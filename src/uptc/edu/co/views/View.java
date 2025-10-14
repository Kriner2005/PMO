/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.views;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class View {

    private Scanner sc;

    public View() {
        sc = new Scanner(System.in);
    }

    public String obtenerDato(String message) {
        System.out.println(message);
        String ms = sc.nextLine();
        return ms;
    }

    public int readInt(String message) {
        return Integer.parseInt(obtenerDato(message));
    }

    public void mostrarMensaje(String message) {
        System.out.println(message);
    }

    public void mostrarMenú(String[] items) {
        for (String item : items) {
            System.out.println(item);
        }
    }

    public void mostrarMenú(ArrayList<String> items) {
        for (String item : items) {
            System.out.println(item);
        }
    }
}
