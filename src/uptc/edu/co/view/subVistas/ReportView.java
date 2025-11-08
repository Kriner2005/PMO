package uptc.edu.co.view.subVistas;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uptc.edu.co.utilities.Utilities;

public class ReportView extends JFrame {
    public JButton btnStatistics, btnRanking;
    public JPanel cardPanel;
    public CardLayout cardLayout;
    private ActionListener listener;
    
    public ReportView(JPanel statisticsPanel, JPanel rankingPanel, ActionListener listener) {
        this.listener = listener;
        setTitle("Report");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        topBar.setBackground(Utilities.PANEL_REPORTVIEW_COLOR);
        
        btnStatistics = new JButton("STATISTICS");
        btnStatistics.setActionCommand("SHOW_STATISTICS");
        btnStatistics.addActionListener(listener);
        
        btnRanking = new JButton("RANKING");
        btnRanking.setActionCommand("SHOW_RANKING");
        btnRanking.addActionListener(listener);
        
        for (JButton b : new JButton[]{btnStatistics, btnRanking}) {
            b.setBackground(Utilities.BUTTON_REPORTVIEW_COLOR);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setPreferredSize(new Dimension(120, 30));
            topBar.add(b);
        }
        
        add(topBar, BorderLayout.NORTH);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(statisticsPanel, "STATISTICS");
        cardPanel.add(rankingPanel, "RANKING");
        
        add(cardPanel, BorderLayout.CENTER);
        
    }
    public static void main(String[] args) {
        ReportView report = new ReportView(null, null, null);
        report.setVisible(true);
    }
}