package gay.amethyst.cosmic.util

import net.minecraft.world.level.ItemLike

sealed class LootTable {
    object DropNothing : LootTable()
    object DropSelf : LootTable()
    data class DropItem(
        val item: ItemLike,
        val count: Int = 1
    ) : LootTable()
    data class DropRange(
        val item: ItemLike,
        val min: Int,
        val max: Int,
        val rolls: Int = 1
    ) : LootTable()
    data class DropMultiple(
        val drops: List<LootTable>
    ) : LootTable()
    data class DropChance(
        val chance: Float,
        val roll: LootTable,
        val fallback: LootTable = DropNothing
    )
}