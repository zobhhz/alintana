const express = require("express");
const { getQuiz, addQuizResult } = require("../controllers/quiz");
//TODO: Add from controller (require...)
const router = express.Router();

//TODO: Add from controller (router.get stuff)
router.route("/:id").get(getQuiz);
router.route("/").post(addQuizResult);
module.exports = router;
