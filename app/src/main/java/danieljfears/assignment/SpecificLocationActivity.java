package danieljfears.assignment;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SpecificLocationActivity extends ActionBarActivity {

    private TextView lblLocationTitle;
    private TextView lblCountryTitle;
    private ImageView imgFlag;

    private GridView gridView;
    private List<String> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_location_activity);

        TopLocationObject location = (TopLocationObject) getIntent().getSerializableExtra("EXTRA_TOP_LOCATION");

        lblLocationTitle = (TextView)findViewById(R.id.lblLocationTitle);
        lblCountryTitle = (TextView)findViewById(R.id.lblCountryTitle);
        imgFlag = (ImageView)findViewById(R.id.imgFlag);

        lblLocationTitle.setText(location.getCityName());
        lblCountryTitle.setText(location.getCountryName());
        imgFlag.setImageResource(location.getFlagResource());

        ImageView imgPhoto = (ImageView)findViewById(R.id.imgPhoto);

        Picasso.with(SpecificLocationActivity.this).load(location.getMapPictureURL()).into(imgPhoto);

        photos = new ArrayList<>();

        photos.add("https://i.imgur.com/ZczFK.png");
        photos.add("https://i.imgur.com/ZczFK.png");
        photos.add("https://i.imgur.com/ZczFK.png");
        photos.add("https://i.imgur.com/ZczFK.png");
        photos.add("https://i.imgur.com/ZczFK.png");
        photos.add("https://i.imgur.com/ZczFK.png");
        photos.add("https://i.imgur.com/ZczFK.png");
        photos.add("https://i.imgur.com/ZczFK.png");

        PhotoAdapter adapter = new PhotoAdapter(SpecificLocationActivity.this);
        gridView.setAdapter(adapter);
    }

    private class PhotoAdapter extends BaseAdapter {
        private Context mContext;

        public PhotoAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return photos.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.cell_photo, null);
            }

            ImageView imgPhoto = (ImageView)convertView.findViewById(R.id.imgPhoto);

            Picasso.with(SpecificLocationActivity.this).
                    load(photos.get(position)).into(imgPhoto);


            return convertView;
        }//end get view


    }//end top image adapter
}
