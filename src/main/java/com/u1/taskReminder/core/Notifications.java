package com.u1.taskReminder.core;

import com.u1.taskReminder.ui.UIUtils;

import apple.foundation.NSCalendar;
import apple.foundation.NSDate;
import apple.foundation.NSDateComponents;
import apple.foundation.NSError;
import apple.foundation.enums.NSCalendarUnit;
import apple.uikit.UIApplication;
import apple.usernotifications.UNCalendarNotificationTrigger;
import apple.usernotifications.UNMutableNotificationContent;
import apple.usernotifications.UNNotification;
import apple.usernotifications.UNNotificationRequest;
import apple.usernotifications.UNNotificationResponse;
import apple.usernotifications.UNUserNotificationCenter;
import apple.usernotifications.enums.UNAuthorizationOptions;
import apple.usernotifications.enums.UNNotificationPresentationOptions;
import apple.usernotifications.protocol.UNUserNotificationCenterDelegate;

public final class Notifications implements UNUserNotificationCenterDelegate {

    public static void enable(UIApplication app) {
        UNUserNotificationCenter.currentNotificationCenter().requestAuthorizationWithOptionsCompletionHandler(
                UNAuthorizationOptions.Alert | UNAuthorizationOptions.Badge | UNAuthorizationOptions.Sound,
                new UNUserNotificationCenter.Block_requestAuthorizationWithOptionsCompletionHandler() {
                    @Override
                    public void call_requestAuthorizationWithOptionsCompletionHandler(boolean wasGranted, NSError nsError) {
                        if (!wasGranted || nsError != null)
                            UIUtils.showAlert("Unable to Authorize",
                                    "Could not authorize user notifications. You need to enable them manually for this Application in Settings");
                    }
                });
        UNUserNotificationCenter.currentNotificationCenter().setDelegate(new Notifications());
    }

    public static void schedule(String id, NSDate dateTime, UNMutableNotificationContent content) {
        NSDateComponents dateComponents = NSCalendar.currentCalendar().componentsFromDate(
                NSCalendarUnit.CalendarUnitYear | NSCalendarUnit.CalendarUnitMonth | NSCalendarUnit.CalendarUnitDay |
                        NSCalendarUnit.CalendarUnitHour | NSCalendarUnit.CalendarUnitMinute, dateTime);
        UNCalendarNotificationTrigger trigger =
                UNCalendarNotificationTrigger.triggerWithDateMatchingComponentsRepeats(dateComponents, false);
        UNNotificationRequest request = UNNotificationRequest.requestWithIdentifierContentTrigger(id, content, trigger);

        UNUserNotificationCenter.currentNotificationCenter()
                .addNotificationRequestWithCompletionHandler(request,
                        new UNUserNotificationCenter.Block_addNotificationRequestWithCompletionHandler() {
                            @Override
                            public void call_addNotificationRequestWithCompletionHandler(NSError error) {
                                if (error != null)
                                    UIUtils.showAlert("Notification Completion Error", error.description());
                            }
                        });
    }

    @Override
    public void userNotificationCenterDidReceiveNotificationResponseWithCompletionHandler(
            UNUserNotificationCenter center, UNNotificationResponse response, UNUserNotificationCenterDelegate.Block_userNotificationCenterDidReceiveNotificationResponseWithCompletionHandler completionHandler) {
        UIUtils.showAlert("Notification", "WasRespondedTo");
        completionHandler.call_userNotificationCenterDidReceiveNotificationResponseWithCompletionHandler();
    }

    @Override
    public void userNotificationCenterWillPresentNotificationWithCompletionHandler(
            UNUserNotificationCenter center, UNNotification notification, UNUserNotificationCenterDelegate.Block_userNotificationCenterWillPresentNotificationWithCompletionHandler completionHandler) {
        UIUtils.showAlert("Notification", "WasTriggered");
        completionHandler.call_userNotificationCenterWillPresentNotificationWithCompletionHandler(
                UNNotificationPresentationOptions.Sound | UNNotificationPresentationOptions.Badge | UNNotificationPresentationOptions.Alert);
    }
}
