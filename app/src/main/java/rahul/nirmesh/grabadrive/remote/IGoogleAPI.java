package rahul.nirmesh.grabadrive.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by NIRMESH on 16-Mar-18.
 */

public interface IGoogleAPI {
    @GET
    Call<String> getPath(@Url String url);
}
