package rahul.nirmesh.grabadrive.common;

import rahul.nirmesh.grabadrive.remote.IGoogleAPI;
import rahul.nirmesh.grabadrive.remote.RetrofitClient;

/**
 * Created by NIRMESH on 16-Mar-18.
 */

public class Common {
    public static final String baseURL = "https://maps.googleapis.com";

    public static IGoogleAPI getGoogleAPI() {
        return RetrofitClient.getClient(baseURL).create(IGoogleAPI.class);
    }
}
