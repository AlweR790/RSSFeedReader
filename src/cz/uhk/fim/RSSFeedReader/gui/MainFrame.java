package cz.uhk.fim.RSSFeedReader.gui;

import cz.uhk.fim.RSSFeedReader.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame
    {
        private JPanel controlPanel;
        private JButton buttonLoad;
        private JButton buttonSave;
        private JTextArea textAreaFeed;
        private JTextField textFieldAdress;
        private JLabel errorLabel;
        private JPanel errorPanel;

        public MainFrame()
            {

                init();
                initContentUI();
            }

        private void initContentUI()
            {
                controlPanel = new JPanel(new BorderLayout());
                textFieldAdress = new JTextField();
                buttonLoad = new JButton("Load");
                buttonSave = new JButton("Save");
                textAreaFeed = new JTextArea();
                errorLabel = new JLabel();
                errorPanel = new JPanel();
                errorPanel.add(errorLabel, BorderLayout.CENTER);
                errorLabel.setForeground(Color.RED);
                controlPanel.add(buttonLoad, BorderLayout.WEST);
                controlPanel.add(buttonSave, BorderLayout.EAST);
                controlPanel.add(textFieldAdress, BorderLayout.CENTER);
                controlPanel.add(errorPanel, BorderLayout.SOUTH);

                add(controlPanel, BorderLayout.NORTH);


                add(new JScrollPane(textAreaFeed), BorderLayout.CENTER);
                try
                    {
                        textAreaFeed.setText(FileUtils.loadStringFromFile("rssFeed.xml"));
                    }
                catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                buttonLoad.addActionListener(new AbstractAction()
                    {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent)
                            {
                                if(validateInput(textFieldAdress.getText()))
                                    FileUtils.loadStringFromFile(textFieldAdress.getText());
                                else
                                    errorLabel.setText("Bad Input");

                            }
                    });
            }

        private boolean validateInput(String text)
            {
                return !text.trim().isEmpty();
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
        /* TODO
            - Listenery na Load&Save
            - metoda Validate
            - label Error - červený u adress baru
         */
    }
