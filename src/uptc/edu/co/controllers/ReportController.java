/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import uptc.edu.co.models.session.Statistics;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.subVistas.RankingView;
import uptc.edu.co.view.subVistas.ReportView;
import uptc.edu.co.view.subVistas.StatisticsView;

/**
 *
 * @author alber
 */
public class ReportController implements ActionListener {

    private final ReportView view;
    private final RankingView rankingView;
    private final StatisticsView statisticsView;
    private final Statistics statistics;

    public ReportController() {
        this.statistics = new Statistics();
        this.rankingView = new RankingView();
        this.statisticsView = new StatisticsView(this);
        this.view = new ReportView(statisticsView, rankingView, this);
        this.view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SHOW_RANKING" -> {
                loadRanking();
                view.cardLayout.show(view.cardPanel, "RANKING");
            }
            case "SHOW_STATISTICS" ->
                view.cardLayout.show(view.cardPanel, "STATISTICS");
        }
    }

    private void loadRanking() {
        List<Map.Entry<User, Double>> ranking = statistics.getUserRankingByHours();
        //Datos de prubea
        //List<Map.Entry<User, Double>> ranking = statistics.prueba();

        int size = Math.min(10, ranking.size());
        User[] users = new User[size];
        double[] hours = new double[size];

        for (int i = 0; i < size; i++) {
            users[i] = ranking.get(i).getKey();
            hours[i] = ranking.get(i).getValue();
        }

        rankingView.SetData(users, hours);

    }
    
    private void loadStatistics() {
    
    }
    
    public static void main(String[] args) {
        new ReportController();
    }

}
