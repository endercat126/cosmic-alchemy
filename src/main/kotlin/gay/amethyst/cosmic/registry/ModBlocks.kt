package gay.amethyst.cosmic.registry

import gay.amethyst.cosmic.Cosmic
import gay.amethyst.cosmic.util.BlockHolder
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.neoforge.registries.DeferredRegister

object ModBlocks {

    val BLOCKS: MutableList<BlockHolder<*>> = mutableListOf()
    val REGISTRY: DeferredRegister.Blocks = DeferredRegister.createBlocks(Cosmic.ID)

    val METEORITE = copyBlock("meteorite", Blocks.SMOOTH_BASALT).cube().addBlockItem()

    fun <T : Block> registerBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
        props: BlockBehaviour.Properties = BlockBehaviour.Properties.of()
    ): BlockHolder<T> {
        val block = REGISTRY.registerBlock(
            name, factory, props
        )

        val holder = BlockHolder<T>(name, block)
        BLOCKS.add(holder)
        return holder
    }

    fun copyBlock(name: String, block: BlockBehaviour): BlockHolder<Block> =
        registerBlock(name, ::Block, BlockBehaviour.Properties.ofFullCopy(block))

    fun registerBlock(name: String, props: BlockBehaviour.Properties = BlockBehaviour.Properties.of()) =
        registerBlock(name, ::Block, props)

    fun <T : Block> BlockHolder<T>.addBlockItem(props: Item.Properties? = null) = apply {
        val itemProperties = props ?: Item.Properties()
        val item = ModItems.registerItem(id, { props -> BlockItem(block, props) }, itemProperties)

        this.addItem(item)
    }
}