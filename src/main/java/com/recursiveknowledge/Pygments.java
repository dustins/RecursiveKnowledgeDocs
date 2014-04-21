/*
 * Copyright
 */

package com.recursiveknowledge;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lyncode.jtwig.functions.JtwigFunction;
import com.lyncode.jtwig.functions.annotations.JtwigFunctionDeclaration;
import com.lyncode.jtwig.functions.exceptions.FunctionException;
import org.python.util.PythonInterpreter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class Description
 *
 * @author Dustin Sweigart <dustin@swigg.net>
 */
@JtwigFunctionDeclaration(name = "pygments")
public class Pygments implements JtwigFunction {
    @Override
    public Object execute(Object... arguments) throws FunctionException {
        PythonInterpreter interpreter = new PythonInterpreter();

        Set<String> options = Sets.newHashSet();
        Map<String, Object> pyOptions = Maps.newHashMap();
        pyOptions.put("lexer", "python");
        String code = (String) arguments[0];
        if (arguments.length > 1) {
            pyOptions = (Map<String,Object>) arguments[1];

            options.add(pyOptions.get("linenos") == true ? "linenos=True" : "linenos=False");
            pyOptions.put("lexer", pyOptions.containsKey("lexer") ? pyOptions.get("lexer") : "python");

            if (pyOptions.containsKey("hl_lines")) {
                options.add(String.format("hl_lines=[%s]", Joiner.on(',').skipNulls().join((Iterable)pyOptions.get("hl_lines"))));
            }
        }
        options.add("encoding='utf-8'");
        options.add(String.format("cssclass='%s highlight'", pyOptions.get("lexer")));

        interpreter.set("code", code);
        interpreter.exec("from pygments import highlight\n"
                + "from pygments.lexers import get_lexer_by_name\n"
                + "from pygments.formatters import HtmlFormatter\n"
                + "formatter = HtmlFormatter(" + Joiner.on(',').skipNulls().join(options) + ")\n"
                + "lexer = get_lexer_by_name('" + pyOptions.get("lexer") + "')\n"
                + (pyOptions.containsKey("gobble") ? "lexer.add_filter('gobble', n=" + pyOptions.get("gobble") + ")" : "")
                + "\nresult = highlight(code, lexer, formatter)");


        return interpreter.get("result", String.class);
    }
}
