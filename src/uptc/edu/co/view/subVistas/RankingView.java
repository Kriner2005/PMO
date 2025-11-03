
package uptc.edu.co.view.subVistas;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import model.RankingModel;
import uptc.edu.co.utilities.Utilities;

public class RankingView extends JPanel {
    public RankingView(RankingModel model) {
        setLayout(new BorderLayout());
        setBackground(Utilities.RANKING_COLOR);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("RANKING", JLabel.CENTER);
        title.setFont(Utilities.TITLE_FONT_RANKING);
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);
        add(new RankingChartPanel(model), BorderLayout.CENTER);
    }

    static class RankingChartPanel extends JPanel {
        private final RankingModel model;

        public RankingChartPanel(RankingModel model) {
            this.model = model;
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            String[] users = model.getUsers();
            double[] hours = model.getHours();

            int width = getWidth() - 150;
            int height = getHeight();
            int barHeight = height / (users.length * 2);

            double maxVal = 0;
            for (double h : hours) maxVal = Math.max(maxVal, h);

            for (int i = 0; i < users.length; i++) {
                int y = (i * 2 + 1) * barHeight;
                int barWidth = (int) ((hours[i] / maxVal) * width);
                g2.setColor(Color.white);
                g2.fillRect(120, y, barWidth, barHeight);
                g2.setColor(Color.WHITE);
                g2.drawString((i + 1) + ". " + users[i], 20, y + barHeight / 2 + 5);
                g2.drawString(hours[i] + " h", 130 + barWidth, y + barHeight / 2 + 5);
            }
        }
    }
}
