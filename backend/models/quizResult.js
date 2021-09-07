const mongoose = require("mongoose");

const quizResultSchema = mongoose.Schema({
    user: {
        type: mongoose.Schema.ObjectId,
        ref: "User",
    },
    quiz: {
        type: mongoose.Schema.ObjectId,
        ref: "Quiz",
    },
    answers: [
        {
            type: String,
        },
    ],
});

const quizResult = mongoose.model("QuizResult", quizResultSchema);

module.exports = quizResult;
