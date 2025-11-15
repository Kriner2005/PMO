package uptc.edu.co.view.subVistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import uptc.edu.co.utilities.CustomComponents;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import uptc.edu.co.utilities.Utilities;

public class SettingsView extends JFrame {

    private JButton selectedButton, configBtn;
    private JPanel sidebar;
    private ActionListener listener;
    private JTextField pomodoroField;
    private JTextField shortBreakField;
    private JTextField longBreakField;

    public SettingsView(ActionListener listener) {
        this.listener = listener;
        setTitle("Settings Panel");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        sidebar = createPanelSidebar();
        loadIconsSidebar();

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Utilities.SETTINGS_COLOR_FONDO);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        pomodoroField = CustomComponents.createRoundedTextField(10, 13, "25");
        shortBreakField = CustomComponents.createRoundedTextField(10, 13, "5");
        longBreakField = CustomComponents.createRoundedTextField(15, 13, "15");

        pomodoroField.addActionListener(listener);
        pomodoroField.setActionCommand("SET_POMODORO");

        shortBreakField.addActionListener(listener);
        shortBreakField.setActionCommand("SET_SHORT_BREAK");

        longBreakField.addActionListener(listener);
        longBreakField.setActionCommand("SET_LONG_BREAK");

        JPanel timerPanel = createTimerPanel(
                "TIMER",
                "/uptc/edu/co/resources/images/paulas/reloj.png",
                new String[]{"Pomodoro", "Short Break", "Long Break"},
                new JComponent[]{pomodoroField, shortBreakField, longBreakField}
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(timerPanel, gbc);

        JSpinner reminderSpinner = CustomComponents.createRoundedSpinner(5, 1, 10, 1);
        JComboBox<String> shortBreakBox = CustomComponents.createRoundedComboBox(
                new String[]{"Kitchen", "Bell", "Bird", "Digital", "Wood"}, 12);
        shortBreakBox.setSelectedItem("Kitchen");

        shortBreakBox.addActionListener(listener);
        shortBreakBox.setActionCommand("SET_SHORT_BREAK_SOUND");

        reminderSpinner.addChangeListener(e -> listener.actionPerformed(
                new java.awt.event.ActionEvent(reminderSpinner, ActionEvent.ACTION_PERFORMED, "SET_REMINDER")));

        JPanel notifPanel = createSectionPanel(
                "NOTIFICATION",
                "/uptc/edu/co/resources/images/paulas/notificacion.png",
                new String[]{"Reminder", "Short Break"},
                new JComponent[]{reminderSpinner, shortBreakBox}
        );

        gbc.gridy++;
        mainPanel.add(notifPanel, gbc);

        JComboBox<String> languageBox = CustomComponents.createRoundedComboBox(
                new String[]{"English", "Spanish", "French"}, 12);
        languageBox.setSelectedItem("English");
        languageBox.addActionListener(listener);
        languageBox.setActionCommand("CHANGE_LANGUAGE");

        JPanel langWrapper = new JPanel(new GridBagLayout());
        langWrapper.setOpaque(false);
        GridBagConstraints gbcWrapper = new GridBagConstraints();
        gbcWrapper.gridx = 0;
        gbcWrapper.gridy = 0;
        gbcWrapper.fill = GridBagConstraints.NONE;
        gbcWrapper.anchor = GridBagConstraints.CENTER;
        languageBox.setPreferredSize(new Dimension(150, 30));
        langWrapper.add(languageBox, gbcWrapper);

        JPanel langPanel = createSectionPanel(
                "LENGUAJE",
                "/uptc/edu/co/resources/images/paulas/idioma.png",
                new String[]{"Change Language"},
                new JComponent[]{langWrapper}
        );

        gbc.gridy++;
        mainPanel.add(langPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel resetPanel = new JPanel(new GridBagLayout());
        resetPanel.setOpaque(false);

        JButton resetButton = createButtonReset();

        resetButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 60));
                g2.fillRoundRect(5, 5, c.getWidth() - 10, c.getHeight() - 10, 20, 20);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth() - 10, c.getHeight() - 10, 20, 20);
                g2.setColor(Color.WHITE);
                g2.setFont(c.getFont());
                FontMetrics fm = g2.getFontMetrics();
                String text = ((JButton) c).getText();
                int textX = (c.getWidth() - fm.stringWidth(text)) / 2;
                int textY = (c.getHeight() + fm.getAscent()) / 2 - 8;
                g2.drawString(text, textX, textY);
            }
        });

        resetPanel.add(resetButton);
        mainPanel.add(resetPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private JButton createSidebarButton(ImageIcon icon) {
        JButton button = new JButton(icon) {
            @Override
            protected void paintComponent(Graphics g) {
                if (this == selectedButton) {
                    g.setColor(Utilities.SETTINGS_COLOR_SELECT_PAGE);
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }
                super.paintComponent(g);
            }
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(70, 50));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        button.addActionListener(e -> {
            if (selectedButton != null && selectedButton != configBtn) {
                selectedButton.repaint();
            }
            button.repaint();
        });

        return button;
    }

    public void setSelectedButton(JButton selectedButton) {
        this.selectedButton = selectedButton;
    }

    private JLabel createSidebarLogo(ImageIcon icon) {
        JLabel logo = new JLabel(icon);
        //logo.setFont(new Font("Arial", Font.PLAIN, 22));
        //new Color(150, 0, 0)
        return logo;
    }

    private JPanel createSectionPanel(String title, String iconPath, String[] labels, JComponent[] values) {

        JPanel container = RoundedPanelFactory.createRoundedPanel(25, Utilities.SETTINGS_COLOR_PANELES_CONFIG, 2, null);
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(450, 130));

        // TÍTULO
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        titlePanel.setOpaque(false);

        // 
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        JLabel leftIcon = new JLabel(icon);
        JLabel rightIcon = new JLabel(icon);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(leftIcon);
        titlePanel.add(titleLabel);
        titlePanel.add(rightIcon);

        container.add(titlePanel, BorderLayout.NORTH);

        // CAMPOS
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setOpaque(false);
        fieldsPanel.setLayout(new GridLayout(labels.length, 2, 10, 10));
        fieldsPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.PLAIN, 13));
            fieldsPanel.add(lbl);
            fieldsPanel.add(values[i]);
        }

        container.add(fieldsPanel, BorderLayout.CENTER);

        return container;
    }

    private JPanel createTimerPanel(String title, String iconPath, String[] labels, JComponent[] values) {
        JPanel container = RoundedPanelFactory.createRoundedPanel(25, new Color(180, 0, 0), 2, null);
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(450, 130));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        titlePanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        JLabel leftIcon = new JLabel(icon);
        JLabel rightIcon = new JLabel(icon);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(leftIcon);
        titlePanel.add(titleLabel);
        titlePanel.add(rightIcon);

        container.add(titlePanel, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new GridLayout(1, labels.length, 20, 0));
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        for (int i = 0; i < labels.length; i++) {
            JPanel col = new JPanel();
            col.setOpaque(false);
            col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));

            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.PLAIN, 13));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

            values[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            col.add(lbl);
            col.add(Box.createVerticalStrut(5));
            col.add(values[i]);

            fieldsPanel.add(col);
        }

        container.add(fieldsPanel, BorderLayout.CENTER);
        return container;
    }

    private JButton createButtonReset() {
        JButton resetButton = new JButton("RESET ALL") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 25;
                Color mainColor = Utilities.SETTINGS_COLOR_BUTTON_RESET_SOMBRA_2;
                g2.setColor(mainColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                g2.dispose();
                super.paintComponent(g);
            }
        };
        resetButton.setFont(Utilities.FONT_SETTINGS_BUTTON_RESET);
        resetButton.setBackground(Utilities.SETTINGS_COLOR_BUTTON_RESET);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resetButton.setPreferredSize(new Dimension(200, 90));
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return resetButton;
    }

    private JPanel createPanelSidebar() {
        sidebar = new JPanel(new GridLayout(6, 1, 0, 25)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();

                GradientPaint base = new GradientPaint(
                        0, 0, Utilities.SETTINGS_COLOR_FONDO_SLIDERBAR,
                        getWidth(), 0, Utilities.SETTINGS_COLOR_SOMBRA_SLIDERBAR
                );
                g2.setPaint(base);
                g2.fillRect(0, 0, getWidth(), getHeight());

                for (int i = 0; i < 10; i++) {
                    g2.setColor(new Color(0, 0, 0, 30 - i * 3));
                    g2.fillRect(getWidth() - (10 - i), 0, 1, getHeight());
                }

                g2.dispose();
            }
        };
        sidebar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
        sidebar.setPreferredSize(new Dimension(90, 0));
        sidebar.setOpaque(false);
        return sidebar;
    }

    private void loadIconsSidebar() {
    // ✅ Cargar desde classpath
    ImageIcon logo = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/image.png"));
    ImageIcon home = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/house.png"));
    ImageIcon stats = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/statistics.png"));
    ImageIcon config = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/configuration.png"));

    sidebar.add(createSidebarLogo(logo));
    
    JButton homeBtn = createSidebarButton(home);
    homeBtn.setActionCommand("HOME_BUTTON_SETTINGS");
    homeBtn.addActionListener(listener);
    sidebar.add(homeBtn);
    
    JButton statsBtn = createSidebarButton(stats);
    statsBtn.setActionCommand("STATISTICS_BUTTON_SETTINGS");
    statsBtn.addActionListener(listener);
    sidebar.add(statsBtn);
    
    configBtn = createSidebarButton(config);
    configBtn.setActionCommand("CONFIGURATION_BUTTON_SETTINGS");
    configBtn.addActionListener(listener);
    selectedButton = configBtn;
    sidebar.add(configBtn);
    
    add(sidebar, BorderLayout.WEST);
}

    public String getPomodoroField() {
        return pomodoroField.getText();
    }

    public void setPomodoroField(String pomodoroField) {
        this.pomodoroField.setText(pomodoroField);
    }

    public String getShortBreakField() {
        return shortBreakField.getText();
    }

    public void setShortBreakField(String shortBreakField) {
        this.shortBreakField.setText(shortBreakField); ;
    }

    public String getLongBreakField() {
        return longBreakField.getText();
    }

    public void setLongBreakField(String longBreakField) {
        this.longBreakField.setText(longBreakField);
    }  
}
