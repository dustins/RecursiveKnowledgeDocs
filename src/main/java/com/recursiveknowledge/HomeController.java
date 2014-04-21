/*
 * Copyright
 */

package com.recursiveknowledge;

import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dustin Sweigart <dustin@swigg.net>
 */
@Controller
public class HomeController {
    @RequestMapping({"/home"})
    public String index() {
        return "home/index";
    }

    @RequestMapping("/highlight")
    @ResponseBody
    public String highlight() {
        PythonInterpreter interpreter = new PythonInterpreter();
        String code = "<div class=\"highlight\">\n" +
                "<pre><span class=\"k\">print</span> <span class=\"s\">&quot;Hello World&quot;</span></pre>\n" +
                "</div>";
        interpreter.set("code", code);
        interpreter.exec("from pygments import highlight\n"
                + "from pygments.lexers import PythonLexer\n"
                + "from pygments.formatters import HtmlFormatter\n"
                + "formatter = HtmlFormatter(linenos=True)\n"
                + "style = formatter.get_style_defs('.highlight')\n"
                + "\nresult = highlight(code, PythonLexer(), formatter)");


        return "<style type='text/css'>" + interpreter.get("style", String.class) + "</style>" + interpreter.get("result", String.class);
    }
}
