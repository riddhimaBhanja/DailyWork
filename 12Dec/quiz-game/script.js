// Quiz questions and answers
const quizData = [
    {
        question: "What is your name?",
        answers: [
            "Riddhima",
            "Richa",
            "Pariwesh sir",
            "Sonal"
        ],
        correct: 3
    },
    {
        question: "Which planet is known as the Red Planet?",
        answers: [
            "Venus",
            "Mars",
            "Jupiter",
            "Saturn"
        ],
        correct: 1
    },
    {
        question: "What is the largest ocean on Earth?",
        answers: [
            "Atlantic Ocean",
            "Indian Ocean",
            "Arctic Ocean",
            "Pacific Ocean"
        ],
        correct: 3
    },
    {
        question: "Who painted the Mona Lisa?",
        answers: [
            "Vincent van Gogh",
            "me",
            "Leonardo da Vinci",
            "Michelangelo"
        ],
        correct: 2
    },
    {
        question: "What is the smallest prime number?",
        answers: [
            "0",
            "1",
            "2",
            "3"
        ],
        correct: 2
    }
];

let currentQuestionIndex = 0;
let score = 0;
let selectedAnswer = null;

// DOM Elements
const frontPage = document.getElementById('frontPage');
const quizPage = document.getElementById('quizPage');
const resultsPage = document.getElementById('resultsPage');
const startBtn = document.getElementById('startBtn');
const nextBtn = document.getElementById('nextBtn');
const restartBtn = document.getElementById('restartBtn');
const questionText = document.getElementById('questionText');
const answersContainer = document.getElementById('answersContainer');
const currentQuestionSpan = document.getElementById('currentQuestion');
const progressFill = document.getElementById('progressFill');
const finalScoreSpan = document.getElementById('finalScore');

// Event Listeners
startBtn.addEventListener('click', startQuiz);
nextBtn.addEventListener('click', nextQuestion);
restartBtn.addEventListener('click', restartQuiz);

// Start Quiz
function startQuiz() {
    frontPage.classList.remove('active');
    quizPage.classList.add('active');
    currentQuestionIndex = 0;
    score = 0;
    showQuestion();
}

// Show Current Question
function showQuestion() {
    selectedAnswer = null;
    nextBtn.disabled = true;

    const question = quizData[currentQuestionIndex];
    questionText.textContent = question.question;
    currentQuestionSpan.textContent = currentQuestionIndex + 1;

    // Update progress bar
    const progress = ((currentQuestionIndex + 1) / quizData.length) * 100;
    progressFill.style.width = progress + '%';

    // Clear previous answers
    answersContainer.innerHTML = '';

    // Create answer buttons
    question.answers.forEach((answer, index) => {
        const btn = document.createElement('button');
        btn.classList.add('answer-btn');
        btn.textContent = answer;
        btn.addEventListener('click', () => selectAnswer(index, btn));
        answersContainer.appendChild(btn);
    });
}

// Select Answer
function selectAnswer(answerIndex, button) {
    // Remove previous selection
    const allAnswerBtns = document.querySelectorAll('.answer-btn');
    allAnswerBtns.forEach(btn => btn.classList.remove('selected'));

    // Mark new selection
    button.classList.add('selected');
    selectedAnswer = answerIndex;
    nextBtn.disabled = false;
}

// Next Question
function nextQuestion() {
    // Check if answer is correct
    if (selectedAnswer === quizData[currentQuestionIndex].correct) {
        score++;
    }

    currentQuestionIndex++;

    // Check if quiz is complete
    if (currentQuestionIndex < quizData.length) {
        showQuestion();
    } else {
        showResults();
    }
}

// Show Results
function showResults() {
    quizPage.classList.remove('active');
    resultsPage.classList.add('active');
    finalScoreSpan.textContent = score;
}

// Restart Quiz
function restartQuiz() {
    resultsPage.classList.remove('active');
    frontPage.classList.add('active');
    currentQuestionIndex = 0;
    score = 0;
    selectedAnswer = null;
}
