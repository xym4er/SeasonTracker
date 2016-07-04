package com.ychornyi.seasontracker;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ychornyi.seasontracker.model.items.SeriesItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 14.06.2016.
 */

public class UpdateTask extends AsyncTask<Void, Void, List<SeriesItem>> {
    private CustomCallback callback;
    private Context context;
    private List<SeriesItem> films = new ArrayList<>();

    public UpdateTask(CustomCallback callback) {
        this.callback = callback;
        this.context = (Context)callback;
    }

    @Override
    protected List<SeriesItem> doInBackground(Void... params) {
        File lastFilm = context.getFilesDir();
        Log.d("MyTag", "doInBackground: "+lastFilm.toString());

        try {
            Document doc = Jsoup.connect("http://amovies.org/serials/page/1/").get();
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
                SeriesItem seriesItem = new SeriesItem();
                seriesItem.setUrl(link.get(0).attr("href"));
                seriesItem.setDate(link.get(0).getElementsByClass("film-date-t").get(0).text());
                seriesItem.setName(link.get(0).getElementsByClass("film-name").get(0).text());
                seriesItem.setTranslate(link.get(0).getElementsByClass("voice").get(0).text());
                seriesItem.setSeason(Integer.parseInt(link.get(0).getElementsByClass("season").get(0).getElementsByTag("span").get(1).text()));
                seriesItem.setSeria(Integer.parseInt(link.get(0).getElementsByClass("series").get(0).getElementsByTag("span").get(1).text()));
                films.add(seriesItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return films;
    }

    @Override
    protected void onPostExecute(List<SeriesItem> result) {
        if (callback != null) {
            callback.doSomething(result);
        }
    }

    public interface CustomCallback {
        void doSomething(List<SeriesItem> someResult);
    }
}
