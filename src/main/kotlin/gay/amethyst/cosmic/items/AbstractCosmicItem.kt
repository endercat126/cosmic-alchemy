package gay.amethyst.cosmic.items

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

open class AbstractCosmicItem(
    open val builder: Builder,
    props: Properties
) : Item(props) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)
        for (lore in builder.loreLines) {
            tooltipComponents.add(lore)
        }
    }

    abstract class Builder {
        val loreLines = mutableListOf<Component>()

        fun addLore(text: Component) = apply { loreLines.add(text) }
        fun addLore(text: String, colour: Int) = apply {
            addLore(Component.literal(text).withColor(colour))
        }

        fun addLore(text: String, style: Style) = apply {
            addLore(Component.literal(text).withStyle(style))
        }

        fun addLore(text: String) = apply { addLore(Component.literal(text)) }

        abstract fun create(props: Properties): AbstractCosmicItem
    }
}