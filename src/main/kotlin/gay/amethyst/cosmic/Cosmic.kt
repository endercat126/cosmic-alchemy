package gay.amethyst.cosmic

import gay.amethyst.cosmic.registry.ModBlocks
import gay.amethyst.cosmic.registry.ModItems
import gay.amethyst.cosmic.registry.ModSounds
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(Cosmic.ID)
@EventBusSubscriber
object Cosmic {
    const val ID = "cosmic"
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")

        ModBlocks.REGISTRY.register(MOD_BUS)
        ModItems.REGISTRY.register(MOD_BUS)
        ModSounds.REGISTRY.register(MOD_BUS)
    }

    @SubscribeEvent
    private fun onClientSetup(event: FMLClientSetupEvent) {
    }

    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
    }

    @SubscribeEvent
    fun onPlayerLogin(event: PlayerEvent.PlayerLoggedInEvent) {
        val player = event.entity
        player.sendSystemMessage(
            Component.literal(
                "hi ${player.displayName?.string} welcome to cosmic alchemy! " +
                    "this mod is in alpha some thinks might be broken"
            ).withColor(0xffc2e8)
        )
    }

    fun resource(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(ID, path)
}
