object Hangman extends App {
  // Description of the game
  println("Hangman game.\nHow to play:\nGuess letters until you can figure out the word. If it takes you too many guesses, you lose.")
  /**
   * Ask difficulty from user until the user chooses one of allowed options
   * @return difficulty level - either "easy" or "hard"
   */
  def getDifficultyFromUser(): String = {
    var difficulty = "unknown"
    do {
      println("\nChoose an easy word (write 'easy') or a hard word (write 'hard'):")
      difficulty = scala.io.StdIn.readLine()
    } while (difficulty != "easy" && difficulty != "hard")
    difficulty
  }
  /**
   * Checks if input line is a valid guess
   * Only 1 symbol can be written and only lowercase letter in one guess
   * @param line input from user
   * @return true if guess is valid
   */
  def isValidGuess(line: String) = line.length == 1 && ('a' to 'z').contains(line.head)

  val difficulty = getDifficultyFromUser()
  val wordToGuess = (new WordList).getWord(difficulty)
  var guesses = Set[Char]() // Set of guessed letters
  var wrongGuessesLeft = 10

  /**
   * Current guess word progress. Updated after every guess with correctly guessed letters.
   */
  var wordProgress = "_" * wordToGuess.length

  while (wrongGuessesLeft > 0 && wordToGuess != wordProgress) {
    print(s"${wordProgress.mkString(" ")} [Wrong guesses left: ${wrongGuessesLeft}] Letter : ")
    val line = scala.io.StdIn.readLine()
    if (!isValidGuess(line)) println(s"Invalid guess -> '${line}'!")
    else if (guesses.contains(line.head)) println("You guessed this letter already.")
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
    println(s"Congratulations, you guessed the word: $wordToGuess!")
  else {
    println(s"You lose! The word was: $wordToGuess! ")
    // Printing hangman
    println("___\n |\n |o\n/|\\\n/ \\")
  }
}
