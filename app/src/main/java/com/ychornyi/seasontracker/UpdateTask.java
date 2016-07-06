package com.ychornyi.seasontracker;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ychornyi.seasontracker.model.items.SeriesItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateTask extends AsyncTask<Void, Void, List<SeriesItem>> {
    private CustomCallback callback;
    private Context context;
    private List<SeriesItem> films = new ArrayList<>();

    public UpdateTask(CustomCallback callback) {
        this.callback = callback;
        this.context = (Context) callback;
    }

    @Override
    protected List<SeriesItem> doInBackground(Void... params) {
        File lastFilmDir = new File(context.getFilesDir() + "/last.json");
        Gson gson = new Gson();
        String json;
        SeriesItem lastFilm = new SeriesItem("test");
        FileOutputStream outputStream;
        json = gson.toJson(lastFilm);
        if (!lastFilmDir.exists()) {
            try {
                outputStream = new FileOutputStream(lastFilmDir);
                outputStream.write(json.getBytes());
                outputStream.close();
                Log.d("MyTag", "gson to string: " + gson.fromJson(new JsonReader(new FileReader(lastFilmDir)),SeriesItem.class).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                lastFilm = gson.fromJson(new JsonReader(new FileReader(lastFilmDir)), SeriesItem.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        int i = 1;
        while (i < 3) {
            Log.d("MyTag", "doInBackground: " + i);
            try {
                Document doc = Jsoup.connect("http://amovies.org/serials/page/" + i + "/").get();
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
                    seriesItem.setSeason(link.get(0).getElementsByClass("season").get(0).getElementsByTag("span").get(1).text());
                    seriesItem.setSeria(link.get(0).getElementsByClass("series").get(0).getElementsByTag("span").get(1).text());
                    if (seriesItem.equals(lastFilm)) {
                        Log.d("MyTag", "doInBackground: false!!!  " + seriesItem.toString());
                        i = 35;
                        break;
                    } else {
                        films.add(seriesItem);

                    }
                }
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!films.isEmpty()){
            try {
                outputStream = new FileOutputStream(lastFilmDir);
                outputStream.write(gson.toJson(films.get(0)).getBytes());
                outputStream.close();
                Log.d("MyTag", "gson to string: " + gson.fromJson(new JsonReader(new FileReader(lastFilmDir)),SeriesItem.class).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
