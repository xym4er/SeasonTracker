package com.ychornyi.seasontracker;

import android.os.AsyncTask;

import com.ychornyi.seasontracker.model.items.FilmItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 14.06.2016.
 */

public class UpdateTask extends AsyncTask<Void,Void,List<FilmItem>> {
    private CustomCallback callback;
    private List<FilmItem> films = new ArrayList<>();

    public UpdateTask(CustomCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<FilmItem> doInBackground(Void... params) {
//        try {
//            Document doc = Jsoup.connect("http://amovies.org/serials/").get();
//            Elements elements = doc.getElementsByClass("film-name");
//            for (Element element : elements) {
//                films.add(new FilmItem(element.text()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            Document doc = Jsoup.connect("http://amovies.org/serials/").get();
            Elements elements = doc.getElementsByClass("short-film");
            elements.remove(0);
            elements.remove(0);
            elements.remove(0);
            elements.remove(0);
            elements.remove(0);
            for (Element film : elements) {
                Elements link = film.getElementsByTag("a");
//                System.out.println(link.get(0).attr("href"));
//                System.out.println(link.get(0).getElementsByClass("film-date-t").get(0).text());
//                System.out.println(link.get(0).getElementsByClass("film-name").get(0).text());
//                System.out.println(link.get(0).getElementsByClass("voice").get(0).text());
//                System.out.println(link.get(0).getElementsByClass("season").get(0).getElementsByTag("span").get(1).text());
//                System.out.println(link.get(0).getElementsByClass("series").get(0).getElementsByTag("span").get(1).text());

                films.add(new FilmItem(link.get(0).getElementsByClass("film-name").get(0).text(),link.get(0).getElementsByClass("film-date-t").get(0).text()));
//                films.add(new FilmItem(link.get(0).getElementsByClass("film-date-t").get(0).text()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        // ничего не возвращаем потому что я так захотел)
        return films;
    }

    @Override
    protected void onPostExecute(List<FilmItem> result) {
        if (callback!=null){
            callback.doSomething(result);
        }
    }

    public interface CustomCallback
    {
        public void doSomething(List<FilmItem> someResult);
    }
}
