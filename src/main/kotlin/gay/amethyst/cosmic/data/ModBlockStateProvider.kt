package gay.amethyst.cosmic.data

import gay.amethyst.cosmic.Cosmic
import gay.amethyst.cosmic.registry.ModBlocks
import gay.amethyst.cosmic.util.BlockModelType
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.client.model.generators.ConfiguredModel
import net.neoforged.neoforge.client.model.generators.ModelFile
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModBlockStateProvider(
    output: PackOutput, exFileHelper: ExistingFileHelper
) : BlockStateProvider(output, Cosmic.ID, exFileHelper) {
    override fun registerStatesAndModels() {
        for (block in ModBlocks.BLOCKS) {
            when (block.modelType) {
                BlockModelType.CubeAll -> simpleBlock(block.block)
                BlockModelType.NoModel -> {
                    getVariantBuilder(block.block).partialState()
                        .setModels(ConfiguredModel(ModelFile.UncheckedModelFile(modLoc("block/${block.id}"))))
                }

                else -> {}
            }

            if (block.item != null) {
                when (block.modelType) {
                    BlockModelType.CubeAll -> simpleBlockItem(block.block, cubeAll(block.block))
                    BlockModelType.NoModel -> {
                        simpleBlockItem(block.block, ModelFile.UncheckedModelFile(modLoc("block/${block.id}")))
                    }
                    else -> {}
                }
            }
        }
    }
}