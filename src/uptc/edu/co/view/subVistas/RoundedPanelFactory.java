
package uptc.edu.co.view.subVistas;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class RoundedPanelFactory {

    public static JPanel createRoundedPanel(int arc, Color bgColor, int grosorBorde, String rutaImagen) {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (rutaImagen != null) {
                    Image imagenFondo = new ImageIcon(rutaImagen).getImage();
                    g2.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g2.setColor(bgColor);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                    g2.setStroke(new BasicStroke(grosorBorde));
                    g2.setColor(Color.WHITE);
                    g2.drawRoundRect(
                            grosorBorde / 2,
                            grosorBorde / 2,
                            getWidth() - grosorBorde,
                            getHeight() - grosorBorde,
                            arc, arc
                    );
                }

                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(8, 10, 8, 10));
        return panel;
    }
}
