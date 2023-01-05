import github.samyycx.javanhentai.NHentaiApi;
import github.samyycx.javanhentai.api.NHentaiApiService;
import github.samyycx.javanhentai.response.GalleryData;
import github.samyycx.javanhentai.response.MultipleGalleryData;
import github.samyycx.javanhentai.response.NSortMethod;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class Test {

    private static Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1",10808));
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private static String cfid = "JKFvIOY3X.Z76EycS_YVunsSirEHJidytaXPuf9dqnU-1672557768-0-150";

}
