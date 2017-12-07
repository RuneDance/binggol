package com.binggol.controller;

import com.binggol.csp.MarkdownSP;
import com.binggol.entity.ResultMarkdown;
import com.binggol.utils.EncryptTools;
import com.binggol.utils.SPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
public class MarkdownController {

    @Autowired
    private MarkdownSP markdownSP;

    @RequestMapping(value = "/edit")
    public String edit() {
        return "edit";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/full-home")
    public String full_home() {
        return "full-home";
    }

    /**
     * 新增markdown内容
     *
     * @param content
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/addMarkdown", method = RequestMethod.POST)
    public SPResult add(@RequestParam(value = "content", required = false) String content) throws Exception {
        ResultMarkdown resultMarkdown = new ResultMarkdown();
        resultMarkdown.setId(EncryptTools.encryptMD5(content.getBytes()));
        resultMarkdown.setDatetime(new Date());
        resultMarkdown.setContent(content);
        return markdownSP.addMarkdown(resultMarkdown);
    }


}
