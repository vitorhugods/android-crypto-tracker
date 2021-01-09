package xyz.schwaab.crypto.base

import org.koin.core.module.Module
import xyz.schwaab.crypto.base.util.utilsModule

object BaseModules {
    val list: List<Module> = listOf(utilsModule)
}