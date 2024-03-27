package mobile.cs205

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    fun startTimer() {
        if (timerThread == null || !timerThread!!.isAlive) {
            _sharedTime.value = initialTime
            timerThread = CombinedTimerThread(10000) { timeLeft, progress ->
                viewModelScope.launch {
                    _sharedTime.value = timeLeft
                    _progress.value = progress // Assume _progress is a MutableStateFlow<Float> representing progress
                    if (timeLeft <= 0) {
                        _isTimerRunning.value = false
                    }
                }
            }
            timerThread?.start()
            _isTimerRunning.value = true
        }
    }

    fun stopTimer() {
        timerThread?.interrupt()
        timerThread = null
        _isTimerRunning.value = false
    }

    fun resetTimer() {
        stopTimer() // First, stop the timer if it's running.
        _sharedTime.value = initialTime // Reset the time to the initial value.
        _progress.value = 0f // Reset progress to 0f, ensuring the progress bar resets.
        _isTimerRunning.value = false // Indicate that the timer is not running.
    }

    override fun onCleared() {
        super.onCleared()
        timerThread?.interrupt()
    }
}

data class SharedTime(@Volatile var time: Int)

class CombinedTimerThread(
    private val totalTimeMillis: Long = 10000, // 10 seconds in milliseconds
    private val onProgressUpdate: (Int, Float) -> Unit
) : Thread() {
    override fun run() {
        var elapsedTime = 0L
        try {
            while (!isInterrupted && elapsedTime <= totalTimeMillis) {
                sleep(100) // Update every 100 milliseconds
                elapsedTime += 100
                val timeLeft = ((totalTimeMillis - elapsedTime) / 1000).toInt()
                val progress = elapsedTime.toFloat() / totalTimeMillis
                onProgressUpdate(timeLeft, progress)
            }
        } catch (e: InterruptedException) {
            currentThread().interrupt() // Preserve interruption status
        }
    }
}

