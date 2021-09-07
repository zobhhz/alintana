const express = require("express");
const { getQuiz } = require("../controllers/quiz");
//TODO: Add from controller (require...)
const router = express.Router();

//TODO: Add from controller (router.get stuff)
router.route("/:id").get(getQuiz);

module.exports = router;
