package dev.emortal.munchcrunch.database

import dev.emortal.munchcrunch.ConfigurationHelper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File
import java.util.*

class DatabaseUtil() {
    var mainDB: SQLStorage? = null
    init {
        Config.config = initConfigFile(File("./credentials.json"), Config())
        mainDB = SQLStorage(Config.config)
        mainDB!!.connect()
    }

    private inline fun <reified T : Any> initConfigFile(file: File, emptyObj: T): T {
        return if (file.exists()) ConfigurationHelper.format.decodeFromString(file.readText()) else run {
            file.createNewFile()
            file.writeText(ConfigurationHelper.format.encodeToString(emptyObj))
            emptyObj
        }
    }
}