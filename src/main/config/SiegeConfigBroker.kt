package main.config

import src.main.FileUtil
import java.io.File

class SiegeConfigBroker {
  companion object {
    @JvmStatic
    fun changeDataCenter(newDataCenter: String) {
      ConfigFinder.find().forEach { ConfigEditor.changeRegion(it, newDataCenter) }
    }

    @JvmStatic
    fun getValidDataCenters() = arrayOf(
        "default", //ping based
        "eus", // us east
        "cus", // us central
        "scu", // us south central
        "wus", // us west
        "sbr", // brazil south
        "neu", // europe north
        "weu", // europe west
        "eas", // asia east
        "sea", // asia south east
        "eau", // australia east
        "wja" // japan west
    )

    @JvmStatic
    fun setConfigDirectory(newDirectory: String) {
      FileUtil.writeToFile(ConfigFinder.getSiegeFolderLocationFile(), arrayOf(newDirectory))
    }

    @JvmStatic
    fun getConfigDirectory() = File(ConfigFinder.getRootConfigLocation())
  }
}