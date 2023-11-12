package ru.dimsuz.skif

sealed interface Result {
  data class Success(val result: List<Note>) : Result
  data class Error(val text: String, val cause: Throwable? = null) : Result
}
