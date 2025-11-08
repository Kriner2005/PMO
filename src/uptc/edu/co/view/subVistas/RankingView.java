package uptc.edu.co.view.subVistas;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Arrays;
import uptc.edu.co.models.user.User;
import uptc.edu.co.utilities.Utilities;

public class RankingView extends JPanel {

    private RankingChartPanel chartPanel;

    public RankingView() {
        setLayout(new BorderLayout());
        setBackground(Utilities.RANKING_COLOR);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("RANKING", JLabel.CENTER);
        title.setFont(Utilities.TITLE_FONT_RANKING);
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);
        chartPanel = new RankingChartPanel();
        add(chartPanel, BorderLayout.CENTER);
    }

    public void SetData(User[] users, double[] hours) {
        chartPanel.SetData(users, hours);
    }

    static class RankingChartPanel extends JPanel {

        private User[] users;
        private double[] hours;

        public void SetData(User[] users, double[] hours) {
            this.users = users;
            this.hours = hours;
            repaint();
        }

        public RankingChartPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (users.length == 0) {
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            int width = getWidth() - 150;
            int height = getHeight();
            int barHeight = height / (users.length * 2);

            double maxVal = Arrays.stream(hours).max().orElse(1);
            for (double h : hours) {
                maxVal = Math.max(maxVal, h);
            }

            for (int i = 0; i < users.length; i++) {
                int y = (i * 2 + 1) * barHeight;
                int barWidth = (int) ((hours[i] / maxVal) * width);
                g2.setColor(Color.white);
                g2.fillRect(120, y, barWidth, barHeight);
                g2.setColor(Color.WHITE);
                g2.drawString((i + 1) + ". " + users[i].getName(), 20, y + barHeight / 2 + 5);
                g2.drawString(hours[i] + " h", 130 + barWidth, y + barHeight / 2 + 5);
            }
        }
    }
}
