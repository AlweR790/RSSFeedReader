package cz.uhk.fim.RSSFeedReader.gui;

import cz.uhk.fim.RSSFeedReader.model.RSSItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DetailFrame extends JFrame
	{
		private RSSItem item;
		public DetailFrame(RSSItem item) {
			this.item = item;
			init();
		}

		private void init()
			{
				setUndecorated(true);
				setSize(400,400);
				setLocationRelativeTo(null);


				JPanel contentPanel = new JPanel(new WrapLayout());
				contentPanel.add(new DetailView(item));
				contentPanel.setBackground(getColor(item.getTitle(), item.getLink(), item.getDescription()));
				add(new JScrollPane(contentPanel), BorderLayout.CENTER);
				contentPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e)
						{
							dispose();
						}
				});



			}

		private Color getColor(String title, String author, String description){
			int r = Math.abs(title.length() * title.hashCode())%254;
			int g = Math.abs(author.length() * author.hashCode())%254;
			int b = Math.abs(description.length() * description.hashCode())%254;
			return new Color(r,g,b);
		}
	}
