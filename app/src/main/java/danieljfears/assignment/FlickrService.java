package danieljfears.assignment;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FlickrService {
    @GET("/services/rest/?api_key=157999a7197834edf6c1f25cb9b778cf&format=json&safe_search=1&content_" +
            "type=1&extras=url_o,url_m&method=flickr.photos.search&nojsoncallback=1")
    void getPhotosForLocation(@Query("woe_id") String woe_id, Callback<FlickrResponse> cb);


}
