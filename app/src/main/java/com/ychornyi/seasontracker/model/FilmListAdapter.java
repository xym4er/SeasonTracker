package com.ychornyi.seasontracker.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ychornyi.seasontracker.R;
import com.ychornyi.seasontracker.model.items.FilmItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y.chornyi on 14.06.2016.
 */
public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.FilmViewHolder> {

    private List<FilmItem> films = new ArrayList<>();
    private Context context;

    public FilmListAdapter(Context context, List films) {
        this.context = context;
        this.films = films;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_item_main, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        holder.date.setText(films.get(position).getLastUpdate());
        holder.title.setText(films.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView date;

        public FilmViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_film);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            date = (TextView) itemView.findViewById(R.id.tvDate);
        }
    }
}
