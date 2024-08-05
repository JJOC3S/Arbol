import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class TreeGUI extends JFrame {

    private BinaryTree tree;
    private PanelRound treePanel;
    private JTextField inputField;
    private JButton insertButton, deleteButton, preOrderButton, inOrderButton, postOrderButton, nextStepButton, resetTraversalButton;
    private JTextArea traversalArea;

    public TreeGUI() {
        tree = new BinaryTree();
        treePanel = new PanelRound(tree);
        inputField = new JTextField(10);
        insertButton = new JButton("Agregar");
        deleteButton = new JButton("Eliminar");
        preOrderButton = new JButton("Pre-order");
        inOrderButton = new JButton("In-order");
        postOrderButton = new JButton("Post-order");
        nextStepButton = new JButton("Siguiente paso");
        resetTraversalButton = new JButton("Reiniciar recorrido");
        traversalArea = new JTextArea(5, 20);
        traversalArea.setEditable(false);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 2, 5, 5));
        controlPanel.add(new JLabel("Ingresa el valor"));
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);
        controlPanel.add(preOrderButton);
        controlPanel.add(inOrderButton);
        controlPanel.add(postOrderButton);
        controlPanel.add(resetTraversalButton);
        
        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(traversalArea), BorderLayout.SOUTH);
        add(treePanel, BorderLayout.CENTER);

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    tree.insert(value);
                    inputField.setText("");
                    treePanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TreeGUI.this, "Ingresa un numero valido o mas corto.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    tree.delete(value);
                    inputField.setText("");
                    treePanel.repaint(); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TreeGUI.this, "Ingresa un numero valido o mas corto.");
                }
            }
        });

        preOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> traversal = tree.preOrder(tree.root);
                traversalArea.setText(String.join(" -> ", traversal.stream().map(String::valueOf).toArray(String[]::new)));
                treePanel.updateTraversal(traversal);
            }
        });

        inOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> traversal = tree.inOrder(tree.root);
                traversalArea.setText(String.join(" -> ", traversal.stream().map(String::valueOf).toArray(String[]::new)));
                treePanel.updateTraversal(traversal);
            }
        });

        postOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> traversal = tree.postOrder(tree.root);
                traversalArea.setText(String.join(" -> ", traversal.stream().map(String::valueOf).toArray(String[]::new)));
                treePanel.updateTraversal(traversal);
            }
        });

        nextStepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                treePanel.nextStep();
            }
        });

        resetTraversalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                treePanel.resetTraversal();
                traversalArea.setText("");
            }
        });

        setTitle("Jimmy Ontaneda ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TreeGUI();
            }
        });
    }
}
