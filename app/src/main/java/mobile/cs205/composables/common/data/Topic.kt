package mobile.cs205.composables.common.data

data class Question(val question : String, val answerOptions : List<String>, val correctAnswer : String)
data class Topic(val topicName : String, val description : String, val questions : List<Question>)

val standardEmptyQuestionBank = listOf(
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = ""),
    Question(question = "",
        answerOptions = listOf("","","",""),
        correctAnswer = "")
)

val singaporeHistory1Questions = listOf(
    Question(question = "What was the name of Singapore before the arrival of the British?",
        answerOptions = listOf("Temasek","Pulau Ujong","Singapura","Malayu"),
        correctAnswer = "Temasek"),
    Question(question = "Who was the legendary founder of ancient Singapore according to Malay folklore?",
        answerOptions = listOf("Sang Nila Utama","Parameswara","Hang Tuah","Sultan Mahmud Shah"),
        correctAnswer = "Sang Nila Utama"),
    Question(question = "What was the primary economic activity of ancient Singapore before the arrival of the British?",
        answerOptions = listOf("Trading","Fishing","Farming","Mining"),
        correctAnswer = "Trading"),
    Question(question = "Which ancient kingdom exerted influence over Singapore before the arrival of the British?",
        answerOptions = listOf("Srivijaya","Majapahit","Chola Empire","Malacca Sultanate"),
        correctAnswer = "Srivijaya"),
    Question(question = "What was the name of the local indigenous people who inhabited Singapore before the arrival of the British?",
        answerOptions = listOf("Orang Laut","Dayak","Bugis","Batak"),
        correctAnswer = "Orang Laut"),
    Question(question = "Which ancient empire established a trading port in Singapore in the 14th century?",
        answerOptions = listOf("Majapahit","Ming Dynasty","Chola Empire","Srivijaya"),
        correctAnswer = "Majapahit"),
    Question(question = "What was the main reason for the decline of ancient Singapore as a trading port before the arrival of the British?",
        answerOptions = listOf("Political instability","Pirate attacks","Natural disasters","Trade route shifts"),
        correctAnswer = "Political instability"),
    Question(question = "What was the name of the treaty signed between the British and the Sultan of Johor to establish a trading post in Singapore?",
        answerOptions = listOf("Treaty of Friendship and Alliance","Singapore Agreement 1819","Johor-British Treaty of 1824","Treaty of Temasek"),
        correctAnswer = "Treaty of Friendship and Alliance"),
    Question(question = "Which Chinese explorer is believed to have visited Singapore during the early 15th century?",
        answerOptions = listOf("Zheng He","Cheng Ho","Xu Fu","Wang Dayuan"),
        correctAnswer = "Zheng He"),
    Question(question = "What was the name of the Sultan of Johor who signed the treaty with the British to establish a trading post in Singapore in 1819?",
        answerOptions = listOf("Sultan Hussein Shah","Sultan Mahmud Shah","Sultan Abdul Jalil Shah","Sultan Abu Bakar"),
        correctAnswer = "Sultan Hussein Shah")
)

