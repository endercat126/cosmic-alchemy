package gay.amethyst.cosmic.util

import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.DeferredBlock
import java.util.function.Supplier

data class BlockHolder<T : Block>(
    val id: String, val blockHolder: DeferredBlock<T>, private var itemSupplier: Supplier<Item>? = null
) : Supplier<T> by blockHolder {
    val block: T
        get() = blockHolder.get()
    val item
        get() = itemSupplier?.get()

    fun addItem(supplier: Supplier<Item>) = apply { itemSupplier = supplier }

    var lootTable: LootTable = LootTable.DropSelf
    var modelType: BlockModelType = BlockModelType.None

    //    Loot tables
    fun noDrops() = apply { lootTable = LootTable.DropNothing }
    fun dropSelf() = apply { lootTable = LootTable.DropSelf }
    fun drops(item: ItemLike) = apply { lootTable = LootTable.DropItem(item) }
    fun drops(item: ItemLike, count: Int) = apply { lootTable = LootTable.DropItem(item, count) }
    fun drops(item: ItemLike, min: Int, max: Int) = apply { lootTable = LootTable.DropRange(item, min, max) }
    fun loot(loot: LootTable) = apply { lootTable = loot }

    //    Block Models
    fun cube() = apply { modelType = BlockModelType.CubeAll }
    fun noModel() = apply { modelType = BlockModelType.NoModel }
}