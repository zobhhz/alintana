const mongoose = require("mongoose");

const quizResultSchema = mongoose.Schema({
    user: {
        type: mongoose.Schema.ObjectId,
        ref: "User",
    },
    category: {
        type: String,
    },
    answers: [
        {
            type: String,
        },
    ],
});

quizResultSchema.pre(/^find/, function (next) {
    this.populate({
        path: "user",
        model: "User",
    });

    // this.populate({
    //     path: "quiz",
    //     model: "Quiz",
    // });
    next();
});

const quizResult = mongoose.model("QuizResult", quizResultSchema);

module.exports = quizResult;
