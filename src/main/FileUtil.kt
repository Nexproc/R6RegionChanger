package src.main

import java.io.*

class FileUtil {
    companion object {
        fun writeToFile(file: File, lines: Array<String>) {
            var writer = BufferedWriter(FileWriter(file))
            writer.write(lines.joinToString(System.getProperty("line.separator")))
            writer.close()
        }

        fun readFromFile(file: File): Array<String> {
            return BufferedReader(FileReader(file)).readLines().toTypedArray()
        }
    }
}