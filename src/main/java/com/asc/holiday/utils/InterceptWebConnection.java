package com.asc.holiday.utils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;

import java.io.IOException;

/**
 * User:dijian
 * Date:2018/4/24
 */
public class InterceptWebConnection extends FalsifyingWebConnection {

    /**
     * Constructs an instance and places itself as connection of the WebClient.
     *
     * @param webClient the WebClient which WebConnection should be wrapped
     * @throws IllegalArgumentException if the WebClient is <code>null</code>
     */
    public InterceptWebConnection(WebClient webClient) throws IllegalArgumentException {
        super(webClient);
    }

    @Override
    public WebResponse getResponse(WebRequest request) throws IOException {
        WebResponse response=super.getResponse(request);
        String responseUrl = response.getWebRequest().getUrl().toString();
        if(responseUrl.contains("http://cpro.baidu.com")
                || responseUrl.contains("http://eiv.baidu.com")
                || responseUrl.contains("https://dup.baidustatic.com")
                || responseUrl.contains("http://newsapicom.dftoutiao.com")
                || responseUrl.contains("http://tools.2345.com/js/header_2012new.js")){
            return createWebResponse(response.getWebRequest(), "", "application/javascript", 200, "Ok");
        }
        return super.getResponse(request);
    }
}
