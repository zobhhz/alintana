const express = require("express");
const { getQuiz, addQuizResult, getLeaderboards } = require("../controllers/quiz");
const router = express.Router();

router.route("/:category").get(getQuiz);
router.route("/").post(addQuizResult);
router.route("/getLeaderboard/:user/:category").get(getLeaderboards);
module.exports = router;
