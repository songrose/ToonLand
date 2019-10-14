package ca.bcit.toonland;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.io.IOException;

public class showBook extends AppCompatActivity {
    private Book b;
    TextView titler;
  //  TextView tvOutput = (TextView) findViewById (R.id.title);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);
        Intent intent = getIntent();
        String isbn_10 = intent.getStringExtra("isbn_10");

        for (Book t: MainActivity.bookList){
            if(t.getISB_10().equals( isbn_10)){
                b = t;
            }
        }
      //  View view = getLayoutInflater().inflate(R.layout.activity_show_book, null);
        //convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);


        titler = (TextView)  findViewById(R.id.title222);
        System.out.println("Boop" );

        System.out.println("!@#!@#!@#!@#@!#!@" + titler.getText() + "!!!!"+  b.getPublisher());
      Toast.makeText(MainActivity.activity, b.getDescription(), Toast.LENGTH_SHORT).show();

        titler.setTextColor(Color.RED);
        titler.setText("fasdf");
        //tvOutput.setText("asdf");

  }
    @Override
    protected void onStart() {
        super.onStart();
        TextView title;
        View view = getLayoutInflater().inflate(R.layout.activity_show_book, null);

        title = (TextView) view.findViewById(R.id.title);

        setContentView(R.layout.activity_show_book);

    }
      public void update(){
        titler.setText(b.getTitle());

    }
}
