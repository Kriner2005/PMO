package uptc.edu.co.view;

import com.sun.source.tree.Tree;
import uptc.edu.co.utilities.CustomComponents;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uptc.edu.co.utilities.CargarFuente;

public class View extends javax.swing.JFrame {

//    prueba 
    private javax.swing.Timer timer;  // Timer
    private int segundosRestantes = 1500;  // 25 minutos
    private int tiempoTotal = 1500;

    public View() {
        initComponents();
        aplicarEstilos();
        configurarComponentes();
    }

    @SuppressWarnings("unchecked")
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
        titleTask = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rightCalendar = new javax.swing.JPanel();
        titleTask1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        helpBtn = new javax.swing.JButton();

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
            .addGroup(menuNavLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nav, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        navSubBtn.setBackground(new java.awt.Color(255, 255, 255));
        navSubBtn.setPreferredSize(new java.awt.Dimension(1100, 125));

        pomodoroBtn.setBackground(new java.awt.Color(60, 63, 65));
        pomodoroBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/pomodoro_Btn.png"))); // NOI18N
        pomodoroBtn.setBorder(null);
        pomodoroBtn.setBorderPainted(false);
        pomodoroBtn.setContentAreaFilled(false);
        pomodoroBtn.setFocusPainted(false);
        pomodoroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pomodoroBtnActionPerformed(evt);
            }
        });

        longBtn.setBackground(new java.awt.Color(60, 63, 65));
        longBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/long_Btn.png"))); // NOI18N
        longBtn.setBorder(null);
        longBtn.setBorderPainted(false);
        longBtn.setContentAreaFilled(false);
        longBtn.setFocusPainted(false);
        longBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                longBtnActionPerformed(evt);
            }
        });

        shortBtn1.setBackground(new java.awt.Color(60, 63, 65));
        shortBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/sub_btn/short_Btn.png"))); // NOI18N
        shortBtn1.setBorder(null);
        shortBtn1.setBorderPainted(false);
        shortBtn1.setContentAreaFilled(false);
        shortBtn1.setFocusPainted(false);
        shortBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortBtn1ActionPerformed(evt);
            }
        });

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
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/laterales/start.png"))); // NOI18N
        btnStart.setToolTipText("");
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setFocusPainted(false);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

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

        titleTask.setFont(new java.awt.Font("Source Sans Pro", 1, 40)); // NOI18N
        titleTask.setForeground(new java.awt.Color(255, 255, 255));
        titleTask.setText("<html>T<br> <br>A<br> <br>S<br> <br>K</html>");

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
                .addComponent(titleTask, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        leftTareaLayout.setVerticalGroup(
            leftTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftTareaLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(titleTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        helpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(leftTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(navSubBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainLayout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addComponent(rightCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(menuNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(1149, 1149, 1149)
                        .addComponent(helpBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(menuNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                        .addComponent(leftTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rightCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(navSubBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pomodoroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pomodoroBtnActionPerformed

        if (timer != null) {
            timer.stop();
        }

        tiempoTotal = 1500;
        segundosRestantes = 1500;
        jLabelTiempo.setText("25:00");
        jProgressBar.setValue(0);

        Color fondoPomodoro = new Color(205, 92, 92);
        Color bordePomodoro = new Color(139, 0, 0);

        leftTarea.setBackground(new Color(194, 11, 11));
        menuNav.setBackground(new Color(194, 11, 11));
        rightCalendar.setBackground(new Color(194, 11, 11));
        nav.setBackground(new Color(194, 11, 11));

        centerPanel.setBackground(fondoPomodoro);
        CustomComponents.hacerPanelRedondeado(
                centerPanel,
                25,
                fondoPomodoro,
                bordePomodoro,
                3
        );

        jProgressBar.setForeground(bordePomodoro);

        centerPanel.revalidate();
        centerPanel.repaint();
        menuNav.repaint();
        menuNav.revalidate();
        leftTarea.repaint();
        rightCalendar.repaint();
        nav.revalidate();
        nav.repaint();

    }//GEN-LAST:event_pomodoroBtnActionPerformed

    private void longBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_longBtnActionPerformed

        if (timer != null) {
            timer.stop();
        }

        tiempoTotal = 900;
        segundosRestantes = 900;
        jLabelTiempo.setText("15:00");
        jProgressBar.setValue(0);

        Color fondoLong = new Color(57, 112, 151);
        Color bordeLong = new Color(37, 82, 121);

        leftTarea.setBackground(fondoLong);
        menuNav.setBackground(fondoLong);
        rightCalendar.setBackground(fondoLong);
        nav.setBackground(fondoLong);

        centerPanel.setBackground(fondoLong);
        CustomComponents.hacerPanelRedondeado(
                centerPanel,
                25,
                fondoLong,
                bordeLong,
                3
        );

        jProgressBar.setForeground(bordeLong);

        centerPanel.revalidate();
        centerPanel.repaint();
        menuNav.repaint();
        nav.revalidate();
        nav.repaint();
        leftTarea.repaint();
        rightCalendar.repaint();
    }//GEN-LAST:event_longBtnActionPerformed

    private void shortBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortBtn1ActionPerformed
        // Detener timer si está corriendo
        if (timer != null) {
            timer.stop();
        }

        // Configurar tiempo para Short Break (5 minutos)
        tiempoTotal = 300;
        segundosRestantes = 300;
        jLabelTiempo.setText("05:00");
        jProgressBar.setValue(0);

        // Cambiar colores
        Color fondoShort = new Color(52, 124, 129); // #347c81
        Color bordeShort = new Color(32, 94, 99);   // Un poco más oscuro

        // Cambiar todos los paneles al color verde
        nav.setBackground(fondoShort);
        leftTarea.setBackground(fondoShort);
        menuNav.setBackground(fondoShort);
        rightCalendar.setBackground(fondoShort); // Corregí el nombre

        // Aplicar borde redondeado solo al centerPanel (UNA SOLA VEZ)
        centerPanel.setBackground(fondoShort);
        CustomComponents.hacerPanelRedondeado(
                centerPanel,
                25,
                fondoShort,
                bordeShort,
                3
        );

        // Forzar actualización visual
        centerPanel.revalidate();
        centerPanel.repaint();
        nav.revalidate();
        nav.repaint();
        menuNav.repaint();
        leftTarea.repaint();
        rightCalendar.repaint();

        // Actualizar color de la barra de progreso
        jProgressBar.setForeground(bordeShort);
    }//GEN-LAST:event_shortBtn1ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        if (timer != null) {
            timer.stop();
        }
        segundosRestantes = tiempoTotal;
        jLabelTiempo.setText("25:00");
        jProgressBar.setValue(0);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (timer != null && timer.isRunning()) {
            // Si está corriendo, pausar
            timer.stop();

        } else {
            // Iniciar o reanudar
            iniciarTimer();

        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void helpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpBtnActionPerformed

    }//GEN-LAST:event_helpBtnActionPerformed

//    notificando de abrir la ventana 
    public JButton getBtnHelp() {
        return helpBtn;
    }

    

    public JPanel getBtnTasks() {
        return leftTarea; // Botón para abrir TaskPanel
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

    private void iniciarTimer() {
        if (timer == null) {
            timer = new javax.swing.Timer(1000, e -> {
                segundosRestantes--;

                // Actualizar el label del tiempo
                int minutos = segundosRestantes / 60;
                int segundos = segundosRestantes % 60;
                jLabelTiempo.setText(String.format("%02d:%02d", minutos, segundos));

                // Actualizar la barra de progreso
                int progreso = (int) (((tiempoTotal - segundosRestantes) / (double) tiempoTotal) * 100);
                jProgressBar.setValue(progreso);

                // Cuando llegue a 0
                if (segundosRestantes <= 0) {
                    timer.stop();
                    // Aquí puedes poner un sonido o notificación
                    javax.swing.JOptionPane.showMessageDialog(this, "¡Pomodoro completado!");
                    segundosRestantes = tiempoTotal;
                    jLabelTiempo.setText("25:00");

                    jProgressBar.setValue(0);
                }
            });
        }
        timer.start();
    }

    private void configurarComponentes() {
        // Configurar el label del tiempo
        jLabelTiempo.setFont(new Font("Source San Pro", Font.BOLD, 90));
        jLabelTiempo.setForeground(Color.WHITE);
        jLabelTiempo.setText("25:00");
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStart;
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
    private javax.swing.JLabel titleTask;
    private javax.swing.JLabel titleTask1;
    private javax.swing.JButton userBtn;
    // End of variables declaration//GEN-END:variables
public static void main(String[] args) {
        View io = new View();
        io.setVisible(true);
    }
    
}
