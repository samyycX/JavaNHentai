package github.samyycx.javanhentai.async;

import github.samyycx.javanhentai.response.MultipleGalleryData;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NCallback<T> implements Callback<T> {

    @Override
    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        switch (response.code()) {
            case 503:
                onCfidInvalid();
                onCfidInvalidOriginal(call, response);
                return;
            case 404:
                onEmptyResult();
                onEmptyResultOriginal(call, response);
                return;
            case 200:
                T data = response.body();
                if (data instanceof MultipleGalleryData) {
                    if (((MultipleGalleryData) data).getResult().isEmpty()) {
                        onEmptyResult();
                        onEmptyResultOriginal(call, response);
                        return;
                    }
                }
                onSuccess(data);
                onSuccessOriginal(data, call, response);
                return;
            default:
                onUnknownError(response.code(), response.message());
                onUnknownErrorOriginal(response.code(), response.message(), call, response);
        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
        onFailed(t);
        onFailedOriginal(call, t);
    }

    public void onSuccess(T data) {};

    public void onCfidInvalid() {}

    public void onEmptyResult() {}

    public void onUnknownError(int code, String message) {}
    public void onFailed(Throwable t) {}

    public void onSuccessOriginal(T data, @NotNull Call<T> call, Response<T> response) {}

    public void onCfidInvalidOriginal(@NotNull Call<T> call, Response<T> response) {}

    public void onEmptyResultOriginal(@NotNull Call<T> call, Response<T> response) {}

    public void onUnknownErrorOriginal(int code, String message, @NotNull Call<T> call, Response<T> response) {}

    public void onFailedOriginal(@NotNull Call<T> call, @NotNull Throwable t) {}
}
