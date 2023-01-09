package github.samyycx.javanhentai.box;

import lombok.Getter;

@Getter
public class NResult<T> {

    private NErrorType error;
    private int code;
    private String message;

    private T data;

    public NResult(NErrorType error, int code, String message) {
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public NResult(NResult<?> result) {
        if (result.isError()) {
            this.error = result.getError();
            this.code = result.getCode();
            this.message = result.getMessage();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public NResult(T data) {
        this.data = data;
    }

    public boolean isError() {
        return error != null;
    }


}
