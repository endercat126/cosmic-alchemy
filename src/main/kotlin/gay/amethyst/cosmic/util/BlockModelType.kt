package gay.amethyst.cosmic.util

sealed class BlockModelType {
    object None : BlockModelType()
    object NoModel : BlockModelType()
    object CubeAll : BlockModelType()
}