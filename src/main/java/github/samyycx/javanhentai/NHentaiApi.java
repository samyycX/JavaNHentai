package github.samyycx.javanhentai;

import github.samyycx.javanhentai.api.NHentaiApiService;
import github.samyycx.javanhentai.api.NHentaiImageAPI;
import github.samyycx.javanhentai.box.NError;
import github.samyycx.javanhentai.box.NResult;
import github.samyycx.javanhentai.response.GalleryData;
import github.samyycx.javanhentai.response.MultipleGalleryData;
import github.samyycx.javanhentai.response.NSortMethod;
import lombok.Setter;
import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NHentaiApi implements Interceptor {

    private static final String API_BASE_URL = "https://nhentai.net/api/";
    @Setter
    private Proxy proxy;
    // 这个id应该是会过期的
    private String cfClearanceId;
    // ua必须和cfid对应
    private String userAgent;
    private Retrofit retrofit;
    private OkHttpClient client;
    private NHentaiApiService api;
    private NHentaiImageAPI imageApi = new NHentaiImageAPI();

    public NHentaiApi(@Nullable Proxy proxy, String userAgent, String cfClearanceId) {
        this.proxy = proxy;
        this.userAgent = userAgent;
        this.cfClearanceId = cfClearanceId;
        getRetrofitApi();
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("User-Agent", userAgent)
                .header("cookie","cf_clearance="+cfClearanceId)
                .build();

        return chain.proceed(request);
    }

    public NHentaiApiService getRetrofitApi() {
        if (api == null) {

            this.client = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(this)
                    .proxy(proxy)
                    .build();

            this.retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            this.api = retrofit.create(NHentaiApiService.class);
        }

        return api;

    }

    public void updateCfClearanceId(String cfClearanceId) {
        this.cfClearanceId = cfClearanceId;
    }

    /**
     * 通过NHentai的本子ID获取单个本子 (不是mediaID)
     * @param id 本子ID
     */
    public NResult<GalleryData> getHentai(int id) {
        return request(api.getHentaiById(id));
    }

    /**
     * 网站的关键字搜索功能，可以是tag也可以是标题
     * @param keyword 关键字 例如 "chinese cosplay uniform"
     * @param page 页数
     */
    public NResult<MultipleGalleryData> searchHentai(String keyword, int page) {
        return request(api.searchHentai(keyword, page));
    }

    /**
     * 网站的关键字搜索功能，可以是tag也可以是标题
     * @param keywords 关键字
     * @param page 页数
     */
    public NResult<MultipleGalleryData> searchHentai(String[] keywords, int page) {
        return searchHentai(String.join("+",keywords), page);
    }

    /**
     * 网站的关键字搜索功能，可以是tag也可以是标题
     * @param keywords 关键字
     * @param page 页数
     */
    public NResult<MultipleGalleryData> searchHentai(List<String> keywords, int page) {
        return searchHentai(String.join("+",keywords), page);
    }

    /**
     * 网站的关键字搜索功能 (带排序)，可以是tag也可以是标题
     * @param keyword 关键字，空格用+号分隔
     * @param page 页数
     * @param method 排序方法, 有三种
     * @see NSortMethod
     */
    public NResult<MultipleGalleryData> searchHentai(String keyword, int page, NSortMethod method) {
        return request(api.searchHentaiAndSort(keyword, page, method.getType()));
    }

    /**
     * 网站的关键字搜索功能 (带排序)，可以是tag也可以是标题
     * @param keywords 关键字，空格用+号分隔
     * @param page 页数
     * @param method 排序方法, 有三种
     * @see NSortMethod
     */
    public NResult<MultipleGalleryData> searchHentai(String[] keywords, int page, NSortMethod method) {
        return searchHentai(String.join("+",keywords), page, method);
    }

    /**
     * 网站的关键字搜索功能 (带排序)，可以是tag也可以是标题
     * @param keywords 关键字，空格用+号分隔
     * @param page 页数
     * @param method 排序方法, 有三种
     * @see NSortMethod
     */
    public NResult<MultipleGalleryData> searchHentai(List<String> keywords, int page, NSortMethod method) {
        return searchHentai(String.join("+",keywords), page, method);
    }

    /**
     * 根据tag查找本子
     * @param tagId tagId, nhentai的这个tagid应该是tags, artists, language等属性混在一起的
     * @param page 页数
     */
    public NResult<MultipleGalleryData> searchHentaiByTag(int tagId, int page) {
        return request(api.searchHentaiByTag(tagId, page));
    }

    /**
     * 根据一个本子的id搜索与他相关的本子
     * @param id 本子id
     */
    public NResult<MultipleGalleryData> searchHentaiAlike(int id) {
        return request(api.searchHentaiAlike(id));
    }

    public List<URL> getAllImage(GalleryData data) {
        return imageApi.getAllImageURL(data);
    }

    public NResult<List<URL>> getAllImage(int id) {
        NResult<GalleryData> result = getHentai(id);
        if (result.error()) return new NResult<>(result.getError());
        GalleryData data = result.getData();
        return new NResult<>(getAllImage(data));
    }

    public List<URL> getAllThumbnail(GalleryData data) {
        return imageApi.getAllThumbnailURL(data);
    }

    public NResult<List<URL>> getAllThumbnail(int id) {
        NResult<GalleryData> result = getHentai(id);
        if (result.error()) return new NResult<>(result.getError());
        GalleryData data = result.getData();
        return new NResult<>(getAllThumbnail(data));
    }

    public URL getCover(GalleryData data) {
        return imageApi.getCoverURL(data);
    }

    public NResult<URL> getCover(int id) {
        NResult<GalleryData> result = getHentai(id);
        if (result.error()) return new NResult<>(result.getError());
        GalleryData data = result.getData();
        return new NResult<>(getCover(data));
    }

    @SneakyThrows(IOException.class)
    public NResult<GalleryData> getRandomHentai() {
        String randomUrl = "https://nhentai.net/random/";
        OkHttpClient newClient = client.newBuilder().followRedirects(false).followSslRedirects(true).build();
        Request request = new Request.Builder().url(randomUrl).build();
        okhttp3.Response response = newClient.newCall(request).execute();
        if (response.code() == 503) return new NResult<>(NError.CFID_EXPIRED);
        String target = response.header("Location");
        String id = target.replace("/g/","").replace("/","");
        return getHentai(Integer.parseInt(id));
    }


    @SneakyThrows(IOException.class)
    private static <T> NResult<T> request(Call<T> call) {
        Response<T> resp = call.execute();
        switch (resp.code()) {
            case 503:
                return new NResult<>(NError.CFID_EXPIRED);
            case 404:
                return new NResult<>(NError.NOT_FOUND);
            case 200:
                return new NResult<>(resp.body());
            default:
                return new NResult<>(NError.OTHER);
        }
    }


}
