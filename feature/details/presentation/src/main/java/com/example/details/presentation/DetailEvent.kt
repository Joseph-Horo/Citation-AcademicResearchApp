package com.example.details.presentation

sealed class DetailEvent {
    object Refresh: DetailEvent()
}