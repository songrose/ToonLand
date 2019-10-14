package ca.bcit.toonland;
//make sure apk is properly signed and in the root folder and test it out by dragging it onto an emulator.
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    public static Activity activity;

    // URL to get contacts JSON
    private static String SERVICE_URL = "https://www.googleapis.com/books/v1/volumes?q=harry+potter";
    public static  ArrayList<Book> bookList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;


        bookList = new ArrayList<Book>();
        lv = findViewById(R.id.toonList);
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node

                    JSONObject jsonString = new JSONObject(jsonStr);
                    JSONArray array = jsonString.getJSONArray("items");


                    //
                    // looping through All Contacts
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject books = array.getJSONObject(i);
                        //getting title
                        JSONObject volume = books.getJSONObject("volumeInfo");
                        String title = volume.getString("title");
                        //getting author array
                        JSONArray authorArray = volume.getJSONArray("authors");
                        String[] authors = new String[authorArray.length()];
                        for (int i0 = 0; i0 < authorArray.length(); i0++) {
                            authors[i0]= (String)authorArray.getString(i0);
                        }
                        //getting publisher, published date, description
                        String publisher = volume.getString("publisher");
                        String publishedDate = volume.getString("publishedDate");
                      //  String description = "description placeholder";
                        String description;
                        if (    volume.has("description")){
                             description = volume.getString("description");

                        } else {
                             description = "No description available.";

                        }
                       // String description = volume.getString("description");

                        //getting isbn
                        JSONArray isbnArray = volume.getJSONArray("industryIdentifiers");
                        JSONObject ISBNObj = isbnArray.getJSONObject(1);
                        String ISBN_10 = ISBNObj.getString("identifier");

                        //getting image links
                        JSONObject imagelinkss = volume.getJSONObject("imageLinks");
                        String smallThumbnail = imagelinkss.getString("smallThumbnail");



                        //!!!!!!!!!!!!!!!!!!!!!

//                        String id = c.getString("id");
//                        String firstName = c.getString("firstName");
//                        String lastName = c.getString("lastName");
//                        String occupation = c.getString("occupation");
//                        String gender = c.getString("gender");
//                        String pictureUrl = c.getString("pictureUrl");

                        // tmp hash map for single contact
                        Book book = new Book(ISBN_10, title, description,publishedDate, publisher, authors, smallThumbnail);

                        // adding each child node to HashMap key => value
                        book.setTitle(title);
                       // toon.setLastName(title);
                       // toon.setOccupation(title);
                      //  toon.setGender(title);
                      //  toon.setPictureUrl("https://flyjazz.ca/wp-content/uploads/2017/01/dummy-user.jpg");

                        // adding contact to contact list
                        bookList.add(book);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            //Toon[] toonArray = toonList.toArray(new Toon[toonList.size()]);

            BooksAdapter adapter = new BooksAdapter(MainActivity.this, bookList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }

}
