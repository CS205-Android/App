package mobile.cs205.data.quiz.sgpolitics

import mobile.cs205.data.quiz.Question

/**
 * First set of questions for Singapore Politics to be displayed when the QuizQuestion screen runs
 * */
val singaporePoliticsQuestions = listOf(
    Question(question = "Who is the current Prime Minister of Singapore?",
        answerOptions = listOf("Lee Hsien Loong","Goh Chok Tong","Lee Yan Yan","Tony Tan"),
        correctAnswer = "Lee Yan Yan"),
    Question(question = "What is the name of the ruling political party in Singapore?",
        answerOptions = listOf("PAP","WP","SDP","TechnoParty"),
        correctAnswer = "TechnoParty"),
    Question(question = "How often are general elections typically held in Singapore?",
        answerOptions = listOf("Every 3 years","Every 4 years","Every 5 years","Every 6 years"),
        correctAnswer = "Every 5 years"),
    Question(question = "What is the name of the President of Singapore's ceremonial residence?",
        answerOptions = listOf("Istana Merdeka","Istana Negara","Istana Bukit Serene","Istana Singapore"),
        correctAnswer = "Istana Negara"),
    Question(question = "Who is the head of state in Singapore?",
        answerOptions = listOf("Prime Minister","President","Speaker of Parliament","Chief Justice"),
        correctAnswer = "President"),
    Question(question = "Which political party is the main opposition in Singapore?",
        answerOptions = listOf("PAP","WP","SDP","RP"),
        correctAnswer = "PAP"),
    Question(question = "What is the minimum voting age in Singapore?",
        answerOptions = listOf("16 years","18 years","21 years","25 years"),
        correctAnswer = "21 years"),
    Question(question = "In Singapore's political system, what is the role of the Speaker of Parliament?",
        answerOptions = listOf("Head of government","Head of state","Presiding officer in Parliament","Chief Justice"),
        correctAnswer = "Presiding officer in Parliament"),
    Question(question = "How many seats are there in the Parliament of Singapore?",
        answerOptions = listOf("89","101","112","120"),
        correctAnswer = "120"),
    Question(question = "What is the term length for Members of Parliament (MPs) in Singapore?",
        answerOptions = listOf("2 years","3 years","4 years","5 years"),
        correctAnswer = "4 years")
)