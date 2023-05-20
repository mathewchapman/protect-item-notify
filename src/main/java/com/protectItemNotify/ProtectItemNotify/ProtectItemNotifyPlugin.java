package com.protectItemNotify.ProtectItemNotify;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import com.protectItemNotify.ProtectItemNotify.ProtectItemNotifyOverlay;
import java.util.Optional;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.Prayer;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Varbits;
import org.apache.commons.lang3.ArrayUtils;

@Slf4j
@PluginDescriptor(
	name = "Protect Item Notify"
)
public class ProtectItemNotifyPlugin extends Plugin
{
	private static final Set<Integer> WILD_VARROCK_REGIONS = ImmutableSet.of(13918, 13919, 13920, 14174, 14175, 14176, 14430, 14431, 14432);
	private static final Set<Integer> DESERT_ISLAND_REGIONS = ImmutableSet.of(13658, 13659, 13914, 13915);
	private static final Set<Integer> LAST_MAN_STANDING_REGIONS = Stream.concat(WILD_VARROCK_REGIONS.stream(), DESERT_ISLAND_REGIONS.stream()).collect(Collectors.toSet());

	@Inject private Client client;

	@Inject private ProtectItemNotifyConfig config;

	@Inject private ProtectItemNotifyOverlay protectItemNotifyOverlay;

	@Inject private OverlayManager overlayManager;

	private boolean protectItemOn = true;

	@Override
	protected void startUp() {
		overlayManager.add(protectItemNotifyOverlay);
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(protectItemNotifyOverlay);
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		this.protectItemOn = !client.isPrayerActive(Prayer.PROTECT_ITEM);
	}

	@Provides
	ProtectItemNotifyConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(ProtectItemNotifyConfig.class);
	}
	public boolean isInPVP() {
		return client.getVarbitValue(Varbits.PVP_SPEC_ORB) == 1;
	}

	public boolean isProtectItemOn() {
		return protectItemOn;
	}

	public boolean isAtLMS()
	{
		final int[] mapRegions = client.getMapRegions();

		for (int region : LAST_MAN_STANDING_REGIONS)
		{
			if (ArrayUtils.contains(mapRegions, region))
			{
				return true;
			}
		}
		return false;
	}
}
