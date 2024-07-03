package net.xstopho.resource_backpacks;

import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.datagen.RecipeProv;
import net.xstopho.resource_backpacks.network.OpenBackpackPacket;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainerScreen;
import net.xstopho.resource_backpacks.util.BackpackKeyMappings;
import net.xstopho.resource_config_api.api.ConfigRegistry;

import java.util.concurrent.CompletableFuture;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks() {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER);

        ItemRegistry.init();
        MenuTypeRegistry.init();
    }

    //-----------------------------------------------------------------//
    // Handles all Mod Events that are necessary for Client and Server
    //-----------------------------------------------------------------//
    @EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ResourceBackpacksEventHandler {

        @SubscribeEvent
        public static void registerPacketHandler(RegisterPayloadHandlersEvent event) {
            registerPackets(event.registrar(BackpackConstants.MOD_ID));
        }

        public static void registerPackets(PayloadRegistrar registrar) {
            registrar.playToServer(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket.PACKET_CODEC, OpenBackpackPacket::apply);
        }

        @SubscribeEvent
        public static void data(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper fileHelper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

            generator.addProvider(event.includeServer(), new RecipeProv(output, provider));
        }
    }

    //-----------------------------------------------------------------//
    // Handles all MOd Events that are necessary for Client
    //-----------------------------------------------------------------//
    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ResourceBackpacksClientEventHandler {

        @SubscribeEvent
        public static void initScreens(RegisterMenuScreensEvent event) {
            event.register(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
            event.register(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
            event.register(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), BackpackContainerScreen::new);
            event.register(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), BackpackContainerScreen::new);
            event.register(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), BackpackContainerScreen::new);
            event.register(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        }

        @SubscribeEvent
        public static void initKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(BackpackKeyMappings.OPEN_BACKPACK);
        }
    }

    //-----------------------------------------------------------------//
    // Handles all Events fired by NeoForge that are necessary
    // for the Client
    //-----------------------------------------------------------------//
    @EventBusSubscriber(value = Dist.CLIENT)
    public static class NeoForgeClientEventHandler {
        @SubscribeEvent
        public static void initClientTickEvent(ClientTickEvent.Post event) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                while (BackpackKeyMappings.OPEN_BACKPACK.consumeClick()) {
                    Minecraft client = Minecraft.getInstance();
                    PacketDistributor.sendToServer(new OpenBackpackPacket(1));
                }
            }
        }
    }
}
