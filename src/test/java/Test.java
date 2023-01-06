import github.samyycx.javanhentai.NHentaiAPI;
import github.samyycx.javanhentai.box.NResult;
import github.samyycx.javanhentai.condition.*;
import github.samyycx.javanhentai.condition.component.*;
import github.samyycx.javanhentai.response.MultipleGalleryData;
import github.samyycx.javanhentai.response.NSortMethod;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class Test {

    private static Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1",10808));
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private static String cfid = "JKFvIOY3X.Z76EycS_YVunsSirEHJidytaXPuf9dqnU-1672557768-0-150";

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

}
