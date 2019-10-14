package ca.bcit.toonland;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<Book> {
    Context _context;
    public BooksAdapter(Context context, ArrayList<Book> toons) {
        super(context, 0, toons);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        final Book toon = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }
        
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(activity, toon.getDescription(), Toast.LENGTH_SHORT).show();
                //Intent something = new
                      //  something.putExtra("book", book)
                Intent intent = new Intent(_context, showBook.class);
                intent.putExtra("isbn_10", toon.getISB_10());
//starting the activity
                _context.startActivity(intent);
            }
        });
        
        // Lookup view for data population
        TextView tvFirstName = convertView.findViewById(R.id.title);
       // TextView tvLastName = convertView.findViewById(R.id.lastName);
        // Populate the data into the template view using the data object
        tvFirstName.setText(toon.getTitle());
      //  tvLastName.setText(toon.getLastName());

       // ImageView imgOnePhoto = convertView.findViewById(R.id.thumbImage);
        //DownloadImageTask dit = new DownloadImageTask(_context, imgOnePhoto);
        //dit.execute(toon.getPicture());
        if (toon.getPictureUrl() != null) {
          //  new ImageDownloaderTask(imgOnePhoto).execute(toon.getPictureUrl());
        }

        // Return the completed view to render on screen
        return convertView;
    }
}

