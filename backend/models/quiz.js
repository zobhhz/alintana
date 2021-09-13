const mongoose = require("mongoose");

const quizSchema = mongoose.Schema({
    questions: [
        {
            question: {
                type: String,
            },
            choices: [
                {
                    type: String,
                },
            ],
        },
    ],
    category: {
        type: String,
    },
});

const Quiz = mongoose.model("Quiz", quizSchema);

module.exports = Quiz;
