package cz.uhk.fim.RSSFeedReader.gui;

import cz.uhk.fim.RSSFeedReader.model.RSSItem;
import cz.uhk.fim.RSSFeedReader.model.RSSList;
import cz.uhk.fim.RSSFeedReader.utils.RSSparser;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
	{
		private JPanel controlPanel;
		private JButton buttonAdd;
		private JButton buttonSave;
		private JTextField textFieldAddress;
		private JLabel errorLabel;
		private JPanel errorPanel;
		private JPanel contentPanel;

		public MainFrame()
			{

				init();
				initContentUI();
			}

		private void initContentUI()
			{
				controlPanel = new JPanel(new BorderLayout());
				textFieldAddress = new JTextField();
				buttonAdd = new JButton("Add");
				buttonSave = new JButton("Save");
				errorLabel = new JLabel();
				errorPanel = new JPanel();
				errorPanel.add(errorLabel, BorderLayout.CENTER);
				errorLabel.setForeground(Color.RED);
				controlPanel.add(buttonAdd, BorderLayout.WEST);
				controlPanel.add(buttonSave, BorderLayout.EAST);
				controlPanel.add(textFieldAddress, BorderLayout.CENTER);
				controlPanel.add(errorPanel, BorderLayout.SOUTH);

				add(controlPanel, BorderLayout.NORTH);

				contentPanel = new JPanel(new WrapLayout());

				loadRSS("rss.xml");

				add (new JScrollPane(contentPanel), BorderLayout.CENTER);

				buttonAdd.addActionListener(abstractAction ->
				{
					AddView add = new AddView();
					add.setVisible(true);
				});


			}

		private void loadRSS(String source)
			{
				contentPanel.removeAll();
				errorLabel.setText("");
				try
					{
						RSSList rssList = new RSSparser().getParsed(source);
						for (RSSItem item : rssList.getAllItems())
							{
								contentPanel.add(new CardView(item));
							}
					}
				catch (Exception e)
					{
						errorLabel.setText("Error while loading RSS");
					}
			}



		private void init()
			{
				setTitle("RSS Feed Reader");
				setSize(1200, 800);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setLocationRelativeTo(null);
			}

	}
