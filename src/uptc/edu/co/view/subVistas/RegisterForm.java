package uptc.edu.co.view.subVistas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import uptc.edu.co.utilities.CustomComponents;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import uptc.edu.co.utilities.Utilities;

public class RegisterForm extends JDialog {

    private JTextField nameField, emailField;
    private JPasswordField passField;
    private JLabel labelImagen, name, email, password;
    private ActionListener listener;

    public RegisterForm(ActionListener listener) {
        this.listener = listener;
        setTitle("Registro - POMODORO TIMER");
        setSize(500, 450);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new CustomComponents().createRoundedPanel(30, Utilities.MAIN_COLOR, 4, null);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Utilities.REGISTER_COLOR_FONDO);
        panel.setSize(500, 450);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        File file = new File("src/uptc/edu/co/resources/images/paulas/image.png");
        ImageIcon icono = new ImageIcon(file.getAbsolutePath());

        labelImagen = new JLabel(icono);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(labelImagen, gbc);

        JLabel title = new JLabel("POMODORO TIMER");
        title.setFont(Utilities.TITLE_FONT_REGISTER);
        title.setForeground(Color.WHITE);
        gbc.gridx = 1;
        panel.add(title, gbc);

        labelImagen = new JLabel(icono);
        gbc.gridx = 2;
        panel.add(labelImagen, gbc);

        JLabel subtitle = new JLabel("Registro");
        subtitle.setFont(Utilities.SUBTITLE_FONT_REGISTER);
        subtitle.setForeground(Color.WHITE);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(subtitle, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        name = new JLabel("Nombre:");
        name.setFont(Utilities.FONT_REGISTER_FIELDS);
        name.setForeground(Color.white);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(name, gbc);

        nameField = new CustomComponents().createRoundedTextField(15, 30, "Ingrese su nombre...");
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        email = new JLabel("Email:");
        email.setFont(Utilities.FONT_REGISTER_FIELDS);
        email.setForeground(Color.white);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(email, gbc);

        emailField = new CustomComponents().createRoundedTextField(15, 30, "Ingrese su correo...");
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        password = new JLabel("Contraseña:");
        password.setFont(Utilities.FONT_REGISTER_FIELDS);
        password.setForeground(Color.white);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(password, gbc);

        passField = new CustomComponents().createRoundedJPaswJPasswordField(15, 30, "Ingrese una contraseña...");
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        panel.add(passField, gbc);

        JButton registerBtn = new CustomComponents().createRoundedButton("Registrar", Utilities.BUTTON_COLOR_REGISTER, 30, 4, 8);
        gbc.gridy++;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        registerBtn.setActionCommand("BUTTON_REGISTER");
        registerBtn.addActionListener(listener);
        panel.add(registerBtn, gbc);

        JButton backBtn = new CustomComponents().createRoundedButton("↩", Utilities.BUTTON_COLOR_REGISTER, 30, 4, 8);
        gbc.gridy++;
        backBtn.setActionCommand("BUTTON_BACK_REGISTER");
        backBtn.addActionListener(listener);
        panel.add(backBtn, gbc);

        add(panel);
        this.setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);

    }

    public String getTextName() {
        String name = nameField.getText();
        return name;
    }

    public String getTextEmail() {
        String email = emailField.getText();
        return email;
    }

    public String getTextPassword() {
        String password = new String(passField.getPassword());
        return password;
    }
    public static void main(String[] args) {
        RegisterForm rg = new RegisterForm(null);
    }
}