val singaporeHistory2Questions = listOf(
    Question(question = "When did the British establish Singapore as a trading post?",
        answerOptions = listOf("1819","1835","1867","1901"),
        correctAnswer = "1819"),
    Question(question = "Who is often credited with founding modern Singapore as a British colony?",
        answerOptions = listOf("Stamford Raffles","Adolf Hitler","John Crawfurd","William Farquhar"),
        correctAnswer = "Stamford Raffles"),
    Question(question = "What was the main commodity traded in Singapore during British rule?",
        answerOptions = listOf("Spices","Rubber","Tea","Tin"),
        correctAnswer = "Spices"),
    Question(question = "What event led to the fall of Singapore to the Japanese during World War II?",
        answerOptions = listOf("Battle of Singapore","Bombing of Singapore","Battle of Britain","Pearl Harbor attack"),
        correctAnswer = "Battle of Singapore"),
    Question(question = "What was the status of Singapore after the end of World War II?",
        answerOptions = listOf("Returned to British rule","Became independent","Annexed by Malaysia","Became a Japanese territory"),
        correctAnswer = "Returned to British rule"),
    Question(question = "Which treaty officially transferred Singapore to British control in 1824?",
        answerOptions = listOf("Anglo-Dutch Treaty","Treaty of Nanjing","Treaty of Paris","Treaty of London"),
        correctAnswer = "Anglo-Dutch Treaty"),
    Question(question = "What was the term used to describe British colonial rule in Singapore?",
        answerOptions = listOf("Crown colony","Imperial administration","Raj","Dominion"),
        correctAnswer = "Crown colony"),
    Question(question = "Who was the first British Resident of Singapore?",
        answerOptions = listOf("William Farquhar","John Crawfurd","Lee Hsien Loong","Sir Hugh Low"),
        correctAnswer = "William Farquhar"),
    Question(question = "What was the main reason for the British to establish a presence in Singapore?",
        answerOptions = listOf("Trading opportunities","Military expansion","Religious missions","Agricultural development"),
        correctAnswer = "Trading opportunities"),
    Question(question = "What significant infrastructure did the British build in Singapore during their rule?",
        answerOptions = listOf("Jurong Industrial Estate","Changi Airport","Marina Bay Sands","Sentosa Island"),
        correctAnswer = "Jurong Industrial Estate")
)

val singaporeHistory3Questions = listOf(
    Question(question = "What year did World War II start?",
        answerOptions = listOf("1939","1941","1943","1945"),
        correctAnswer = "1939"),
    Question(question = "Which country invaded Singapore during World War II?",
        answerOptions = listOf("Japan","Germany","Italy","United States"),
        correctAnswer = "Japan"),
    Question(question = "What was the name of the British military force defending Singapore during World War II?",
        answerOptions = listOf("Indian Army","British Army","Australian Army","Singaporean Army"),
        correctAnswer = "Indian Army"),
    Question(question = "Who was the British commander during the Battle of Singapore?",
        answerOptions = listOf("Arthur Percival","Winston Churchill","Douglas MacArthur","George Marshall"),
        correctAnswer = "Arthur Percival"),
    Question(question = "Which defensive line was built to protect Singapore from a Japanese invasion?",
        answerOptions = listOf("Malayan Barrier","Fortress Singapore","Maginot Line","Siegfried Line"),
        correctAnswer = "Malayan Barrier"),
    Question(question = "What was the nickname given to the Japanese occupation of Singapore?",
        answerOptions = listOf("Syonan-to","Bushido","Manchukuo","Chrysanthemum Throne"),
        correctAnswer = "Syonan-to"),
    Question(question = "Which event marked the start of the Japanese invasion of Malaya and Singapore?",
        answerOptions = listOf("Attack on Pearl Harbor","Battle of Midway","Battle of Okinawa","Battle of Tarawa"),
        correctAnswer = "Attack on Pearl Harbor"),
    Question(question = "What was the code name for the Japanese invasion of Singapore?",
        answerOptions = listOf("Operation Ka","Operation Merlion","Operation Tiderace","Operation Matador"),
        correctAnswer = "Operation Ka"),
    Question(question = "Who was the Japanese military commander responsible for the invasion of Malaya and Singapore?",
        answerOptions = listOf("Tomoyuki Yamashita","Isoroku Yamamoto","Hiroshi Oshima","Hideki Tojo"),
        correctAnswer = "Tomoyuki Yamashita"),
    Question(question = "Where did the British surrender to the Japanese in Singapore?",
        answerOptions = listOf("Fort Canning","Former Ford Factory","Changi Beach","Bukit Timah"),
        correctAnswer = "Fort Canning")
)

