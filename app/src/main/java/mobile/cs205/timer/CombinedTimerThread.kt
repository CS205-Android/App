package mobile.cs205.timer

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