package uptc.edu.co.view.subVistas;

import uptc.edu.co.utilities.CustomComponents;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import uptc.edu.co.utilities.Utilities;

public class Help extends JDialog {

    private JTextArea title, text;
    private JLabel labelImagen;
    private ActionListener listener;
    private int numberPage;
    private ImageIcon icono, iconNext;
    private JPanel panel, panelNorth, panelCenter, panelLeft, panelRigth;


    public Help(ActionListener listener, int numberPage) {
        this.listener = listener;
        this.numberPage = numberPage;
        this.setTitle("Ayuda");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(500, 300);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        panel = new CustomComponents().createRoundedPanel(30, Utilities.MAIN_COLOR, 4, null);
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelNorth = new JPanel(new GridBagLayout());
        panelNorth.setOpaque(false);
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        addLogoSection(gbc);
        panel.add(panelNorth, BorderLayout.NORTH);

        panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setOpaque(false);
        gbc.insets = new Insets(1, 10, 1, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        title = createJTextArea("", Utilities.TITLE_FONT_HELP, Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(title, gbc);

        text = createJTextArea("", Utilities.TEXT_FONT_HELP, Color.WHITE);
        text.setBorder(BorderFactory.createEmptyBorder(10, 2, 30, 2));
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelCenter.add(text, gbc);

        panel.add(panelCenter, BorderLayout.CENTER);

        panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setOpaque(false);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        panelRigth = new JPanel(new GridBagLayout());
        panelRigth.setOpaque(false);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        createButtons(gbc);
        panel.add(panelLeft, BorderLayout.WEST);
        panel.add(panelRigth, BorderLayout.EAST);

        setPageContent();

        this.add(panel);
    }

    public void setTextTitle(String title) {
        this.title.setText(title);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    private void createButtons(GridBagConstraints gbc) {
//        icono = new ImageIcon("src/resources/flecha1.png");
//        iconNext = new ImageIcon("src/resources/flecha.png");

        icono = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/flecha1.png"));
        iconNext = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/flecha.png"));

        gbc.gridy = 2;
        gbc.gridwidth = 1;

        if (numberPage > 1) {
            JButton prev = createButton(icono, "PREV_PAGE");
            gbc.anchor = GridBagConstraints.WEST;

            panelLeft.add(prev, gbc);
        }
        if (numberPage == 1) {
            JLabel ext = createLabel("             ", Utilities.TEXT_FONT_HELP, Utilities.MAIN_COLOR);
            panelLeft.add(ext, gbc);
        }

        if (numberPage < 5) {
            JButton next = createButton(iconNext, "NEXT_PAGE");
            gbc.anchor = GridBagConstraints.EAST;

            panelRigth.add(next, gbc);
        }
        if (numberPage == 5) {
            JLabel ext = createLabel("             ", Utilities.TEXT_FONT_HELP, Utilities.MAIN_COLOR);
            panelRigth.add(ext, gbc);
        }
    }

    private JButton createButton(ImageIcon icon, String command) {
        JButton button = new CustomComponents().createRoundedButton("", Utilities.MAIN_COLOR, 20, 0, 0);
        button.setIcon(icon);
        button.setActionCommand(command);
        button.addActionListener(listener);
        return button;
    }

    public void setPageContent() {
        if (numberPage >= 1 && numberPage <= Utilities.PAGES.length) {
            setTextTitle(Utilities.PAGES[numberPage - 1][0]);
            setText(Utilities.PAGES[numberPage - 1][1]);

            // IMPORTANTE: Actualizar los botones
            updateButtons();
        }
    }

// Nuevo método para actualizar los botones según la página actual
    private void updateButtons() {
        // Limpiar los paneles
        panelLeft.removeAll();
        panelRigth.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Botón PREV
        if (numberPage > 1) {
            JButton prev = createButton(icono, "PREV_PAGE");
            gbc.anchor = GridBagConstraints.WEST;
            panelLeft.add(prev, gbc);
        } else {
            JLabel ext = createLabel("             ", Utilities.TEXT_FONT_HELP, Utilities.MAIN_COLOR);
            panelLeft.add(ext, gbc);
        }

        // Botón NEXT
        if (numberPage < 5) {
            JButton next = createButton(iconNext, "NEXT_PAGE");
            gbc.anchor = GridBagConstraints.EAST;
            panelRigth.add(next, gbc);
        } else {
            JLabel ext = createLabel("             ", Utilities.TEXT_FONT_HELP, Utilities.MAIN_COLOR);
            panelRigth.add(ext, gbc);
        }

        // Refrescar los paneles
        panelLeft.revalidate();
        panelLeft.repaint();
        panelRigth.revalidate();
        panelRigth.repaint();
    }

    public void setNumenerPage(int numberpage) {
        this.numberPage = numberpage;
    }

    public int getNumenerPage() {
        return this.numberPage;
    }

    private void addLogoSection(GridBagConstraints gbc) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/image.png"));


        JLabel leftImg = new JLabel(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelNorth.add(leftImg, gbc);

        JLabel logoText = createLabel("POMODORO TIMER", Utilities.LOGO_FONT, Color.WHITE);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelNorth.add(logoText, gbc);

        JLabel rightImg = new JLabel(icon);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelNorth.add(rightImg, gbc);
    }

    private JTextArea createJTextArea(String text, Font font, Color color) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(font);
        textArea.setForeground(color);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        return textArea;
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
    public static void main(String[] args) {
        Help hp = new Help(null, 1);
        hp.setVisible(true);
    }
}
