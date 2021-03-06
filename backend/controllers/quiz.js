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
    const {
      user,
      category,
      answer1,
      answer2,
      answer3,
      answer4,
      answer5,
    } = req.body;

    let data = {
      user,
      category,
      answers: [answer1, answer2, answer3, answer4, answer5],
    };
    console.log(data);

    // hanap
    const existing = await QuizResult.findOne({ user, category });
    let quizResult;
    console.log(existing);

    // check if existing
    if (existing) {
      await QuizResult.findByIdAndUpdate(existing._id, {
        answers: [answer1, answer2, answer3, answer4, answer5],
      });
      quizResult = await QuizResult.findById(existing._id);
    } else {
      let myUser = await User.findById(user);
      quizResult = await QuizResult.create(data);
      quizResult.user = myUser
    }

    await User.findByIdAndUpdate(user, {
      $inc: { dailyGame: 1, allTimeGame: 1, experience: 40 },
    });

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
    const { user, category } = req.params;

    // find quiz result
    const myResult = await QuizResult.findOne({ user, category });

    // find matches
    const matchList = await Match.find({ sender: user });
    const matchedId = matchList.map((item) => item.receiver);
    const pairs = await Match.find({
      sender: { $in: matchedId },
      receiver: user,
    });
    const pairsId = pairs.map((item) => item.sender);
    const data = await User.find({ _id: { $in: [...pairsId] } });

    // get quiz result of matches
    let quizMatches = [];
    for (let match of data) {
      let user = match._id;
      let otherResult = await QuizResult.findOne({ user, category });

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

    // sorting in descending order
    quizMatches.sort((a, b) =>
      a.percent < b.percent
        ? 1
        : a.percent === b.percent
        ? a.user > b.user
          ? 1
          : -1
        : -1
    );

    // getting top 5
    quizMatches = quizMatches.slice(0, 5);

    console.log("getLeaderboard: quizMatches", quizMatches);
    console.log("getLeaderboard: quizMatches length", quizMatches.length);

    res.status(200).json({ status: "Success", quizMatches });
  } catch (err) {
    console.log(err);
    res.status(500).json({
      status: "Error",
      message: "Error",
    });
  }
};
