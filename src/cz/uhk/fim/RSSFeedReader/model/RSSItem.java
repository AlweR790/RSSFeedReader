package cz.uhk.fim.RSSFeedReader.model;

public class RSSItem
    {
        private String title,description, link, pubDate,  author;

        public RSSItem(String title, String description, String link, String pubDate, String author)
            {
                this.title = title;
                this.description = description;
                this.link = link;
                this.pubDate = pubDate;
                this.author = author;
            }

        public RSSItem()
            {

            }

        public String getTitle()
            {
                return title;
            }

        public void setTitle(String title)
            {
                this.title = title;
            }

        public String getDescription()
            {
                return description;
            }

        public void setDescription(String description)
            {
                this.description = description;
            }

        public String getLink()
            {
                return link;
            }

        public void setLink(String link)
            {
                this.link = link;
            }

        public String getPubDate()
            {
                return pubDate;
            }

        public void setPubDate(String pubDate)
            {
                this.pubDate = pubDate;
            }

        public String getAuthor()
            {
                return author;
            }

        public void setAuthor(String author)
            {
                this.author = author;
            }
    }
