package com.u1.taskReminder.core;

import apple.foundation.NSUserDefaults;

public class Settings {
    public static void save(String key, String value) {
        NSUserDefaults.alloc().init().setObjectForKey(value, key);
    }

    public static String loadString(String key) {
        return (String)NSUserDefaults.alloc().init().objectForKey(key);
    }
}
