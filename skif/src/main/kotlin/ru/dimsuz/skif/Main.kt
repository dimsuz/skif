package ru.dimsuz.skif

import java.lang.Exception
import java.util.concurrent.CancellationException

internal suspend fun applyRules(rules: List<Rule>): Result {
  val gitlab = Gitlab()
  val notes = mutableListOf<Note>()
  val collector = NoteCollector { notes.add(it) }
  val errors = mutableListOf<Throwable>()
  rules.forEach { rule ->
    try {
      rule.apply(gitlab, collector)
    } catch (e: Exception) {
      if (e is CancellationException) {
        throw e
      }
      errors.add(e)
    }
  }
  return when {
    errors.isEmpty() -> Result.Success(notes)
    notes.isNotEmpty() -> Result.PartialSuccess(notes, errors)
    else -> Result.Error(errors)
  }
}

fun main(args: Array<String>) {
  println("Hello World!")

  // Try adding program arguments via Run/Debug configuration.
  // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
  println("Program arguments: ${args.joinToString()}")
}
