package youtubesearch.modeso.ch.youtubesearch.responsies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public class Error {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
