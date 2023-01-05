package github.samyycx.javanhentai.api;

import github.samyycx.javanhentai.response.GalleryData;
import github.samyycx.javanhentai.response.MultipleGalleryData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface NHentaiApiService {

    /**
     * 通过NHentai的本子ID获取单个本子 (不是mediaID)
     * @param id 本子ID
     */
    @GET("gallery/{id}")
    Call<GalleryData> getHentaiById(@Path("id") int id);

    /**
     * 网站的关键字搜索功能，可以是tag也可以是标题
     * @param params 关键字 例如"chinese cosplay uniform"
     * @param page 页数
     */
    @GET("galleries/search")
    Call<MultipleGalleryData> searchHentai(@Query("query") String params, @Query("page") int page);

    /**
     * 网站的关键字搜索功能 (带排序)，可以是tag也可以是标题
     * @param params 关键字，空格用+号分隔
     * @param page 页数
     * @param sortMethod 排序方法, 有三种
     *<br><br>
     * popular-today => 今日最热门<br>
     * popular-week => 本周最热门<br>
     * popular => 统计最热门<br>
     */
    @GET("galleries/search")
    Call<MultipleGalleryData> searchHentaiAndSort(@Query("query") String params, @Query("page") int page, @Query("sort") String sortMethod);

    /**
     * 根据tag查找本子
     * @param tagId tagId, nhentai的这个tagid应该是tags, artists, language等属性混在一起的
     * @param page 页数
     */
    @GET("galleries/tagged")
    Call<MultipleGalleryData> searchHentaiByTag(@Query("tag_id") int tagId, @Query("page") int page);

    /**
     * 根据一个本子的id搜索与他相关的本子
     * @param id 本子id
     */
    @GET("gallery/{id}/related")
    Call<MultipleGalleryData> searchHentaiAlike(@Path("id") int id);


}
