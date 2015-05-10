package danieljfears.assignment;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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

        topLocations.add(new TopLocationObject("London", "England", R.drawable.england, "http://i.telegraph.co.uk/multimedia/archive/02423/london_2423609k.jpg", "44418"));
        topLocations.add(new TopLocationObject("Paris", "France", R.drawable.france, "http://www.telegraph.co.uk/travel/destination/article130148.ece/ALTERNATES/w620/parisguidetower.jpg", "615702"));
        topLocations.add(new TopLocationObject("Berlin", "Germany", R.drawable.germany, "http://www.telegraph.co.uk/travel/destination/article128328.ece/ALTERNATES/w620/berlin.jpg", "638242"));
        topLocations.add(new TopLocationObject("Madrid", "Spain", R.drawable.spain, "http://i.telegraph.co.uk/multimedia/archive/02509/madrid_2509809b.jpg", "766273"));
        topLocations.add(new TopLocationObject("Rome", "Italy", R.drawable.italy, "http://www.telegraph.co.uk/incoming/article33812.ece/ALTERNATES/w620/Fontana_di_Trevi.jpg", "721943"));
        topLocations.add(new TopLocationObject("Dublin", "Ireland", R.drawable.ireland, "http://cdni.condenast.co.uk/646x430/d_f/dublin_cnt_24nov09_iStock_b.jpg", "560743"));
        topLocations.add(new TopLocationObject("Brussels", "Belgium", R.drawable.belgium, "http://media-cdn.tripadvisor.com/media/photo-s/03/9b/2f/53/brussels.jpg", "968019"));
        topLocations.add(new TopLocationObject("Canberra", "Australia", R.drawable.australia, "http://international.cit.edu.au/__data/assets/image/0006/27636/Canberra-Aerial-view-of-lake.jpg", "1100968"));
        topLocations.add(new TopLocationObject("Athens", "Greece", R.drawable.greece, "http://upload.wikimedia.org/wikipedia/commons/9/91/View_of_the_Acropolis_Athens_(pixinn.net).jpg", "946738"));
        topLocations.add(new TopLocationObject("Cairo", "Egypt", R.drawable.egypt, "http://www.egyptunlimitedtours.com/Islamic%20Old%20Cairo.jpg", "1521894"));

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
