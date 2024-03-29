package mobile.cs205.data.quiz.sglaw

import mobile.cs205.data.quiz.Question

/**
 * First set of questions for Singapore Law to be displayed when the QuizQuestion screen runs
 * */
val singaporeLawQuestions = listOf(
    Question(question = "What is the minimum age for criminal responsibility in Singapore?",
        answerOptions = listOf("7 years old","8 years old","9 years old","10 years old"),
        correctAnswer = "10 years old"),
    Question(question = "Which court in Singapore handles civil claims up to \$250,000?",
        answerOptions = listOf("State Courts","High Court","District Court","Supreme Court"),
        correctAnswer = "State Courts"),
    Question(question = "What is the maximum penalty for drug trafficking in Singapore?",
        answerOptions = listOf("Life imprisonment","20 years imprisonment","Death penalty","15 years imprisonment"),
        correctAnswer = "Death penalty"),
    Question(question = "Which law governs the registration and regulation of companies in Singapore?",
        answerOptions = listOf("Business Act","Corporation Law","Companies Act","Business Entities Act"),
        correctAnswer = "Companies Act"),
    Question(question = "What is the legal age for consuming alcohol in Singapore?",
        answerOptions = listOf("18 years","19 years","20 years","21 years"),
        correctAnswer = "18 years"),
    Question(question = "Who is the final court of appeal in Singapore?",
        answerOptions = listOf("High Court","Court of Appeal","Supreme Court","State Courts"),
        correctAnswer = "Court of Appeal"),
    Question(question = "Under Singapore law, what is the maximum notice period for termination of employment without cause?",
        answerOptions = listOf("1 month","2 weeks","1 week","3 months"),
        correctAnswer = "3 months"),
    Question(question = "Which government agency is responsible for enforcing competition laws in Singapore?",
        answerOptions = listOf("FCA","CCCS","MHA","MOM"),
        correctAnswer = "CCCS"),
    Question(question = "What is the legal blood alcohol limit for drivers in Singapore?",
        answerOptions = listOf("0.08%","0.05%","0.02%","0.00%"),
        correctAnswer = "0.00%"),
    Question(question = "In Singapore, what is the maximum penalty for littering in public places?",
        answerOptions = listOf("\$100 fine","\$500 fine","\$1,000 fine","\$2,000 fine"),
        correctAnswer = "\$1,000 fine")
)