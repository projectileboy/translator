package com.bitbakery.plugin.translator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LanguagesTest {

    @Test
    public void testStaticInitialization() {
        // Simple check that we don't burst into flames when we load the class
        System.out.println(Language.getDefaultLanguage());
    }

    @Test
    public void testIsSupported() {
        // Sanity check...
        assertTrue(Language.isSupported("en"));
        assertTrue(Language.isSupported("ar"));
        assertTrue(Language.isSupported("es"));
        assertTrue(Language.isSupported("fr"));
        assertTrue(Language.isSupported("ru"));
        assertTrue(Language.isSupported("zh-CN")); // Note that this is unique to Google Translate - <lang>-<dialect>

        assertFalse(Language.isSupported("glurf"));

        // ...and make sure that we're internally consistent
        for (Language lang : Language.LANGUAGES) {
            assertTrue(Language.isSupported(lang.code));
        }
    }
}
