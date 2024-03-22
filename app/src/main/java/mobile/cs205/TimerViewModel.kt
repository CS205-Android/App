package mobile.cs205

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mobile.cs205.CombinedTimerThread
import mobile.cs205.SharedTime

class TimerViewModel : ViewModel() {
    private val _sharedTime = MutableStateFlow(10) // Initial time
    val sharedTime: StateFlow<Int> = _sharedTime
    private var timerThread: CombinedTimerThread? = null

    init {
        // Initialize and start the timer thread
        val sharedTimeObject = SharedTime(10)
        timerThread = CombinedTimerThread(sharedTimeObject) {
            // Update the time and notify observers
            _sharedTime.value = it
        }
        timerThread?.start()
    }

    override fun onCleared() {
        super.onCleared()
        timerThread?.interrupt() // Stop the thread when ViewModel is cleared
    }
}

data class SharedTime(@Volatile var time: Int)

class CombinedTimerThread(
    private val sharedTime: SharedTime,
    private val onTimeUpdate: (Int) -> Unit
) : Thread() {
    override fun run() {
        while (sharedTime.time > 0) {
            sleep(1000) // Simulate a one-second tick
            synchronized(sharedTime) {
                sharedTime.time--
            }
            onTimeUpdate(sharedTime.time)
        }
    }
}
