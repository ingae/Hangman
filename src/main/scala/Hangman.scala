object Hangman extends App {
  def getDifficultyFromUser(): String = {
    var difficulty = "unknown"
    do {
      println("Do you want an easy word or a hard word? [easy/hard]")
      difficulty = scala.io.StdIn.readLine()
    } while (difficulty != "easy" && difficulty != "hard")
    difficulty
  }

  def isValidGuess(line: String) = line.length == 1 && ('a' to 'z').contains(line.head)

  val difficulty = getDifficultyFromUser()
  val wordToGuess = (new WordList).getWord(difficulty)
  var badGuessCount = 5

  var wordProgress = "_" * wordToGuess.length

  while (badGuessCount > 0 && wordToGuess != wordProgress) {
    print(s"${wordProgress.mkString(" ")} [Guesses left: ${badGuessCount}] Letter : ")
    val line = scala.io.StdIn.readLine()
    if (!isValidGuess(line)) println(s"Invalid guess -> '${line}'!")
    else if (wordProgress.contains(line.head)) println("You already guessed this letter.")
    else {
      val letter: Char = line.head
      if (wordToGuess.contains(letter)) {
        // Letter is part of word -> mark it in wordProgress
        wordProgress = wordToGuess.zipWithIndex.map { case (c, i) => if (wordProgress(i) == '_' && c != letter) '_' else c }.mkString("")
      } else {
        // Letter is not found in word -> increment badGuessCount
        badGuessCount -= 1
      }
    }
  }
  if (wordToGuess == wordProgress)
    println(s"Congratulations you correctly guessed the word: $wordToGuess!")
  else
    println(s"Too bad, the word was: $wordToGuess")
}
