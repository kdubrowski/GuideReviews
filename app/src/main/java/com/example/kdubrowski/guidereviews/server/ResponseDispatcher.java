package com.example.kdubrowski.guidereviews.server;

import java.net.HttpURLConnection;
import java.util.UUID;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static java.lang.Thread.currentThread;

/**
 * Response dispatcher for MockWebServer to dispatch mocked responses for the upload of the review
 */
public class ResponseDispatcher extends Dispatcher {

    @Override
    public MockResponse dispatch(final RecordedRequest request) throws InterruptedException {
        switch (request.getPath()) {
            case "/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json":
                return successResponse();
            default:
                return errorResponse();
        }
    }

    private MockResponse successResponse() {
        return new MockResponse().setResponseCode(HttpURLConnection.HTTP_CREATED).setBody("The review was uploaded successfully");
    }


    private MockResponse errorResponse() {
        return new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody("Wrong Request");
    }
}
