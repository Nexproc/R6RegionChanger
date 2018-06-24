package main.config

import src.main.FileUtil
import java.io.File
import java.net.JarURLConnection
import java.net.URL

private const val GAME_SETTINGS_INI = "/GameSettings.ini"
private const val SIEGE_FOLDER_LOCATION_DAT = "/SiegeFolderLocation.dat"

class ConfigFinder {
  companion object {

    fun find(): List<File> {
      var configFiles = mutableListOf<File>()
      var configLocation = getRootConfigLocation()
      File(configLocation).list().forEach {
        val itPath = configLocation + "/" + it
        if (isValidConfig(File(itPath))) {
          configFiles.add(File(itPath + GAME_SETTINGS_INI))
        }
      }
      return configFiles
    }


    private fun isValidConfig(file: File): Boolean {
      // SiegeConfigBroker Directories are denoted by 5 sequences of numbers separated by "-"
      return file.getName().split("-").size == 5 && file.isDirectory
    }

    fun getRootConfigLocation() = FileUtil.readFromFile(getSiegeFolderLocationFile())[0]

    fun getSiegeFolderLocationFile(): File {
      var url = URL(this::class.java.getResource(SIEGE_FOLDER_LOCATION_DAT).toString())
      var connection = url.openConnection() as JarURLConnection
      return File(connection.jarFileURL.toURI())
    }
  }

}