package mobile.cs205

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerViewModel : ViewModel() {
    private val _sharedTime = MutableStateFlow(10) // Initial time
    val sharedTime: StateFlow<Int> = _sharedTime
    private var timerThread: CombinedTimerThread? = null
    val initialTime = 10
    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning

    fun startTimer() {
        if (timerThread == null || !timerThread!!.isAlive) {
            val sharedTimeObject = SharedTime(initialTime)
            timerThread = CombinedTimerThread(sharedTimeObject) {
                _sharedTime.value = it
                if (it <= 0) {
                    _isTimerRunning.value = false
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
        stopTimer()
        _sharedTime.value = initialTime
    }

    override fun onCleared() {
        super.onCleared()
        timerThread?.interrupt()
    }
}

data class SharedTime(@Volatile var time: Int)

class CombinedTimerThread(
    private val sharedTime: SharedTime,
    private val onTimeUpdate: (Int) -> Unit
) : Thread() {
    override fun run() {
        try {
            while (!isInterrupted && sharedTime.time > 0) {
                sleep(1000)
                synchronized(sharedTime) {
                    sharedTime.time--
                }
                onTimeUpdate(sharedTime.time)
            }
        } catch (e: InterruptedException) {
            // Handle the case where the thread is interrupted
            Thread.currentThread().interrupt() // Preserve interruption status
        }
    }
}

