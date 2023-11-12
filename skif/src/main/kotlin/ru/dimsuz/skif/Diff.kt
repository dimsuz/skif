package ru.dimsuz.skif

import java.nio.file.Path

data class Diff(
  val oldFile: Path,
  val newFile: Path,
  val text: String,
)
