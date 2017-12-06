package com.company;

import java.util.regex.Pattern;

class HtmlToTextPattern {
    private String text;

    private  static final Pattern htmlCommentHeaderScriptPattern =
            Pattern.compile("<!--(.*?)(\\n.*?)*?-->|<script(.*?)(\\n.*?)*?/script>|<head(.*?)(\\n.*?)*?/head>");

    private static final Pattern htmlNewlinePattern =
            Pattern.compile("\\s*<(br|/?p)>\\s*");

    private static final Pattern htmlListPattern =
            Pattern.compile("\\s*<li>\\s*");

    private static final Pattern htmlTagPattern =
            Pattern.compile("</?([^<]*)>");


    String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    private String CleanHtml(String text){
        text = htmlCommentHeaderScriptPattern.matcher(text).replaceAll("");
        text = htmlNewlinePattern.matcher(text).replaceAll("\n");
        text = htmlListPattern.matcher(text).replaceAll("\n- ");
        text = htmlTagPattern.matcher(text).replaceAll("");
        return text;
    }

    HtmlToTextPattern(String text) {
        setText(CleanHtml(text));
    }
}
