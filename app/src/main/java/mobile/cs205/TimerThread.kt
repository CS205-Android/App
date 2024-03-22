package mobile.cs205

fun main() {
    // Define what happens on time update. For this example, it simply prints the time.
    val onUpdate = { time: Int ->
        println("Time updated: $time seconds")
    }
    val sharedTime = SharedTime(10, onUpdate)
    val combinedTimerThread = CombinedTimerThread(sharedTime)
    combinedTimerThread.start()
}

data class SharedTime(var time: Int, var onUpdate: (Int) -> Unit) // Added an onUpdate lambda to call when updating the time

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
                sharedTime.time--
                intervalProgress = 1000
                sharedTime.onUpdate(sharedTime.time) // Notify about the update

                // Interval update
                println("Interval Timer - Time remaining: ${sharedTime.time} seconds")
            }

            // Continuous update
            val progress = (elapsed.toFloat() / (initialTime * 1000) * 100).toInt()
            println("Continuous Timer - Time remaining: ${sharedTime.time} seconds - Progress: $progress%")
        }
    }
}