val singaporeHistory4Questions = listOf(
    Question(question = "When did Singapore gain independence from Malaysia?",
        answerOptions = listOf("1965","1959","1963","1971"),
        correctAnswer = "1965"),
    Question(question = "Who was the Prime Minister of Singapore during the independence negotiations?",
        answerOptions = listOf("Lee Kuan Yew","Goh Chok Tong","Lee Hsien Loong","S. Rajaratnam"),
        correctAnswer = "Lee Kuan Yew"),
    Question(question = "What was the name of the agreement that led to Singapore's independence?",
        answerOptions = listOf("Separation Agreement 1965","Singapore Separation Accord","Independence Accord of 1965","Malaysia-Singapore Agreement"),
        correctAnswer = "Separation Agreement 1965"),
    Question(question = "Which country did Singapore merge with in 1963, leading to its brief union?",
        answerOptions = listOf("Malaysia","Indonesia","Thailand","Vietnam"),
        correctAnswer = "Malaysia"),
    Question(question = "Who represented Singapore during the negotiations for independence?",
        answerOptions = listOf("S. Rajaratnam","Lee Hsien Loong","Toh Chin Chye","Goh Keng Swee"),
        correctAnswer = "S. Rajaratnam"),
    Question(question = "What was the main reason for Singapore's separation from Malaysia?",
        answerOptions = listOf("Political disagreements","Cultural conflicts","Religious disputes","Economic differences"),
        correctAnswer = "Political disagreements"),
    Question(question = "What is the date of Singapore's Independence Day?",
        answerOptions = listOf("August 9, 1965","August 9, 1963","August 31, 1957","July 4, 1776"),
        correctAnswer = "August 9, 1965"),
    Question(question = "What was the primary language used during the negotiations for Singapore's independence?",
        answerOptions = listOf("English","Malay","Mandarin","Tamil"),
        correctAnswer = "English"),
    Question(question = "Which political party was in power in Singapore during the independence negotiations?",
        answerOptions = listOf("People's Action Party (PAP)","Democratic Progressive Party","Labour Front","UMNO"),
        correctAnswer = "People's Action Party (PAP)"),
    Question(question = "What was the reaction of the United Nations to Singapore's independence?",
        answerOptions = listOf("Did not intervene","Supported it immediately","Rejected it outright","Requested further negotiations"),
        correctAnswer = "Did not intervene")
)

val singaporeTechnologicalAdvancementsQuestions = listOf(
    Question(question = "Which advanced technology allows Singaporeans to teleport within the city in 2100?",
        answerOptions = listOf("Teleportation Pods","Sky Tunnels","Quantum Transporters","Mind Control Devices"),
        correctAnswer = "Teleportation Pods"),
    Question(question = "What is the name of the AI-controlled skyscraper that serves as Singapore's central command center in 2100?",
        answerOptions = listOf("SingaTower","SkyScape Central","Quantum Nexus","AuraDome"),
        correctAnswer = "SingaTower"),
    Question(question = "Which floating city serves as Singapore's offshore residential district in 2100?",
        answerOptions = listOf("AquaMetropolis","NeoAtlantis","Oceanic Haven","Floatopia"),
        correctAnswer = "AquaMetropolis"),
    Question(question = "What is the primary mode of transportation used for interplanetary travel from Singapore's spaceport in 2100?",
        answerOptions = listOf("Space Shuttles","Hyperloop Capsules","Warp Gates","Anti-Gravity Pods"),
        correctAnswer = "Space Shuttles"),
    Question(question = "Which artificial intelligence system manages Singapore's weather control system in 2100?",
        answerOptions = listOf("WeatherNet","ClimateMind","StormGuard","AtmoAI"),
        correctAnswer = "WeatherNet"),
    Question(question = "Which underwater research facility allows scientists to explore the depths of the ocean surrounding Singapore in 2100?",
        answerOptions = listOf("DeepSeaLab","AquaSphere","Neptune Station","Oceanic Nexus"),
        correctAnswer = "DeepSeaLab"),
    Question(question = "What is the name of the virtual reality platform that immerses users in historical simulations of Singapore's past in 2100?",
        answerOptions = listOf("HoloHistory","ChronoSim","SingaScape","VirtualPast"),
        correctAnswer = "HoloHistory"),
    Question(question = "Which bio-engineered organism helps maintain Singapore's ecosystem by filtering pollutants from the air and water in 2100?",
        answerOptions = listOf("EcoBots","PurifyPlants","BioCleaners","NatureNets"),
        correctAnswer = "EcoBots"),
    Question(question = "What is the energy source powering Singapore's floating solar farms in 2100?",
        answerOptions = listOf("Solar HelioCells","Fusion Generators","Quantum Reactors","Tidal Turbines"),
        correctAnswer = "Solar HelioCells"),
    Question(question = "Which advanced medical technology allows Singaporeans to extend their lifespan significantly in 2100?",
        answerOptions = listOf("RegenPulse Nanobots","YouthInfini Serum","EternalLife Implants","AgeZero Pills"),
        correctAnswer = "RegenPulse Nanobots")
)

