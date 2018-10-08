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
                getContentPane().setBackground(Color.DARK_GRAY);

            }
    }
