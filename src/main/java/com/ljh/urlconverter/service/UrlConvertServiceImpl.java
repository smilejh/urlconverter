package com.ljh.urlconverter.service;

import com.ljh.urlconverter.domain.UrlInfo;
import com.ljh.urlconverter.util.UrlConverterUtil;
import org.springframework.stereotype.Service;

@Service
public class UrlConvertServiceImpl implements UrlConvertService {
    private final UrlConverterUtil urlConverterUtil;

    public UrlConvertServiceImpl(UrlConverterUtil urlConverterUtil) {
        this.urlConverterUtil = urlConverterUtil;
    }

    @Override
    public UrlInfo getShortUrl(UrlInfo urlInfo) {
        urlInfo.setShortUrl(urlConverterUtil.originalUrlToShortUrl(urlInfo.getOriginalUrl()));
        return urlInfo;
    }

    @Override
    public UrlInfo getOriginalUrl(UrlInfo urlInfo) {
        urlInfo.setOriginalUrl(urlConverterUtil.shortUrlToOriginalUrl(urlInfo.getShortUrl()));
        return urlInfo;
    }

    @Override
    public UrlInfo getOriginalUrl(String key) {
        UrlInfo urlInfo = new UrlInfo();
        urlInfo.setOriginalUrl(urlConverterUtil.keyToOriginalUrl(key));
        return urlInfo;
    }
}
