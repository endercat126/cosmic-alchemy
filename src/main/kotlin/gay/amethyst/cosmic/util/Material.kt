package gay.amethyst.cosmic.util

import gay.amethyst.cosmic.registry.ModMaterials

class Material {
    var name: String = ""

    init {
        ModMaterials.MATERIALS.add(this)
    }
}