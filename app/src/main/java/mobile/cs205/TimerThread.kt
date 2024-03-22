package mobile.cs205

fun main() {
    val sharedTime = SharedTime(10)
    // Start the combined timer thread
    val combinedTimerThread = CombinedTimerThread(sharedTime)
    combinedTimerThread.start()
}

data class SharedTime(@Volatile var time: Int)

class CombinedTimerThread(private val sharedTime: SharedTime) : Thread() {
    private var intervalProgress = 1000 // Set to 1 second initially
    private val initialTime = sharedTime.time

    override fun run() {
        var elapsed = 0

        while (sharedTime.time > 0) {
            sleep(100) // Update every 0.1 second
            elapsed += 100

            // Decrement the interval progress
            intervalProgress -= 100

            // Every second, decrement the shared time and reset the interval progress
            if (intervalProgress <= 0) {
                synchronized(sharedTime) {
                    sharedTime.time--
                    intervalProgress = 1000
                }
                // Interval update
                println("Interval Timer - Time remaining: ${sharedTime.time} seconds")
            }

            // Continuous update
            val progress = (elapsed.toFloat() / (initialTime * 1000) * 100).toInt()
            println("Continuous Timer - Time remaining: ${sharedTime.time} seconds - Progress: $progress%")

            // Update UI safely outside of the synchronized block if necessary
            // runOnUiThread { /* Update UI elements here with progress */ }
        }

        // When timer reaches 0, do something if necessary
        // runOnUiThread { /* Perform action on main thread */ }
    }
}
