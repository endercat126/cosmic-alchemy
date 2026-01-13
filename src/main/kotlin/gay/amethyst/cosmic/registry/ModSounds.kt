package gay.amethyst.cosmic.registry

import gay.amethyst.cosmic.Cosmic
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.sounds.SoundEvent
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModSounds {
    val REGISTRY: DeferredRegister<SoundEvent> = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Cosmic.ID)

    val MARKIPLIER = registerSoundEvent("markiplier")

    fun registerSoundEvent(name: String): DeferredHolder<SoundEvent, SoundEvent> = REGISTRY.register(
        name, Supplier { SoundEvent.createVariableRangeEvent(Cosmic.resource(name)) })
}