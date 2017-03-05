package com.sfuronlabs.livescore.football.util;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import com.sfuronlabs.livescore.football.util.CollectionUtils;

import java.util.List;

/**
 * @author Ripon
 */

public class StringUtils {
    public static boolean isEachStringNotEmpty(String... strings) {
        for (String string : strings) {
            if (isEmpty(string)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String join(Object... values) {
        return joinWithDelimiter(", ", values);
    }

    public static String joinWithDelimiter(String delimiter, Object... values) {
        List elements = CollectionUtils.toList(values);

        StringBuilder sb = new StringBuilder("");
        for (Object element : elements) {
            String str = (element == null) ? null : String.valueOf(element);

            if (isEmpty(str)) {
                continue;
            }

            if (sb.length() > 0) {
                sb.append(delimiter);
            }

            sb.append(str);
        }

        return sb.toString();
    }

    public static SpannableString getTitleBoldSpannableText(String title, String text) {
        SpannableString spanString = new SpannableString(title + " " + text);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), 0);
        return spanString;
    }

    public static SpannableString getTitleSpannableText(String title, String text) {
        return new SpannableString(title + " " + text);
    }

    public static String getStringFromBytes(byte[] content) {
        return content == null ? "" : new String(content);
    }
}
