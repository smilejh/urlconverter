package com.ljh.urlconverter.service;

import com.ljh.urlconverter.domain.UrlInfo;

public interface UrlConvertService {
    public UrlInfo getShortUrl(UrlInfo urlInfo);
    public UrlInfo getOriginalUrl(UrlInfo urlInfo);
    public UrlInfo getOriginalUrl(String key);
}
