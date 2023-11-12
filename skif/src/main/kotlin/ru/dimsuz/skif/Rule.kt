package ru.dimsuz.skif

import java.nio.file.Path

abstract class Rule {
  abstract suspend fun apply(gitlab: Gitlab)

  // needs to be synchronized properly because is accessed in warn/message/fail which usually are called
  // from within the "apply" method which is a suspend function => can potentially have various context switches
  @Volatile
  private var activeOutput: NoteCollector? = null

  internal suspend fun apply(gitlab: Gitlab, output: NoteCollector) {
    activeOutput = output
    apply(gitlab)
    activeOutput = null
  }

  /**
   * Adds a warning as a top-level comment to a merge request.
   * If multiple warnings will be posted, they will be joined in one comment in form of a table
   */
  fun warn(text: String) {
    requireActiveOutput()
  }

  /**
   * Adds an inline comment styled as a warning
   *
   * @param text message text, can be markdown or plain text
   * @param file file path, relative to project's repository root
   * @param line line number, 1-based, relative to the file contents
   * for the HEAD of the source branch of the merge request
   */
  fun warn(text: String, file: Path, line: Int) {
    requireActiveOutput()

  }

  /**
   * Adds an inline comment styled as a warning
   *
   * @param text message text, can be markdown or plain text
   * @param diff a diff object obtained from the [Gitlab] object
   * @param line line number, 1-based, relative to the diff text, for example the following diff
   * ```
   * +foo
   * -bar
   *  baz
   *  ```
   *  would have line 3 represent the line containing "baz"
   */
  fun warn(text: String, diff: Diff, line: Int) {
    requireActiveOutput()

  }

  private fun requireActiveOutput() {
    if (activeOutput == null) {
      throw RuleExecutionError("internal error: activeOutput is null")
    }
  }

  // TODO @dz @Rules add message() and fail()
}
