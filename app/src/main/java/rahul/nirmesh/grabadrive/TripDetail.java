package rahul.nirmesh.grabadrive;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

import rahul.nirmesh.grabadrive.common.Common;

public class TripDetail extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private TextView txtDate, txtFare, txtBaseFare, txtTime, txtDistance, txtEstimatedFare, txtFrom, txtTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtDate = findViewById(R.id.txtDate);
        txtFare = findViewById(R.id.txtFare);
        txtBaseFare = findViewById(R.id.txtBaseFare);
        txtTime = findViewById(R.id.txtTime);
        txtDistance = findViewById(R.id.txtDistance);
        txtEstimatedFare = findViewById(R.id.txtEstimatedFare);
        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        settingInformation();
    }

    private void settingInformation() {
        if (getIntent() != null) {
            Calendar calendar = Calendar.getInstance();
            String date = String.format("%s, %d/%d", convertToDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH));
            txtDate.setText(date);

            txtFare.setText(String.format("$ %.2f", getIntent().getDoubleExtra("total", 0.0)));
            txtEstimatedFare.setText(String.format("$ %.2f", getIntent().getDoubleExtra("total", 0.0)));
            txtBaseFare.setText(String.format("$ %.2f", Common.base_fare));
            txtTime.setText(String.format("%s min", getIntent().getStringExtra("duration")));
            txtDistance.setText(String.format("%s km", getIntent().getStringExtra("distance")));
            txtFrom.setText(getIntent().getStringExtra("start_address"));
            txtTo.setText(getIntent().getStringExtra("end_address"));

            String[] location_end = getIntent().getStringExtra("location_end").split(",");
            LatLng dropOff = new LatLng(Double.parseDouble(location_end[0]), Double.parseDouble(location_end[1]));

            mMap.addMarker(new MarkerOptions()
                                    .position(dropOff)
                                    .title("Drop Off Here")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dropOff, 12.0f));
        }
    }

    private String convertToDayOfWeek(int day) {
        switch (day) {
            case Calendar.SUNDAY:
                return "SUNDAY";
            case Calendar.MONDAY:
                return "MONDAY";
            case Calendar.TUESDAY:
                return "TUESDAY";
            case Calendar.WEDNESDAY:
                return "WEDNESDAY";
            case Calendar.THURSDAY:
                return "THURSDAY";
            case Calendar.FRIDAY:
                return "FRIDAY";
            case Calendar.SATURDAY:
                return "SATURDAY";
            default:
                return "UNK";
        }
    }
}
