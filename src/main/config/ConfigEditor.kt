package main.config

import src.main.FileUtil
import java.io.File

private const val DATA_CENTER_HINT = "DataCenterHint="

class ConfigEditor {
  companion object {
    fun changeConfigRoot(configDat: File, newLocation: String) {

    }

    fun changeRegion(configFile: File, region: String) {
      var configLines = FileUtil.readFromFile(configFile)
      val regionLineIndex = findRegionLine(configLines)
      configLines[regionLineIndex] = DATA_CENTER_HINT + region
      FileUtil.writeToFile(configFile, configLines)
    }

    private fun findRegionLine(configLines: Array<String>) = configLines.indexOfFirst {
      it.startsWith(DATA_CENTER_HINT) }
  }
}