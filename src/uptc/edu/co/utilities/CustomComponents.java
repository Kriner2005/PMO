package uptc.edu.co.utilities;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * Clase unificada de componentes personalizados con bordes redondeados Fusiona
 * funcionalidades de View.CustomComponents y Util.CustomComponents
 */
public class CustomComponents {

    // ==================== TEXT FIELDS ====================
    /**
     * Crea un JTextField con bordes redondeados simple
     */
    public static JTextField createRoundedTextField(int arc) {
        JTextField textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }

            @Override
            public void setBorder(Border border) {
            }
        };

        textField.setOpaque(false);
        textField.setBorder(new EmptyBorder(5, 10, 5, 10));
        return textField;
    }

    /**
     * Crea un JTextField con placeholder y bordes redondeados
     */
    public static JTextField createRoundedTextField(int columns, int radius, String placeholder) {
        JTextField textField = new JTextField(columns) {
            private boolean showingPlaceholder = true;

            {
                setForeground(new Color(180, 0, 0));
                setText(placeholder);
                setFont(new Font("Arial", Font.ITALIC, 12));

                this.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (showingPlaceholder) {
                            setText("");
                            setForeground(Color.BLACK);
                            setFont(new Font("Arial", Font.PLAIN, 12));
                            showingPlaceholder = false;
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (getText().isEmpty()) {
                            setText(placeholder);
                            setForeground(Color.GRAY);
                            setFont(new Font("Arial", Font.ITALIC, 12));
                            showingPlaceholder = true;
                        }
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.setColor(new Color(200, 0, 0));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        textField.setOpaque(false);
        textField.setBorder(new EmptyBorder(5, 10, 5, 10));
        return textField;
    }

    // ==================== PASSWORD FIELDS ====================
    /**
     * Crea un JPasswordField con placeholder y bordes redondeados
     */
    public static JPasswordField createRoundedJPaswJPasswordField(int columns, int radius, String placeholder) {
        JPasswordField passwordField = new JPasswordField(columns) {
            boolean showingPlaceholder = true;

            {
                setForeground(Color.GRAY);
                setEchoChar((char) 0);
                setText(placeholder);
                setFont(new Font("Arial", Font.ITALIC, 12));

                this.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (showingPlaceholder) {
                            setText("");
                            setForeground(Color.BLACK);
                            setEchoChar('•');
                            setFont(new Font("Arial", Font.PLAIN, 12));
                            showingPlaceholder = false;
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (getPassword().length == 0) {
                            setForeground(Color.GRAY);
                            setEchoChar((char) 0);
                            setText(placeholder);
                            setFont(new Font("Arial", Font.ITALIC, 12));
                            showingPlaceholder = true;
                        }
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.setColor(new Color(200, 0, 0));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        passwordField.setOpaque(false);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return passwordField;
    }

    // ==================== SPINNERS ====================
    /**
     * Crea un JSpinner con estilo redondeado
     */
    public static JSpinner createRoundedSpinner(int value, int min, int max, int step) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(value, min, max, step));
        spinner.setOpaque(false);
        spinner.setBorder(BorderFactory.createLineBorder(Color.WHITE, 8, true));

        JComponent editor = spinner.getEditor();
        editor.setOpaque(false);
        spinner.setForeground(new Color(180, 0, 0));
        return spinner;
    }

    // ==================== COMBOBOXES ====================
    /**
     * Crea un JComboBox con estilo redondeado
     */
    public static JComboBox<String> createRoundedComboBox(String[] items, int borderRadius) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setForeground(new Color(180, 0, 0));
        combo.setBackground(new Color(0, 0, 0, 0));

        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);
                button.setOpaque(false);
                return button;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, borderRadius, borderRadius);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), borderRadius, borderRadius);
                g2.setColor(new Color(180, 0, 0));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, borderRadius, borderRadius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setOpaque(true);
                lbl.setBackground(Color.WHITE);
                lbl.setForeground(Color.BLACK);
                return lbl;
            }
        });

        if (combo.getEditor() != null) {
            Component editor = combo.getEditor().getEditorComponent();
            if (editor instanceof JComponent) {
                ((JComponent) editor).setOpaque(true);
                editor.setBackground(Color.WHITE);
                ((JComponent) editor).setForeground(Color.BLACK);
            }
        }

        return combo;
    }

    // ==================== PANELS ====================
    /**
     * Crea un JPanel con bordes redondeados y opción de imagen de fondo
     */
    /**
     * Crea un JPanel con bordes redondeados y opción de imagen de fondo
     */
    public static JPanel createRoundedPanel(int arc, Color bgColor, int grosorBorde, String rutaImagen) {
        final Image imagenFondoCargada;

        // ✅ Intentar cargar desde el classpath
        Image tempImg = null;
        if (rutaImagen != null) {
            java.net.URL imgURL = CustomComponents.class.getResource(rutaImagen);
            if (imgURL != null) {
                tempImg = new ImageIcon(imgURL).getImage();
            } 
        }
        imagenFondoCargada = tempImg;

        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // 🔹 Muy importante para refrescar correctamente

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (imagenFondoCargada != null) {
                    // 🔹 Dibuja la imagen escalada al tamaño actual del panel
                    g2.drawImage(imagenFondoCargada, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // 🔹 Si no hay imagen, usar color de fondo
                    g2.setColor(bgColor);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                }

                g2.dispose();
            }
        };

        panel.setOpaque(true); // 🔹 Ponemos true, para evitar que otros fondos “tapen”
        panel.setBorder(new EmptyBorder(8, 10, 8, 10));
        return panel;
    }

    /**
     * Crea un panel con borde redondeado solo en el lado izquierdo (sidebar)
     */
    public static JPanel createLeftRoundedSideBar(int arc) {
        JPanel sideBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                g2.setColor(getBackground());

                java.awt.geom.GeneralPath path = new java.awt.geom.GeneralPath();
                path.moveTo(arc, 0);
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(arc, height);
                path.quadTo(0, height, 0, height - arc);
                path.lineTo(0, arc);
                path.quadTo(0, 0, arc, 0);
                path.closePath();

                g2.fill(path);
                g2.dispose();

                super.paintComponent(g);
            }
        };

        sideBar.setOpaque(false);
        sideBar.setPreferredSize(new Dimension(20, 0));
        return sideBar;
    }

    /**
     * Aplica un borde redondeado personalizado a un panel existente
     */
    public static void aplicarBordeRedondeado(JPanel panel, Color color, int grosor, int radio) {
        panel.setBorder(new RoundedBorder(color, grosor, radio));
    }

    /**
     * Hace que un panel existente tenga bordes redondeados modificando su UI
     */
    public static void hacerPanelRedondeado(JPanel panel, int radio, Color colorFondo, Color colorBorde, int grosorBorde) {
        panel.setOpaque(false);
        panel.setBackground(colorFondo);
        panel.setBorder(null);

        panel.setUI(new javax.swing.plaf.PanelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo redondeado
                g2.setColor(colorFondo);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radio, radio);

                // Borde redondeado
                g2.setColor(colorBorde);
                g2.setStroke(new BasicStroke(grosorBorde));
                g2.drawRoundRect(grosorBorde / 2, grosorBorde / 2,
                        c.getWidth() - grosorBorde, c.getHeight() - grosorBorde, radio, radio);

                g2.dispose();
            }
        });
    }

    // ==================== BUTTONS ====================
    /**
     * Crea un botón redondeado simple
     */
    public static JButton createRoundedButton(String text, Color bgColor, int arc) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
        return button;
    }

    /**
     * Crea un botón redondeado con sombra
     */
    public static JButton createRoundedButton(String text, Color bgColor, int arc, int sombra, int transparencia) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(isEnabled() ? bgColor : Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                int sombraOffset = 2;
                int alpha = 80;

                g2.setColor(new Color(0, 0, 0, alpha));
                g2.fillRoundRect(sombraOffset, sombraOffset, getWidth() - 1, getHeight() - 1, arc, arc);

                g2.setColor(isEnabled() ? bgColor : Color.GRAY);
                g2.fillRoundRect(0, 0, getWidth() - sombraOffset, getHeight() - sombraOffset, arc, arc);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        button.setText(text);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);

        String emojiFont = "Segoe UI Emoji";
        Font testFont = new Font(emojiFont, Font.PLAIN, 12);
        if (testFont.canDisplayUpTo("❤") != -1) {
            emojiFont = "Arial";
        }

        button.setFont(new Font(emojiFont, Font.BOLD, 13));
        button.setBorder(new EmptyBorder(8, 12, 8, 12));
        return button;
    }

    // ==================== CLASES INTERNAS ====================
    /**
     * Clase interna para crear bordes redondeados personalizados
     */
    private static class RoundedBorder extends AbstractBorder {

        private Color color;
        private int grosor;
        private int radio;

        public RoundedBorder(Color color, int grosor, int radio) {
            this.color = color;
            this.grosor = grosor;
            this.radio = radio;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(grosor));
            g2.draw(new RoundRectangle2D.Double(
                    x + grosor / 2.0,
                    y + grosor / 2.0,
                    width - grosor,
                    height - grosor,
                    radio,
                    radio
            ));
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(grosor, grosor, grosor, grosor);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = grosor;
            insets.right = grosor;
            insets.top = grosor;
            insets.bottom = grosor;
            return insets;
        }
    }
}
