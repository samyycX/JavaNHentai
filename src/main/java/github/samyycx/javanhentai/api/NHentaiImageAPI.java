package github.samyycx.javanhentai.api;

import github.samyycx.javanhentai.response.GalleryData;
import github.samyycx.javanhentai.response.GalleryImageData;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NHentaiImageAPI {

    private static final String IMAGE_BASE_URL = "https://i.nhentai.net/galleries/{mediaId}/{page}.{ext}";
    private static final String THUMBNAIL_BASE_URL = "https://t.nhentai.net/galleries/{mediaId}/{page}t.{ext}";
    private static final String COVER_BASE_URL = "https://t.nhentai.net/galleries/{mediaId}/cover.{ext}";

    public List<URL> getAllImageURL(GalleryData data) {
        List<URL> urls = new ArrayList<>();
        for (int i = 0; i < data.getImages().getPages().size(); i++) {
            urls.add(url(IMAGE_BASE_URL
                    .replace("{mediaId}",data.getMedia_id())
                    .replace("{page}",String.valueOf(i+1))
                    .replace("{ext}",ext(data.getImages().getPages().get(i).getT()))
                    ));
        }
        return urls;
    }

    public List<URL> getAllThumbnailURL(GalleryData data) {
        List<URL> urls = new ArrayList<>();
        String ext = ext(data.getImages().getThumbnail().getT());
        for (int i = 0; i < data.getImages().getPages().size(); i++) {
            urls.add(url(THUMBNAIL_BASE_URL
                    .replace("{mediaId}",data.getMedia_id())
                    .replace("{page}",String.valueOf(i+1))
                    .replace("{ext}",ext)
                    ));
        }
        return urls;
    }

    public URL getCoverURL(GalleryData data) {
            return url(COVER_BASE_URL
                .replace("{mediaId}",data.getMedia_id())
                .replace("{ext}",ext(data.getImages().getCover().getT()))
                );
    }



    private String ext(String e) {
        switch (e) {
            case "j":
                return "jpg";
            case "p":
                return "png";
            default:
                return "gif"; // i guess?
        }
    }

    private URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            // not possible
            e.printStackTrace();
            return null;
        }
    }

}
