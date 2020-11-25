package com.nasalevich.androidintermidiate.utils

interface Mapper<I, O> {
    fun map(input: I): O
}
