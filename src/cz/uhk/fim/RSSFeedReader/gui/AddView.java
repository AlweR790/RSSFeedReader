package cz.uhk.fim.RSSFeedReader.gui;

import cz.uhk.fim.RSSFeedReader.model.RSSSource;
import cz.uhk.fim.RSSFeedReader.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddView extends JFrame
	{
		private JTextField sourceText;
		private JTextField nameText;
		private JLabel sourceLabel;
		private JLabel nameLabel;
		private JButton addButton;

		private JPanel sourcePanel;
		private JPanel namePanel;

		private List<RSSSource> sources;

		public AddView()
			{
				setLayout(new FlowLayout());
				setSize(400,300);
				setLocationRelativeTo(null);
				initMenu();
				setTitle("Add");

				addButton.addActionListener(actionListener ->
				{
					if(validateInput(nameText.getText()) && validateInput(sourceText.getText()));
					{

						System.out.println("aa");
						sources = FileUtils.loadSources();
						sources.add(new RSSSource(editInput(nameText.getText()), editInput(sourceText.getText())));
						FileUtils.saveSources(sources);

						nameText.setText("");
						sourceText.setText("");
					}
				});

			}

		private void initMenu()
			{
				sourceLabel = new JLabel("Source: ");
				nameLabel = new JLabel("Name:  ");

				nameText = new JTextField();
				nameText.setPreferredSize(new Dimension(300, 25));

				sourceText = new JTextField();
				sourceText.setPreferredSize(new Dimension(300, 25));

				addButton = new JButton("Add");
				sourcePanel = new JPanel();
				sourcePanel.setPreferredSize(new Dimension(400, 50));

				sourcePanel.add(sourceLabel);
				sourcePanel.add(sourceText);

				namePanel =  new JPanel();

				namePanel.add(nameLabel);
				namePanel.add(nameText);
				namePanel.setPreferredSize(new Dimension(400, 50));

				add(sourcePanel);
				add(namePanel);
				add(addButton);
			}
		private boolean validateInput(String text)
			{
				return !text.trim().isEmpty();
			}
		private String editInput(String text)
			{
				return text.replaceAll(";", "");
			}
	}
