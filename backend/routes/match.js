const express = require("express");
const { getPossibleMatches, addMatch, getMatched, swipeLeft } = require("../controllers/match");
const router = express.Router();

router.get("/possible/:id", getPossibleMatches);
router.post("/add", addMatch);
router.post("/ignore", swipeLeft);
router.get("/:id", getMatched);

module.exports = router;
