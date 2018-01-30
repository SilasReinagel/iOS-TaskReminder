package com.u1.taskReminder.ui;

import com.u1.taskReminder.core.Settings;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.IBAction;
import org.moe.natj.objc.ann.IBOutlet;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Property;
import org.moe.natj.objc.ann.Selector;

import apple.uikit.UIButton;
import apple.uikit.UILabel;
import apple.uikit.UITextField;
import apple.uikit.UIViewController;

@org.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("SettingsController")
@RegisterOnStartup
public class SettingsController extends UIViewController {

    @Owned
    @Selector("alloc")
    public static native ScheduleNotificationController alloc();

    @Selector("init")
    public native ScheduleNotificationController init();

    public SettingsController(Pointer peer) {
        super(peer);
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        String savedValue =Settings.loadString("demo");
        if (savedValue != null)
            getStringDemoLabel().setText(savedValue);
    }

    @IBOutlet
    @Property
    @Selector("stringDemoLabel")
    public native UITextField getStringDemoLabel();

    @IBAction
    @Selector("saveAll:")
    public void saveAll(UIButton sender) {

    }

    @IBAction
    @Selector("saveDemoString:")
    public void saveDemoString(UIButton sender) {
        Settings.save("demo", getStringDemoLabel().text());
    }
}
