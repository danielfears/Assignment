package danieljfears.assignment;

import java.util.List;

public class FlickrResponse {

    private FlickrPhotos photos;

    public FlickrPhotos getPhotos() {return photos;}


        public class FlickrPhotos {
        private List<FlickrPhoto> photo;

        public List<FlickrPhoto> getPhoto() {return photo;
        }

        public class FlickrPhoto {

            private String title;
            private String url_o;
            private String url_m;

            public String getTitle() {return title;}
            public String getUrl_o() {return url_o;}
            public String getUrl_m() {return url_m;}

            public String getImageURL() {return getImageURL();}


        }
    }

}
