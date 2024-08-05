import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PanelRound extends JPanel {

    private BinaryTree tree;
    private List<Integer> currentTraversal;
    private int currentIndex;
    private List<Point> nodePositions;
    private Timer timer;
    private final int nodeRadius = 30;  
    private final Font nodeFont = new Font("Arial", Font.BOLD, 14); 

    public PanelRound(BinaryTree tree) {
        this.tree = tree;
        this.currentTraversal = new ArrayList<>();
        this.currentIndex = -1;
        this.nodePositions = new ArrayList<>();
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTraversal != null && !currentTraversal.isEmpty()) {
                    nextStep();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (tree.root != null) {
            nodePositions.clear(); 
            drawTree(g2, tree.root, getWidth() / 2, 50, getWidth() / 4); 
        }

        if (!currentTraversal.isEmpty() && currentIndex >= 0) {
            drawTraversal(g2);
        }

        g2.dispose();
    }

    private void drawTree(Graphics2D g2, Node node, int x, int y, int xOffset) {
        if (node != null) {
            g2.setColor(Color.BLACK);
            g2.fillOval(x - nodeRadius, y - nodeRadius, nodeRadius * 2, nodeRadius * 2);
            g2.setColor(Color.WHITE);
            g2.setFont(nodeFont);
            g2.drawString(String.valueOf(node.value), x - 10, y + 5); 

            nodePositions.add(new Point(x, y));

            if (node.left != null) {
                g2.setColor(Color.YELLOW); 
                g2.drawLine(x, y, x - xOffset, y + 50);
                drawTree(g2, node.left, x - xOffset, y + 50, xOffset / 2);
            }
            if (node.right != null) {
                g2.setColor(Color.RED); 
                g2.drawLine(x, y, x + xOffset, y + 50);
                drawTree(g2, node.right, x + xOffset, y + 50, xOffset / 2);
            }
        }
    }

    private void drawTraversal(Graphics2D g2) {
        if (currentIndex >= 0 && currentIndex < currentTraversal.size()) {
            int nodeValue = currentTraversal.get(currentIndex);
            Point position = findNodePosition(nodeValue);
            if (position != null) {
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillOval(position.x - nodeRadius, position.y - nodeRadius, nodeRadius * 2, nodeRadius * 2);
            }
        }
    }

    private Point findNodePosition(int value) {
        for (int i = 0; i < nodePositions.size(); i++) {
            if (tree.preOrder(tree.root).get(i) == value) {
                return nodePositions.get(i);
            }
        }
        return null;
    }

    public void updateTraversal(List<Integer> traversal) {
        this.currentTraversal = traversal;
        this.currentIndex = 0;
        timer.start(); 
        repaint();
    }

    public void nextStep() {
        if (currentIndex < currentTraversal.size() - 1) {
            currentIndex++;
            repaint();
        } else {
            timer.stop();
        }
    }

    public void resetTraversal() {
        this.currentIndex = -1;
        timer.stop(); 
        repaint();
    }
}
