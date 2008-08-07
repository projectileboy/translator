package com.bitbakery.plugin.translator;

import java.util.*;

/**
 * Encapsulates details about what Google Translate supports, and what it doesn't
 */
public class Language implements Comparable<Language> {

    public static final Language ARABIC = new Language("ar");
    public static final Language BULGARIAN = new Language("bg");
    public static final Language CHINESE = new Language("zh", "CN"); // Simplified
    // public static final Language CHINESE = new Language("zh", "TW"); // Traditional
    public static final Language CROATIAN = new Language("hr");
    public static final Language CZECH = new Language("cs");
    public static final Language DANISH = new Language("da");
    public static final Language DUTCH = new Language("nl");
    public static final Language ENGLISH = new Language("en");
    public static final Language FINNISH = new Language("fi");
    public static final Language FRENCH = new Language("fr");
    public static final Language GERMAN = new Language("de");
    public static final Language GREEK = new Language("el");
    public static final Language HINDI = new Language("hi");
    public static final Language ITALIAN = new Language("it");
    public static final Language JAPANESE = new Language("ja");
    public static final Language KOREAN = new Language("ko");
    public static final Language NORWEGIAN = new Language("no");
    public static final Language POLISH = new Language("pl");
    public static final Language PORTUGESE = new Language("pt");
    public static final Language ROMANIAN = new Language("ro");
    public static final Language RUSSIAN = new Language("ru");
    public static final Language SPANISH = new Language("es");
    public static final Language SWEDISH = new Language("sv");


    private static HashMap<String, Language> CODE_MAPPING;
    public static Language[] LANGUAGES;

    static {
        LANGUAGES = sort(ARABIC, BULGARIAN, CHINESE, CROATIAN, CZECH, DANISH, DUTCH,
                ENGLISH, FINNISH, FRENCH, GERMAN, GREEK, HINDI, ITALIAN, JAPANESE, KOREAN,
                NORWEGIAN, POLISH, PORTUGESE, ROMANIAN, RUSSIAN, SPANISH, SWEDISH);

        CODE_MAPPING = new HashMap<String, Language>();
        CODE_MAPPING.put(ARABIC.code, ARABIC);
        CODE_MAPPING.put(BULGARIAN.code, BULGARIAN);
        CODE_MAPPING.put(CHINESE.code, CHINESE);
        // CODE_MAPPING.put(CHINESE-SIMPLIFIED.code, CHINESE-SIMPLIFIED);
        // CODE_MAPPING.put(CHINESE-TRADITIONAL.code, CHINESE-TRADITIONAL);
        CODE_MAPPING.put(CROATIAN.code, CROATIAN);
        CODE_MAPPING.put(CZECH.code, CZECH);
        CODE_MAPPING.put(DANISH.code, DANISH);
        CODE_MAPPING.put(DUTCH.code, DUTCH);
        CODE_MAPPING.put(ENGLISH.code, ENGLISH);
        CODE_MAPPING.put(FINNISH.code, FINNISH);
        CODE_MAPPING.put(FRENCH.code, FRENCH);
        CODE_MAPPING.put(GERMAN.code, GERMAN);
        CODE_MAPPING.put(GREEK.code, GREEK);
        CODE_MAPPING.put(HINDI.code, HINDI);
        CODE_MAPPING.put(ITALIAN.code, ITALIAN);
        CODE_MAPPING.put(JAPANESE.code, JAPANESE);
        CODE_MAPPING.put(KOREAN.code, KOREAN);
        CODE_MAPPING.put(NORWEGIAN.code, NORWEGIAN);
        CODE_MAPPING.put(POLISH.code, POLISH);
        CODE_MAPPING.put(PORTUGESE.code, PORTUGESE);
        CODE_MAPPING.put(ROMANIAN.code, ROMANIAN);
        CODE_MAPPING.put(RUSSIAN.code, RUSSIAN);
        CODE_MAPPING.put(SPANISH.code, SPANISH);
        CODE_MAPPING.put(SWEDISH.code, SWEDISH);
    }

    private static Language[] sort(Language... l) {
        Arrays.sort(l);
        return l;
    }


    public static String getDisplayName(final String code) {
        return get(code).display;
    }

    public static Language[] getTargetLanguages(Language source) {
        List languages = new ArrayList(LANGUAGES.length);
        languages.addAll(Arrays.asList(LANGUAGES));
        languages.remove(source);
        return (Language[]) languages.toArray(new Language[LANGUAGES.length - 1]);
    }

    public static Language getDefaultLanguage() {
        // TODO - Kill the hack, and actually handle dialects!
        String languageCode = Locale.getDefault().getLanguage();
        if (CHINESE.code.startsWith(languageCode)) {
            return CHINESE;
        }
        return CODE_MAPPING.get(languageCode);
    }

    public static Language get(String code) {
        return CODE_MAPPING.get(code);
    }

    public static boolean isSupported(String code) {
        return CODE_MAPPING.containsKey(code);
    }


    public String code;
    public String display;

    private Language(String code) {
        this.code = code;
        display = new Locale(code).getDisplayLanguage();
    }

    private Language(String code, String dialect) {
        this.code = code + "-" + dialect;
        display = new Locale(code, dialect).getDisplayLanguage();
    }

    public String toString() {
        return display;
    }

    public int compareTo(Language that) {
        return display.compareTo(that.display);
    }
}
