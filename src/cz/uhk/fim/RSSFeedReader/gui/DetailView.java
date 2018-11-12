package cz.uhk.fim.RSSFeedReader.gui;

import cz.uhk.fim.RSSFeedReader.model.RSSItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DetailView extends JPanel
	{
		private static final int ITEM_WIDTH = 200;
		private static final int COMPONENT_WIDTH = 250;
		private static final int HEIGHT = 1;

		private final String startHtml = "<html><p style='width: "+ COMPONENT_WIDTH + " px'>";
		private final String endHtml = "</p></html>";

		DetailView(RSSItem item){
			setLayout(new WrapLayout());
			setSize(ITEM_WIDTH,HEIGHT);
			setTitle(item.getTitle());
			Color bgColor = getColor(item.getTitle(), item.getLink(), item.getDescription());
			setDescription(item.getDescription());
			setBackground(bgColor);
			if(item.getAuthor() == null)
				item.setAuthor("Unknown");
			setInfo(String.format("%s - %s", item.getAuthor(), item.getPubDate()));


			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)){
						if(e.getClickCount()==2){
							new DetailFrame(item).setVisible(true);
						}
					}
				}
			});

		}

		private void setTitle(String title){
			JLabel lblTitle = new JLabel();
			lblTitle.setFont(new Font("Courier", Font.BOLD, 13));
			lblTitle.setSize(COMPONENT_WIDTH,HEIGHT);
			lblTitle.setText(String.format("%s%s%s",startHtml,title,endHtml));
			add(lblTitle);

		}

		private void setDescription(String description){
			JLabel lblDescription = new JLabel();
			lblDescription.setFont(new Font("Courier",Font.PLAIN,11));
			lblDescription.setSize(COMPONENT_WIDTH,HEIGHT);
			lblDescription.setText(String.format("%s%s%s",startHtml,description,endHtml));
			add(lblDescription);
		}
		private void setInfo(String info){
			JLabel lblInfo = new JLabel();
			lblInfo.setFont( new Font("Courier",Font.ITALIC,9));
			lblInfo.setSize(COMPONENT_WIDTH,HEIGHT);
			lblInfo.setForeground(Color.GRAY);
			lblInfo.setText(String.format("%s%s%s",startHtml,info,endHtml));
			add(lblInfo);
		}

		private Color getColor(String title, String author, String description){
			int r = Math.abs(title.length() * title.hashCode())%254;
			int g = Math.abs(author.length() * author.hashCode())%254;
			int b = Math.abs(description.length() * description.hashCode())%254;
			return new Color(r,g,b);
		}

		private Color getInverseColor(Color color)
			{
				return new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue());
			}

	}