val singlishQuestions = listOf(
    Question(question = "What does \"shiok\" mean in Singlish?",
        answerOptions = listOf("Delicious or satisfying","Sleep","Angry","Confused"),
        correctAnswer = "Delicious or satisfying"),
    Question(question = "What does \"kiasu\" refer to in Singlish?",
        answerOptions = listOf("Fear of losing out","Being brave","Eating quickly","Being generous"),
        correctAnswer = "Fear of losing out"),
    Question(question = "What does \"lepak\" mean in Singlish?",
        answerOptions = listOf("Relax or chill out","Work hard","Exercise vigorously","Go shopping"),
        correctAnswer = "Relax or chill out"),
    Question(question = "What does \"bo jio\" mean in Singlish?",
        answerOptions = listOf("Why didn't you invite me?","Have you eaten?","Let's go","Congratulations"),
        correctAnswer = "Why didn't you invite me?"),
    Question(question = "What does \"act blur\" mean in Singlish?",
        answerOptions = listOf("Pretend not to know","Act quickly","Play dumb","Wear glasses"),
        correctAnswer = "Pretend not to know"),
    Question(question = "What does \"steady lah\" mean in Singlish?",
        answerOptions = listOf("Very good or excellent","Unstable","Slow down","Don't worry"),
        correctAnswer = "Very good or excellent"),
    Question(question = "What does \"sabo\" mean in Singlish?",
        answerOptions = listOf("Sabotage or play a prank","A type of clothing","A type of dance","Savoury food"),
        correctAnswer = "Sabotage or play a prank"),
    Question(question = "What does \"blur like sotong\" mean in Singlish?",
        answerOptions = listOf("Confused","Very clear","Smart","Happy"),
        correctAnswer = "Confused"),
    Question(question = "What does \"pai seh\" mean in Singlish?",
        answerOptions = listOf("Embarrassed","Proud","Shy","Confident"),
        correctAnswer = "Embarrassed"),
    Question(question = "What does \"chiong\" mean in Singlish?",
        answerOptions = listOf("To rush or go all out","To sleep","To eat quickly","To relax"),
        correctAnswer = "To rush or go all out")
)

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
        questions = singaporeHistory1Questions),
    Topic(topicName = "Singapore History #2",
        description = "This quiz covers the British colonization portion of Singapore’s History!",
        questions = singaporeHistory2Questions),
    Topic(topicName = "Singapore History #3",
        description = "This quiz covers the World War II period of Singapore’s History!",
        questions = singaporeHistory3Questions),
    Topic(topicName = "Singapore History #4",
        description = "This quiz covers the independence of Singapore Agreement 1965!",
        questions = singaporeHistory4Questions),
    Topic(topicName = "Singapore Technological Advancements",
        description = "This quiz covers some of the technological advancements in our recent times",
        questions = singaporeTechnologicalAdvancementsQuestions),
    Topic(topicName = "Singlish",
        description = "Lar lor leh meh, do you know all the Singlish slangs? Find out in this quiz!",
        questions = singlishQuestions),
    Topic(topicName = "Singapore Laws",
        description = "Did you know about some laws that are unique to Singapore?",
        questions = listOf()),
    Topic(topicName = "Singapore Politics",
        description = "How much did you know about politics in Singapore? Guess we are about to find out now!",
        questions = listOf())
)

