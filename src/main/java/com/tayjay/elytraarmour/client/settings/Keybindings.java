package com.tayjay.elytraarmour.client.settings;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * Created by tayjay on 2016-07-24.
 */
public enum Keybindings
{
    OPEN_ELYTRA_ARMOUR("keys.elytaarmour.openelytraarmour", Keyboard.KEY_I);

    private final KeyBinding keyBinding;

    Keybindings(String keyName, int defaultKeyCode)
    {
        keyBinding = new KeyBinding(keyName,defaultKeyCode,"key.elytraarmour.category");
    }

    public KeyBinding getKeybind(){return keyBinding;}

    public boolean isPressed()
    {
        return keyBinding.isPressed();
    }
}
