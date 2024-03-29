package mobile.cs205.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Creates a global ViewModel to maintain the states of the timer outside of the composable file
 * It was design as such partially due to the fact that members who are in-charge of creating the threading functions does not have the actual timer composables at their dispose during creation
 * The modularity of this ViewModel allows them to design the threading model of our application while the UI is still in progress
 *
 * TimerViewModel controls the lifecyle of the CombinedTimerThread based on the user input and, synchronous and asynchronous events happening
 * @return A Timer which extends the ViewModel class
 * */
class TimerViewModel : ViewModel() {
    private val _sharedTime = MutableStateFlow(10) // Initial time
    val sharedTime: StateFlow<Int> = _sharedTime

    // Declare _progress here
    private val _progress = MutableStateFlow(0f) // Progress starts at 0
    val progress: StateFlow<Float> = _progress

    private var timerThread: CombinedTimerThread? = null
    private val initialTime = 10
    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning

    // Starts the worker thread
    fun startTimer() {
        if (timerThread == null || !timerThread!!.isAlive) {
            _sharedTime.value = initialTime
            timerThread = CombinedTimerThread(10000) { timeLeft, progress ->
                viewModelScope.launch {
                    _sharedTime.value = timeLeft
                    _progress.value =
                        progress // Assume _progress is a MutableStateFlow<Float> representing progress
                    if (timeLeft <= 0) {
                        _isTimerRunning.value = false
                    }
                }
            }
            timerThread?.start()
            _isTimerRunning.value = true
        }
    }

    // Stops the worker thread
    fun stopTimer() {
        timerThread?.interrupt()
        timerThread = null
        _isTimerRunning.value = false
    }

    // Resets the worker thread to start form the beginning
    fun resetTimer() {
        stopTimer() // First, stop the timer if it's running.
        _sharedTime.value = initialTime // Reset the time to the initial value.
        _progress.value = 0f // Reset progress to 0f, ensuring the progress bar resets.
        _isTimerRunning.value = false // Indicate that the timer is not running.
    }

    // Removes the worker thread when it is no longer in use (This happens when the quiz finishes or the app is killed
    override fun onCleared() {
        super.onCleared()
        timerThread?.interrupt()
    }

    // After a certain duration (specified by delayMillis), execute a desired function (specified by action as a lambda)
    fun launchCoroutine(delayMillis: Long, action: () -> Unit) {
        viewModelScope.launch {
            delay(delayMillis)
            action()
        }
    }
}

