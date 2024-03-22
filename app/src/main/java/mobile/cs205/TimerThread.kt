package mobile.cs205

fun main() {
    // Start the interval timer thread
    val intervalTimerThread = IntervalTimerThread(10)
    intervalTimerThread.start()

    // Start the continuous timer thread
    val continuousTimerThread = ContinuousTimerThread(10)
    continuousTimerThread.start()
}

class IntervalTimerThread(private val totalTime: Int) : Thread() {
    private var timePassed = 0

    override fun run() {
        while (timePassed <= totalTime) {
            val timeRemaining = totalTime - timePassed

            synchronized(this) {
                // Calculate progress as a percentage
                val progress = (timePassed.toFloat() / totalTime * 100).toInt()
                println("Interval Timer - Time remaining: $timeRemaining seconds - Progress: $progress%")
            }

            // Pretend this is UI update
            // runOnUiThread { /* Update UI elements here */ }

            if (timePassed == totalTime) {
                break
            }

            try {
                sleep(1000) // Wait for 1 second
            } catch (ex: InterruptedException) {
                println(ex.message)
                break
            }

            timePassed++
        }
    }
}

class ContinuousTimerThread(private val totalTime: Int) : Thread() {
    private var timePassedMillis = 0

    override fun run() {
        while (timePassedMillis <= totalTime * 1000) {
            val timeRemaining = totalTime - timePassedMillis / 1000

            synchronized(this) {
                // Calculate continuous progress
                val progress = (timePassedMillis.toFloat() / (totalTime * 1000) * 100).toInt()
                println("Continuous Timer - Time remaining: $timeRemaining seconds - Progress: $progress%")
            }

            // Pretend this is UI update
            // runOnUiThread { /* Update UI elements here */ }

            if (timePassedMillis == totalTime * 1000) {
                break
            }

            try {
                sleep(100) // Update every 0.1 second
            } catch (ex: InterruptedException) {
                println(ex.message)
                break
            }

            timePassedMillis += 100
        }
    }
}
