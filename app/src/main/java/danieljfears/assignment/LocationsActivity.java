package danieljfears.assignment;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LocationsActivity extends ActionBarActivity {

    private ListView ListLocations;
    private List<TopLocationObject> topLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        ListLocations = (ListView)findViewById(R.id.ListLocations);
        topLocations = new ArrayList<TopLocationObject>();

        topLocations.add(new TopLocationObject("London", "England", R.drawable.england, "http://upload.wikimedia.org/wikipedia/commons/3/3a/London_from_a_hot_air_balloon.jpg"));
        topLocations.add(new TopLocationObject("Paris", "France", R.drawable.france, "http://www.impots-locaux.net/wp-content/uploads/2014/04/paris-aide-de-l-etat.jpg"));
        topLocations.add(new TopLocationObject("Berlin", "Germany", R.drawable.germany, "http://upload.wikimedia.org/wikipedia/commons/5/53/%C3%9Cber_den_D%C3%A4chern_von_Berlin.jpg"));
        topLocations.add(new TopLocationObject("Madrid", "Spain", R.drawable.spain, "http://upload.wikimedia.org/wikipedia/commons/f/f3/Palacio_de_Comunicaciones_-_07.jpg"));
        topLocations.add(new TopLocationObject("Rome", "Italy", R.drawable.italy, "http://static1.squarespace.com/static/542ad2d5e4b0621639b1d3b4/t/543570d2e4b0d35b2d7fa10d/1412788435472/rome-italy.jpg?format=1500w"));
        topLocations.add(new TopLocationObject("Dublin", "Ireland", R.drawable.ireland, "http://streetmedicine.org/wordpress/wp-content/uploads/2014/05/Dublin-OConnell-Street-bridge-at-night.jpg"));
        topLocations.add(new TopLocationObject("Brussels", "Belgium", R.drawable.belgium, "http://www.brussels.info/grand-place-brussels.jpg"));
        topLocations.add(new TopLocationObject("Copenhagen", "Denmark", R.drawable.denmark, "http://static1.squarespace.com/static/53714acce4b0bb13e3c90e93/t/547dfa34e4b0e1f3b2412f0b/1417542200686/international-moving-to-denmark.jpg?format=1500w"));
        topLocations.add(new TopLocationObject("Athens", "Greece", R.drawable.greece, "http://upload.wikimedia.org/wikipedia/commons/9/91/View_of_the_Acropolis_Athens_(pixinn.net).jpg"));
        topLocations.add(new TopLocationObject("Amsterdam", "Netherlands", R.drawable.netherlands, "https://www.augustana.edu/Images/Amsterdam_Netherlands_031.jpg"));

        //set up the adapter
        LocationAdapter adapter = new LocationAdapter(topLocations);

        //set adapter to list view
        ListLocations.setAdapter(adapter);

        ListLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("FLICKR_TAG", "clicked on = " + position);
                Intent i = new Intent(LocationsActivity.this, SpecificLocationActivity.class);
                i.putExtra("EXTRA_TOP_LOCATION", topLocations.get(position));
                startActivity(i);
            }
        });

    }

    private class LocationAdapter extends ArrayAdapter<TopLocationObject > {

        public LocationAdapter(List<TopLocationObject > items) {
            super(LocationsActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.row_location, null);
            }

            ImageView imgFlag = (ImageView)convertView.findViewById(R.id.imgFlag);
            TextView lblCity = (TextView)convertView.findViewById(R.id.lblCity);
            TextView lblCountry = (TextView)convertView.findViewById(R.id.lblCountry);

            //get location
            TopLocationObject location = topLocations.get(position);

            //set ui elements to the location
            imgFlag.setImageResource(location.getFlagResource());
            lblCountry.setText(location.getCountryName());
            lblCity.setText(location.getCityName());

            return convertView;
        }// end get view

    }// end adapter class
}
