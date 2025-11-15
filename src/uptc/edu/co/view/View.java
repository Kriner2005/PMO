package uptc.edu.co.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import uptc.edu.co.utilities.CustomComponents;

public class View extends javax.swing.JFrame {

    public View(ActionListener listener) {
        initComponents();
        aplicarEstilos();
        configurarComponentes();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        menuNav = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        nav = new javax.swing.JPanel();
        userBtn = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();
        settingBtn = new javax.swing.JButton();
        navSubBtn = new javax.swing.JPanel();
        pomodoroBtn = new javax.swing.JButton();
        longBtn = new javax.swing.JButton();
        shortBtn1 = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textPanelInt = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        jLabelTiempo = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        leftTarea = new javax.swing.JPanel();
        btnTask = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rightCalendar = new javax.swing.JPanel();
        titleTask1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        helpBtn = new javax.swing.JButton();
        adminBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POMODORO");
        setBackground(new java.awt.Color(255, 255, 255));

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setMaximumSize(new java.awt.Dimension(1280, 800));
        main.setPreferredSize(new java.awt.Dimension(1280, 800));

        menuNav.setBackground(new java.awt.Color(194, 11, 11));
        menuNav.setPreferredSize(new java.awt.Dimension(1280, 90));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/logo.png"))); // NOI18N
        logo.setMaximumSize(new java.awt.Dimension(407, 70));
        logo.setMinimumSize(new java.awt.Dimension(407, 70));
        logo.setPreferredSize(new java.awt.Dimension(407, 70));

        nav.setBackground(new java.awt.Color(194, 11, 11));
        nav.setPreferredSize(new java.awt.Dimension(175, 50));

        userBtn.setBackground(new java.awt.Color(60, 63, 65));
        userBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/user_btn.png"))); // NOI18N
        userBtn.setBorder(null);
        userBtn.setBorderPainted(false);
        userBtn.setContentAreaFilled(false);
        userBtn.setFocusPainted(false);

        reportBtn.setBackground(new java.awt.Color(60, 63, 65));
        reportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/report_btn.png"))); // NOI18N
        reportBtn.setBorder(null);
        reportBtn.setBorderPainted(false);
        reportBtn.setContentAreaFilled(false);
        reportBtn.setFocusPainted(false);

        settingBtn.setBackground(new java.awt.Color(60, 63, 65));
        settingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/setting_btn.png"))); // NOI18N
        settingBtn.setBorder(null);
        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setFocusPainted(false);

        javax.swing.GroupLayout navLayout = new javax.swing.GroupLayout(nav);
        nav.setLayout(navLayout);
        navLayout.setHorizontalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(reportBtn)
                .addGap(59, 59, 59)
                .addComponent(userBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(settingBtn)
                .addGap(23, 23, 23))
        );
        navLayout.setVerticalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingBtn)
                    .addComponent(reportBtn)
                    .addComponent(userBtn))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout menuNavLayout = new javax.swing.GroupLayout(menuNav);
        menuNav.setLayout(menuNavLayout);
        menuNavLayout.setHorizontalGroup(
            menuNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuNavLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(79, 79, 79)
                .addComponent(nav, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        menuNavLayout.setVerticalGroup(
            menuNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
            .addGroup(menuNavLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nav, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        navSubBtn.setBackground(new java.awt.Color(255, 255, 255));
        navSubBtn.setPreferredSize(new java.awt.Dimension(1100, 125));

        pomodoroBtn.setBackground(new java.awt.Color(60, 63, 65));
        pomodoroBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/pomodoro_Btn.png"))); // NOI18N
        pomodoroBtn.setBorder(null);
        pomodoroBtn.setBorderPainted(false);
        pomodoroBtn.setContentAreaFilled(false);
        pomodoroBtn.setFocusPainted(false);

        longBtn.setBackground(new java.awt.Color(60, 63, 65));
        longBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/long_Btn.png"))); // NOI18N
        longBtn.setBorder(null);
        longBtn.setBorderPainted(false);
        longBtn.setContentAreaFilled(false);
        longBtn.setFocusPainted(false);

        shortBtn1.setBackground(new java.awt.Color(60, 63, 65));
        shortBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/short_Btn.png"))); // NOI18N
        shortBtn1.setBorder(null);
        shortBtn1.setBorderPainted(false);
        shortBtn1.setContentAreaFilled(false);
        shortBtn1.setFocusPainted(false);

        javax.swing.GroupLayout navSubBtnLayout = new javax.swing.GroupLayout(navSubBtn);
        navSubBtn.setLayout(navSubBtnLayout);
        navSubBtnLayout.setHorizontalGroup(
            navSubBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navSubBtnLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(pomodoroBtn)
                .addGap(35, 35, 35)
                .addComponent(shortBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(longBtn)
                .addGap(101, 101, 101))
        );
        navSubBtnLayout.setVerticalGroup(
            navSubBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navSubBtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(navSubBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shortBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(longBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pomodoroBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        centerPanel.setBackground(new java.awt.Color(193, 83, 83));
        centerPanel.setPreferredSize(new java.awt.Dimension(750, 514));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/herramienta.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/herramienta.png"))); // NOI18N
        jLabel6.setText("jLabel5");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/herramienta.png"))); // NOI18N
        jLabel7.setText("jLabel5");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/herramienta.png"))); // NOI18N
        jLabel8.setText("jLabel5");

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Sesion  #1");

        javax.swing.GroupLayout textPanelIntLayout = new javax.swing.GroupLayout(textPanelInt);
        textPanelInt.setLayout(textPanelIntLayout);
        textPanelIntLayout.setHorizontalGroup(
            textPanelIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textPanelIntLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        textPanelIntLayout.setVerticalGroup(
            textPanelIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textPanelIntLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/reset.png"))); // NOI18N
        btnReset.setToolTipText("");
        btnReset.setBorderPainted(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setFocusPainted(false);

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/start.png"))); // NOI18N
        btnStart.setToolTipText("");
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setFocusPainted(false);

        jLabelTiempo.setFont(new java.awt.Font("Source Sans Pro", 1, 92)); // NOI18N
        jLabelTiempo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTiempo.setText("25:00");

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, centerPanelLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(textPanelInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                        .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                        .addComponent(jLabelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(249, 249, 249))))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(textPanelInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jLabelTiempo)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        leftTarea.setBackground(new java.awt.Color(194, 11, 11));
        leftTarea.setPreferredSize(new java.awt.Dimension(90, 525));

        btnTask.setFont(new java.awt.Font("Source Sans Pro", 1, 40)); // NOI18N
        btnTask.setForeground(new java.awt.Color(255, 255, 255));
        btnTask.setText("<html>T<br> <br>A<br> <br>S<br> <br>K</html>");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/angulo.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/angulo.png"))); // NOI18N
        jLabel2.setText("jLabel1");

        javax.swing.GroupLayout leftTareaLayout = new javax.swing.GroupLayout(leftTarea);
        leftTarea.setLayout(leftTareaLayout);
        leftTareaLayout.setHorizontalGroup(
            leftTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftTareaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftTareaLayout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(btnTask, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        leftTareaLayout.setVerticalGroup(
            leftTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftTareaLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(26, 26, 26))
        );

        rightCalendar.setBackground(new java.awt.Color(194, 11, 11));
        rightCalendar.setPreferredSize(new java.awt.Dimension(90, 525));

        titleTask1.setFont(new java.awt.Font("Source Sans Pro", 1, 35)); // NOI18N
        titleTask1.setForeground(new java.awt.Color(255, 255, 255));
        titleTask1.setText("<html>C<br>A<br>L<br>E<br>N<br>D<br>A<br>R</html>");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/angulo.png"))); // NOI18N
        jLabel3.setText("jLabel1");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/angulo.png"))); // NOI18N
        jLabel4.setText("jLabel1");

        javax.swing.GroupLayout rightCalendarLayout = new javax.swing.GroupLayout(rightCalendar);
        rightCalendar.setLayout(rightCalendarLayout);
        rightCalendarLayout.setHorizontalGroup(
            rightCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightCalendarLayout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(rightCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightCalendarLayout.createSequentialGroup()
                        .addGroup(rightCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightCalendarLayout.createSequentialGroup()
                        .addComponent(titleTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        rightCalendarLayout.setVerticalGroup(
            rightCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightCalendarLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleTask1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(26, 26, 26))
        );

        helpBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/question.png"))); // NOI18N
        helpBtn.setBorderPainted(false);
        helpBtn.setContentAreaFilled(false);
        helpBtn.setFocusPainted(false);

        adminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/admin.png"))); // NOI18N
        adminBtn.setBorderPainted(false);
        adminBtn.setContentAreaFilled(false);
        adminBtn.setFocusPainted(false);

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(leftTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170)
                                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainLayout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(navSubBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addComponent(rightCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(menuNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(adminBtn)
                        .addGap(1038, 1038, 1038)
                        .addComponent(helpBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(menuNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(helpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rightCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(navSubBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leftTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    get de los componentes
    public JButton getBtnStart() {
        return btnStart;
    }

    public JPanel getLeftTarea() {
        return leftTarea;
    }
    
    

    public JButton getBtnReset() {
        return btnReset;
    }

    public JButton getBtnPomodoro() {
        return pomodoroBtn;
    }

    public JButton getBtnShortBreak() {
        return shortBtn1;
    }

    public JButton getBtnLongBreak() {
        return longBtn;
    }

    public JLabel getJLabelTiempo() {
        return jLabelTiempo;
    }

    public JProgressBar getJProgressBar() {
        return jProgressBar;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

//    notificando de abrir la ventana 
    public JButton getBtnHelp() {
        helpBtn.setActionCommand("SHOW_HELP");
        return helpBtn;
    }
// Botón para abrir TaskPanel

    public JLabel getBtnTasks() {
        return btnTask;
    }

    public JButton getBtnLogin() {
        return userBtn;
    }

    public JButton getBtnReport() {
        return reportBtn;
    }

    public JButton getBtnSettings() {
        return settingBtn;
    }

    public JPanel getBtnCalendar() {
        return rightCalendar; // Botón para abrir TaskPanel
    }

    public JButton getUserBtn() {
        return userBtn;
    }

    public JButton getReportBtn() {
        return reportBtn;
    }

    public JButton getSettingBtn() {
        return settingBtn;
    }

    public JButton getHelpBtn() {
        return helpBtn;
    }
    
//    get del boton de administrador
    public JButton getAdminBtn(){
        return adminBtn;
    }

    public void actualizarTiempo(String tiempo) {
        jLabelTiempo.setText(tiempo);
    }

    public void actualizarProgreso(int porcentaje) {
        jProgressBar.setValue(porcentaje);
    }

//    este metodo hay que verlo con detalle 
    public void cambiarTextoBotonStart(String texto) {
        btnStart.setText(texto);
    }

//    Cambiar el tema de la pantalla principal 
    public void cambiarTemaPomodoro() {
        Color fondoPomodoro = new Color(205, 92, 92);
        Color bordePomodoro = new Color(139, 0, 0);
        aplicarColores(fondoPomodoro, bordePomodoro);
    }

    public void cambiarTemaShortBreak() {
        Color fondoShort = new Color(52, 124, 129);
        Color bordeShort = new Color(32, 94, 99);
        aplicarColores(fondoShort, bordeShort);
    }

    public void cambiarTemaLongBreak() {
        Color fondoLong = new Color(57, 112, 151);
        Color bordeLong = new Color(37, 82, 121);
        aplicarColores(fondoLong, bordeLong);
    }

    //  método privado auxiliar
    private void aplicarColores(Color fondo, Color borde) {
        leftTarea.setBackground(fondo);
        menuNav.setBackground(fondo);
        rightCalendar.setBackground(fondo);
        nav.setBackground(fondo);

        centerPanel.setBackground(fondo);
        CustomComponents.hacerPanelRedondeado(centerPanel, 25, fondo, borde, 3);
        jProgressBar.setForeground(borde);

        // Forzar actualización visual
        centerPanel.revalidate();
        centerPanel.repaint();
        menuNav.repaint();
        nav.revalidate();
        nav.repaint();
        leftTarea.repaint();
        rightCalendar.repaint();
    }

    private void aplicarEstilos() {
        CustomComponents.hacerPanelRedondeado(
                centerPanel, // panel
                25, // radio
                new Color(205, 92, 92), // fondo
                new Color(139, 0, 0), // borde
                3 // grosor
        );
        CustomComponents.hacerPanelRedondeado(
                textPanelInt, // panel
                25, // radio
                new Color(194, 11, 11), // fondo
                new Color(255, 255, 255), // borde
                3 // grosor
        );
    }

    private void configurarComponentes() {
        // Configurar el label del tiempo
        jLabelTiempo.setFont(new Font("Source San Pro", Font.BOLD, 90));
        jLabelTiempo.setForeground(Color.WHITE);
        
        jLabelTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // Configurar la barra de progreso
        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(100);
        
        jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true); // Muestra el porcentaje
        jProgressBar.setForeground(new Color(139, 0, 0)); // Color de la barra (rojo oscuro)
        jProgressBar.setBackground(new Color(255, 200, 200)); // Fondo de la barra
        jProgressBar.setFont(new Font("Source San Pro", Font.BOLD, 25)); // Fuente del porcentaje
        jProgressBar.setBorderPainted(false); // Sin borde
    }

    public void updateTimeLabel(String tiempo) {
        jLabelTiempo.setText(tiempo);
    }

//Actualiza el valor de la barra de progreso
    public void updateProgressBar(int porcentaje) {
        jProgressBar.setValue(porcentaje);
    }

//Cambia el ícono del botón Start entre play y pause
    public void updateStartButtonIcon(boolean enEjecucion) {
        if (enEjecucion) {
            // Cuando está ejecutándose, mostrar ícono de PAUSA
            btnStart.setIcon(new javax.swing.ImageIcon(
                    getClass().getResource("/uptc/edu/co/resources/images/laterales/pause.png")
            ));
        } else {
            // Cuando está pausado/detenido, mostrar ícono de PLAY
            btnStart.setIcon(new javax.swing.ImageIcon(
                    getClass().getResource("/uptc/edu/co/resources/images/laterales/start.png")
            ));
        }
    }

//Actualiza los colores de los paneles principales
    public void updatePanelColors(Color color) {
        menuNav.setBackground(color);
        leftTarea.setBackground(color);
        rightCalendar.setBackground(color);
        nav.setBackground(color);
    }

//Actualiza el color de la barra de progreso
    public void updateProgressBarColor(Color color) {
        jProgressBar.setForeground(color);
    }

//Fuerza el repintado de todos los componentes
    public void repaintComponents() {
        centerPanel.revalidate();
        centerPanel.repaint();
        menuNav.repaint();
        menuNav.revalidate();
        nav.revalidate();
        nav.repaint();
        leftTarea.repaint();
        rightCalendar.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminBtn;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel btnTask;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton helpBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelTiempo;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JPanel leftTarea;
    private javax.swing.JLabel logo;
    private javax.swing.JButton longBtn;
    private javax.swing.JPanel main;
    private javax.swing.JPanel menuNav;
    private javax.swing.JPanel nav;
    private javax.swing.JPanel navSubBtn;
    private javax.swing.JButton pomodoroBtn;
    private javax.swing.JButton reportBtn;
    private javax.swing.JPanel rightCalendar;
    private javax.swing.JButton settingBtn;
    private javax.swing.JButton shortBtn1;
    private javax.swing.JPanel textPanelInt;
    private javax.swing.JLabel title;
    private javax.swing.JLabel titleTask1;
    private javax.swing.JButton userBtn;
    // End of variables declaration//GEN-END:variables

}
