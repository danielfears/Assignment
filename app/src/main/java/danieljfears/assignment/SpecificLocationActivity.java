package danieljfears.assignment;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class SpecificLocationActivity extends ActionBarActivity {

    private TextView lblLocationTitle;
    private TextView lblCountryTitle;
    private ImageView imgFlag;

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
    }
}
