package com.u1.taskReminder.ui;

import apple.uikit.UIAlertView;

public final class UIUtils {

    public static void showAlert(String title, String msg) {
        UIAlertView v = UIAlertView.alloc().initWithTitleMessageDelegateCancelButtonTitleOtherButtonTitles(
                title,
                msg,
                null,
                "Ok",
                null);
        v.show();
    }
}
