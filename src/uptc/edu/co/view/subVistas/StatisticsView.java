package uptc.edu.co.view.subVistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import uptc.edu.co.utilities.Utilities;

public class StatisticsView extends JPanel {

    public JButton prevWeek, nextWeek, prevYear, nextYear;
    public JLabel weekLabel, yearLabel;
    public BarChartPanel barChartPanel;
    public LineChartPanel lineChartPanel;
    private ActionListener listener;

    public StatisticsView(ActionListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        initComponents();
    }

    public void initComponents() {
        

        // Métricas superiores
        JPanel metricsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        metricsPanel.setBackground(Color.WHITE);
        metricsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        metricsPanel.add(createMetricCard("Tasks Done", "15"));
        metricsPanel.add(createMetricCard("Streak Weeks", "2"));
        metricsPanel.add(createMetricCard("Focus Hours", "16.5"));
        add(metricsPanel, BorderLayout.NORTH);

        // Paneles de gráficos
        JPanel chartsPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        chartsPanel.setBackground(Color.WHITE);
        chartsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel de barras
        JPanel barContainer = new JPanel(new BorderLayout());
        barChartPanel = new BarChartPanel();
        barContainer.add(barChartPanel, BorderLayout.CENTER);

        JPanel weekNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        prevWeek = new JButton("<");
        prevWeek.setActionCommand("PREV_WEEK");
        prevWeek.addActionListener(listener);
        nextWeek = new JButton(">");
        nextWeek.setActionCommand("NEXT_WEEK");
        nextWeek.addActionListener(listener);
        weekLabel = new JLabel("Week 1", JLabel.CENTER);
        weekNav.add(prevWeek);
        weekNav.add(weekLabel);
        weekNav.add(nextWeek);
        barContainer.add(weekNav, BorderLayout.SOUTH);

        //  Panel de líneas 
        JPanel lineContainer = new JPanel(new BorderLayout());
        lineChartPanel = new LineChartPanel();
        lineContainer.add(lineChartPanel, BorderLayout.CENTER);

        JPanel yearNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        prevYear = new JButton("<");
        prevYear.setActionCommand("PREV_YEAR");
        prevYear.addActionListener(listener);
        nextYear = new JButton(">");
        nextYear.setActionCommand("NEXT_YEAR");
        nextYear.addActionListener(listener);
        yearLabel = new JLabel("2024", JLabel.CENTER);
        yearNav.add(prevYear);
        yearNav.add(yearLabel);
        yearNav.add(nextYear);
        lineContainer.add(yearNav, BorderLayout.SOUTH);

        chartsPanel.add(barContainer);
        chartsPanel.add(lineContainer);
        add(chartsPanel, BorderLayout.CENTER);
    }

    private JPanel createMetricCard(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Utilities.STATISTICS_LABELS_COUNT);
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(Utilities.STATISTICS_LABELS_VALUE_FONT);
        valueLabel.setForeground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titleLabel.setForeground(Color.WHITE);

        panel.add(valueLabel, BorderLayout.CENTER);
        panel.add(titleLabel, BorderLayout.SOUTH);
        return panel;
    }

    // Panel de barras
    public static class BarChartPanel extends JPanel {

        private int[] values = new int[7];
        private final String[] labels = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        public void setValues(int[] v) {
            this.values = v;
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int width = getWidth(), height = getHeight(), maxVal = 6;
            int barWidth = width / values.length - 20;

            g2.setColor(Color.GRAY);
            for (int i = 1; i <= maxVal; i++) {
                int y = height - (i * (height - 80) / maxVal) - 40;
                g2.drawLine(50, y, width - 20, y);
                g2.drawString(String.valueOf(i), 30, y + 5);
            }

            for (int i = 0; i < values.length; i++) {
                int barHeight = (int) ((double) values[i] / maxVal * (height - 80));
                int x = i * (barWidth + 15) + 60;
                int y = height - barHeight - 40;
                g2.setColor(new Color(200, 0, 0));
                g2.fillRect(x, y, barWidth, barHeight);
                g2.setColor(Color.BLACK);
                g2.drawString(labels[i], x + 5, height - 20);
            }

            double avg = Arrays.stream(values).average().orElse(0);
            int avgY = height - (int) (avg / maxVal * (height - 80)) - 40;
            g2.setColor(Color.BLUE);
            g2.drawLine(50, avgY, width - 20, avgY);
        }
    }

    // Panel de líneas
    public static class LineChartPanel extends JPanel {

        private int[] focus = new int[12];
        private int[] breakTime = new int[12];
        private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        public void setValues(int[] focus, int[] breakTime) {
            this.focus = focus;
            this.breakTime = breakTime;
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int width = getWidth(), height = getHeight(), maxVal = 60;
            int xStep = width / (months.length + 1);

            g2.setColor(Color.GRAY);
            for (int i = 10; i <= maxVal; i += 10) {
                int y = height - (i * (height - 80) / maxVal) - 30;
                g2.drawLine(50, y, width - 20, y);
                g2.drawString(String.valueOf(i), 30, y + 5);
            }

            g2.setColor(Color.RED);
            drawLine(g2, focus, xStep, height, maxVal);
            g2.setColor(Color.BLUE);
            drawLine(g2, breakTime, xStep, height, maxVal);

            g2.setColor(Color.BLACK);
            for (int i = 0; i < months.length; i++) {
                int x = (i + 1) * xStep;
                g2.drawString(months[i], x - 10, height - 10);
            }
        }

        private void drawLine(Graphics2D g2, int[] values, int xStep, int height, int maxVal) {
            int prevX = 0, prevY = 0;
            for (int i = 0; i < values.length; i++) {
                int x = (i + 1) * xStep;
                int y = height - (int) ((double) values[i] / maxVal * (height - 80)) - 30;
                g2.fillOval(x - 3, y - 3, 6, 6);
                if (i > 0) {
                    g2.drawLine(prevX, prevY, x, y);
                }
                prevX = x;
                prevY = y;
            }
        }
    }
}
