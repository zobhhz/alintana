const mongoose = require("mongoose");

const quizSchema = mongoose.Schema({
    questions: [
        {
            type: mongoose.Schema.ObjectId,
            ref: "Question",
        },
    ],
    category: {
        type: String,
    },
});

quizSchema.pre(/^find/, function (next) {
    this.populate({
        path: "questions",
        model: "Question",
    });
    next();
});

const Quiz = mongoose.model("Quiz", quizSchema);

module.exports = Quiz;
