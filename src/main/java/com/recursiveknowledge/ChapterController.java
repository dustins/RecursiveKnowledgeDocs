/*
 * Copyright
 */

package com.recursiveknowledge;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dustin Sweigart <dustin@swigg.net>
 */
@Controller
public class ChapterController {
    @RequestMapping({"/chapters"})
    public String index() {
        return "chapters/index";
    }

    @RequestMapping({"/chapters/**"})
    public String view(HttpServletRequest request) {
        String view = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();

        // don't allow directory traversals
        if (view.contains("..")) {
            throw new IllegalArgumentException("File not found.");
        }

        // assume loading of an index if there are only 2 path segments
        int numberOfOccurrences = StringUtils.countOccurrencesOf(StringUtils.trimTrailingCharacter(view, '/'), "/");
        if (2 == numberOfOccurrences) {
            view += "/index";
        }

        return StringUtils.trimLeadingCharacter(view, '/');
    }
}
