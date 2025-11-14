package uptc.edu.co.view.subVistas;

import uptc.edu.co.utilities.CustomComponents;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import uptc.edu.co.utilities.Utilities;

public class TaskPanel extends JPanel {

    private JPanel taskListPanel;
    private JFrame frame;
    private ActionListener listener;
    private JPanel contentPanel;
    private JLabel title;
    private JScrollPane scrollPane;
    private JScrollBar vBar;
    private JButton addTaskBtn;
    private JPanel bottomPanel;
    private JButton checkBtn;

    public TaskPanel(ActionListener listener) {

        this.listener = listener;
        frame = new JFrame("Tasks");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(515, 550);
        frame.setLocationRelativeTo(null);
        init();
        frame.setContentPane(this);

    }

    public JFrame getParentFrame() {
        return frame;
    }

    public void init() {

        setLayout(new BorderLayout());
        setBackground(Color.decode("#b21818"));

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        title = new JLabel("TASKS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        contentPanel.add(title, BorderLayout.NORTH);

        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        taskListPanel.setBackground(Utilities.TASKPANEL_FONDO_PANELTASK);
        taskListPanel.setBorder(new EmptyBorder(10, 16, 10, 16));

        scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Utilities.TASKPANEL_SCROLLPANE);
        scrollPane.getVerticalScrollBar().setBackground(Utilities.TASKPANEL_SCROLLPANE_VERTICAL);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        vBar = scrollPane.getVerticalScrollBar();
        vBar.setUnitIncrement(16);
        vBar.setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        vBar.setUI(new CustomScrollBarUI());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        addTaskBtn = new JButton("ADD TASK");
        addTaskBtn.setFont(Utilities.TASKPANEL_ADD_BUTTON_FONT);
        addTaskBtn.setForeground(Color.WHITE);
        addTaskBtn.setBackground(Color.decode("#b21818"));
        addTaskBtn.setFocusPainted(false);
        addTaskBtn.setBorder(new DashedBorder(Color.WHITE, 2));
        addTaskBtn.setPreferredSize(new Dimension(200, 40));
        addTaskBtn.setActionCommand("ADD_TASK");
        addTaskBtn.addActionListener(listener);

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.decode("#b21818"));
        bottomPanel.setBorder(new EmptyBorder(0, 20, 65, 20));
        bottomPanel.add(addTaskBtn);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        contentPanel.setBounds(0, 0, 500, 550);
        layeredPane.add(contentPanel, JLayeredPane.DEFAULT_LAYER);

        ImageIcon nut = new ImageIcon("src/resources/herramienta.png");

        JLabel nutTL = new JLabel(nut);
        JLabel nutTR = new JLabel(nut);
        JLabel nutBL = new JLabel(nut);
        JLabel nutBR = new JLabel(nut);

        nutTL.setBounds(10, 10, nut.getIconWidth(), nut.getIconHeight());
        nutTR.setBounds(500 - nut.getIconWidth() - 15, 10, nut.getIconWidth(), nut.getIconHeight());
        nutBL.setBounds(10, 470, nut.getIconWidth(), nut.getIconHeight());
        nutBR.setBounds(500 - nut.getIconWidth() - 15, 470, nut.getIconWidth(), nut.getIconHeight());

        layeredPane.add(nutTL, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(nutTR, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(nutBL, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(nutBR, JLayeredPane.PALETTE_LAYER);
    }

    public static Image tintImage(Image src, Color color) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        BufferedImage tinted = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = tinted.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setColor(color);
        g2.fillRect(0, 0, w, h);
        g2.dispose();

        return tinted;
    }

    public void addTask(String text, boolean done) {
        JPanel taskPanel = CustomComponents.createRoundedPanel(20, Color.WHITE, 0, null);
        taskPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        taskPanel.setLayout(new BorderLayout());
        taskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JPanel sideBar = CustomComponents.createLeftRoundedSideBar(10);

        Color sideColor = done ? Color.GRAY : new Color(201, 94, 99, 179);
        sideBar.setBackground(sideColor);

        taskPanel.add(sideBar, BorderLayout.WEST);

        JLabel taskLabel = new JLabel("<html>" + text + "</html>");
        taskLabel.setFont(new Font("Arial", Font.BOLD, 14));
        taskLabel.setForeground(done ? Color.GRAY : Color.decode("#b21818"));
        taskLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        if (done) {
            taskLabel.setText("<html><strike>" + text + "</strike></html>");
        }

        checkBtn = new JButton();
        checkBtn.setPreferredSize(new Dimension(40, 40));
        checkBtn.setBorderPainted(false);
        checkBtn.setFocusPainted(false);
        checkBtn.setContentAreaFilled(false);
        checkBtn.setOpaque(false);
        checkBtn.setActionCommand("MARK_DONE");
        checkBtn.addActionListener(listener);

        ImageIcon baseIcon = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/check.png"));
        Color iconColor = done ? Color.GRAY : new Color(201, 94, 99, 179);
        Image recoloredIcon = tintImage(baseIcon.getImage(), iconColor);
        checkBtn.setIcon(new ImageIcon(recoloredIcon));

        taskPanel.add(taskLabel, BorderLayout.CENTER);
        taskPanel.add(checkBtn, BorderLayout.EAST);

        taskListPanel.add(taskPanel);
        taskListPanel.add(Box.createVerticalStrut(10));
        taskListPanel.revalidate();
        taskListPanel.repaint();
    }

    public void markTaskDone(JButton btn) {
        JPanel taskPanel = (JPanel) btn.getParent();
        JLabel label = (JLabel) ((BorderLayout) taskPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);

        if (label != null) {
            label.setText("<html><strike>" + label.getText().replace("<html>", "").replace("</html>", "") + "</strike></html>");
            label.setForeground(Color.GRAY);

            ImageIcon baseIcon = new ImageIcon(getClass().getResource("/uptc/edu/co/resources/images/paulas/check.png"));

            Image grayIcon = tintImage(baseIcon.getImage(), Color.GRAY);
            btn.setIcon(new ImageIcon(grayIcon));

            btn.setEnabled(false);
            taskPanel = (JPanel) btn.getParent();
            JPanel sideBar = (JPanel) ((BorderLayout) taskPanel.getLayout()).getLayoutComponent(BorderLayout.WEST);
            if (sideBar != null) {
                sideBar.setBackground(Color.GRAY);
            }

        }

    }

    static class DashedBorder extends LineBorder {

        public DashedBorder(Color color, int thickness) {
            super(color, thickness, true);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(lineColor);
            float[] dash = {6f, 3f};
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10f, dash, 0f));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 12, 12);
            g2d.dispose();
        }
    }

    static class CustomScrollBarUI extends BasicScrollBarUI {

        private final Color thumbColor = new Color(255, 255, 255, 160);
        private final Color trackColor = new Color(178, 24, 24);

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(trackColor);
            g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, 10, 10);
            g2.dispose();
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            if (!scrollbar.isEnabled() || thumbBounds.width > thumbBounds.height) {
                return;
            }
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(thumbColor);
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            g2.dispose();
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createInvisibleButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createInvisibleButton();
        }

        private JButton createInvisibleButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setVisible(false);
            return button;
        }
    }

    public void clearTasks() {
        taskListPanel.removeAll();   // Quita todos los paneles de tareas
        taskListPanel.revalidate();  // Actualiza el layout
        taskListPanel.repaint();     // Redibuja en pantalla
    }

}
