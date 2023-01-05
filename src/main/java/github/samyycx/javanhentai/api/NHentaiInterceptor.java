package github.samyycx.javanhentai.api;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class NHentaiInterceptor implements Interceptor {

    private String cfClearanceId;
    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }


    public String getCfClearanceId() {
        return cfClearanceId;
    }

    public void setCfClearanceId(String cfClearanceId) {
        this.cfClearanceId = cfClearanceId;
    }


    public NHentaiInterceptor(String userAgent, String cfClearanceId) {
        this.userAgent = userAgent;
        this.cfClearanceId = cfClearanceId;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("User-Agent", userAgent)
                .header("cookie","cf_clearance="+cfClearanceId)
                .build();

        return chain.proceed(request);

    }
}
