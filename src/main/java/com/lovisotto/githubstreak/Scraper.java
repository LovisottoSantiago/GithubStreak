package com.lovisotto.githubstreak;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Scraper {
    
    private Document doc;

    public Scraper(String userName) throws IOException{
        try {
            doc = Jsoup.connect(userName).get();
        }
        catch (IOException e) {
            e.getMessage();
        }
    }

    public int getData(){
        Elements calendar = doc.select(".ContributionCalendar-grid");


        return 1;

    }


}
