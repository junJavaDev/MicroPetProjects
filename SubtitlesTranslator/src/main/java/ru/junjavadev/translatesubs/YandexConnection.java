package ru.junjavadev.translatesubs;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class YandexConnection {

    private static final String IAM = "t1.9euelZqRx42SjJOSkoyOyMiSmZCRle3rnpWajoyPlcfIxsmYnprGzpaOi8zl8_cfZkZl-e9XDWQ8_t3z918URGX571cNZDz-.3G-2mRVOHskrjk_vKKsR9HWjfYlvML9Ms-NdLeXazDZDX3EREMY9PalAvpfQTSOJZtWfCddDzIRc3RRKGnvcBQ";
    private static final String AUTHORIZATION = "Bearer " + IAM;
    private static final String FOLDER_ID = "b1g7295t3d6rn7v2d88h";
    private static final String SOURCE_LANGUAGE_CODE = "en";
    private static final String TARGET_LANGUAGE_CODE = "ru";
    private static final String CONTENT_TYPE = "application/json";
    private static final String URI = "https://translate.api.cloud.yandex.net/translate/v2/translate";

    public static String translate(String text) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = getHttpPost(text);
            JSONObject response = getResponse(httpClient, request);
            return parseJsonArrayParam(response);
        } catch (Exception ignored) {
        }
        return text;
    }

    private static String parseJsonArrayParam(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("translations");
        return jsonArray.getJSONObject(0).getString("text");
    }

    private static JSONObject getResponse(CloseableHttpClient httpClient, HttpPost request) throws IOException {
        HttpClientResponseHandler<String> responseHandler = new MyResponseHandler();
        String execute = httpClient.execute(request, responseHandler);
        return new JSONObject(execute);
    }

    private static HttpPost getHttpPost(String text) {
        HttpPost request = new HttpPost(URI);
        StringEntity params = new StringEntity(buildJson(text).toString());
        request.addHeader("content-type", CONTENT_TYPE);
        request.addHeader("Authorization", AUTHORIZATION);
        request.setEntity(params);
        return request;
    }

    private static JSONObject buildJson(String text) {
        JSONObject json = new JSONObject();
        json.put("folderId", FOLDER_ID);
        json.put("sourceLanguageCode", SOURCE_LANGUAGE_CODE);
        json.put("targetLanguageCode", TARGET_LANGUAGE_CODE);
        json.put("texts", new String[]{text});
        return json;
    }
}

