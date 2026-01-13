package gay.amethyst.cosmic.data

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@EventBusSubscriber
object ModDataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput
        val existingFileHelper = event.existingFileHelper


        generator.addProvider(
            event.includeClient(), ModBlockStateProvider(output, existingFileHelper)
        )
        generator.addProvider(
            event.includeClient(), ModItemModelProvider(output, existingFileHelper)
        )
    }
}