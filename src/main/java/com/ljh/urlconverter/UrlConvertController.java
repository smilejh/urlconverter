package com.ljh.urlconverter;

import com.ljh.urlconverter.domain.UrlInfo;
import com.ljh.urlconverter.service.UrlConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UrlConvertController {
    private final UrlConvertService urlConvertService;

    @Autowired
    public UrlConvertController(UrlConvertService urlConvertService) {
        this.urlConvertService = urlConvertService;
    }

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("urlInfo", new UrlInfo());
        return mav;
    }

    @PostMapping("/url/convert")
    public ModelAndView urlConvert(@ModelAttribute("urlInfo") UrlInfo urlInfo) {
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("urlInfo", urlConvertService.getShortUrl(urlInfo));
        return mav;
    }

    @PostMapping("/url/connect")
    public ModelAndView urlConnect(@ModelAttribute("urlInfo") UrlInfo urlInfo) {
        UrlInfo convertUrlInfo = urlConvertService.getOriginalUrl(urlInfo);
        return new ModelAndView("redirect:"+convertUrlInfo.getOriginalUrl());
    }

    @GetMapping("/{key}")
    public ModelAndView urlRedirect(@PathVariable("key") String key) {
        UrlInfo convertUrlInfo = urlConvertService.getOriginalUrl(key);
        return new ModelAndView("redirect:"+convertUrlInfo.getOriginalUrl());
    }
}
