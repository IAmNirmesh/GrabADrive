package rahul.nirmesh.grabadrive.common;

import rahul.nirmesh.grabadrive.remote.IGoogleAPI;
import rahul.nirmesh.grabadrive.remote.RetrofitClient;

/**
 * Created by NIRMESH on 16-Mar-18.
 */

public class Common {
    public static final String driver_tbl = "Drivers";
    public static final String user_driver_tbl = "DriversInformation";
    public static final String user_rider_tbl = "RidersInformation";
    public static final String pickup_request_tbl = "PickupRequest";

    public static final String baseURL = "https://maps.googleapis.com";

    public static IGoogleAPI getGoogleAPI() {
        return RetrofitClient.getClient(baseURL).create(IGoogleAPI.class);
    }
}
