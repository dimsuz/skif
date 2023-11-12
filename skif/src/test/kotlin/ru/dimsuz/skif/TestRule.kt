package ru.dimsuz.skif

class TestRule(
  private val body: suspend (Gitlab) -> Unit
) : Rule() {
  override suspend fun apply(gitlab: Gitlab) {
    body(gitlab)
  }
}
