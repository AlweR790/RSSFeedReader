package cz.uhk.fim.RSSFeedReader.app;

import cz.uhk.fim.RSSFeedReader.gui.MainFrame;

import javax.swing.*;

public class App
    {
        public static void main(String[] args)
            {
                SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
            }
    }
