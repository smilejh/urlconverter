package com.ljh.urlconverter.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class UrlConverterUtil {
    private static final char[] encodeChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final String defalutDomain = "http://localhost:8080/";
    private static final Random random = new Random();
    private static final int keyLength = 8;

    private static HashMap<String, String> urlMap = new HashMap<>();


    public String originalUrlToShortUrl(final String originalUrl) {
        String shortUrl = "";
        if( urlMap.containsValue(originalUrl) ) {
            shortUrl = urlMap.entrySet().stream().filter(it -> it.getValue().equals(originalUrl)).map(Map.Entry::getKey).findAny().orElse("");
        } else {
            shortUrl = generateKey(originalUrl);
        }

        return defalutDomain+shortUrl;
    }

    public String shortUrlToOriginalUrl(final String shortUrl) {
        String originalUrl = "";
        String key = shortUrl.substring(defalutDomain.length());
        if( urlMap.containsKey(key) ) {
            originalUrl = urlMap.get(key);
        }

        return originalUrl;
    }

    public String keyToOriginalUrl(final String key) {
        String originalUrl = "";
        if( urlMap.containsKey(key) ) {
            originalUrl = urlMap.get(key);
        }

        return originalUrl;
    }

    private String generateKey(String originalUrl) {
        StringBuilder key = new StringBuilder();
        boolean duplicateFlag = true;

        while( duplicateFlag ) {
            for( int i=0; i<keyLength; i++ ) {
                key.append(encodeChar[random.nextInt(encodeChar.length)]);
            }
            if( urlMap.containsKey(key.toString()) ) {
                key = new StringBuilder();
            } else {
                duplicateFlag = false;
            }
        }

        urlMap.put(key.toString(), originalUrl);

        return key.toString();
    }
}
