package cz.uhk.fim.RSSFeedReader.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
    {

        public MainFrame()
            {
                init();
            }

        private void init()
            {
                setTitle("RSS Feed Reader");
                setSize(1200, 800);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                Color c = new Color(0x222D33); // Protože SystemColor nefunguje na linuxu
                getContentPane().setBackground(c);
            }
    }
