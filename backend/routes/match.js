const express = require("express");
const { getPossibleMatches, addMatch, getMatched } = require("../controllers/match");
const router = express.Router();

router.get("/possible/:id", getPossibleMatches);
router.post("/add", addMatch);
router.get("/:id", getMatched);

module.exports = router;
