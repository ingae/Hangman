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
  var guesses = Set[Char]()
  var wrongGuessesLeft = 10

  var wordProgress = "_" * wordToGuess.length

  while (wrongGuessesLeft > 0 && wordToGuess != wordProgress) {
    print(s"${wordProgress.mkString(" ")} [Wrong guesses left: ${wrongGuessesLeft}] Letter : ")
    val line = scala.io.StdIn.readLine()
    if (!isValidGuess(line)) println(s"Invalid guess -> '${line}'!")
    else if (guesses.contains(line.head)) println("You already guessed this letter.")
    else {
      val letter: Char = line.head
      guesses += letter
      if (wordToGuess.contains(letter)) {
        // Letter is part of word -> mark it in wordProgress
        wordProgress = wordToGuess.map(c => if (guesses.contains(c)) c else "_").mkString("")
      } else {
        // Letter is not found in word -> increment badGuessCount
        wrongGuessesLeft -= 1
      }
    }
  }
  if (wordToGuess == wordProgress)
    println(s"Congratulations you correctly guessed the word: $wordToGuess!")
  else
    println(s"Too bad! The word was: $wordToGuess")
}
