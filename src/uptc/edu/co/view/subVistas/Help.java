package uptc.edu.co.view.subVistas;

import uptc.edu.co.utilities.CustomComponents;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import uptc.edu.co.utilities.Utilities;

public class Help extends JDialog {

    private final ActionListener listener;
    private int numberPage;
    private JLabel JLabelTitle, JLabelText;
    private final ImageIcon icono;
    private final ImageIcon iconNext;
    private JPanel panel, panelNorth, panelCenter, panelLeft, panelRight;

    public Help(ActionListener listener, int numberPage) {
        this.listener = listener;
        this.numberPage = numberPage;
        this.icono = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/flecha1.png"));
        this.iconNext = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/flecha.png"));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(500, 300);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        panel = CustomComponents.createRoundedPanel(30, Utilities.MAIN_COLOR, 4, null);
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

        JLabelTitle = createLabel("", Utilities.TITLE_FONT_HELP, Color.WHITE);
        JLabelTitle.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
        panelCenter.add(JLabelTitle, gbc);

        JLabelText = createLabel("", Utilities.TEXT_FONT_HELP, Color.WHITE);
        JLabelText.setBorder(BorderFactory.createEmptyBorder(10, 2, 30, 2));
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelCenter.add(JLabelText, gbc);

        panel.add(panelCenter, BorderLayout.CENTER);

        panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setOpaque(false);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        panelRight = new JPanel(new GridBagLayout());
        panelRight.setOpaque(false);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        updateButtons();
        panel.add(panelLeft, BorderLayout.WEST);
        panel.add(panelRight, BorderLayout.EAST);

        setPageContent();

        this.add(panel);
    }

    public void setTextTitle(String title) {
        this.JLabelTitle.setText("<html><div style='text-align: center;'>" + title + "</div></html>");

    }

    public void setText(String text) {
        this.JLabelText.setText("<html>" + text + "</html>");
    }

    private JButton createButton(ImageIcon icon, String command) {
        JButton button = CustomComponents.createRoundedButton("", Utilities.MAIN_COLOR, 20, 0, 0);
        button.setIcon(icon);
        button.setActionCommand(command);
        button.addActionListener(listener);
        return button;
    }

    public void setPageContent() {
        if (numberPage >= 1 && numberPage <= Utilities.PAGES.length) {
            setTextTitle(Utilities.PAGES[numberPage - 1][0]);
            setText(Utilities.PAGES[numberPage - 1][1]);
            updateButtons();
            revalidate();
            repaint();
        }
    }

    private void updateButtons() {
        panelLeft.removeAll();
        panelRight.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Botón PREV
        if (numberPage > 1) {
            JButton prev = createButton(icono, "PREV_PAGE");
            gbc.anchor = GridBagConstraints.WEST;
            panelLeft.add(prev, gbc);
        } else {
            JLabel ext = createLabel("       ", Utilities.TEXT_FONT_HELP, Utilities.MAIN_COLOR);
            panelLeft.add(ext, gbc);
        }

        // Botón NEXT
        if (numberPage < Utilities.PAGES.length) {
            JButton next = createButton(iconNext, "NEXT_PAGE");
            gbc.anchor = GridBagConstraints.EAST;
            panelRight.add(next, gbc);
        } else {
            JLabel ext = createLabel("       ", Utilities.TEXT_FONT_HELP, Utilities.MAIN_COLOR);
            panelRight.add(ext, gbc);
        }

        panelLeft.revalidate();
        panelLeft.repaint();
        panelRight.revalidate();
        panelRight.repaint();
    }

    public void setNumberPage(int numberpage) {
        this.numberPage = numberpage;
        setPageContent();

    }

    public int getNumberPage() {
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

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }


}
