package mobile.cs205.data.quiz

import mobile.cs205.data.quiz.sghistory.singaporeHistory1Questions
import mobile.cs205.data.quiz.sghistory.singaporeHistory2Questions
import mobile.cs205.data.quiz.sghistory.singaporeHistory3Questions
import mobile.cs205.data.quiz.sghistory.singaporeHistory4Questions
import mobile.cs205.data.quiz.sglang.singlishQuestions
import mobile.cs205.data.quiz.sgtech.singaporeTechnologicalAdvancementsQuestions

data class Question(val question : String, val answerOptions : List<String>, val correctAnswer : String)
data class Topic(val topicName : String, val description : String, val questions : List<Question>)

val topicNames = listOf(
    "Singapore History #1",
    "Singapore History #2",
    "Singapore History #3",
    "Singapore History #4",
    "Singapore Technological Advancements",
    "Singlish",
    "Singapore Laws",
    "Singapore Politics"
)

val topics = listOf(
    Topic(topicName = "Singapore History #1",
        description = "This quiz covers the pre-British portion of Singapore’s History!",
        questions = singaporeHistory1Questions
    ),
    Topic(topicName = "Singapore History #2",
        description = "This quiz covers the British colonization portion of Singapore’s History!",
        questions = singaporeHistory2Questions
    ),
    Topic(topicName = "Singapore History #3",
        description = "This quiz covers the World War II period of Singapore’s History!",
        questions = singaporeHistory3Questions
    ),
    Topic(topicName = "Singapore History #4",
        description = "This quiz covers the independence of Singapore Agreement 1965!",
        questions = singaporeHistory4Questions
    ),
    Topic(topicName = "Singapore Technological Advancements",
        description = "This quiz covers some of the technological advancements in our recent times",
        questions = singaporeTechnologicalAdvancementsQuestions
    ),
    Topic(topicName = "Singlish",
        description = "Lar lor leh meh, do you know all the Singlish slangs? Find out in this quiz!",
        questions = singlishQuestions
    ),
    Topic(topicName = "Singapore Laws",
        description = "Did you know about some laws that are unique to Singapore?",
        questions = listOf()),
    Topic(topicName = "Singapore Politics",
        description = "How much did you know about politics in Singapore? Guess we are about to find out now!",
        questions = listOf())
)

