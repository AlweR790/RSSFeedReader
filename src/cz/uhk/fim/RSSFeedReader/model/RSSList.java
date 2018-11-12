package cz.uhk.fim.RSSFeedReader.model;


import java.util.ArrayList;
import java.util.List;

public class RSSList {

	private List<RSSItem> itemList = new ArrayList<>();

	public void addItem(RSSItem item) {
		itemList.add(item);
	}

	public List<RSSItem> getAllItems() {
		return itemList;
	}
}
