package ru.dimsuz.skif

sealed interface Result {
  data class Success(val result: List<Note>) : Result
  data class PartialSuccess(val result: List<Note>, val errors: List<Throwable>) : Result
  data class Error(val errors: List<Throwable>) : Result
}
