package com.neeraj.wordapp

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel (private val repository : WordRepository) : ViewModel() {


    val allWords : LiveData<List<Word>> = repository.allWords.asLiveData()


    fun insertWord(word: Word) = viewModelScope.launch {
        repository.insertWord(word)
    }

    fun deleteWord (word: Word) = viewModelScope.launch {
        repository.deleteWord()
    }


     class WordViewModelFactory (private val repository: WordRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel (repository) as T
            }
                throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}