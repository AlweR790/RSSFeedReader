package cz.uhk.fim.RSSFeedReader.gui;

import cz.uhk.fim.RSSFeedReader.model.RSSItem;
import cz.uhk.fim.RSSFeedReader.model.RSSList;
import cz.uhk.fim.RSSFeedReader.model.RSSSource;
import cz.uhk.fim.RSSFeedReader.utils.FileUtils;
import cz.uhk.fim.RSSFeedReader.utils.RSSparser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame
	{
		private JPanel controlPanel;
		private JButton buttonLoad;
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
				buttonLoad = new JButton("Load");
				buttonSave = new JButton("Save");
				errorLabel = new JLabel();
				errorPanel = new JPanel();
				errorPanel.add(errorLabel, BorderLayout.CENTER);
				errorLabel.setForeground(Color.RED);
				controlPanel.add(buttonLoad, BorderLayout.WEST);
				controlPanel.add(buttonSave, BorderLayout.EAST);
				controlPanel.add(textFieldAddress, BorderLayout.CENTER);
				controlPanel.add(errorPanel, BorderLayout.SOUTH);

				add(controlPanel, BorderLayout.NORTH);

				contentPanel = new JPanel(new WrapLayout());

				loadRSS("rss.xml");

				add (new JScrollPane(contentPanel), BorderLayout.CENTER);

				buttonLoad.addActionListener(new AbstractAction()
					{
						@Override
						public void actionPerformed(ActionEvent actionEvent)
							{
								if (validateInput(textFieldAddress.getText()))
									loadRSS(textFieldAddress.getText());
								else
									errorLabel.setText("Bad Input");

                                List<RSSSource> sources = FileUtils.loadSources();
                                for(RSSSource s: sources)
                                    {
                                        System.out.println(s.getName() + " ; " + s.getSource());
                                    }

							}
					});

				buttonSave.addActionListener(new AbstractAction()
					{
						@Override
						public void actionPerformed(ActionEvent actionEvent)
							{
								List<RSSSource> sources = new ArrayList<>();
								sources.add(new RSSSource("živě.cz", "https://refverver.cz"));
								sources.add(new RSSSource("adcveswverbve", "vrevev es"));
								sources.add(new RSSSource("eswrvrewvewrv", "vcesrvesververvbr"));
								FileUtils.saveSources(sources);
							}
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
			}

	}
