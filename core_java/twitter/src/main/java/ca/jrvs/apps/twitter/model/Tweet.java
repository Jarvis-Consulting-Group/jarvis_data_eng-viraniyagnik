package ca.jrvs.apps.twitter.model;

import java.time.LocalDateTime;

    public class Tweet {
        private String text;
        private String author;
        private LocalDateTime timestamp;
        private int likes;
        private int retweets;

        public Tweet(String text, String author, LocalDateTime timestamp, int likes, int retweets) {
            this.text = text;
            this.author = author;
            this.timestamp = timestamp;
            this.likes = likes;
            this.retweets = retweets;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getRetweets() {
            return retweets;
        }

        public void setRetweets(int retweets) {
            this.retweets = retweets;
        }
}


