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
                return;
            case 404:
                onEmptyResult();
                return;
            case 200:
                T data = response.body();
                if (data instanceof MultipleGalleryData) {
                    if (((MultipleGalleryData) data).getResult().isEmpty()) {
                        onEmptyResult();
                        return;
                    }
                }
                onSuccess(data);
                return;
            default:
                onUnknownError(response.code(), response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
        onFailed(t);
    }

    public abstract void onSuccess(T data);

    public abstract void onCfidInvalid();

    public abstract void onEmptyResult();

    public abstract void onUnknownError(int code, String message);
    public abstract void onFailed(Throwable t);
}
