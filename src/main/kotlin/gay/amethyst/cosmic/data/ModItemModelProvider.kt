package gay.amethyst.cosmic.data

import gay.amethyst.cosmic.Cosmic
import gay.amethyst.cosmic.registry.ModBlocks
import gay.amethyst.cosmic.registry.ModItems
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.function.Supplier

class ModItemModelProvider(
    output: PackOutput, existingFileHelper: ExistingFileHelper
) : ItemModelProvider(output, Cosmic.ID, existingFileHelper) {
    override fun registerModels() {
        basicItem(ModItems.MARKIPLIER)
    }

    fun basicItem(sup: Supplier<Item>): ItemModelBuilder = basicItem(sup.get())
}