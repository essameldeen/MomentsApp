package com.example.momentsapp.presentation.new_moment

sealed class CreateNewMomentViewState
{
    object SuccessfulCreationState: CreateNewMomentViewState()
    object LoadingCreationState: CreateNewMomentViewState()
    class ErrorCreationState(val error: Throwable): CreateNewMomentViewState()
}