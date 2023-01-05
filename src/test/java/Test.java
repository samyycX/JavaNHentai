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

    @org.junit.Test
    public void testGetHentaiById() throws IOException {
        NHentaiApi nHentaiApi = new NHentaiApi(proxy, userAgent, cfid);
        NHentaiApiService api = nHentaiApi.getRetrofitApi();
        Call<GalleryData> call = api.getHentaiById(0);
        Response<GalleryData> resp = call.execute();
        // 如果code是503 就代表cloudflare挡了请求
        System.out.println(resp.code());

        System.out.println(resp.body());
    }

    @org.junit.Test
    public void testSearchHentai() throws IOException {
        NHentaiApi nHentaiApi = new NHentaiApi(proxy, userAgent, cfid);
        NHentaiApiService api = nHentaiApi.getRetrofitApi();
        Call<MultipleGalleryData> call = api.searchHentai("初心者女装男子 cosplay", 1);
        Response<MultipleGalleryData> resp = call.execute();
        // 如果code是503 就代表cloudflare挡了请求
        assert resp.code() == 200;

        for (GalleryData data : resp.body().getResult()) {
            System.out.println(data);
        }
    }

    @org.junit.Test
    public void test1() {
        NHentaiApi nHentaiApi = new NHentaiApi(proxy, userAgent, cfid);
        GalleryData data = nHentaiApi.getRandomHentai().getData();
        System.out.println(nHentaiApi.searchHentai("chinese", 1).getData());
    }

}
