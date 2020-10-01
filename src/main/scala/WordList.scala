import scala.util.Random

class WordList {
  val random = new Random

  /**
   * Reads lines (words) from file
   * @param fileName Name of the file to read from
   * @return List of words
   */
  def readWords(fileName: String): Seq[String] = {
    val source = io.Source.fromFile(fileName)
    val words = source.getLines.toSeq
    source.close()
    words
  }

  /**
   * Get random word from the list of words from specified difficulty
   * @param difficulty either "easy" or "hard"
   * @return single word from chosen list
   */
  def getWord(difficulty: String): String = {
    val allWords: Seq[String] = difficulty match {
      case "easy" => readWords("easy-words.txt")
      case "hard" => readWords("hard-words.txt")
    }
    allWords(random.nextInt(allWords.size))
  }
}
