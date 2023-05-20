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

	@ConfigItem(
			keyName = "pvponly",
			name = "PVP Only",
			description = "Should the plugin only display when in a PVP area.")
	default boolean pvponly() {
		return false;
	}

	@ConfigItem(
		keyName = "disableInLms",
		name = "Disable in LMS",
		description = "Disables visibility whilst in LMS matches."
	)
	default boolean disableInLms()
	{
		return false;
	}
}