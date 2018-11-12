package cz.uhk.fim.RSSFeedReader.utils;

import cz.uhk.fim.RSSFeedReader.model.RSSList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

public class RSSparser extends RSSList
	{
		private RSSList rssList;
		private ItemHandler itemHandler;

		public RSSparser()
			{
				rssList = new RSSList();
				itemHandler = new ItemHandler(rssList);
			}
		public RSSList getParsed(String source) throws ParserConfigurationException, SAXException, IOException
			{
				parse(source);
				return rssList;
			}

		private void parse(String source) throws ParserConfigurationException, SAXException, IOException
			{
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				if(source.startsWith("http"))
					parser.parse(new InputSource(new URL(source).openStream()), itemHandler);
				else
					parser.parse(source, itemHandler);
			}
	}
