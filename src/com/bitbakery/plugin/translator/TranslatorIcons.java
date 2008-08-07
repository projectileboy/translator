package com.bitbakery.plugin.translator;

import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

public interface TranslatorIcons {
    @NonNls
    final String DATA_PATH = "/icons/";

    final Icon GLOBE_LARGE_ICON = IconLoader.findIcon(DATA_PATH + "globe32.png");
    final Icon GLOBE_SMALL_ICON = IconLoader.findIcon(DATA_PATH + "globe16.png");
}
