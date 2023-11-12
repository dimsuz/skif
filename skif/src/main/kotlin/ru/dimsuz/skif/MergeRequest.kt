package ru.dimsuz.skif

data class MergeRequest(
  val iid: Iid
)

@JvmInline
value class Iid(val value: Long)
