package ru.dimsuz.skif

import java.lang.RuntimeException

class RuleExecutionError(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
