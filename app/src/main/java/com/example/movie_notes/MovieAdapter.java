package com.example.movie_notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter {

    private Context context;
    ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View movie_row = inflater.inflate(R.layout.movies_row, parent, false);
        MyViewHolder item = new MyViewHolder(movie_row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).txtVw_title.setText(movies.get(position).getMovie_title());
        ((MyViewHolder)holder).txtVw_date.setText("Δημιουργήθηκε: " + movies.get(position).getDate());
        if (((MyViewHolder)holder).txtVw_title.getText().toString().isEmpty()) {
            ((MyViewHolder)holder).txtVw_title.setText("Χωρίς Τίτλο");
        }

        int item_position = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Show_Movie_note.class);
                intent.putExtra("movie_id", movies.get(item_position).getMovie_id());
                context.startActivity(intent);

            }
        });

        ((MyViewHolder)holder).btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Edit_Movie_note.class);
                intent.putExtra("movie_id", movies.get(item_position).getMovie_id());
                context.startActivity(intent);
            }
        });

        ((MyViewHolder)holder).btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Διαγραφή Σημείωσης;");
                builder.setCancelable(false);
                builder.setNegativeButton("ΆΚΥΡΟ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("ΔΙΑΓΡΑΦΗ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        movies.get(item_position).delete(context);
                        ((MainActivity)context).onStart();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtVw_title;
        TextView txtVw_date;
        Button btn_edit;
        Button btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVw_title = itemView.findViewById(R.id.txtVw_title);
            txtVw_date = itemView.findViewById(R.id.txtVw_date);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}


