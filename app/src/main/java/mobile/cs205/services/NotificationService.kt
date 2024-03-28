package mobile.cs205.services

/**
 * Defines the interface for NotificationServiceImpl.
 * */
interface NotificationService {
    /**
     * This function is called to show a notification in the notification centre of the user
     * */
    fun showNotification(
        contentTitle: String,
        contentText: String
    )
}