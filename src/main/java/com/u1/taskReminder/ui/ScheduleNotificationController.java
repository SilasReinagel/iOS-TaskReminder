package com.u1.taskReminder.ui;

import com.u1.taskReminder.core.Notifications;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.IBAction;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Selector;

import apple.foundation.NSDate;
import apple.uikit.UIButton;
import apple.uikit.UIDatePicker;
import apple.uikit.UIViewController;
import apple.usernotifications.UNMutableNotificationContent;

@org.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("ScheduleNotificationController")
@RegisterOnStartup
public class ScheduleNotificationController extends UIViewController {

    NSDate _notificationDateTime = NSDate.distantPast();

    @Owned
    @Selector("alloc")
    public static native ScheduleNotificationController alloc();

    @Selector("init")
    public native ScheduleNotificationController init();

    protected ScheduleNotificationController(Pointer peer) {
        super(peer);
    }

    @IBAction
    @Selector("setNotification:")
    public void setNotification(UIButton sender) {
        UNMutableNotificationContent content = UNMutableNotificationContent.alloc().init();
        content.setTitle("Wake up!");
        content.setBody("Rise and shine! It's morning time!");
        Notifications.schedule("TaskReminder", _notificationDateTime, content);
        UIUtils.showAlert("Log", "Notification Scheduled");
    }

    @IBAction
    @Selector("storeSelectedDate:")
    public void storeSelectedDate(UIDatePicker picker) {
        _notificationDateTime = picker.date();
    }
}
