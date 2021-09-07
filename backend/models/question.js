const mongoose = require("mongoose");

const questionSchema = mongoose.Schema({
    question: {
        type: String,
    },
    choices: [
        {
            type: String,
        },
    ],
});

/**
 *
 * quiz {
 *      id: 'grdy4533563',
 *      questions: [
 *              '354745733',
 *              '354745734',
 *              '354745735',
 *              '354745736',
 *              '354745737',
 *      ],
 *      category: "Video Games"
 * }
 *
 * quizResult {
 *          id: '0000000',
 *          userId: '2030405',
 *          quizId: '2033043'
 *          answers: ['red', 'rainbow six', 'rap', 'refrigerator', 'rope']
 *
 * }
 *
 * getLeaderboard: {
 *
 * }
 */

const Question = mongoose.model("Question", questionSchema);

module.exports = Question;
