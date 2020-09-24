import scala.util.Random

class WordList {
  val random = new Random
  def allWords: Seq[String] = {
    // TODO: Read from file a list of words
    Seq("apple", "orange", "dictionary", "homework", "project")
  }

  def getWord(difficulty: String): String = {
    // TODO: Filter words by difficulty
    allWords(random.nextInt(allWords.size))
  }
}
