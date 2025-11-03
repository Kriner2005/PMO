
package uptc.edu.co.view.subVistas;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import uptc.edu.co.utilities.Utilities;


public class AddTaskDialog extends JDialog {

    private JTextField taskField;
    private boolean confirmed = false;

    public AddTaskDialog(JFrame parent) {
        super(parent, "Add Task", true);

        

        setLayout(new BorderLayout());
        getContentPane().setBackground(Utilities.mainRed);

        JLabel title = new JLabel("NEW TASK", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(15, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 14));
        taskField.setForeground(Color.BLACK);
        taskField.setBackground(Color.WHITE);

        taskField.setPreferredSize(new Dimension(200, 35));

        taskField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Utilities.borderRed, 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Utilities.mainRed);
        centerPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
        centerPanel.add(taskField, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JButton cancelBtn = new JButton("CANCEL");
        styleButton(cancelBtn, Utilities.mainRed, Utilities.darkRed);

        JButton addBtn = new JButton("ADD");
        styleButton(addBtn, Utilities.mainRed, Utilities.darkRed);

        addBtn.addActionListener(e -> {
            if (!taskField.getText().trim().isEmpty()) {
                confirmed = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please enter a task!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Utilities.mainRed);
        buttonPanel.setBorder(new EmptyBorder(0, 20, 15, 20));
        buttonPanel.setLayout(new GridLayout(1, 2, 12, 0));
        buttonPanel.add(cancelBtn);
        buttonPanel.add(addBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(320, 200);
        setLocationRelativeTo(parent);
    }

    public String getTask() {
        return confirmed ? taskField.getText().trim() : null;
    }

    private void styleButton(JButton btn, Color mainRed, Color darkRed) {
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(mainRed);
        btn.setFocusPainted(false);
        btn.setBorder(new DashedBorder(Color.WHITE, 2));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(darkRed);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(mainRed);
            }
        });
    }

    static class DashedBorder extends LineBorder {

        public DashedBorder(Color color, int thickness) {
            super(color, thickness, true);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(lineColor);
            float[] dash = {6f, 3f};
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10f, dash, 0f));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 12, 12);
            g2d.dispose();
        }
    }
    public static void main(String[] args) {
        AddTaskDialog nd = new AddTaskDialog(new JFrame());
        nd.setVisible(true);
        
    }
}
