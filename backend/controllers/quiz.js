const Quiz = require("../models/quiz");
const Question = require("../models/question");
const QuizResult = require("../models/quizResult");
const Match = require("../models/match");
const User = require("../models/user");
const mongoose = require("mongoose");

exports.getQuiz = async (req, res, next) => {
    try {
        const data = await Quiz.findOne({ category: req.params.category });
        if (data == null) throw "No Quiz";

        res.status(200).json(data);
    } catch (err) {
        console.log(err);
        res.status(500).json({
            status: "Error",
            message: "Error",
        });
    }
};

exports.addQuizResult = async (req, res, next) => {
    try {
        const { user, quiz, answer1, answer2, answer3, answer4, answer5 } = req.body;

        let data = {
            user,
            quiz,
            answers: [answer1, answer2, answer3, answer4, answer5],
        };
        console.log(data);

        // hanap
        const existing = await QuizResult.findOne({ user, quiz });
        let quizResult;

        // check if existing
        if (existing) {
            await QuizResult.findByIdAndUpdate(existing._id, {
                answers: [answer1, answer2, answer3, answer4, answer5],
            });
            quizResult = await QuizResult.findById(existing._id);
        } else {
            quizResult = await QuizResult.create(data);
        }

        res.status(201).json(quizResult);
    } catch (err) {
        console.log(err);
        res.status(500).json({
            status: "Error",
            message: "Error",
        });
    }
};

exports.getLeaderboards = async (req, res, next) => {
    try {
        /**
         * user muna: check if may result siya sa category?
         * check if yung mga matches niya have also taken the quiz in the same category
         * if there are matches na nakapag answer na:
         *  compare user and each match's answers and compute the percentage of each
         *  sort by highest and return top 5 only
         * else: prompt nlng sa android ng message if array size is 0
         *
         * if wala si user result sa category:
         *  prompt nlng rin sa android ng message if array size is 0
         *
         * return category and array of top 5 (u/n and %)
         */

        const { user, quiz } = req.params;

        // find quiz result from
        const myResult = await QuizResult.findOne({ user, quiz });

        // find matches
        const matchList = await Match.find({ sender: user });
        // console.log("getLeaderboard: Matchlist", matchList);

        const matchedId = matchList.map((item) => item.receiver);
        const pairs = await Match.find({ sender: { $in: matchedId }, receiver: user });
        const pairsId = pairs.map((item) => item.sender);
        const data = await User.find({ _id: { $in: [...pairsId] } });
        // console.log("getLeaderboard: data", data);

        // get result of matches
        let quizMatches = [];
        for (let match of data) {
            let user = match._id;
            let otherResult = await QuizResult.findOne({ user, quiz });

            // if not null, compute and push into array
            if (otherResult) {
                // for counting of similar answers
                let count = 0;

                // computing
                for (let i = 0; i < 5; i++) {
                    if (myResult.answers[i] === otherResult.answers[i]) count++;
                }

                let percent = (count / 5) * 100;
                let score = { user: otherResult.user.username, percent };

                quizMatches.push(score);
            }
        }

        console.log("getLeaderboard: quizMatches", quizMatches);

        res.status(200).json({
            status: "Success",
            quizMatches,
        });
    } catch (err) {
        console.log(err);
        res.status(500).json({
            status: "Error",
            message: "Error",
        });
    }
};
