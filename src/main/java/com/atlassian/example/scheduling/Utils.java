package com.atlassian.example.scheduling;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author  Erik van Zijst
 */
public class Utils {

    public static String htmlEncode(byte[] bytes) {
        return htmlEncode(new String(bytes));
    }

    private static void htmlEncode(char c, Writer w) throws IOException {
        if (c == '&') {
            w.write("&amp;");
        } else if (c == '<') {
            w.write("&lt;");
        } else if (c == '>') {
            w.write("&gt;");
        } else if (c == '"') {
            w.write("&#34;");
        } else if (c == '\'') {
            w.write("&#39;");
        } else if (((int) c) > 128) {
            w.write("&#" + ((int) c) + ";");
        } else {
            w.write(c);
        }
    }

    public static void htmlEncode(CharSequence s, Writer w, int start, int len) throws IOException {
        if (s == null) {
            return;
        }

        for (int i = start; i < start + len; i++) {
            char c = s.charAt(i);
            htmlEncode(c, w);
        }
    }

    public static void htmlEncode(CharSequence s, Writer w) throws IOException {
        if (s == null) {
            return;
        }
        htmlEncode(s, w, 0, s.length());
    }

    public static String htmlEncode(CharSequence s) {
        StringWriter out = new StringWriter();

        try {
            htmlEncode(s, out);
        } catch (IOException e) {
            // should never happen
        }
        return out.toString();
    }

    public static String htmlEncode(Object o) {
        if (o == null) {
            return "";
        }
        return Utils.htmlEncode(o.toString());
    }
}
