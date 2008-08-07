package com.bitbakery.plugin.translator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * The configuration form for the Translator plugin. This is accessible from the IntelliJ settings dialog.
 */
public class TranslatorConfigForm {
    private JCheckBox isTargetLanguageSticky;
    private JCheckBox isSelectionSticky;
    private JComboBox targetLanguages;
    private JComboBox sourceLanguages;
    private JPanel rootComponent;

    public TranslatorConfigForm() {
        isTargetLanguageSticky.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                targetLanguages.setEnabled(isTargetLanguageSticky.isSelected());
            }
        });
    }

    public JPanel getRootComponent() {
        return rootComponent;
    }

    public void setData(TranslatorConfig data) {
        isTargetLanguageSticky.setSelected(data.isTargetLanguageSticky());
        isSelectionSticky.setSelected(data.isSelectionSticky());
        sourceLanguages.setSelectedItem(Language.get(data.getSourceLanguageCode()));

        hydrateTargetLanguages();
        Language language = Language.get(data.getTargetLanguageCode());
        if (language != null) {
            targetLanguages.setSelectedItem(language);
        } else {
            targetLanguages.setSelectedIndex(0);
        }
        targetLanguages.setEnabled(isTargetLanguageSticky.isSelected());
    }

    public void getData(TranslatorConfig data) {
        data.setTargetLanguageSticky(isTargetLanguageSticky.isSelected());
        data.setSelectionSticky(isSelectionSticky.isSelected());
        data.setSourceLanguageCode(getCode(sourceLanguages));
        data.setTargetLanguageCode(getCode(targetLanguages));
    }

    private String getCode(JComboBox languageComboBox) {
        return languageComboBox.getSelectedItem() == null ? null : ((Language) languageComboBox.getSelectedItem()).code;
    }

    public boolean isModified(TranslatorConfig data) {
        return isModified(isTargetLanguageSticky, data.isTargetLanguageSticky())
                || isModified(isSelectionSticky, data.isSelectionSticky())
                || isModified(sourceLanguages, data.getSourceLanguageCode())
                || isModified(targetLanguages, data.getTargetLanguageCode());
    }

    private boolean isModified(JCheckBox checkBox, boolean data) {
        return checkBox.isSelected() != data;
    }

    private boolean isModified(JComboBox comboBox, String data) {
        return comboBox.getSelectedItem() != null ? !((Language) comboBox.getSelectedItem()).code.equals(data) : data != null;
    }

    private void createUIComponents() {
        sourceLanguages = new JComboBox();
        for (Language source : Language.LANGUAGES) {
            sourceLanguages.addItem(source);
        }
        sourceLanguages.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                hydrateTargetLanguages();
            }
        });
    }

    private void hydrateTargetLanguages() {
        targetLanguages.removeAllItems();
        Language source = (Language) sourceLanguages.getSelectedItem();
        for (Language target : Language.getTargetLanguages(source)) {
            targetLanguages.addItem(target);
        }
    }
}
