package com.protectItemNotify.ProtectItemNotify;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("protectitemnotify")
public interface ProtectItemNotifyConfig extends Config
{
	@ConfigItem(
			keyName = "scale",
			name = "Scale",
			description = "The scale of the ring of protect item image.")
	default int scale() {
		return 1;
	}

}