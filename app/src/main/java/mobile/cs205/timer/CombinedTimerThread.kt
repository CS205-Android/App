package mobile.cs205.timer

/**
 * The CombinedTimerThreads extends the Thread class that will act as our worker thread in our application.
 * It handles the interval based updating of the timer value and synchronous update of the progress bar
 * @param totalTimeMillis: Defines the duration of the timer, generally, always set to 10000
 * @param onProgressUpdate: Receives a state update function to update the current status of the progress bar and timer
 * */
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