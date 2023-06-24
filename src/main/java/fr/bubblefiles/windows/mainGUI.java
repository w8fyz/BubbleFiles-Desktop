package fr.bubblefiles.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class mainGUI extends JFrame {
    private JTabbedPane onglets;

    public mainGUI() {
        setSize(300, 600);
        setResizable(false);

        onglets = new JTabbedPane();
        add(onglets);

        JPanel onglet1 = new JPanel();
        onglet1.setLayout(new FlowLayout()); // Utilise FlowLayout pour organiser les cartes côte à côte
        onglets.addTab("Onglet 1", onglet1);

        Card carte1 = new Card("Moi", "Littéralement moi", new ImageIcon("/Users/thibeau/Downloads/unnamed.png"));
        onglet1.add(carte1); // Ajoute la première carte à l'onglet
        Card carte2 = new Card("Unlockify.png", "Le logo d'un", new ImageIcon("/Users/thibeau/Downloads/unlockify.png"));
        onglet1.add(carte2); // Ajoute la deuxième carte à l'onglet

        // Pour créer un deuxième onglet avec d'autres cartes, répétez les étapes ci-dessus en utilisant un nouvel objet JPanel pour l'onglet et en ajoutant des instances de MaCarte à ce nouvel onglet
    }
}

class Card extends JPanel implements ActionListener {
    private JLabel titre;
    private JLabel description;
    private JLabel icone;

    public Card(String title, String desc, ImageIcon icon) {
        setBackground(Color.GRAY);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        titre = new JLabel(title);
        description = new JLabel(desc);
        icone = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH))); // Crée un JLabel avec l'icône "icone.png"
        add(icone);
        add(titre);
        add(description);
        setPreferredSize(new Dimension(250, 60));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Card clicked !");
    }
}