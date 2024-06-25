package com.priyanshu.reciipiie.domain.models

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)