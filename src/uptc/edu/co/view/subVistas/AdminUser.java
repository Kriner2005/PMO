package uptc.edu.co.view.subVistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uptc.edu.co.controllers.AdminController;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;

public class AdminUser extends javax.swing.JFrame {

    private AdminController control;

    public AdminUser() {
        initComponents();
    }
//    controlador que carga a los usuarios 

    public AdminUser(AdminController control) {
        this.control = control;
    }

    // Método para asignar el controlador después de crear la vista
    public void setControlador(AdminController control) {
        this.control = control;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        JPanelUser = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panerl De Usuarios Existentes");

        jPanel1.setBackground(new java.awt.Color(194, 11, 11));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LISTA DE USUARIOS");

        jScrollPane.setBorder(null);

        JPanelUser.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout JPanelUserLayout = new javax.swing.GroupLayout(JPanelUser);
        JPanelUser.setLayout(JPanelUserLayout);
        JPanelUserLayout.setHorizontalGroup(
            JPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        JPanelUserLayout.setVerticalGroup(
            JPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(JPanelUser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    // End of variables declaration              
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AdminUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AdminUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AdminUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AdminUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AdminUser().setVisible(true);
//            }
//        });
//    }

    // MEOTOD QUE RECIBIRA LOS USUARIOS 
    public void mostrarUsuarios(List<User> usuarios) {
        JPanelUser.removeAll();
        JPanelUser.setLayout(new BoxLayout(JPanelUser, BoxLayout.Y_AXIS));

        if (usuarios == null || usuarios.isEmpty()) {
            // Mostrar mensaje si no hay usuarios
            JLabel mensajeVacio = new JLabel("No hay usuarios para mostrar");
            mensajeVacio.setForeground(Color.GRAY);
            mensajeVacio.setAlignmentX(CENTER_ALIGNMENT);
            JPanelUser.add(Box.createVerticalGlue());
            JPanelUser.add(mensajeVacio);
            JPanelUser.add(Box.createVerticalGlue());
        } else {
            for (User usuario : usuarios) {
                JPanel tarjeta = crearTarjetaUsuario(usuario);
                JPanelUser.add(tarjeta);
                JPanelUser.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        JPanelUser.revalidate();
        JPanelUser.repaint();
    }

    // Métodos para mostrar mensajes (usados por el controlador)
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Visual de como se ven los usuarios
    private JPanel crearTarjetaUsuario(User usuario) {
        JPanel card = new JPanel();
        card.setBackground(new Color(220, 40, 40));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 30, 30), 1),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        card.setLayout(new BorderLayout(20, 0));

        // Panel de texto (izquierda)
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel nombreLabel = new JLabel(usuario.getName() + " - " + usuario.getEmail());
        nombreLabel.setForeground(Color.WHITE);

        boolean esAdmin = usuario.getRol() == Role.ADMIN;
        JLabel rolLabel = new JLabel(esAdmin ? "👑 Administrador" : "👤 Usuario");
        rolLabel.setForeground(new Color(255, 255, 255, 180));
        rolLabel.setFont(rolLabel.getFont().deriveFont(11f));

        textPanel.add(nombreLabel);
        textPanel.add(rolLabel);
        textPanel.add(Box.createVerticalGlue());
        card.add(textPanel, BorderLayout.CENTER);

        // Panel de botones (derecha)
        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        // Botón Admin/Usuario
        boolean esAdminActual = usuario.getRol() == Role.ADMIN;
        javax.swing.JButton btnAdmin = new javax.swing.JButton(
                esAdminActual ? "👤 Hacer Usuario" : "👤 Hacer Admin"
        );
        btnAdmin.setBackground(esAdminActual
                ? new Color(60, 120, 170) : new Color(57, 151, 66));
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFocusPainted(false);
        btnAdmin.setBorderPainted(false);
        btnAdmin.setPreferredSize(new Dimension(150, 40));
        btnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Efecto hover
        btnAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boolean esAdmin = usuario.getRol() == Role.ADMIN;
                btnAdmin.setBackground(esAdmin
                        ? new Color(70, 130, 180) : new Color(67, 171, 76));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boolean esAdmin = usuario.getRol() == Role.ADMIN;
                btnAdmin.setBackground(esAdmin
                        ? new Color(60, 120, 170) : new Color(57, 151, 66));
            }
        });

        // Listener del botón Admin
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control != null) {
                    control.cambiarRolUsuario(usuario);
                    
                    // Actualizar UI
                    boolean esAdmin = usuario.getRol() == Role.ADMIN;
                    btnAdmin.setText(esAdmin ? "👤 Hacer Usuario" : "👤 Hacer Admin");
                    btnAdmin.setBackground(esAdmin
                            ? new Color(60, 120, 170) : new Color(57, 151, 66));
                    rolLabel.setText(esAdmin ? "👑 Administrador" : "👤 Usuario");
                } else {
                    mostrarError("Error: Controlador no inicializado");
                }
            }
        });

        // Botón Eliminar
        javax.swing.JButton btnEliminar = new javax.swing.JButton("X Eliminar");
        btnEliminar.setBackground(new Color(180, 30, 30));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setPreferredSize(new Dimension(130, 40));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Efecto hover
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminar.setBackground(new Color(200, 50, 50));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminar.setBackground(new Color(180, 30, 30));
            }
        });

        // Listener del botón Eliminar
        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar este usuario?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                if (control != null) {
                    control.eliminarUsuario(usuario);
                    JPanelUser.remove(card);
                    JPanelUser.revalidate();
                    JPanelUser.repaint();
                } else {
                    mostrarError("Error: Controlador no inicializado");
                }
            }
        });

        botonesPanel.add(btnAdmin);
        botonesPanel.add(btnEliminar);

        card.add(botonesPanel, BorderLayout.EAST);

        return card;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel JPanelUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration                   
}