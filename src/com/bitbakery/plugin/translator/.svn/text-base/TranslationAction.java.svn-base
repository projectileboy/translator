package com.bitbakery.plugin.translator;

import com.google.api.translate.Translate;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Event handler for the "Translate" action which appears in the popup menu
 */
public class TranslationAction extends AnAction {
    private TranslatorConfig config;
    private Project project;
    private Editor editor;
    private Language source;

    public void actionPerformed(final AnActionEvent e) {
        editor = e.getData(DataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        project = e.getData(DataKeys.PROJECT);
        config = ApplicationManager.getApplication().getComponent(TranslatorConfig.class);
        source = Language.get(config.getSourceLanguageCode());

        if (config.isTargetLanguageSticky()) {
            translateSelectedText(Language.get(config.getTargetLanguageCode()));
        } else {
            new TargetLanguageDialog(source).showDialog(this, getCaretPosition(editor));
        }
    }

    public void translateSelectedText(final Language target) {
        (new WriteCommandAction.Simple(project) {
            protected void run() throws Throwable {
                SelectionModel sel = editor.getSelectionModel();
                String text = sel.getSelectedText();
                if (StringUtil.isEmptyOrSpaces(text)) {
                    return;
                }
                editor.getDocument().replaceString(
                        sel.getSelectionStart(),
                        sel.getSelectionEnd(),
                        Translate.translate(text, source.code, target.code));

                if (!config.isSelectionSticky()) {
                    sel.setSelection(editor.getCaretModel().getOffset(), editor.getCaretModel().getOffset());
                }
            }
        }).execute();
    }

    private Point getCaretPosition(Editor ed) {
        VisualPosition caret = ed.getCaretModel().getVisualPosition();
        Point pt = ed.visualPositionToXY(caret);
        SwingUtilities.convertPointToScreen(pt, ed.getContentComponent());
        return pt;
    }
}
