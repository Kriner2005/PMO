package uptc.edu.co.view.subVistas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import uptc.edu.co.utilities.CustomComponents;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import uptc.edu.co.utilities.Utilities;

public class PomodoroLogin extends JDialog {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel labelImagen;
    private ActionListener listener;
    private JPanel fondo;

    public PomodoroLogin(ActionListener listener) {
        this.listener = listener;
        setTitle("POMODORO TIMER");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // ✅ CORRECTO: Sin la barra inicial
        fondo = CustomComponents.createRoundedPanel(0, Color.white, 0, "/uptc/edu/co/resources/images/paulas/fondologin.png");

        fondo.setLayout(new GridBagLayout());
        initComponent();
        this.add(fondo);
    }

    public void initComponent() {
        JPanel panel = new CustomComponents().createRoundedPanel(30, Utilities.MAIN_COLOR, 4, null);
        panel.setBorder(new EmptyBorder(40, 40, 40, 40));
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        panel.setSize(400, 500);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        fondo.add(panel, gbc);

        File file = new File("src/uptc/edu/co/resources/images/paulas/image.png");
        ImageIcon icono = new ImageIcon(file.getAbsolutePath());

        labelImagen = new JLabel(icono);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(labelImagen, gbc);

        JLabel title = new JLabel("POMODORO TIMER");
        title.setFont(Utilities.LOGO_FONT);
        title.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(title, gbc);

        labelImagen = new JLabel(icono);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(labelImagen, gbc);

        JLabel subtitle = new JLabel("LOGIN");
        subtitle.setFont(Utilities.SUBTITLE_FONT_LOGIN);
        subtitle.setForeground(Color.white);
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy++;
        panel.add(subtitle, gbc);

        emailField = new CustomComponents().createRoundedTextField(15, 20, "EMAIL ID");
        gbc.gridwidth = 3;
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(emailField, gbc);

        gbc.gridy++;
        passwordField = new CustomComponents().createRoundedJPaswJPasswordField(15, 30, "PASSWORD");
        panel.add(passwordField, gbc);

        gbc.insets = new Insets(1, 10, 1, 10);

        JButton loginBtn = new CustomComponents().createRoundedButton("login", Utilities.BUTTON_COLOR_LOGIN, 30, 4, 8);
        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx = 0;
        loginBtn.setActionCommand("BUTTON_LOGIN");
        loginBtn.addActionListener(listener);
        panel.add(loginBtn, gbc);

        JButton registerBtn = new CustomComponents().createRoundedButton("Registrar", Utilities.BUTTON_COLOR_LOGIN, 30, 4, 8);
        gbc.gridx = 1;
        registerBtn.setActionCommand("BUTTON_REGISTER_LOGIN");
        registerBtn.addActionListener(listener);
        panel.add(registerBtn, gbc);

        JButton backBtn = new CustomComponents().createRoundedButton("↩", Utilities.BUTTON_COLOR_LOGIN, 20, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        backBtn.setActionCommand("BUTTON_BACK_LOGIN");
        backBtn.addActionListener(listener);
        panel.add(backBtn, gbc);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);

    }

    public String getTextEmail() {
        String email = emailField.getText();
        return email;
    }

    public String getTextPassword() {
        String pass = new String(passwordField.getPassword());
        return pass;
    }

    public void setTextEmail() {
        emailField.setText("");

    }

    public void setTextPassword() {
        passwordField.setText("");

    }

}
