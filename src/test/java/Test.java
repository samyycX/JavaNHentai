import github.samyycx.javanhentai.NHentaiAPI;
import github.samyycx.javanhentai.box.NResult;
import github.samyycx.javanhentai.condition.*;
import github.samyycx.javanhentai.condition.component.*;
import github.samyycx.javanhentai.response.GalleryData;
import github.samyycx.javanhentai.response.MultipleGalleryData;
import github.samyycx.javanhentai.response.NSortMethod;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class Test {

    private static Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1",10808));
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private static String cfid = "uCDiks_L7DhDuBoDMG6Hu5VlhnZoQLSqz5LEwN65nyw-1673234038-0-150";

    @org.junit.Test
    public void paramTest() {
        NHentaiAPI api = new NHentaiAPI(proxy, userAgent, cfid);
        ConditionBuilder c1 = new ConditionBuilder()
                .include(new TitleCond("无能狂怒"))
                .include(new ArtistCond("kataokasan"))
                .include(new ParodyCond("arknights"))
                .include(new CharacterCond("exusiai"))
                .exclude(new LanguageCond("english"))
                .include(new CategoryCond("doujinshi"))
                //.include(new PageLimiterCondition(2, PageLimiterCondition.NSymbol.LESS_THAN))
                .include(new UploadTimeRangeCond(NSymbol.GREATER_THAN, 3, NTimeUnit.YEAR));
                //.include(new UploadTimeCondition(4, UploadTimeCondition.NTimeUnit.MONTH))

        ConditionBuilder c2 = new ConditionBuilder()
                .include(new GroupCond("000"));
        NResult<MultipleGalleryData> result = api.searchHentai(c2, 1, NSortMethod.DEFAULT);
        System.out.println(result.getData());
    }

    @org.junit.Test
    public void testGetOne() {
        NHentaiAPI api = new NHentaiAPI(proxy, userAgent, cfid);
        NResult<GalleryData> result = api.getHentai(435966);
        GalleryData data = result.getData();
        System.out.println(data.getParodies());
        System.out.println(data.getCharacters());
        System.out.println(data.getRealStringTags());
        System.out.println(data.getArtists());
        System.out.println(data.getGroups());
        System.out.println(data.getLanguage());
        System.out.println(data.isTranslated());
        System.out.println(data.getCategories());
        System.out.println(data.getCover());
        System.out.println(data.getAllThumbnail());
        System.out.println(data.getAllImages());
    }

    @org.junit.Test
    public void testRandomHentai() {
        NHentaiAPI api = new NHentaiAPI(proxy, userAgent, cfid);
        System.out.println(api.getRandomHentai("chinese").getData());
    }

}
