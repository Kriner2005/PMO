/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import uptc.edu.co.view.subVistas.Help;

/**
 *
 * @author alber
 */
public class HelpController implements ActionListener {

    private Help help;

    public HelpController() {
        help = new Help(this, 1);
        abrirHelp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            // === HELP ===
            case "NEXT_PAGE":
                nextPage();
                break;

            case "PREV_PAGE":
                prevPage();
                break;
            default:
                throw new AssertionError();
        }
    }

    private void abrirHelp() {
        help.setVisible(true);
        //Centrar la ventana con la ventana: help.setLocationRelativeTo(vista);
    }

    private void nextPage() {
        int page = help.getNumberPage();
        if (page < 5) {
            help.setNumberPage(page + 1);

        }
    }

    private void prevPage() {
        int page = help.getNumberPage();
        if (page > 1) {
            help.setNumberPage(page - 1);

        }
    }
}
