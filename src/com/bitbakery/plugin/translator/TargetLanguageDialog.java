package com.bitbakery.plugin.translator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Displays the target languages to which we can translate the selcted string.
 */
public class TargetLanguageDialog extends JDialog {
    private JList list;
    private TranslationAction action;

    public TargetLanguageDialog(Language source) throws HeadlessException {
        setUndecorated(true);
        setAlwaysOnTop(true);
        add(buildTargetList(source));
        pack();
    }

    private JList buildTargetList(final Language source) {
        list = new JList(Language.getTargetLanguages(source));
        list.setBorder(LineBorder.createGrayLineBorder());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectAndClose(event);
                } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    close(event);
                }
            }

            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    if (list.getSelectedIndex() <= 0) {
                        list.setSelectedIndex(getLastIndex());
                        event.consume();
                    }
                } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (list.getSelectedIndex() >= getLastIndex()) {
                        list.setSelectedIndex(0);
                        event.consume();
                    }
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                selectAndClose(event);
            }
        });
        list.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent event) {
                setVisible(false);
                dispose();
            }
        });
        return list;
    }

    private void selectAndClose(InputEvent event) {
        Language target = (Language) list.getSelectedValue();
        if (target != null) {
            action.translateSelectedText(target);
        }
        close(event);
    }

    private void close(InputEvent event) {
        setVisible(false);
        event.consume();
        dispose();
    }

    private int getLastIndex() {
        return list.getModel().getSize() - 1;
    }

    public void showDialog(TranslationAction action, Point location) {
        this.action = action;
        setLocation(location);
        setVisible(true);
    }
}
