package com.bitbakery.plugin.translator;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Persists Translator configuration settings
 */
@State(name = "TranslatorConfig",
        storages = {@Storage(id = "main", file = "$APP_CONFIG$/translator-settings.xml")})
public class TranslatorConfig implements ApplicationComponent, Configurable, PersistentStateComponent<TranslatorConfig> {

    private boolean isTargetLanguageSticky;
    private boolean isSelectionSticky;
    private String sourceLanguageCode;
    private String targetLanguageCode;

    private volatile TranslatorConfigForm form;


    public void initComponent() {
        // Do nothing
    }

    public void disposeComponent() {
        // Do nothing
    }

    @NotNull
    public String getComponentName() {
        return "TranslatorConfig";
    }


    @Nls
    public String getDisplayName() {
        return TranslatorStrings.message("plugin.translator.name");
    }

    public Icon getIcon() {
        return TranslatorIcons.GLOBE_LARGE_ICON;
    }

    @Nullable
    @NonNls
    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        if (form == null) {
            form = new TranslatorConfigForm();
            form.setData(this);
        }
        return form.getRootComponent();
    }

    public boolean isModified() {
        return form != null && form.isModified(this);
    }

    public void apply() throws ConfigurationException {
        if (form != null) {
            form.getData(this);
        }
    }

    public void reset() {
        if (form != null) {
            form.setData(this);
        }
    }

    public void disposeUIResources() {
        form = null;
    }

    public TranslatorConfig getState() {
        return this;
    }

    public void loadState(TranslatorConfig that) {
        this.isSelectionSticky = that.isSelectionSticky;
        this.isTargetLanguageSticky = that.isTargetLanguageSticky;
        this.sourceLanguageCode = that.sourceLanguageCode;
        this.targetLanguageCode = that.targetLanguageCode;
    }

    public boolean isTargetLanguageSticky() {
        return isTargetLanguageSticky;
    }

    public void setTargetLanguageSticky(boolean targetLanguageSticky) {
        this.isTargetLanguageSticky = targetLanguageSticky;
    }

    public boolean isSelectionSticky() {
        return isSelectionSticky;
    }

    public void setSelectionSticky(boolean selectionSticky) {
        this.isSelectionSticky = selectionSticky;
    }

    public String getSourceLanguageCode() {
        if (sourceLanguageCode == null) {
            sourceLanguageCode = Language.getDefaultLanguage().code;
        }
        return sourceLanguageCode;
    }

    public void setSourceLanguageCode(String sourceLanguageCode) {
        this.sourceLanguageCode = sourceLanguageCode;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }

    public void setTargetLanguageCode(String targetLanguageCode) {
        this.targetLanguageCode = targetLanguageCode;
    }
}
