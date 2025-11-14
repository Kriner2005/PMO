package uptc.edu.co.view.subVistas;

import uptc.edu.co.utilities.CustomComponents;
import java.awt.*;

import javax.swing.*;
import uptc.edu.co.utilities.Utilities;

public final class EmergentWindow extends JDialog {

    private JLabel titleLabel, textLabel, labelImagen;
    private JPanel panel, panelCenter, panelLeft;

    public EmergentWindow(String title, String text, int image) {
        createImage(image);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(350, 150);
        initComponents();
        this.setTextTitle(title);
        this.setText(text);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        panel = CustomComponents.createRoundedPanel(30, Utilities.MAIN_COLOR, 6, null);
        CustomComponents.aplicarBordeRedondeado(panel, Color.WHITE, 2, 30);
        panel.setLayout(new BorderLayout(10, 10));
        panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setOpaque(false);
        gbc.insets = new Insets(5, 10, 1, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.titleLabel = createJLabel("", Utilities.TITLE_FONT_EMERGENT, Color.WHITE);
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelCenter.add(this.titleLabel, gbc);

        this.textLabel = createJLabel("", Utilities.TEXT_FONT_EMERGENT, Color.WHITE);
        this.textLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        gbc.gridy++;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelCenter.add(this.textLabel, gbc);
        panel.add(panelCenter, BorderLayout.CENTER);

        panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setOpaque(false);
        gbc.insets = new Insets(10, 10, 10, 10);
        panelLeft.add(labelImagen);
        panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        panel.add(panelLeft, BorderLayout.WEST);

        this.add(panel);
    }

    public void setTextTitle(String title) {
        this.titleLabel.setText("<html>" + title + "</html>");
    }

    public void setText(String text) {
        this.textLabel.setText("<html>" + text + "</html>");
    }

    private void createImage(int image) {
        switch (image) {
            case 1 ->
                this.labelImagen = new JLabel(new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/cancelar.png")));
            case 2 ->
                this.labelImagen = new JLabel(new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/confirm.png")));
        }
    }

    private JLabel createJLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

}
