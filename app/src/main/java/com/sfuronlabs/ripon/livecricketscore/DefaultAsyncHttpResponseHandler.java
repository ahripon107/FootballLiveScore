package com.sfuronlabs.ripon.livecricketscore;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.Collection;

import cz.msebera.android.httpclient.Header;

/**
 * @author Ripon
 */

public class DefaultAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Handler handler;
    private JavaType valueType;

    public DefaultAsyncHttpResponseHandler(Handler handler) {
        this(handler, Void.class);
    }

    public DefaultAsyncHttpResponseHandler(Handler handler, Type valueType) {
        this.handler = handler;
        this.valueType = objectMapper.getTypeFactory().constructType(valueType);
    }

    public DefaultAsyncHttpResponseHandler(Handler handler, Type valueType,
                                           Class<? extends Collection> collectionClass) {
        this.handler = handler;
        this.valueType = objectMapper.getTypeFactory().constructCollectionType(collectionClass, (Class) valueType);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String content = StringUtils.getStringFromBytes(responseBody);

        try {
            sendMessageToTarget(RequestStatus.SUCCESS, objectMapper.readValue(content, valueType));
        } catch (Exception e) {
            sendMessageToTarget(RequestStatus.FAILURE, "Failed "+e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        String content = StringUtils.getStringFromBytes(responseBody);

        sendMessageToTarget(RequestStatus.FAILURE, "Failed "+ content);
    }

    private void sendMessageToTarget(int what, Object obj) {
        Message.obtain(handler, what, obj).sendToTarget();
    }
}
