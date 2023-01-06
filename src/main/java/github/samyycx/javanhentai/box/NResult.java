package github.samyycx.javanhentai.box;

import lombok.Getter;

public class NResult<T> {

    @Getter
    private NError error;

    @Getter
    private T data;

    public NResult(NError error) {
        this.error = error;
    }

    public NResult(T data) {
        this.data = data;
    }

    public boolean isError() {
        return error != null;
    }

    public NError getError() {
        return error;
    }

    public T getData() {
        return data;
    }

}
