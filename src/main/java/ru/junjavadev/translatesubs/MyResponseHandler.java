package ru.junjavadev.translatesubs;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

class MyResponseHandler implements HttpClientResponseHandler<String> {

    @Override
    public String handleResponse(ClassicHttpResponse classicHttpResponse) throws HttpException, IOException {
        int status = classicHttpResponse.getCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = classicHttpResponse.getEntity();
            if(entity == null) {
                return "";
            } else {
                return EntityUtils.toString(entity);
            }
        } else {
            return ""+status;
        }
    }
}