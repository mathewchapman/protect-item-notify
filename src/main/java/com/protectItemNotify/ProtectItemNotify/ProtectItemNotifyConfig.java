package com.protectItemNotify.ProtectItemNotify;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("protectitemnotify")
public interface ProtectItemNotifyConfig extends Config
{
	@ConfigItem(
			keyName = "hpthreshold",
			name = "Hp Threshold",
			description = "If your current health falls below this value you will be notified. Set to 0 to disable."
	)
	default int hpthreshold() {return 0;}
	@ConfigItem(
			keyName = "scale",
			name = "Scale %",
			description = "The scale of the ring of protect item image.")
	default int scale() {
		return 100;
	}

	@ConfigItem(
			keyName = "pvponly",
			name = "PVP Only",
			description = "Should the plugin only display when in a PVP area.")
	default boolean pvponly() {
		return false;
	}

}