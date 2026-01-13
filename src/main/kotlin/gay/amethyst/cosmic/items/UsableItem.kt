package gay.amethyst.cosmic.items

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

typealias UseAction = UsableItem.(Level, Player, InteractionHand) -> InteractionResultHolder<ItemStack?>

open class UsableItem(override val builder: Builder, props: Properties) : AbstractCosmicItem(builder, props) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack?> =
        builder.onUse?.invoke(this, level, player, usedHand) ?: super.use(level, player, usedHand)

    class Builder : AbstractCosmicItem.Builder() {
        internal var onUse: UseAction? = null

        fun onUse(action: UseAction) = apply {
            this.onUse = action
        }

        override fun create(props: Properties) = UsableItem(this, props)
    }
}