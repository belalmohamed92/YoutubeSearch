package youtubesearch.modeso.ch.youtubesearch.services;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import youtubesearch.modeso.ch.youtubesearch.responsies.ApiResponse;
import youtubesearch.modeso.ch.youtubesearch.responsies.Error;
import youtubesearch.modeso.ch.youtubesearch.responsies.ErrorResponse;
import youtubesearch.modeso.ch.youtubesearch.utils.Constants;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public abstract class YoutubeApiClient {


    public static void searchYoutube(String searchQuery, ApiCallBack apiCallBack) {
        try {
            String encodedSearchQuery = URLEncoder.encode(searchQuery, "UTF-8");
            URL url = new URL(Constants.BASE_URL + encodedSearchQuery);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");

            int code = httpsURLConnection.getResponseCode();
            Gson gson = new Gson();
            if (code == 200 || code == 201) {
                if (apiCallBack != null) {
                    apiCallBack.onSuccess(gson.fromJson(getBodyString(httpsURLConnection.getInputStream()), ApiResponse.class));
                }
            } else {
                if (apiCallBack != null) {
                    apiCallBack.onError(gson.fromJson(getBodyString(httpsURLConnection.getErrorStream()), ErrorResponse.class));
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            if (apiCallBack != null) {
                apiCallBack.onError(getErrorResponse(1111, ex));
            }
        }
    }


    private static String getBodyString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        return stringBuilder.toString();
    }

    private static ErrorResponse getErrorResponse(int code, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();

        error.setCode(code);
        error.setMessage(ex.getMessage());
        errorResponse.setError(error);

        return errorResponse;
    }

    public interface ApiCallBack {

        void onSuccess(ApiResponse apiResponse);

        void onError(ErrorResponse errorResponse);
    }
}
