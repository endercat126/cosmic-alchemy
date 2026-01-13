package gay.amethyst.cosmic.registry

import gay.amethyst.cosmic.Cosmic
import gay.amethyst.cosmic.items.UsableItem
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModItems {
    val ITEMS: MutableList<Supplier<Item>> = mutableListOf()
    val REGISTRY: DeferredRegister.Items = DeferredRegister.createItems(Cosmic.ID)

    //    🩷 register items below!!
    val MARKIPLIER = registerItem("markiplier", UsableItem.Builder().onUse { level, player, hand ->
        level.playSound(null, player, ModSounds.MARKIPLIER.get(), SoundSource.PLAYERS, 1f, 1f)
        player.cooldowns.addCooldown(this, 80)
        InteractionResultHolder.success(player.getItemInHand(hand))
    }.addLore("silly")::create)

    fun registerItem(
        name: String, factory: (Item.Properties) -> Item, props: Item.Properties = Item.Properties()
    ): Supplier<Item> {
        val supplier = REGISTRY.registerItem(
            name, factory, props
        )
        ITEMS.add(supplier)
        return supplier
    }

    fun registerItem(name: String, props: Item.Properties = Item.Properties()): Supplier<Item> = registerItem(
        name, ::Item, props
    )
}