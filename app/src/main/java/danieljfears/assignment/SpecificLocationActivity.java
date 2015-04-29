package danieljfears.assignment;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SpecificLocationActivity extends ActionBarActivity {

    private TextView lblLocationTitle;
    private TextView lblCountryTitle;
    private ImageView imgFlag;
    private TopLocationObject location;

    private GridView gridView;
    private List<FlickrResponse.FlickrPhotos.FlickrPhoto> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_location_activity);

        location = (TopLocationObject) getIntent().getSerializableExtra("EXTRA_TOP_LOCATION");

        lblLocationTitle = (TextView)findViewById(R.id.lblLocationTitle);
        lblCountryTitle = (TextView)findViewById(R.id.lblCountryTitle);
        imgFlag = (ImageView)findViewById(R.id.imgFlag);
        gridView = (GridView)findViewById(R.id.gridview);

        lblLocationTitle.setText(location.getCityName());
        lblCountryTitle.setText(location.getCountryName());
        imgFlag.setImageResource(location.getFlagResource());

        getDataFromFlickr();

    }

    private void getDataFromFlickr() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.flickr.com")
                .build();

        FlickrService service = restAdapter.create(FlickrService.class);

        service.getPhotosForLocation(location.getWoeid() , new Callback<FlickrResponse>() {
            @Override
            public void success(FlickrResponse flickrResponse, Response response) {
                Log.d("DAN_FLICKR", "success = " + flickrResponse.getPhotos().getPhoto().size());

                if (flickrResponse.getPhotos().getPhoto() != null &&
                        flickrResponse.getPhotos().getPhoto().size() > 0){

                    photos = flickrResponse.getPhotos().getPhoto();

                    PhotoAdapter adapter = new PhotoAdapter(SpecificLocationActivity.this);
                    gridView.setAdapter(adapter);

                }


            }//end on success

            @Override
            public void failure(RetrofitError retrofitError) {

                Log.d("DAN_FLICKR", retrofitError.getLocalizedMessage());

            }//end on failure

        }//end callbacks

        );//end get photos request

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

            if (photos.get(position) != null){


                if (photos.get(position).getUrl_m() != null){

                    Picasso.with(SpecificLocationActivity.this).
                            load(photos.get(position).getUrl_m()).into(imgPhoto);
                }




            }





            return convertView;
        }//end get view


    }//end top image adapter
}
