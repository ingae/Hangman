import scala.util.Random

class WordList {
  val random = new Random

  def readWords(fileName: String): Seq[String] = {
    val source = io.Source.fromFile(fileName)
    val words = source.getLines.toSeq
    source.close()
    words
  }

  def getWord(difficulty: String): String = {
    val allWords: Seq[String] = difficulty match {
      case "easy" => readWords("easy-words.txt")
      case "hard" => readWords("hard-words.txt")
    }
    allWords(random.nextInt(allWords.size))
  }
}
